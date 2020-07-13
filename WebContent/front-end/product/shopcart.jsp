<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/style.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/shopcart.css">
</head>
<body id="mybody">
		<c:if test="${empty shoppingcart}">
			<div class="container-fluid">
				<div class="row ml-3 mr-3 mt-2">
					<div class="col-12">
						<div class="jumbotron" id="myjumbotron">
						  <h1 class="display-4">您的購物車是空的!</h1>
						  <p class="lead">Your shopping cart is empty!!</p>
						  <hr class="my-4">
						  <p class="lead">
						    <a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp">返回購物</a>
						  </p>
						</div>
					</div>	
				</div>
			</div>
		</c:if>


		<c:if test="${not empty shoppingcart}">
		<div class="container">
			<div class="row ml-3 mr-3 mt-2" id="mybody">
				<div class="col-12">
					<a href="<%= request.getContextPath()%>/front-end/product/shopindex.jsp" class="navbar-brand ml-3">Cute:)<span style="color:#00E8E8;">Family-寵物商城</span></a>
				</div>
					<div class="col-12">
						<table class="table table-striped">
							<thead class="thead">
								<tr>
									<th>產品名稱</th>
									<th>產品單價</th>
									<th>產品圖片</th>
									<th>減少數量</th>
									<th>購買數量</th>
									<th>增加數量</th>
									<th>小計</th>
									<th>可購買量</th>
									<th>修改</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="proVO" items="${shoppingcart}">		
									<tr class="carttr table-Default">
										<th>${proVO.productname}</th>
										<th class="text-danger">${proVO.productprice}</th>
										<td><img src="<%=request.getContextPath()%>/product/getPic?proid=${proVO.productid}" alt=""></td>
										<td> 
											<form action="<%=request.getContextPath()%>/product/cart.do" method="POST">
											<input type="hidden" name="action" value="minuscartfromcart">
											<input type="hidden" name="productid" value="${proVO.productid}">
											<input type="hidden" name="productname" value="${proVO.productname}">
											<input type="hidden" name="productprice" value="${proVO.productprice}">
											<input class="btn btn-secondary plusandminus minus" value="-" type="submit">
											</form>
										</td>
										<td>								
											<label class="quantity">${proVO.quantity}</label>										
										</td>
										<td>
											<form action="<%=request.getContextPath()%>/product/cart.do" method="POST">
											<input type="hidden" name="action" value="addcartfromcart">
											<input type="hidden" name="productid" value="${proVO.productid}">
											<input type="hidden" name="productname" value="${proVO.productname}">
											<input type="hidden" name="productprice" value="${proVO.productprice}">
											<input class="btn btn-secondary plusandminus plus" value="+" type="submit">
											</form>			
										</td>
										<td class="price">${proVO.productprice*proVO.quantity}</td>	
										<td id="stock">足夠</td>			
										<td>
											<form action="<%=request.getContextPath()%>/product/cart.do" method="POST">
											<input type="hidden" name="action" value="delete">
											<input type="hidden" name="productid" value="${proVO.productid}">
											<input type="hidden" name="productname" value="${proVO.productname}">
											<input type="hidden" name="productprice" value="${proVO.productprice}">
											<input class="btn btn-secondary delete" value="刪除購物車" type="submit">
											</form> 
										</td>								
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<div class="col-12 d-flex justify-content-end mb-2">
						<h4 class="label label-default d-inline mr-2" id="total">總額:NT$${total}</h4>
						<a class="btn btn-info" href="<%=request.getContextPath()%>/front-end/product/check.jsp">結帳</a>
					</div>
				</div>
			</div>
		</c:if>
		
			<div class="toast" data-autohide="true" style="position:fixed; left: 10px; bottom: 20px; width:250px " data-delay="2000">
		    <div class="toast-header">
		      <svg class=" rounded mr-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
		                    <rect fill="#007aff" width="100%" height="100%" /></svg>
		      <strong class="mr-auto">訊息通知</strong>
		      <small class="text-muted">now</small>
		      <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                  </button>
		    </div>
		    <div class="toast-body" style="font-size:15px; color:red;">
		    </div>
		  </div>
		
		
		<c:if test="${not empty memNO}">
			<script>
				var MyPoint = "/socketformember/${memNO}";
				var host = window.location.host;
				var path = window.location.pathname;
				var webCtx = path.substring(0, path.indexOf('/', 1));
				var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
				var websocket = null;
				
				if("WebSocket" in window){
				websocket = new WebSocket(endPointURL);
				}
				websocket.onmessage = function(e){
					$(".toast-body").text(e.data);
					$('.toast').toast('show');
				}
			</script>
		</c:if>
</body>
</html>
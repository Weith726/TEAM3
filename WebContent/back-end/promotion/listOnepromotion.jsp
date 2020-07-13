<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%@include file="/back-end/backEndInclude/head.jsp" %>
</head>
<style>
table tr {
	border: solid black 1px;
	color:blue;
	line-height:40px;
	text-align:center;
	font-size:20px;
}
#myjumbotron{
	background-color:#A39EDA;
	}
.price{
	display:none;
}
</style>
<body>
<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService"></jsp:useBean>
		<%@include file="/back-end/backEndInclude/header.jsp" %>
	<c:if test="${ empty listpromoDetail}">
		<div class="container-fluid">
				<div class="row ml-3 mr-3 mt-2">
					<div class="col-12">
						<div class="jumbotron" id="myjumbotron">
						  <h1 class="display-4">查看優惠!</h1>
						  <p class="lead">Your shopping cart is empty!!</p>
						  <hr class="my-4">
						  <p class="lead">
						    <a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/back-end/promotion/listAllpromotion.jsp">前往查看優惠!</a>
						  </p>
						</div>
					</div>	
				</div>
			</div>
	</c:if>
	<c:if test="${not empty listpromoDetail}">
		<div class="container">
		<div class="row ml-3 mr-3 mt-2">
			<div class="col-12">
				<h2 class="w-100">優惠方案</h2>
			</div>		
			<div class="col-12">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th>優惠方案編號</th>
							<th>商品名稱</th>
							<th>優惠價格</th>
							<th>修改</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="promoDetailVO" items="${listpromoDetail}">
						<tr ${(promoDetailVO.productid==param.productid)? 'bgcolor=#C2E5F9':'' }>
								<th>${promoDetailVO.promotionno}</th>
								<c:forEach var="proVO" items="${prosvc.all}">
									<c:if test="${promoDetailVO.productid==proVO.productid}">
										<td>${proVO.productname}</td>
									</c:if>
								</c:forEach>
								<td>
									NT$${promoDetailVO.promotionprice}
								</td>										
								<td>
									<form method="POST" action="<%=request.getContextPath()%>/ProModetail/promodetail.do" class="myform">
									<input class="price " type="text" size="4" name="promotionprice" placeholder="修改價格">
									<button type="button" class="btn btn-info mr-1">修改</button>
									<input disabled type="submit" value="送出" class="btn btn-danger send">
									<input type="hidden" name="action" value="updatepromoDetail">
									<input type="hidden" name="productid" value="${promoDetailVO.productid}">
									<input type="hidden" name="promotionno" value="${promoDetailVO.promotionno}">
									<input type="hidden" name="requestURI" value="<%=request.getServletPath() %>">
									</form>							
								</td>					
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/back-end/promotion/listAllpromotion.jsp">前往查看優惠!</a>
			</div>
		</div>
	</div>
	</c:if>
	<script>
		$(function(){
			$(".btn").click(function(){
				$(this).siblings(".price").toggle();
				$(this).siblings(".send").removeAttr("disabled");
			});
			$(".send").click(function(e){
				e.preventDefault();
				var check = true;
				if($(this).siblings(".price").val().trim()===""){
					check = false;
				}
				if(!check){
					alert("no empty");
					$(this).attr("disabled",true);
				}else{
					$(this).closest(".myform").submit();
				}
			});
		});
	</script>
	<%@include file="/back-end/backEndInclude/footer.jsp" %>
</body>
</html>
<%@page import="com.promotionDetail.model.PromoDetailService"%>
<%@page import="com.productorder.model.PorderVO"%>
<%@page import="com.productorder.model.PorderService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/myorder.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/shopindex.css">
</head>
<body id="mybody">
	<%
		String memno = (String) session.getAttribute("memNO");
		PorderService svc = new PorderService();
		List<PorderVO> list = svc.getbymemno(memno);
		request.setAttribute("orderlist", list);
	%>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">取消訂單</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form action="<%=request.getContextPath()%>/order/porder.do" method="POST" >
	          <div class="form-group">
	            <label for="message-text" class="col-form-label">取消原因</label>
	            <textarea class="form-control" id="message-text" placeholder="請輸入原因" name="textarea"></textarea>
	          </div>
	          <input disabled type="submit" value="送出" class="btn btn-primary w-100" id="cancelbtn">
	          <input type="hidden" value="cancelorder" name="action">
	          <input type="hidden" name="orderid" id="modalorderid" value="">
	          <input type="hidden" name="url"  value="<%=request.getServletPath()%>">
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<c:if test="${empty orderlist}">
		<div class="container-fluid">
				<div class="row ml-3 mr-3 mt-2">
					<div class="col-12">
						<div class="jumbotron" id="myjumbotron">
						  <h1 class="display-4">您沒有任何訂單!!</h1>
						  <hr class="my-4">
						  <p class="lead">
						    <a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp">返回購物</a>
						  </p>
						</div>
					</div>	
				</div>
			</div>
	</c:if>
	<c:if test="${not empty orderlist}">
	  <header>
    <nav class="navbar navbar-expand-lg navbar-light ">
      <a href="#" class="navbar-brand ml-3">Cute:)<span style="color:#7CE3A6;">Family</span></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu"
      aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse"></div>
    <div class="collapse navbar-collapse" id="navbarMenu">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a href="index.html" class="nav-link">首頁</a>
        </li>
                    <!-- <li class="nav-item dropdown">
                        <a href="#" class="nav-link">會員專區</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">門診專區</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">寵物旅館</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">寵物商城</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">領養專區</a>
                      </li> -->
                      <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        會員專區
                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">會員登入</a>
                        <a class="dropdown-item" href="#">編輯會員資料</a>
                        <a class="dropdown-item" href="#">管理您的寵物</a>
                        <a class="dropdown-item" href="#">瀏覽最新通知</a>
                      </div>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                      aria-haspopup="true" aria-expanded="false">
                      門診專區
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="#">門診預約</a>
                      <a class="dropdown-item" href="#">門診查詢</a>
                      <a class="dropdown-item" href="#">瀏覽看診進度</a>
                    </div>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    寵物旅館
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">瀏覽房型</a>
                    <a class="dropdown-item" href="#">旅館預約</a>
                    <a class="dropdown-item" href="#">旅館預約查詢</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  寵物商城
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                  <a class="dropdown-item" href="#">購物車</a>
                  <a class="dropdown-item" href="#">搜尋商品資訊</a>
                  <a class="dropdown-item" href="#">瀏覽商品資訊</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                領養專區
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">互動預約</a>
                <a class="dropdown-item" href="#">互動預約查詢</a>
                <a class="dropdown-item" href="#">瀏覽流浪動物</a>
                <a class="dropdown-item" href="#">領養流浪動物</a>
              </div>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0">
            <button class="btn menu-right-btn border" type="submit">
              登入
            </button>
          </form>
                <!-- <button class="btn menu-right-btn border" type="submit">
                    註冊
                  </button> -->
                </div>
              </nav>
            </header>
			
	<div class="container mb-0">
		<div class="row ml-3 mr-3 mt-2" id="mybody">
			<div class="col-12 mt-1">
				   <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-end mynavbar">
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp">商城首頁</a> 
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/dogindex.jsp">狗狗專區</a>
                  <a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/catindex.jsp">貓咪專區</a>
                  <a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/anotherindex.jsp">其他專區</a>
                  <a class="p-2  ml-2 dropdown" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">個人資料</a>
                  <a class="p-2  ml-2 text-danger" href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp">購物車</a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/product/myorder.jsp">查看歷史訂單</a>
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp">我的購物車</a>
                    <a class="dropdown-item" href="#">瀏覽最新通知</a>
                  </div>
                </nav>
              </div>
				<c:forEach var="orderVO" items="${orderlist}">
					<table class="table">
						<thead class="thead">
							<tr class="ordertr">
								<th colspan="5">我的訂單</th>
							</tr>
						</thead>
						<tbody>
							<tr class="carttr table-Default">
								<th>${orderVO.orderid}</th>
								<th>訂單日期:${orderVO.orderdate}</th>
								<th>訂單總額:NT$${orderVO.ordertotal}</th>
								<th>訂單狀態:${(orderVO.orderstatus==0)?'未出貨':(orderVO.orderstatus==1)?'已出貨':(orderVO.orderstatus==2)?'已完成':(orderVO.orderstatus==3)?'已取消':(orderVO.orderstatus==4)?'審核中':''}</th>
								<th>
									<button class="btn btn-info showmodal" value="${orderVO.orderstatus}">取消訂單</button>
									<input type="hidden" value="${orderVO.orderid}">
								</th>
							</tr>
						</tbody>
					</table>
					<div class="container mt-0 p-0">
						<div class="row mt-0">
							<div class="col-8 mb-3">
								<button class="btn btn-info border-radius getdetail">查看明細</button>
								<table class="table detail mt-2">
									<thead class="thead">
										<tr class="detailtr">
											<th>商品名稱</th>
											<th>商品圖片</th>
											<th>購買數量</th>
											<th>商品單價</th>
										</tr>
									</thead>
									<tbody>	
										<jsp:useBean id="detailsvc" scope="page" class="com.porderDetail.model.PorderDetailService"></jsp:useBean> 
										<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService"></jsp:useBean> 
										<c:forEach var="detailVO" items="${detailsvc.getdetailbyid(orderVO.orderid)}">
											<tr class="carttr table-Default">
												<th>${prosvc.getOneproduct(detailVO.proid).productname}</th>
												<th><img class="pimg" src="<%=request.getContextPath()%>/product/getPic?proid=${detailVO.proid}"></th>
												<th>${detailVO.quantity}</th>
												<th>${detailVO.unitprice}</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:forEach>
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
	<script>
		$(function(){
				$(".showmodal").click(function(){
					$("#modalorderid").val($(this).siblings("input").val());
					$("#message-text").val("");
					$("#myModal").modal({show: true});
					$("#cancelbtn").attr("disabled",true);
					$("#message-text").change(function() {
					 if($("#message-text").val().trim()!=""){
						$("#cancelbtn").removeAttr("disabled");
					}
				});		
			});
			$(".getdetail").click(function(){
				$(this).siblings(".detail").toggle("slow");
			});
		});
	</script>
</body>
</html>
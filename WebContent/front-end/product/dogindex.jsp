<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>萌寵家族</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/style.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/dogindex.css">
<!-- bootstrap、FontAwesome、googleFont -->
<style>
.myjumbotron {
	background-image: url("<%=request.getContextPath()%>/front-end/product/img/6.jpg");
	background-size: 100% 100%;
	background-repeat: no-repeat;
	height: 450px;
}
</style>
</head>

<body id="mybody">
	                                                                
	<!-- 	*******************************搜尋錯誤 -->
	<a href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp"><img alt="" src="<%=request.getContextPath()%>/front-end/product/img/cart.png" id="cart"></a>
<%
	String memNO = (String) session.getAttribute("memNO");
	String memName = (String) session.getAttribute("memName");
%>


	<header>
		<nav class="navbar navbar-expand-lg navbar-light ">
			<a href="<%=request.getContextPath()%>/front-end/frontEndIndex/index.jsp" class="navbar-brand ml-3">
				Cute:)
				<span style="color: #00E8E8;">Family</span>
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu" aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
	
			<div class="collapse navbar-collapse"></div>
			<div class="collapse navbar-collapse" id="navbarMenu">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a href="index.html" class="nav-link">首頁</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 會員專區 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">會員登入</a>
							<form action="<%=request.getContextPath()%>/Puppy/mem.do" METHOD="post" style="margin-bottom: 0px;">
								<input type="submit" value="編輯會員資料" class="dropdown-item">
								<input type="hidden" name="memNO" value="${memNO}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
							<a class="dropdown-item" href="#">管理您的寵物</a>
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
								<input type="submit" value="瀏覽最新通知" class="dropdown-item">
								<input type="hidden" name="mli" value="${memNO}">
								<input type="hidden" name="action" value="getAll_For_Display">
							</form>
						</div>
					</li>
					<li class="nav-item dropdown">
						<a href="#" class="nav-link">門診專區</a>
					</li>
					<li class="nav-item dropdown">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/myIndex.jsp" class="nav-link">寵物旅館</a>
					</li>
					<li class="nav-item dropdown">
						<a href="#" class="nav-link">寵物商城</a>
					</li>
					<li class="nav-item dropdown">
						<a href="#" class="nav-link">領養專區</a>
					</li>
				</ul>
				<div style="<%=(memNO == null) ? "visibility:hidden" : "visibility:"%>">
					<img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memNO}" style="height: 50px" id="mempic">
					<%=memName%>您好~
				</div>
				<a href="#">
					<button class="btn menu-right-btn border" type="button" style="<%=(memNO == null) ? "display:" : "display:none"%>">註冊</button>
				</a>
				<a href="#">
					<button class="btn menu-right-btn border" type="submit" id="login" style="<%=(memNO == null) ? "display:" : "display:none"%>">登入</button>
				</a>
				<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/Puppy/logout.do">
					<button class="btn menu-right-btn border" type="submit" id="logout" style="<%=(memNO != null) ? "display:" : "display:none"%>">登出</button>
				</form>
			</div>
		</nav>
	</header>

	<div class="container">
		<div class="nav-scroller py-1 mb-2">
			<nav class="nav d-flex justify-content-end mynavbar">
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp">商城首頁</a> 
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/dogindex.jsp">狗狗專區</a>
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/catindex.jsp">貓咪專區</a> 
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/anotherindex.jsp">其他專區</a>
				<a class="p-2  ml-2 dropdown" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">個人資料</a>
					<a class="p-2  ml-2 text-danger" href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp">購物車</a>
				<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				    <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/product/myorder.jsp">查看歷史訂單</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp">我的購物車</a> 
					<a class="dropdown-item" href="#">瀏覽最新通知</a>
				</div>
			</nav>
		</div>
		<div class="jumbotron p-4 p-md-5 text-white rounded bg-dark myjumbotron">
			<div class="col-md-6 px-0">
				<h1 class="display-4 font-italic text-dark">歡迎來到狗狗專區</h1>
				<p class="lead my-3 text-secondary font-weight-bold">Welcome to
					Cute Family</p>
				<p class="lead my-3 text-secondary font-weight-bold">Go Check it
					Out~</p>
			</div>
		</div>

				<input id="productname" name="productname" class="" type="text" placeholder="搜尋商品" aria-label="Search"> 
				<input name="action" value="selectdog" type="hidden"></input> 
				<button class="btn btn-outline-secondary my-2 my-sm-0" id="btn">搜尋商品</button>
					
		<div class="row mb-2" >
			<!--  ********************************************狗狗********************************************* -->
			<div class="col-12 mt-3">
				<h2 id="selectnum"></h2>
			</div>
			<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService" />
			<jsp:useBean id="promoDetailSVC" scope="page" class="com.promotionDetail.model.PromoDetailService"/>
				<c:forEach var="provo" items="${prosvc.dog}">
						<div class="col-md-3 mb-3 mt-5 pro" id="${provo.productid}">
							<div class="card-group">
								<div class="card ">
									<img class="card-img-top mt-4 ml-auto"
										src="<%=request.getContextPath()%>/product/getPic?proid=${provo.productid}"
										alt="Card image cap">
									<div class="card-body">
										<h5 class="card-title">${provo.productname}</h5>
									</div>
									<div class="ml-4">
										<p class="card-text">${provo.productintro}</p>
									</div>
									<div class="ml-4">
										<p class="card-text">
											<small class="text-muted">${provo.producton}</small>
										</p>
									</div>
									<div class="ml-4">
										<h5 id="NT${provo.productid}" class="card-text mt-1">NT$${provo.productprice}</h5>
									</div>
									<div
										class="card-body d-flex align-items-end justify-content-between">
										<input class="productid" name="productid"      value="${provo.productid}" type="hidden"> 
										<input class="productname" name="productname"  value="${provo.productname}" type="hidden"> 
										<input id="price${provo.productid}" class="productprice" name="productprice" value="${provo.productprice}"type="hidden"> 	
										<button class="d-inline-block btn btn-secondary addcart">加入購物車</button>
										<label id="label${provo.productid}"></label>
									</div>
								</div>
							</div>
						</div>
				</c:forEach>
				<script>
                	   <c:forEach var="pmvo" items="${promoDetailSVC.promotionprice}">
                  			$("#NT${pmvo.productid}").text("NT$${pmvo.promotionprice}");
                  			$("#NT${pmvo.productid}").addClass("PMprice");
                  			$("#price${pmvo.productid}").val("${pmvo.promotionprice}");
                  			$("#label${pmvo.productid}").text("優惠中");
                       </c:forEach> 
                </script>         
			</div>
		</div>
		<footer>
			<div class="section-5 text-center">
				<h4 style="margin-top: 5%;">最完善的寵物平台</h4>
				<h4 class="my-4">如果您有需要 請聯絡我們</h4>
				<div class="form-inline justify-content-center">
					<input type="text" name="Email" id="email" placeholder="Email" size="40" class="form-control px-4 py-2">
					<input type="button" value="Contact US" class="btn btn-danger px-4 py-2 ml-1">
				</div>
				<a href="">
					<button class="btn btn-outline-secondary" style="margin-top: 2%;">
						<h5>我要客訴</h5>
					</button>
				</a>
				<div class="social" style="margin: 3%;">
					<div class="d-flex flex-row justify-content-center">
						<i class="fab fa-facebook-f m-2"></i>
						<i class="fab fa-twitter m-2"></i>
						<i class="fab fa-instagram m-2"></i>
						<i class="fab fa-youtube m-2"></i>
					</div>
				</div>
				<hr>
				<h5 style="color: lightseagreen;">Cute Family &copy;</h5>
			</div>
		</footer>
		
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
			$(document).ready(function(){
				$("#btn").click(function(){
					$.ajax({
						url:"<%=request.getContextPath()%>/product/product.do",
						type:"POST",
						data:{action:"selectdog", productname: $("#productname").val() },
						dataType:"json",
						success:function(data){
							if(data.length>0){
								   $(".pro").addClass("proselect");
								   $(".selectpage").addClass("proselect");
							       $(data).each(function (index, item) {    	 
						               let productid = item.productid;
						              	$(`#`+productid+``).removeClass("proselect");
						              	$("#selectnum").text("符合您的搜尋共有"+data.length+"筆資料");
						          });	
							}
							else{
								swal("查無資料!");
								 $(".pro").removeClass("proselect");
								 $(".selectpage").removeClass("proselect");
								 $("#selectnum").text(" ");
							}
						}
					});
				});
				$(".addcart").click(function(){
					$.ajax({
						url:"<%=request.getContextPath()%>/product/cart.do",
						type:"POST",
						data:{
							action:"addcart", 
							productid: $(this).siblings(".productid").val(),
							productname: $(this).siblings(".productname").val(),
							productprice: $(this).siblings(".productprice").val(),
						},
						success:function(data){
							swal("加入購物車");
						}
					});
				});
			});
		</script>
</body>
</html>
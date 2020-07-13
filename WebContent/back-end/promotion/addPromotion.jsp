<%@page import="com.promotion.model.PromotionVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title></title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%@include file="/back-end/backEndInclude/head.jsp" %>
<style type="text/css" media="screen">
img
{
	max-width:220px;
	height:190px;
	margin:auto auto;
}
.cardborder
{
	border:2px solid black;
}
.cardnone{
	display:none;
}
.error{
	color:red;
	font-size:20px;
}
.label{
	color:red;
	font-size:15px;
}
.none{
	display:none;
}
</style>
</head>
<body>
		<%@include file="/back-end/backEndInclude/header.jsp" %>
<c:if test="${not empty success}">
	<script>
		swal("${success}");
	</script>
</c:if>
<form id="myform" method="POST" action="<%=request.getContextPath()%>/promotion/promotion.do">
	<div class="container mycontainer">
		<div class="row">
			<div class="col-12 mt-2">
				<h4><a href="<%= request.getContextPath()%>/back-end/promotion/listAllpromotion.jsp" class="btn btn-success">查詢優惠方案</a></h4>
			</div>
			<div class="col-12 mt-2">
				<table class="table">
					<thead class="text-center thead-dark">
						<tr>
							<th scope="col">方案名稱</th>
							<th scope="col">開始日期</th>
							<th scope="col">結束日期</th>
						</tr>
					</thead>
					<tbody class="text-center">
						<tr>
							<th><input type="text" name="promotionname" value="" id="promotionname"></th>
							<td><input type="date" name="startday" value="" id="startday"></td>
							<td><input type="date" name="endday" value="" id="endday"></td>
						</tr>
						<tr>
							<td><button id="btndog" class="btn btn-secondary w-100">狗狗產品</button></td>
							<td><button id="btncat"  class="btn btn-secondary w-100">貓咪產品</button></td>
							<td><button id="btnanother"  class="btn btn-secondary w-100">其他產品</button></td>
						</tr>
					</tbody>
				</table>
			</div>
			<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService"></jsp:useBean>
				<c:forEach var="proVO" items="${prosvc.dog}">
					<div class="col-3 dog" id="${proVO.productid}">
						<div class="card-group cardg">
							<label>
								<div class="card" id="bg${proVO.productid}">
									<img src="<%=request.getContextPath()%>/product/getPic?proid=${proVO.productid}" class="card-img-top ml-3" alt="...">
									<div class="card-body">
										<h5 class="card-title d-inline-block">${proVO.productname}</h5>
										<input type="checkbox" class="check" name="productid" value="${proVO.productid}"/><label class="ml-3 label" id="l${proVO.productid}"></label>
										<input disabled type="text" value="${proVO.productprice}" placeholder="輸入優惠價格" class="mt-1 form-control"/>
										<input disabled onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="promotionprice" placeholder="輸入優惠價格" class="mt-1 form-control promotionprice"/>
									</div>
								</div>
							</label>
						</div>
					</div>
				</c:forEach>
				<c:forEach var="proVO" items="${prosvc.cat}">
					<div class="col-3 cat" id="${proVO.productid}">
						<div class="card-group cardg">
							<label>
								<div class="card">
									<img src="<%=request.getContextPath()%>/product/getPic?proid=${proVO.productid}" class="card-img-top ml-3" alt="...">
									<div class="card-body">
										<h5 class="card-title d-inline-block">${proVO.productname}</h5>
										<input type="checkbox" class="check" name="productid" value="${proVO.productid}"><label class="ml-3 label" id="l${proVO.productid}"></label>
										<input disabled type="text" value="${proVO.productprice}" placeholder="輸入優惠價格" class="mt-1 form-control">
										<input disabled onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="promotionprice" placeholder="輸入優惠價格" class="mt-1 form-control promotionprice">
									</div>
								</div>
							</label>
						</div>
					</div>
				</c:forEach>
				<c:forEach var="proVO" items="${prosvc.another}">
					<div class="col-3 another" id="${proVO.productid}">
						<div class="card-group cardg">
							<label>
								<div class="card">
									<img src="<%=request.getContextPath()%>/product/getPic?proid=${proVO.productid}" class="card-img-top" alt="...">
									<div class="card-body">
										<h5 class="card-title d-inline-block">${proVO.productname}</h5>
										<input type="checkbox" class="check" name="productid" value="${proVO.productid}"><label class="ml-3 label" id="l${proVO.productid}"></label>
										<input disabled type="text" value="${proVO.productprice}" class="mt-1 form-control">
										<input disabled onkeyup="value=value.replace(/[^\d]/g,'')" type="text" name="promotionprice" placeholder="輸入優惠價格" class="mt-1 form-control promotionprice">
									</div>
								</div>
							</label>
						</div>
					</div>
				</c:forEach>
			<div class="col-12 mb-3">
				<input class="btn btn-info w-100" id="submitX" value="送出" type="submit">
				<input  type="hidden" name="action" value="addpromotion">
			</div>
			<jsp:useBean id="pmsvc" scope="page" class="com.promotionDetail.model.PromoDetailService"></jsp:useBean>
			<script>
				<c:forEach var="pmvo" items="${pmsvc.promotionprice}">
					$("#${pmvo.productid}").addClass("none");
				</c:forEach>		
			</script>
		</div>
	</div>
</form>
<%@include file="/back-end/backEndInclude/footer.jsp" %>
<script>
	$(document).ready(function(){
		$(".cat").addClass("cardnone");
		$(".another").addClass("cardnone");
		$("#btndog").attr("disabled",true);
		$('.check').change(function(){
			if($(this).prop('checked')){
				$("#submit").removeAttr("disabled");
				$(this).closest(".card").addClass("cardborder");
				$(this).addClass("checked");
				$(this).siblings(".promotionprice").removeAttr("disabled");
			}else{
				$(this).closest(".card").removeClass("cardborder");
				$(this).removeClass("checked");
				$(this).siblings(".promotionprice").attr("disabled",true);
			}
		});
		$("#btndog").click(function(){
			$(".dog").removeClass("cardnone");
			$(".cat").addClass("cardnone");
			$(".another").addClass("cardnone");
			$(this).attr("disabled",true);
			$(".cat").addClass("cardnone");
			$(".another").addClass("cardnone");
			$("#btncat").removeAttr("disabled");
			$("#btnanother").removeAttr("disabled");
		});
		$("#btncat").click(function(){
			$(".dog").addClass("cardnone");
			$(".cat").removeClass("cardnone");
			$(".another").addClass("cardnone");
			$(this).attr("disabled",true);
			$(".dog").addClass("cardnone");
			$(".another").addClass("cardnone");
			$("#btndog").removeAttr("disabled");
			$("#btnanother").removeAttr("disabled");
		});
		$("#btnanother").click(function(){
			$(".dog").addClass("cardnone");
			$(".cat").removeClass("cardnone");
			$(".another").removeClass("cardnone");
			$(this).attr("disabled",true);
			$(".cat").addClass("cardnone");
			$(".dog").addClass("cardnone");
			$("#btndog").removeAttr("disabled");
			$("#btncat").removeAttr("disabled");
		});
		
		$('#submitX').click(function(e) {
			e.preventDefault();
			var check = true;
			var arr = $(".checked").siblings(".promotionprice");
			for(var i = 0; i < arr.length; i++) {
				if($(arr[i]).val().trim() === "") {
					check = false;
				}
			}
		  	if(!check){
		  		swal("數字請勿空白");
		  	}else if($("#promotionname").val().trim()===""){
		  		swal("優惠名稱請勿空白");
		  	}else if($("#startday").val().trim()===""){
		  		swal("開始日期請勿空白");
		  	}else if($("#endday").val().trim()===""){
		  		swal("結束日期請勿空白");
		  	}else{
		  		$('#myform').submit();
		  	}
		})
	});
</script>
</body>
</html>
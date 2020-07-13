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
	color: black;
	line-height:40px;
	text-align:center;
}
#myjumbotron{
	background-color:#80D6D6;
}
</style>
<jsp:useBean id="promotionSVC" scope="page" class="com.promotion.model.PromotionService"></jsp:useBean>
<body>
		<%@include file="/back-end/backEndInclude/header.jsp" %>
	<div class="container">
		<div class="row ml-3 mr-3 mt-4">
			<c:if test="${empty promotionSVC.all}">
				<div class="col-12 -2">
					<div class="jumbotron" id="myjumbotron">
					  <h1 class="display-4">目前尚無優惠!</h1>
					  <p class="lead">There is currently no product with a discount!</p>
					  <hr class="my-4">
					  <p class="lead">
					    <a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/back-end/promotion/addPromotion.jsp">新增優惠</a>
					  </p>
					</div>
				</div>
			</c:if>
				<c:if test="${not empty promotionSVC.all}">	
					<div class="col-12">
						<a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/back-end/promotion/addPromotion.jsp">新增優惠</a>
					</div>
					<div class="col-12 mt-2">
						<table class="table table-striped">
							<thead class="thead-dark">
								<tr>
									<th>優惠方案編號</th>
									<th>優惠方案名稱</th>
									<th>優惠方案開始日期</th>
									<th>優惠方案結束日期</th>
									<th>查詢明細</th>
								</tr>
							</thead>
						<tbody>
						 <c:forEach var="promoVO" items="${promotionSVC.all}">
						 	<form method="post" action="<%=request.getContextPath()%>/promotion/promotion.do">
								<tr>
									<th>${promoVO.promotionno}</th>
									<td>${promoVO.promotionname}</td>
									<td>${promoVO.startday}</td>
									<td>${promoVO.endday}</td>							
									<td><input class="btn btn-info" value="查詢" type="submit"></td>
									<input type="hidden" name="promotionno" value="${promoVO.promotionno}">							
									<input type="hidden" name="action" value="getonepromotiondetail">							
								</tr>
							</form>
						 </c:forEach>
					  </c:if>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@include file="/back-end/backEndInclude/footer.jsp" %>
</body>
</html>
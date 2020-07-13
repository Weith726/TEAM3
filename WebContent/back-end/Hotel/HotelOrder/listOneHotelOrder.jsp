<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.HotelOrder.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
	HotelOrderVO hotelOrderVO = (HotelOrderVO) request.getAttribute("hotelOrderVO");
%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
#section-1 {
	position: relative;
	top: -10px;
}

.images {
	width: 270px;
	height: 180px;
}

#table {
	margin-top: 1%;
}
#table td, #table th {
    padding: .65rem; 
    vertical-align: middle;
    border-top: 1px solid #dee2e6;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid" id="section-1">
		<div class="row">
			<div class="col-12">
				<h1 class="topTitle text-center">
					<span class="badge badge-warning">旅館訂單管理(單筆資料)</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp" role="button">回首頁</a>
					</div>
				</h1>
			</div>
		</div>


		<div class="row">
			<div class="col-12">
				<table class="table table-striped text-center" id="table">
					<thead>
						<tr>
							<th scope="col">訂單編號</th>
							<th scope="col">會員編號</th>
							<th scope="col">寵物編號</th>
							<th scope="col">房型編號</th>
							<th scope="col">房間編號</th>
							<th scope="col">房型價格</th>
							<th scope="col">訂單時間</th>
							<th scope="col">入住時間</th>
							<th scope="col">退房時間</th>
							<th scope="col">訂單總價</th>
							<th scope="col">訂單狀態</th>
							<th scope="col">訂單修改</th>
							<th scope="col">訂單驗證</th>
							<th scope="col">訂單取消</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<td>${hotelOrderVO.orderNo}</td>
							<td>${hotelOrderVO.memNo}</td>
							<td>${hotelOrderVO.petNo}</td>
							<td>${hotelOrderVO.roomTypeNo}</td>
							<td>${hotelOrderVO.roomNo}</td>
							<td>${hotelOrderVO.roomTypePrice}</td>
							<td>
								<fmt:formatDate value="${hotelOrderVO.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<fmt:formatDate value="${hotelOrderVO.checkInDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>
								<fmt:formatDate value="${hotelOrderVO.checkOutDate}" pattern="yyyy-MM-dd HH:mm:ss" />
							</td>
							<td>${hotelOrderVO.totalPrice}</td>
							<td>
								<c:if test="${hotelOrderVO.hotelOrderStatus == 0}">
									<c:out value="待確認"></c:out>
								</c:if>
								<c:if test="${hotelOrderVO.hotelOrderStatus == 1}">
									<c:out value="已完成"></c:out>
								</c:if>
								<c:if test="${hotelOrderVO.hotelOrderStatus == 2}">
									<c:out value="已取消"></c:out>
								</c:if>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" style="margin-bottom: 0px;">
									<button type="submit" class="btn btn-primary">修改</button>
									<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" style="margin-bottom: 0px;">
									<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
									<input type="hidden" name="hotelOrderStatus" value="1">
									<input type="hidden" name="action" value="confirm">
									<button type="submit" class="btn btn-info">驗證</button>
								</FORM>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" style="margin-bottom: 0px;">
									<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
									<input type="hidden" name="action" value="cancel">
									<button type="submit" class="btn btn-secondary">取消</button>
								</FORM>
							</td>
						</tr>

					</tbody>

				</table>
			</div>
		</div>
	</div>

	<c:if test="${not empty updateOk}">
		<script>
			swal("更新成功", "", "success");
		</script>
	</c:if>


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>

	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
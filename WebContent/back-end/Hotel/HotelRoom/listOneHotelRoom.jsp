<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.HotelRoom.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	HotelRoomVO hotelRoomVO = (HotelRoomVO) request.getAttribute("hotelRoomVO");
%>

<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
#section-1 {
	position: relative;
	top: -10px;
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
					<span class="badge badge-warning">- 房間管理 -(單筆資料)</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp" role="button">回首頁</a>
					</div>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th scope="col">房間編號</th>
							<th scope="col">房型編號</th>
							<th scope="col">寵物編號</th>
							<th scope="col">房間狀態</th>
							<th scope="col">房間修改</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${hotelRoomVO.roomNo}</td>
							<td>${hotelRoomVO.roomTypeNo}</td>
							<td>${hotelRoomVO.petNo}</td>
							<td>
								<c:if test="${hotelRoomVO.roomStatus == 0}">
									<c:out value="已存在"></c:out>
								</c:if>
								<c:if test="${hotelRoomVO.roomStatus == 1}">
									<c:out value="已移除"></c:out>
								</c:if>
							</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelRoom/HotelRoom.do" style="margin-bottom: 0px;">
									<button type="submit" class="btn btn-primary">修改</button>
									<input type="hidden" name="roomNo" value="${hotelRoomVO.roomNo}">
									<input type="hidden" name="action" value="getOne_For_Update">
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

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
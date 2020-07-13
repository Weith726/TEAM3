<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.HotelRoom.model.*"%>

<%
	HotelRoomVO hotelRoomVO = (HotelRoomVO) request.getAttribute("hotelRoomVO");
%>

<!-- 運算式(Boolean) -->
<%-- <%=hotelRoomVO == null%>--${empVO.deptno}-- 與103行有關 --%>

<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>

<style>
#section-1 {
	position: relative;
	top: -10px;
}

#addBtn {
	margin-top: 3%;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid" id="section-1">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<h1 class="topTitle text-center">
					<span class="badge badge-warning">- 房間新增 -</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp" role="button" id="back_to_index">回首頁</a>
					</div>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>

		<jsp:useBean id="hotelRoomTypeSvc" scope="page" class="com.HotelRoomType.model.HotelRoomTypeService" />

		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelRoom/HotelRoom.do" name="form1" id="frm_input_srt">
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1">房間編號(ex.101)</label>
						<input type="text" class="form-control" name="roomNo" size="45" value="" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1">寵物編號(ex.P0001)</label>
						<input type="text" class="form-control" name="petNo" value="" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<input type="hidden" name="roomStatus" size="45" value="1" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1">房型名稱</label>
						<select size="1" name="roomTypeNo" class="form-control">
							<c:forEach var="hotelRoomTypeVO" items="${hotelRoomTypeSvc.all}">
								<option value="${hotelRoomTypeVO.roomTypeNo}">${hotelRoomTypeVO.roomTypeName}
							</c:forEach>
						</select>
						<div class="d-flex justify-content-center">
							<input type="hidden" name="action" value="insert">
							<input type="submit" class="btn btn-primary" id="addBtn" value="送出新增">
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
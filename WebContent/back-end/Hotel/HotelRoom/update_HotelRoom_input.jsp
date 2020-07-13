<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.HotelRoom.model.*"%>
<%@ page import="com.HotelRoomType.model.*"%>

<%
	HotelRoomVO hotelRoomVO = (HotelRoomVO) request.getAttribute("hotelRoomVO");//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>

<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>

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
			<div class="col-4"></div>
			<div class="col-4">
				<h1 class="topTitle text-center">
					<span class="badge badge-warning">- 房間修改 -</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelRoom/listAllHotelRoom.jsp" role="button">回首頁</a>
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



		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelRoom/HotelRoom.do" name="form1">
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1"> 房間編號 : </label>
						<%=(hotelRoomVO == null) ? "" : hotelRoomVO.getRoomNo()%>
						<input type="hidden" name="roomNo" size="45" value="<%=(hotelRoomVO == null) ? "RT004" : hotelRoomVO.getRoomNo()%>" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1"> 房型編號 : </label>
						<%=(hotelRoomVO == null) ? "" : hotelRoomVO.getRoomTypeNo()%>
						<input type="hidden" name="roomTypeNo" size="45" value="<%=(hotelRoomVO == null) ? "RT004" : hotelRoomVO.getRoomTypeNo()%>" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1">寵物編號 :</label>
						<input type="text" class="form-control" name="petNo" value="${hotelRoomVO.petNo}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label for="exampleInputEmail1">房間狀態 :</label>
						<select class="form-control" id="exampleFormControlSelect1" name="roomStatus">
							<option value="0" ${(hotelRoomVO.roomStatus==0)?'selected':'' }>已存在
							<option value="1" ${(hotelRoomVO.roomStatus==1)?'selected':'' }>已移除
						</select>
					</div>
					<div class="d-flex justify-content-center">
						<input type="hidden" name="action" value="update">
						<input type="hidden" name="roomNo" value="<%=hotelRoomVO.getRoomNo()%>">
						<button type="submit" class="btn btn-primary">送出修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.HotelOrder.model.*"%>

<%
	HotelOrderVO hotelOrderVO = (HotelOrderVO) request.getAttribute("hotelOrderVO");
%>

<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<style>
#section-1 {
	position: relative;
	top: -10px;
}

.back_to_index {
	margin-left: 30%;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>
</head>
<body>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid" id="section-1">
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<h1 class="topTitle text-center">
					<span class="badge badge-warning">- 訂單新增 -</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp" role="button" id="back_to_index">回首頁</a>
					</div>
				</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<!--錯誤表列 -->
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
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" name="form1">
					<div class="form-group font-weight-bold" id="select3">
						<input type="hidden" name="orderNo" size="45" value="${hotelOrderVO.orderNo}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>會員編號:</label>
						<input type="text" class="form-control" name="memNo" value="${hotelOrderVO.memNo}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>寵物編號:</label>
						<input type="text" class="form-control" name="petNo" value="${hotelOrderVO.petNo}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>房型編號: </label>
						<input type="text" class="form-control" name="roomTypeNo" value="${hotelOrderVO.roomTypeNo}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>房間編號: </label>
						<input type="text" class="form-control" name="roomNo" value="${hotelOrderVO.roomNo}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>房型價格: </label>
						<input type="text" class="form-control" id="roomTypePrice" name="roomTypePrice" value="${hotelOrderVO.roomTypePrice}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>入住日期: </label>
						<input type="text" id="start_dateTime" class="form-control" name="checkInDate" value="${hotelOrderVO.checkInDate}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>退房日期: </label>
						<input type="text" id="end_dateTime" class="form-control" name="checkOutDate" value="${hotelOrderVO.checkOutDate}" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>訂單總價: </label>
						<input type="text" class="form-control" id="totalPrice" name="totalPrice" value="${hotelOrderVO.totalPrice}" readonly unselectable="on" />
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>訂單狀態: </label>
						<select class="form-control" id="exampleFormControlSelect1" name="hotelOrderStatus">
							<option value="0">待確認
							<option value="1">已完成
							<option value="2">已取消
						</select>
					</div>
					<br>
					<input type="hidden" name="action" value="insert_back">
					<div class="d-flex justify-content-center">
						<input type="submit" class="btn btn-primary" value="送出新增">
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/back-end/Hotel/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/back-end/Hotel/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/back-end/Hotel/datetimepicker/jquery.datetimepicker.full.js"></script>
	<script>
		$.datetimepicker.setLocale('zh'); // kr ko ja en
		$(function() {
			$('#start_dateTime').datetimepicker(
					{
						format : 'Y-m-d H:i',
						onShow : function() {
							this.setOptions({
								maxDate : $('#end_dateTime').val() ? $(
										'#end_dateTime').val() : false
							})
						},
						timepicker : true,
						step : 60
					});

			$('#end_dateTime').datetimepicker(
					{
						format : 'Y-m-d H:i',
						onShow : function() {
							this.setOptions({
								minDate : $('#start_dateTime').val() ? $(
										'#start_dateTime').val() : false
							})
						},
						timepicker : true,
						step : 60
					});
		});
	</script>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
	<script>
		//計算總金額
		endTimeInMill = null;
		startTimeInMill = null;

		$('#start_dateTime').blur(function() {
			startTime = $(this).val();
			console.log(startTime);

			startTimeInMill = Date.parse(startTime);
			console.log(startTimeInMill);

		});

		$('#end_dateTime').blur(
				function() {
					endTime = $(this).val();
					console.log(endTime);

					endTimeInMill = Date.parse(endTime);
					console.log(endTimeInMill);

					totalDays = Math.ceil((endTimeInMill - startTimeInMill)
							/ (24 * 60 * 60 * 1000));
					console.log(totalDays);

					roomPrice = $('#roomTypePrice').val();
					console.log(roomPrice);

					totalPrice = roomPrice * totalDays;
					$('#totalPrice').val(totalPrice);
				});
	</script>
</body>
</html>
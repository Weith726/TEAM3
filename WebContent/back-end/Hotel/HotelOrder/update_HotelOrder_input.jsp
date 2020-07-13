<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.HotelOrder.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
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
					<span class="badge badge-warning">- 旅館訂單管理 -</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelOrder/listAllHotelOrder.jsp" role="button">回首頁</a>
					</div>
				</h1>
			</div>
		</div>

		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
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
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" name="form1">
					<div class="form-group font-weight-bold" id="select3">
						<label>訂單編號 :</label>
						<input type="text" class="form-control" name="orderNo" size="45" value="${hotelOrderVO.orderNo}" readonly unselectable="on"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>會員編號:</label>
						<input type="text" class="form-control" name="memNo" value="${hotelOrderVO.memNo}" readonly unselectable="on"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>寵物編號:</label>
						<input type="text" class="form-control" name="petNo" value="${hotelOrderVO.petNo}"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>房型名稱: </label>
						<input type="text" class="form-control" name="roomTypeNo" value="${hotelOrderVO.roomTypeNo}"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>房間編號: </label>
						<input type="text" class="form-control" name="roomNo" value="${hotelOrderVO.roomNo}"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>房型價格: </label>
						<input type="text" class="form-control" id="roomTypePrice" name="roomTypePrice" value="${hotelOrderVO.roomTypePrice}" readonly unselectable="on"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>入住時間: </label>
						<input type="text" id="start_dateTime" class="form-control" name="checkInDate" value="<fmt:formatDate value='${hotelOrderVO.checkInDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>退房時間: </label>
						<input type="text" id="end_dateTime" class="form-control" name="checkOutDate" value="<fmt:formatDate value='${hotelOrderVO.checkOutDate}' pattern='yyyy-MM-dd HH:mm'/>"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>訂單總價: </label>
						<input type="text" class="form-control" id="totalPrice" name="totalPrice" value="${hotelOrderVO.totalPrice}" readonly unselectable="on"/>
					</div>
					<div class="form-group font-weight-bold" id="select3">
						<label>訂單狀態: </label>
						<select class="form-control" id="exampleFormControlSelect1" name="hotelOrderStatus">
							<option value="0" ${(hotelOrderVO.hotelOrderStatus==0)?'selected':'' }>待確認
							<option value="1" ${(hotelOrderVO.hotelOrderStatus==1)?'selected':'' }>已完成
							<option value="2" ${(hotelOrderVO.hotelOrderStatus==2)?'selected':'' }>已取消
						</select>
					</div>
					<br>
					<input type="hidden" name="action" value="update">
					<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
					<div class="d-flex justify-content-center">
						<input type="submit" class="btn btn-primary" value="送出修改">
					</div>
				</form>
			</div>
		</div>
	</div>



	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
	<!-- 參考網站: https://xdsoft.net/jqplugins/datetimepicker/ -->

	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>

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
					step : 30
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
					step : 30
				});
	});
</script>
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
</html>
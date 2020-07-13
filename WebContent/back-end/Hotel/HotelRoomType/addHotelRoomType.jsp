<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.HotelRoomType.model.*"%>

<%
	HotelRoomTypeVO hotelRoomTypeVO = (HotelRoomTypeVO) request.getAttribute("hotelRoomTypeVO");
%>

<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<title>房型資料新增</title>

<style>
#section-1 {
	position: relative;
	top: -10px;
}

.topTitle {
	margin-top: 2%;
}

table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}

#body {
	background-color: white;
}

table {
	width: 600px;
	background-color: white;
	margin-top: 3%;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}

.back_to_index {
	margin-left: 30%;
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
					寵物旅館
					<span class="badge badge-warning">房型新增</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelRoomType/listAllHotelRoomType.jsp" role="button" id="back_to_index">回首頁</a>
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
			<div class="col-12">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelRoomType/HotelRoomType.do" name="form1" enctype="multipart/form-data" id="frm_input_srt">
					<div class="row">
						<div class="col-7">
							<div class="form-group font-weight-bold" id="select3">
								<input type="hidden" name="roomTypeNo" size="45" value="<%=(hotelRoomTypeVO == null) ? "RT004" : hotelRoomTypeVO.getRoomTypeNo()%>" />
							</div>
							<div class="form-group font-weight-bold" id="select3">
								<label for="exampleInputEmail1">房型名稱:</label>
								<input type="text" class="form-control" name="roomTypeName" value="<%=(hotelRoomTypeVO == null) ? "舒適房" : hotelRoomTypeVO.getRoomTypeName()%>" />
							</div>
							<div class="form-group font-weight-bold" id="select3">
								<label for="exampleInputEmail1">房型簡介: </label>
								<input type="text" class="form-control" name="roomTypeDescribe" value="<%=(hotelRoomTypeVO == null) ? "最溫馨最舒適的房間，讓您的寵物享受優游夏日時光。不限貓狗，一天『600』NT" : hotelRoomTypeVO.getRoomTypeDescribe()%>" />
							</div>
							<div class="form-group font-weight-bold" id="select3">
								<label for="exampleInputEmail1">房型空間:(長 X 高 X 寬(cm)) </label>
								<input type="text" class="form-control" name="roomTypeSpace" value="<%=(hotelRoomTypeVO == null) ? "200cm*200cm*300cm" : hotelRoomTypeVO.getRoomTypeSpace()%>" />
							</div>
							<div class="form-group font-weight-bold" id="select3">
								<label for="exampleInputEmail1">房型價格: </label>
								<input type="text" class="form-control" name="roomTypePrice" value="<%=(hotelRoomTypeVO == null) ? "750" : hotelRoomTypeVO.getRoomTypePrice()%>" />
							</div>
							<div class="form-group font-weight-bold" id="select3">
								<label for="exampleInputEmail1">房型服務: </label>
								<input type="text" class="form-control" name="roomTypeService" value="<%=(hotelRoomTypeVO == null) ? "公園散步" : hotelRoomTypeVO.getRoomTypeService()%>" />
							</div>
							<div class="form-group font-weight-bold" id="select3">
								<input type="hidden" class="form-control" name="roomTypeStatus" value="0" />
							</div>
						</div>
						<div class="col-5">
							<div class="form-group font-weight-bold" id="select3">
								<label for="exampleInputEmail1">房型圖片: </label>
								<br>
								<div class="d-flex justify-content-center">
									<img id="previewPic" src="<%=request.getContextPath()%>/back-end/Hotel/HotelRoomType/images/NOPIC.jpg" width="350px" height="220px">
								</div>
								<div class="d-flex justify-content-center">
									<input type="FILE" id="pic" name="roomTypePic" size="45" value="<%=(hotelRoomTypeVO == null) ? "" : hotelRoomTypeVO.getRoomTypePic()%>" placeholder="請上傳圖片" />
								</div>
							</div>
							<br>
							<div class="d-flex justify-content-center">
								<input type="hidden" name="action" value="insert">
								<input type="submit" class="btn btn-primary" id="addBtn" value="送出新增">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>

	<script>
		$("#pic").change(function() {
			readURL(this);
		});

		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader();
				reader.onload = function(e) {
					$("#previewPic").attr('src', e.target.result);
				}
				reader.readAsDataURL(input.files[0]);
			}
		}

	</script>

	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.petshelter.model.*"%>

<%
	PetShelterVO petShelterVO = (PetShelterVO) request.getAttribute("petShelterVO");
%>


<jsp:useBean id="adoptedpetsSvc" scope="page"
	class="com.adoptedpets.model.AdoptedPetsService" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">

<title>Update Pet Shelter</title>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"
	integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
	integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
	integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
	crossorigin="anonymous"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<style>
.rounded {
	height: 100px;
}

.col-2, .col-3 {
	padding-left: 0px;
}
</style>
</head>
<body>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>
	<div class="container">
		<div class="row">
			<div class="col">
				<form method="post"
					action="<%=request.getContextPath()%>/petshelter/petshelter.do">
					<fieldset disabled>
						<div class="form-group row">
							<label for="shelterNo">收容住所編號</label> <input type="text"
								name="shelterNo" id="shelterNo" class="form-control"
								value="自動生成">
						</div>
					</fieldset>

					<div class="form-group row">
						<label for="cleanDate">消毒日期</label> <input type="text"
							class="form-control" name="cleanDate" id="cleanDate"
							value="${petShelterVO.cleanDate}">
					</div>
					<div class="form-group row">
						<div class="col-2">收容住所狀態</div>
						<div class="col">
							<select class="custom-select" name="shelterStatus"
								id="shelterStatus">
								<option selected id="shelterStatusd4"></option>
								<option value="0">空住所</option>
								<option value="1">未滿</option>
								<option value="2">已滿</option>
							</select>
						</div>
						<script>
							if ('${petShelterVO.shelterStatus}' === '0') {
								$('#shelterStatusd4').attr("value", "0");
								$('#shelterStatusd4').text("空住所");
							} else if ('${petShelterVO.shelterStatus}' === '1') {
								$('#shelterStatusd4').attr("value", "1");
								$('#shelterStatusd4').text("未滿");
							} else if ('${petShelterVO.shelterStatus}' === '2') {
								$('#shelterStatusd4').attr("value", "2");
								$('#shelterStatusd4').text("已滿");
							}
						</script>
					</div>
					<input type="hidden" name="shelterNo"
						value="${petShelterVO.shelterNo}"> <input type="hidden"
						name="action" value="insert">
					<div class="form-group row">
						<div class="col text-center">
							<button type="submit" class="btn btn-primary ">送出</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

</body>

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
	$.datetimepicker.setLocale('zh');

	$('#cleanDate').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (這是timepicker的預設間隔60分鐘)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',		   
	});
</script>
</html>
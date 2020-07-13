<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.interaction.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	InteractionVO interactionVO = (InteractionVO) request.getAttribute("interactionVO");
%>

<jsp:useBean id="adoptedPetsSvc" scope="page"
	class="com.adoptedpets.model.AdoptedPetsService" />
<jsp:useBean id="adopterSvc" scope="page"
	class="com.adopter.model.AdopterService" />

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

<title>Update Interaction</title>

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
		<font style="color: red">�Эץ��H�U���~:</font>
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
					action="<%=request.getContextPath()%>/interaction/interaction.do">
					<fieldset disabled>
						<div class="form-group row">
							<label for="interactionNo">���e�d���s��</label> <input type="text"
								name="interactionNo" id="interactionNo" class="form-control"
								value="${interactionVO.interactionNo}">
						</div>
					</fieldset>

					<div class="form-group row">
						<div class="col-2">���e�d���s��</div>
						<div class="col">
							<select class="custom-select" name="petNo" id="petNo">
								<option selected value="${interactionVO.petNo}">${interactionVO.petNo}</option>
								<c:forEach var="adoptedPetsVO" items="${adoptedPetsSvc.all}">
									<option value="${adoptedPetsVO.petNo}">${adoptedPetsVO.petNo}</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="form-group row">
						<div class="col-2">��i�H�s��</div>
						<div class="col">
							<select class="custom-select" name="adopterNo" id="adopterNo">
								<option selected value="${interactionVO.adopterNo}">${interactionVO.adopterNo}</option>
								<c:forEach var="adopterVO" items="${adopterSvc.all}">
									<option value="${adopterVO.adopterNo}">${adopterVO.adopterNo}</option>
								</c:forEach>
							</select>
						</div>
					</div>


					<div class="form-group row">
						<label for="interactionDate">���ʮɬq</label> <input type="text"
							class="form-control" name="interactionDate" id="interactionDate"
							value="${interactionVO.interactionDate}">
					</div>

					<div class="form-group row">
						<label for="interactionStatus">���ʼҦ�</label> <input type="text"
							class="form-control" name="interactionStatus"
							id="interactionStatus" value="${interactionVO.interactionStatus}">
					</div>

					<div class="form-group row">
						<div class="col-2">��i���A</div>
						<div class="col">
							<select class="custom-select" name="adoptDesire" id="adoptDesire">
								<option selected id="adoptDesired4"></option>
								<option value="0">���N�@</option>
								<option value="1">�L�N�@</option>
								<option value="2"></option>
							</select>
						</div>
						<script>
							if ('${interactionVO.adoptDesire}' === '0') {
								$('#adoptDesired4').attr("value", "0");
								$('#adoptDesired4').text("���N�@");
							} else if ('${interactionVO.adoptDesire}' === '1') {
								$('#adoptDesired4').attr("value", "1");
								$('#adoptDesired4').text("���N�@");
							} else if ('${interactionVO.adoptDesire}' === '') {
								$('#adoptDesired4').attr("value", "2");
							}
						</script>
					</div>
					<input type="hidden" name="interactionNo"
						value="${interactionVO.interactionNo}"> <input
						type="hidden" name="action" value="update">
					<div class="form-group row">
						<div class="col-auto text-center">
							<button type="submit" class="btn btn-primary ">�e�X</button>
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
	$.datetimepicker.setLocale('ch');

	$('#interactionDate').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : true, //timepicker:true,
		step : 60, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d H:i', //format:'Y-m-d H:i:s',
		allowTimes : [ '09:00', '11:00', '13:00', '15:00', '17:00', '19:00' ], // �]�wtimepicker��ܪ��ɶ�   �p�GallowTimes:['09:00','11:00','12:00','21:00'],
		opened : false,
		closeOnWithoutClick : false,
	});

	var somedate1 = new Date();
	$('#interactionDate')
			.datetimepicker(
					{
						beforeShowDay : function(date) {
							if (date.getYear() < somedate1.getYear()
									|| (date.getYear() == somedate1.getYear() && date
											.getMonth() < somedate1.getMonth())
									|| (date.getYear() == somedate1.getYear()
											&& date.getMonth() == somedate1
													.getMonth() && date
											.getDate() < somedate1.getDate())) {
								return [ false, "" ]
							}
							return [ true, "" ];
						}
					});
</script>
</html>
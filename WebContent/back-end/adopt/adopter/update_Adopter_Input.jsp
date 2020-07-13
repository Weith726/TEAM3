<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adopter.model.*"%>

<%
	AdopterVO adoptetVO = (AdopterVO) request.getAttribute("adopterVO");
%>

<jsp:useBean id="petShelterSvc" scope="page"
	class="com.petshelter.model.PetShelterService" />
<jsp:useBean id="adopterSvc" scope="page"
	class="com.adopter.model.AdopterService" />

<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">

<title>Update Adopter</title>

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
<%@ include file="/back-end/backEndInclude/header.jsp"%>
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
				<form method="post" enctype="multipart/form-data"
					action="<%=request.getContextPath()%>/adopter/adopter.do">
					<fieldset disabled>
						<div class="form-group row">
							<label for="adopterNo">領養人編號</label> <input type="text"
								name="adopterNo" id="adopterNo" class="form-control"
								value="${adopterVO.adopterNo}">
						</div>
					</fieldset>
					<div class="form-group row">
						<label for="adopterName">姓名</label> <input type="text"
							class="form-control" name="adopterName" id="adopterName"
							value="${adopterVO.adopterName}">
					</div>
					<div class="form-group row">
						<div class="col-2">性別</div>
						<div class="col">
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="adopterGender" id="adopterGender1" value="男"> <label
									class="form-check-label" for="adopterGender1">男</label>
							</div>
							<div class="form-check form-check-inline">
								<input class="form-check-input" type="radio"
									name="adopterGender" id="adopterGender2" value="女"> <label
									class="form-check-label" for="adopterGender2">女</label>
							</div>
						</div>
					</div>
					<div class="form-group row">
						<label for="adopterOccupation">職業</label> <input type="text"
							class="form-control" name="adopterOccupation" id="adopterOccupation"
							value="${adopterVO.adopterOccupation}">
					</div>
					<div class="form-group row">
						<label for="adopterMail">信箱</label> <input type="email"
							class="form-control" name="adopterMail" id="adopterMail"
							value="${adopterVO.adopterMail}">
					</div>
					<input type="hidden" name="adopterNo" value="${adopterVO.adopterNo}">
					<input type="hidden" name="action" value="update">
					<div class="form-group row">
						<div class="col text-center">
							<button type="submit" class="btn btn-primary ">送出</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
<script>
	var gender = '${adopterVO.adopterGender}';
	if (gender === "男") {
		$('#adopterGender1').attr("checked", "checked");
	} else {
		$('#adopterGender2').attr("checked", "checked");
	}
</script>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.petshelter.model.*"%>
<%@ page import="java.sql.Date"%>

<%
	PetShelterService petShelterSvc = new PetShelterService();
List<PetShelterVO> list = petShelterSvc.getAll();
pageContext.setAttribute("list", list);
%>



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

<title>List All Adopter</title>

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
<style>
.adopter-container {
	top: 0px;
}

.rounded {
	height: 100px;
}

th, td, .pageSelect2 {
	/* 	white-space: nowrap; */
	text-align: center;
}

.table td, .table th {
	vertical-align: middle;
}

.pageSelect1, .align-self-end {
	text-align: right;
}

.pageSelect3 {
	text-align: left;
}

.input-group {
	align-items: center;
	justify-content: center;
}
</style>

</head>


<body>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid adopter-container">
		<div class="row">
			<div class="col-10">
				<form method="post"
					action="<%=request.getContextPath()%>/petshelter/petshelter.do">
					<div class="form-row align-items-cent er">
						<div class="col-sm-5 my-1">
							<input class="form-control form-control-sm" type="text"
								name="shelterNo" placeholder="輸入收容住所編號"> <input
								type="hidden" name="action" value="getOne_For_Display">
						</div>
						<div class="col-auto my-1">
							<button type="submit" class="btn btn-primary ">搜尋</button>
						</div>
						<div class="col-auto my-1">
							<a class="btn btn-success"
								href="<%=request.getContextPath()%>/back-end/petshelter/add_PetShelter.jsp"
								role="button">新增</a>
						</div>
					</div>
				</form>
			</div>
			<div class="col-2 align-self-end">
				<%@ include file="page1.file"%>
			</div>
		</div>
		<div class="row">
			<div class="col">
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th scope="col">收容住所編號</th>
							<th scope="col">消毒日期</th>
							<th scope="col">住所狀態</th>
							<th scope="col">修改</th>
							<th scope="col">刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="petShelterVO" items="${list}" varStatus="loop"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${petShelterVO.shelterNo}</td>
								<td>${petShelterVO.cleanDate}</td>
								<td id="shelterStatus-${loop.index}">${petShelterVO.shelterStatus}</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/petshelter/petshelter.do">
										<div class="input-group">
											<div>
												<button class="btn btn-warning" type="submit"
													id="button-addon1">修改</button>
											</div>
											<input type="hidden" class="form-control" name="shelterNo"
												value="${petShelterVO.shelterNo}"> <input
												type="hidden" class="form-control" name="action"
												value="getOne_For_Update">
										</div>
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/petshelter/petshelter.do">
										<div class="input-group">
											<div>
												<button class="btn btn-danger" type="submit"
													id="button-addon1">刪除</button>
											</div>
											<input type="hidden" class="form-control" name="shelterNo"
												value="${petShelterVO.shelterNo}"> <input
												type="hidden" class="form-control" name="action"
												value="delete">
										</div>
									</FORM>
								</td>
							</tr>
							<script type="text/javascript">
								if ('${petShelterVO.shelterStatus}' === '0')
									$('#shelterStatus-${loop.index}').text('空住所');
								else if ('${petShelterVO.shelterStatus}' === '1')
									$('#shelterStatus-${loop.index}').text('未滿');
								else if ('${petShelterVO.shelterStatus}' === '2')
									$('#shelterStatus-${loop.index}').text('已滿');
							</script>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<%@ include file="page2.file"%>
	</div>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>

</body>
</html>
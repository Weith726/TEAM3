<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.adoptedpets.model.*"%>

<%
	AdoptedPetsVO adoptedpetsVO = (AdoptedPetsVO) request.getAttribute("adoptedpetsVO");
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
	
<title>List One Adopted Pets</title>

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
img.rounded {
	height: 100px;
}

th, td {
	/* 	white-space: nowrap; */
	text-align: center;
}

.table td, .table th {
	vertical-align: middle;
}
</style>

</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th scope="col">收容寵物編號</th>
							<th scope="col">領養人編號</th>
							<th scope="col">住所編號</th>
							<th scope="col">寵物品種</th>
							<th scope="col">收容日期</th>
							<th scope="col">收容寵物圖片</th>
							<th scope="col">領養日期</th>
							<th scope="col">訪查日期</th>
							<th scope="col">訪查內容</th>
							<th scope="col">寵物類型</th>
							<th scope="col">寵物性別</th>
							<th scope="col">領養狀態</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>${adoptedpetsVO.petNo}</td>
							<td>${adoptedpetsVO.adopterNo}</td>
							<td>${adoptedpetsVO.shelterNo}</td>
							<td>${adoptedpetsVO.petBreed}</td>
							<td>${adoptedpetsVO.adoptedDate}</td>


							<td><img
								src="<%=request.getContextPath()%>/adoptedpetspic.do?petNo=${adoptedpetsVO.petNo}"
								class="rounded mx-auto d-block"></td>

							<td>${adoptedpetsVO.adoptDate}</td>
							<td>${adoptedpetsVO.interviewDate}</td>
							<td>${adoptedpetsVO.interviewInfo}</td>
							<td>${adoptedpetsVO.petSpecies}</td>
							<td>${adoptedpetsVO.petGender}</td>
							<td id="adoptStatus"></td>
							<script type="text/javascript">
								if ('${adoptedpetsVO.adoptStatus}' === '0')
									$('#adoptStatus').text('未領養');
								else if ('${adoptedpetsVO.adoptStatus}' === '1')
									$('#adoptStatus').text('已領養');
								else if ('${adoptedpetsVO.adoptStatus}' === '2')
									$('#adoptStatus').text('已移除');
							</script>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
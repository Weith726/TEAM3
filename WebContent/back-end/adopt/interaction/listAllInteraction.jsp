<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.interaction.model.*"%>
<%@ page import="java.sql.Timestamp"%>

<%
	InteractionService interactionSvc = new InteractionService();
	List<InteractionVO> list = interactionSvc.getAll();
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

<title>List All Interaction</title>

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
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid adopter-container">
		<div class="row">
			<div class="col-10">
				<form method="post"
					action="<%=request.getContextPath()%>/interaction/interaction.do">
					<div class="form-row align-items-cent er">
						<div class="col-sm-5 my-1">
							<input class="form-control form-control-sm" type="text"
								name="interactionNo" placeholder="輸入互動編號">
							<input type="hidden" name="action" value="getOne_For_Display">
						</div>
						<div class="col-auto my-1">
							<button type="submit" class="btn btn-primary ">搜尋</button>
						</div>
						<div class="col-auto my-1">
							<a class="btn btn-success"
								href="<%=request.getContextPath()%>/back-end/interaction/add_Interaction.jsp"
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
							<th scope="col">互動編號</th>
							<th scope="col">寵物編號</th>
							<th scope="col">領養人編號</th>
							<th scope="col">互動時段</th>
							<th scope="col">互動模式</th>
							<th scope="col">領養意願</th>					
							<th scope="col">修改</th>
							<th scope="col">刪除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="interactionVO" items="${list}" varStatus="loop"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							
							<tr>
								<td>${interactionVO.interactionNo}</td>
								<td>${interactionVO.petNo}</td>
								<td>${interactionVO.adopterNo}</td>								
								<td><fmt:formatDate value="${interactionVO.interactionDate}" pattern="yyyy-MM-dd HH:mm" /></td>
								<td>${interactionVO.interactionStatus}</td>
								<td>${interactionVO.adoptDesire}</td>								
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/interaction/interaction.do">
										<div class="input-group">
											<div>
												<button class="btn btn-warning" type="submit"
													id="button-addon1">修改</button>
											</div>
											<input type="hidden" class="form-control" name="interactionNo"
												value="${interactionVO.interactionNo}"> <input type="hidden"
												class="form-control" name="action" value="getOne_For_Update">
										</div>
									</FORM>
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/interaction/interaction.do">
										<div class="input-group">
											<div>
												<button class="btn btn-danger" type="submit"
													id="button-addon1">刪除</button>
											</div>
											<input type="hidden" class="form-control" name="interactionNo"
												value="${interactionVO.interactionNo}"> <input type="hidden"
												class="form-control" name="action" value="delete">
										</div>
									</FORM>
								</td>
							</tr>
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
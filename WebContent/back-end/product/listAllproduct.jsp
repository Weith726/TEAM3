<%@page import="java.util.List"%>
<%@page import="com.product.model.ProService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService" />
<%@include file="/back-end/backEndInclude/head.jsp" %>
<style type="text/css">
img {
	width: 70px;
	height: 70px;
}
table tr {
	border: solid black 1px;
	color: black;
	line-height:60px;
	text-align:center;
}
#proid{
	color:red;
}
#select{
	color:red;
}
.mycontainer{
	top:0px;
}
</style>
</head>
<body>
	<c:if test="${not empty noProduct}">
		<script>swal("${noProduct}")</script>
	</c:if>
	<%@include file="/back-end/backEndInclude/header.jsp" %>
	
	<c:if test="${empty select}">
		<div class="container-fluid mycontainer">
		<div class="row ml-3 mr-3">
			<div class="col-6">
				<h2 class="d-inline d-flex justify-content-start"><a href="<%= request.getContextPath()%>/back-end/product/addProduct.jsp">�s�W�ӫ~!!!!!</a></h2>
			</div>
			<div class="col-6">
				<form method="POST" class="d-flex justify-content-end form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/product/product.do">
				   <input name="productname" class="form-control mr-sm-2" type="text" placeholder="�j�M�ӫ~" aria-label="Search">
				   <input name="action" value="select" type="hidden"></input>
				   <input value="�j�M���~" class="btn btn-outline-success my-2 my-sm-0" type="submit"></input>
				</form>
			</div>
			
			<%  List list = null;
				list = prosvc.getAll();
			%>
		   <div class="col-md-12 ml-3 mb-2">
               	<%@ include file="/back-end/product/page1.file"%>
			</div>
			<div class="col-12">
				<table class="table">
					<thead class="thead-dark">
						<tr>
							<th>���~�s��</th>
							<th>���~�W��</th>
							<th>���~���</th>
							<th>���~�W�[�ɶ�</th>
							<th>���~�w�s</th>
							<th>���~�w���w�s</th>
							<th>���~�Ϥ�</th>
							<th>���~����</th>
							<th>���~���A</th>
							<th>���~����</th>
							<th>�\��</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="provo" items="${prosvc.all}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr ${(provo.productid==param.proid)? 'bgcolor=#D8ECEC':''}>
								<th id="proid">${provo.productid}</th>
								<td>${provo.productname}</td>
								<td>NT$${provo.productprice}</td>
								<td>${provo.producton}</td>
								<td>${provo.productstock}</td>
								<td>${provo.productsafe}</td>
								<td><img alt="" src="<%=request.getContextPath()%>/product/getPic?proid=${provo.productid}"></td>
								<td>${provo.productintro}</td>
								<td>${(provo.productstatus==1)? '�W�[��':(provo.productstatus==2)? '�ɳf':(provo.productstatus==3)? '�U�[':''}</td>
								<td>${(provo.kindno==0)? '����':(provo.kindno==1)? '�߫}':(provo.kindno==2)? '��L':''}
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/product/product.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="�ק�" class="btn btn-info">
										<input type="hidden" name="proid" value="${provo.productid}">
										<input type="hidden" name="requestURL" value="<%=request.getServletPath()%>">
										<input type="hidden" name="whichPage"	value="<%=whichPage%>">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			  <div class="col-md-12 ml-3 mb-2">
               		<%@ include file="/back-end/product/page2.file"%>
			</div>
		</div>
	</div>
	</c:if>
	
	<c:if test="${not empty select}">
	<div class="container-fluid mycontainer">
		<div class="row ml-3 mr-3">
			<div class="col-6">
				<h2 class="d-inline d-flex justify-content-start"><a href="<%= request.getContextPath()%>/back-end/product/addProduct.jsp">�s�W�ӫ~!!!!!</a></h2>
			</div>
			<div class="col-6">
				<form method="POST" class="d-flex justify-content-end form-inline my-2 my-lg-0" action="<%= request.getContextPath()%>/product/product.do">
				   <input name="productname" class="form-control mr-sm-2" type="text" placeholder="�j�M�ӫ~" aria-label="Search">
				   <input name="action" value="select" type="hidden"></input>
				   <input value="�j�M���~" class="btn btn-outline-success my-2 my-sm-0" type="submit"></input>
				</form>
			</div>
			<div class="col-12">
				<h2 id="select">�ŦX�z���j�M�`�@${select}�������!</h2>
			</div>
			<div class="col-12">
				<table class="table table-striped">
					<thead class="thead-dark">
						<tr>
							<th>���~�s��</th>
							<th>���~�W��</th>
							<th>���~���</th>
							<th>���~�W�[�ɶ�</th>
							<th>���~�w�s</th>
							<th>���~�w���w�s</th>
							<th>���~�Ϥ�</th>
							<th>���~����</th>
							<th>���~���A</th>
							<th>���~����</th>
							<th>�\��</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="provo" items="${product}">
							<tr>
								<th id="proid">${provo.productid}</th>
								<td>${provo.productname}</td>
								<td>NT$${provo.productprice}</td>
								<td>${provo.producton}</td>
								<td>${provo.productstock}</td>
								<td>${provo.productsafe}</td>
								<td><img alt="" src="<%=request.getContextPath()%>/product/getPic?proid=${provo.productid}"></td>
								<td>${provo.productintro}</td>
								<td>${(provo.productstatus==1)? '�W�[��':(provo.productstatus==2)? '�ɳf':(provo.productstatus==3)? '�U�[':''}</td>
								<td>${(provo.kindno==0)? '����':(provo.kindno==1)? '�߫}':(provo.kindno==2)? '��L':''}
								</td>
								<td>
									<FORM METHOD="post"
										ACTION="<%=request.getContextPath()%>/product/product.do"
										style="margin-bottom: 0px;">
										<input type="submit" value="�ק�" class="btn btn-info">
										<input type="hidden" name="proid" value="${provo.productid}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	</c:if>
		<%@include file="/back-end/backEndInclude/footer.jsp" %>
</body>
</html>
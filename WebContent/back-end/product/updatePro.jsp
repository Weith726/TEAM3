<%@page import="com.product.model.ProVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>

<%
	ProVO proValue = (ProVO) request.getAttribute("productVO");
%>
<html>
<head>
<meta charset="charset=UTF-8">
<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<script src="<%= request.getContextPath()%>/back-end/product/viewpic.js"></script>
<style>
	
	#img{
		height:290px;
		width: 290px;
	}
	#myimg{
		height:300px;
		width: 300px;
		box-sizing:border-box;
		border: solid #1DE7E7 5px;
		border-radius:20px;
	}
	body{
		background-image:url("img/bgdog.jpg");
	}
	#h1{
		color:#4330CB;
		font-weight:bold;
	}
	label{
		color:blue;
		font-weight:bold;
	}
	input[type=text],[type=date] {
	  border: 1px solid black;
	  border-radius:3px;
	  color:#0D0F0F;
	  font-weight:bold;
	}
	input:disabled{
		color:red;
	}
</style>
<%@include file="/back-end/backEndInclude/head.jsp" %>
</head>
<body>
		<%@include file="/back-end/backEndInclude/header.jsp" %>
	<div class="container contact ">
		<div class="row mt-4">
			<div class="col-md-12">
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">�Эץ��H�U���~:</font>
					<c:forEach var="message" items="${errorMsgs}">
						<p class="d-inline" style="color:blue; font-size:25px;">${message}~</p>
					</c:forEach>
				</c:if>
			</div>
			<div class="col-md-4 mt-4">
				<div class="contact-info d-flex align-items-end mb-4">
					<h1 id="h1" >�ק�ӫ~</h1>
				</div>
				<div id="myimg">
					<img alt="" src="<%=request.getContextPath()%>/product/getPic?proid=${productVO.productid}" id="img">
				</div>
			</div>
			
			<div class="col-md-4 mt-5">
				
				<form method="post" action="<%= request.getContextPath()%>/product/product.do" enctype="multipart/form-data">
				
					<div class="form-group">			
					<label class="control-label">���~�s��</label>
						<div class="">
							<input type="text" disabled class="form-control" name="productname" value="<%=proValue.getProductid()%>">
						</div>
					</div>
					<div class="form-group">			
					<label class="control-label">���~�W��</label>
						<div class="">
							<input type="text" class="form-control" name="productname" value="<%=proValue.getProductname()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">���~���</label>
						<div class="">
							<input type="text" class="form-control" name="productprice" value="<%=proValue.getProductprice()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">�W�[�ɶ�</label>
						<div class="">
							<input type="Date" class="form-control" name="producton" value="<%=proValue.getProducton()%>">
						</div>
					</div><div class="form-group">
						<label class="control-label">���~�w�s</label>
						<div class="" >
							<input type="text" class="form-control" name="productstock" value="<%=proValue.getProductstock()%>">
						</div>
					</div>
				</div>
				<div class="col-md-4 mt-5">
					<div class="form-group">
						<label class="control-label">���~�w���w�s</label>
						<div class="">
						<input type="text" class="form-control" name="productsafe" value="<%=proValue.getProductsafe()%>">
					</div>
					</div>
					<div class="form-group">
						<label class="control-label">���~�Ϥ�</label>
						<div class="">
							<input accept="image/*" class="form-control-file" type="file" name="productpic" id="pic" value="proValue.getProductpic()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">���~����</label>
						<div class="">
							<input type="text" class="form-control" name="productintro" value="<%=proValue.getProductintro()%>">
						</div>
					</div>
					<div class="form-group">
						<label class="control-label">���~���A</label>
						<div class="">
							<select size="1" name="productstatus">
								<option value="1" ${(productVO.productstatus==1)? 'selected':''}>�W�[	
								<option value="2" ${(productVO.productstatus==2)? 'selected':''}>�ɳf
								<option value="3" ${(productVO.productstatus==3)? 'selected':''}>�U�[	
				   		</select>
						</div>
					</div>
					<jsp:useBean id="kindsvc" scope="page" class="com.productkind.model.KindService" />
					<div class="form-group">
						<label class="control-label">���~����</label>
						<div class="">
					<select size="1" name="kindno">
						<c:forEach var="kindvo" items="${kindsvc.all}">
							<option value="${kindvo.kindno}"
								${(productVO.kindno==kindvo.kindno)? 'selected':'' }>${kindvo.kindname}
						</c:forEach>
					</select>
						</div>
					</div>
					<input type="hidden" name="proid" value="<%=proValue.getProductid()%>"> 
					<input type="hidden" name="action" value="update_check"> 
					<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>">
					<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  
					<input type="submit" value="�e�X�ק�" class="btn btn-secondary">
				</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="/back-end/backEndInclude/footer.jsp" %>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.opt.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	OptVO optVO = (OptVO) request.getAttribute("optVO"); //EmpServlet.java(Concroller), �s�Jreq��optVO����
%>

<html>
<head>

<%@ include file="/back-end/backEndInclude/head.jsp"%>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #CCCCFF;
}

table, th, td {
	text-align: center;
}

th {
	padding: 5px;
	background-color: lightcoral;
}

th, td {
	padding: 5px;
	border-bottom: dotted;
	border-width: 1px;
	border-color: rgba(0, 0, 0, 0.5);
}

img {
	max-width: 100px;
}

.addEmpBtn {
	float: right;
	font-size: 22px;
	padding: 5px 20px;
	background-color: #e7e7e7;
	color: black;
	margin-bottom: 20px;
}
</style>


</head>
<body>

<%@ include file="/back-end/backEndInclude/header.jsp"%>


	<span class="mainTitle">�Z��޲z</span>
	
	<a href="select_page.jsp">��^�D��</a>


	<hr class="mainTitlehr">

	<table>
		<tr>
			<!-- 			<th>���u�s��</th> -->
			<th>�ɬq�s��</th>
			<th>��ͽs��</th>
			<th>���E���</th>
			<th>���E�ɬq</th>
			<th>�ثe�w����</th>
			<th>�̤j�w����</th>
		</tr>
		<tr>

			<td>${optVO.sessionNo}</td>
			<td>${optVO.docNo}</td>
			<td><fmt:formatDate value="${optVO.optDate}"
					pattern="yyyy/MM/dd" /></td>

			<td>${optVO.optSession}</td>
			<td>${optVO.currentCount}</td>
			<td>${optVO.maximum}</td>
			

		</tr>
	</table>


		
		<%@ include file="/back-end/backEndInclude/footer.jsp"%>

</body>
</html>


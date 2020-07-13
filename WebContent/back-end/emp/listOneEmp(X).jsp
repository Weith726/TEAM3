<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ page import="com.emp.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	
	
%>

<html>
<head>
<title>���u��� - listOneEmp.jsp</title>

<style>
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
</style>

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
}

.mainTitle {
	letter-spacing: 8px;
	color: #42454C;
	font-weight: bold;
	font-size: 28px;
	padding-left: 20px;
}
/*�D���e���D�U���j�u*/
.mainTitlehr {
	border: 2px solid lightcoral;
}

img{
max-width:220px;

}
</style>

</head>
<body bgcolor='white'>


	<span class="mainTitle">���u���</span>


	<hr class="mainTitlehr">

	<table>
		<tr>
<!-- 			<th>���u�s��</th> -->
			<th>���u�m�W</th>
			<th>�ʧO</th>
			<th>�ͤ�</th>
			<th>¾��</th>
			<th>�q��</th>
			<th>�a�}</th>
			<th>���u�b��</th>
			<th>���u�K�X</th>
			<th>���u�Ӥ�</th>
			<th>��¾��</th>
			<th>��¾��</th>
			<th>���u���A</th>
			<th>�ק�</th>
			<th>�R��</th>
		</tr>
		<tr>
<%-- 			<td>${empVO.empID}</td> --%>
			<td>${empVO.empName}</td>
			<td>${empVO.empGender}</td>
			
		    <% // <td>${empVO.empBirth}</td> �쥻�g�k�A �U�����JSTL %>
			
			<td><fmt:formatDate value="${empVO.empBirth}"
					pattern="yyyy/MM/dd" /></td>

			<td>${empVO.empJob}</td>
			<td>${empVO.empPhone}</td>
			<td>${empVO.empAddress}</td>
			<td>${empVO.empAcc}</td>
			<td>${empVO.empPwd}</td>
			<td><img src="<%= request.getContextPath()%>/back-end/emp/img.do?empID=${empVO.empID}"></td>
			<td><fmt:formatDate value="${empVO.hiredate}"
					pattern="yyyy/MM/dd" /></td>
			<td><fmt:formatDate value="${empVO.quitdate}"
					pattern="yyyy/MM/dd" /></td>
			<td>${empVO.empStatus}</td>
			<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�ק�"> <input type="hidden"
							name="empID" value="${empVO.empID}"> <input type="hidden"
							name="action" value="getOne_For_Update">
					</FORM>
				</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<input type="submit" value="�R��"> <input type="hidden"
							name="empID" value="${empVO.empID}"> <input type="hidden"
							name="action" value="delete">
					</FORM>
				</td>
			
		</tr>
	</table>
	
		<input class="addEmpBtn" type="button" value="��^���u�޲z" onclick="location.href='listAllEmp.jsp'">

</body>
</html>


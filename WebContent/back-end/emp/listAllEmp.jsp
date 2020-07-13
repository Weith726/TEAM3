<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.emp.model.*"%>
<%-- �����m�߱ĥ� EL ���g�k���� --%>

<%
	EmpService empSvc = new EmpService();
	List<EmpVO> list = empSvc.getAll();
	pageContext.setAttribute("list", list);
	
	EmpVO empVO = (EmpVO) request.getAttribute("empVO"); //EmpServlet.java(Concroller), �s�Jreq��empVO����
	
	
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

#addbtn {
	float: right;
	font-size: 26px;
	padding: 5px 20px;
	margin-bottom: 20px;
}


</style>

</head>
<body>


	<%@ include file="/back-end/backEndInclude/header.jsp"%>

	<span class="mainTitle">���u�b���޲z</span>

	<!-- 	<a href="select_page.jsp">�^����</a> -->


	<hr class="mainTitlehr">


	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<table>
		<tr>
			<th>���u�s��</th>
			<th>���u�Ӥ�</th>
			<th>���u�m�W</th>
			<th>�ʧO</th>
			<th>�ͤ�</th>
			<th>¾��</th>
			<th>�q��</th>
	<!-- 			<th>�a�}</th> -->
	<!-- 			<th>���u�b��</th> -->
	<!-- 			<th>���u�K�X</th> -->			
	<!-- 			<th>��¾��</th> -->
	<!-- 			<th>��¾��</th> -->
			<th>���u���A</th>
			<th>�ק�</th>
<!-- 			<th>�R��</th> -->
			<th>�ԲӸ��</th>

		</tr>

		<%@ include file="page1.file"%>

		<button type="button" class="btn btn-success" id="addbtn" onclick="location.href='addEmp.jsp'">�s�W���u</button>	
			
		<c:forEach var="empVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">

			<tr>
				<td>${empVO.empID}</td>
				<td><img
					src="<%= request.getContextPath()%>/back-end/emp/img.do?empID=${empVO.empID}"></td>
				<td>${empVO.empName}</td>
				<td>${(empVO.empGender=='�k')?'<font color="darkblue">�k':'<font color="deeppink">�k'}</td>
				<%
					//<td>${empVO.hiredate}</td> �쥻�g�k�A �U�����JSTL
				%>
				<td><fmt:formatDate value="${empVO.empBirth}"
						pattern="yyyy/MM/dd" /></td>

				<td>${empVO.empJob}</td>
				<td>${empVO.empPhone}</td>
<!-- 				�H�U���b�ԲӸ�Ƥ� -->
<%-- 				<td>${empVO.empAddress}</td> --%>
<%-- 				<td>${empVO.empAcc}</td> --%>
<%-- 				<td>${empVO.empPwd}</td> --%>

				
<%-- 				<td>${empVO.hiredate}</td> --%>
<%-- 				<td>${empVO.quitdate}</td> --%>
<!-- 				�H�W���b�ԲӸ�Ƥ� -->
				<td>${(empVO.empStatus =='1')?'<font color="green">�b¾��':(empVO.empStatus =='2')?'�𰲤�':'<font color="darkred">�w��¾'}</td>
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-warning">�ק�</button>
						<input type="hidden" name="empID" value="${empVO.empID}"> 
						<input type="hidden" name="action" value="getOne_For_Update">
					</FORM>
				</td>
<!-- 				<td> -->
<!-- 					<FORM METHOD="post" id="formDel" onsubmit=" return confirmDel();" -->
<%-- 						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do" --%>
<!-- 						style="margin-bottom: 0px;"> -->
<!-- 						<button type="submit" class="btn btn-danger">�R��</button><input type="hidden" -->
<%-- 							name="empID" value="${empVO.empID}"> <input type="hidden" --%>
<!-- 							name="action" value="delete" id="delete"> -->
<!-- 					</FORM> -->
<!-- 				</td> -->
				<td>
					<FORM METHOD="post"
						ACTION="<%=request.getContextPath()%>/back-end/emp/emp.do"
						style="margin-bottom: 0px;">
						<button type="submit" class="btn btn-info" >�ԲӸ��</button>
						<input type="hidden" name="empID" value="${empVO.empID}"> 
						<input type="hidden" name="action" value="getOne_For_Display">
					</FORM>
				</td>

			</tr>

		</c:forEach>
	</table>
	
	<!-- Button trigger modal -->
<input type="hidden" id="detile" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">



<!-- Modal -->
<c:if test="${empVO!=null}">
	
	
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">�ԲӸ��</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">


	<table>
		<tr>
<!-- 			<th>���u�s��</th> -->
			<th>���u�m�W</th>
			<th>���u�b��</th>
			<th>���u�K�X</th>
			<th>�a�}</th>
			<th>��¾��</th>
			<th>��¾��</th>


		</tr>
		<tr>
<%-- 			<td>${empVO.empID}</td> --%>
			<td>${empVO.empName}</td>
			<td>${empVO.empAcc}</td>
			<td>${empVO.empPwd}</td>
			<td>${empVO.empAddress}</td>
			<td><fmt:formatDate value="${empVO.hiredate}"
					pattern="yyyy/MM/dd" /></td>
			<td><fmt:formatDate value="${empVO.quitdate}"
					pattern="yyyy/MM/dd" /></td>
	
			
			
		</tr>
	</table>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</c:if>
	
	<%@ include file="page2.file"%>

	<%@ include file="/back-end/backEndInclude/footer.jsp"%>

	<script type="text/javascript">
	$(function() {
		$( "#detile" ).trigger( "click" );
	});
	
	//�R���e���T�{
		function confirmDel() {	

			var txt;
			var r = confirm("�T�w�R��?");
			if (r == true) {
				return true;
			} else {
// 				document.getElementById("formDel").value = "";//�M�Ť奻��  
				return false;
			}

		};
	</script>

</body>
</html>
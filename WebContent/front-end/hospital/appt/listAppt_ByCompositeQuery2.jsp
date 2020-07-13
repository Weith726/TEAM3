<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.appt.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listAppt_ByCompositeQuery2" scope="request" type="java.util.List<ApptVO>" />
<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />


<html>
<head><title>�w���d�� - listEmps_ByCompositeQuery2.jsp</title>

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
	width: 800px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h1>




<table>
	<tr>
		<th>���E�s��</th>
		<th>�|���d���s��</th>
		<th>�ݶE�ɶ�</th>
		<th>��v</th>
		<th>�E��</th>
		<th>�ݶE��</th>
		<th>�d���g���y�z</th>
		<th>�g���Ϥ�</th>
	</tr>
	
	<c:forEach var="apptVO" items="${listAppt_ByCompositeQuery2}">
		
		<tr>
			<td>${apptVO.apptno}</td>
			<td>${apptVO.petno}</td>
			
			<td>
			<c:forEach var="optVO" items="${optSvc.all}">
            <c:if test="${apptVO.sessionno==optVO.sessionNo}">
	        ${optVO.optDate}<br>${optVO.optSession}  
	        </c:if>
            </c:forEach>
            </td>
            
            <td>
			<c:forEach var="optVO" items="${optSvc.all}"> 
			<c:forEach var="docVO" items="${docSvc.all}">
            <c:if test="${(apptVO.sessionno==optVO.sessionNo)&&(optVO.docNo==docVO.docno)}">
	        ${docVO.docno}<br>${docVO.docname}<br>
            </c:if>
            </c:forEach>
            </c:forEach>
            </td>
            
            <td>
			<c:forEach var="optVO" items="${optSvc.all}"> 
			<c:forEach var="docVO" items="${docSvc.all}">
            <c:if test="${(apptVO.sessionno==optVO.sessionNo)&&(optVO.docNo==docVO.docno)}">
	        ${docVO.roomno}
            </c:if>
            </c:forEach>
            </c:forEach>
            </td>
            
			<td>${apptVO.seqno}</td>
			<td>${apptVO.symdesc}</td>
			<td>
			<img alt="" src="<%=request.getContextPath()%>/front-end/appt/ShowSymPhoto.do?apptno=${apptVO.apptno}" id="display">
			</td>
			
			<td> 
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" style="margin-bottom: 0px;">
			     <input type="submit" value="����">
			     <input type="hidden" name="apptno"  value="${apptVO.apptno}">
			     <input type="hidden" name="action" value="cancel"></FORM>
			</td>
			
		</tr>
	</c:forEach>
</table>


</body>
</html>
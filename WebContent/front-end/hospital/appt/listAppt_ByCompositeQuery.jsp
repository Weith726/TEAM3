<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.appt.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listAppt_ByCompositeQuery" scope="request" type="java.util.List<ApptVO>" />

 <% 
 ApptService apptSvc = new ApptService();
 List<ApptVO> list  = listAppt_ByCompositeQuery;
 pageContext.setAttribute("list",list);
 
 %>

<html>
<head><title>�ݶE�i�� </title>
<meta http-equiv="refresh" content="10;URL=<%=request.getContextPath()%>/front-end/appt/queue.jsp">
</head>
<body bgcolor='white'>

<h1>

�ثe�ݶE��${fn:length(listAppt_ByCompositeQuery)+1}




<!-- <table> -->
<!-- 	<tr> -->
<!-- 		<th>���u�s��</th> -->
<!-- 		<th>���u�m�W</th> -->
<!-- 		<th>¾��</th> -->
<!-- 		<th>���Τ��</th> -->
<!-- 		<th>�~��</th> -->
<!-- 		<th>����</th> -->
<!-- 		<th>����</th> -->
<!-- 	</tr> -->
	
<%-- 	<c:forEach var="apptVO" items="${listAppt_ByCompositeQuery}"> --%>
		
<!-- 		<tr> -->
<%-- 			<td>${apptVO.apptno}</td> --%>
<%-- 			<td>${apptVO.memno}</td> --%>
<%-- 			<td>${apptVO.sessionno}</td> --%>
<%-- 			<td>${apptVO.seqno}</td> --%>
<%-- 			<td>${apptVO.symdesc}</td> --%>
<!-- 			<td> -->
<%-- 			<img alt="" src="<%=request.getContextPath()%>/front-end/appt/ShowSymPhoto.do?apptno=${apptVO.apptno}" id="display"> --%>
<!-- 			</td> -->
<%-- 			<td>${apptVO.optstate}</td> --%>
			
<!-- 		</tr> -->
<%-- 	</c:forEach> --%>
<!-- </table> -->

</body>
</html>
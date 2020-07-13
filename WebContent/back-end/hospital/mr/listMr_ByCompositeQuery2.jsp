<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.mr.model.*"%>

<%-- �U�νƦX�d��-�i�ѫȤ��select_page.jsp�H�N�W�����Q�d�ߪ���� --%>
<%-- �����u�@���ƦX�d�߮ɤ����G�m�ߡA�i���ݭn�A�W�[�����B�e�X�ק�B�R�����\��--%>

<jsp:useBean id="listMr_ByCompositeQuery" scope="request" type="java.util.List<MrVO>" />
<jsp:useBean id="apptSvc" scope="page" class="com.appt.model.ApptService" />
<jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" />


<!DOCTYPE html>
<html lang="en">
<head>
<%@ include file="/back-end/backEndInclude/head.jsp" %>
	<title>Table V02</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="main.css">
<!--===============================================================================================-->
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp" %>
<div class="limiter">
		<div class="container-table100">
			<div class="wrap-table100">
					<div class="table">
						<div class="row header">
							<div class="cell">
								�E�������s��
							</div>
							<div class="cell">
								���E�s��
							</div>
							<div class="cell">
								��ͽs��
							</div>
							<div class="cell">
								�|���d���s��
							</div>
							<div class="cell">
								�E�_�g��
							</div>
							<div class="cell">
								�E�_�B��
							</div>
							<div class="cell">
								�w���O��
							</div>
							<div class="cell">
								�ī~�O��
							</div>
							<div class="cell">
								��N�O��
							</div>
							<div class="cell">
								�ק�
							</div>
							
						</div>	
	<c:forEach var="mrVO" items="${listMr_ByCompositeQuery}">
		
		<div class="row">
						
							<div class="cell" data-title="Full Name">
							${mrVO.mrno}
							</div>
							<div class="cell" data-title="Age">
							${mrVO.apptno}
							</div>
							<div class="cell" data-title="Job Title">
<%-- 							${mrVO.docno} --%>
							
							<c:forEach var="apptVO" items="${apptSvc.all}">
							<c:forEach var="optVO" items="${optSvc.all}">
            				<c:if test="${(mrVO.apptno==apptVO.apptno)&&(apptVO.sessionno==optVO.sessionNo)}">
	           				${optVO.docNo}<br>
                  			  </c:if>
                			</c:forEach>
                			</c:forEach>
							</div>
							
							<div class="cell" data-title="Location">
							<c:forEach var="apptVO" items="${apptSvc.all}">
            				<c:if test="${mrVO.apptno==apptVO.apptno}">
	           				${apptVO.memno}<br>
                  			  </c:if>
                			</c:forEach>
							</div>
							<div class="cell" data-title="Location">
							${mrVO.symptom}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.prescription}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.apptfee}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.medfee}
							</div>
							<div class="cell" data-title="Location">
							${mrVO.operfee}
							</div>
							<div class="cell" data-title="Location">
							 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/mr/mr.do" style="margin-bottom: 0px;">
			     <input type="image" src="images/icons/edit.jpg" width="20" height="20">
			     <input type="hidden" name="mrno"  value="${mrVO.mrno}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
							</div>
							
						</div></c:forEach>
			</div>
		
			</div>
		</div>
	</div><h7><a href="select_page.jsp" style="color:#3C3C3C;"><img src="images/icons/home.ico" width="20" height="20" border="0">��^�D����</a></h7>



	

<!--===============================================================================================-->	
	<script src="vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/bootstrap/js/popper.js"></script>
	<script src="vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="js/main.js"></script>
<%@ include file="/back-end/backEndInclude/footer.jsp" %>
</body>
</html>
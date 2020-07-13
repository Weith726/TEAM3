<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.opt.model.*"%>
<%@ page import="com.mem.model.*"%>
<%-- �����Ƚm�߱ĥ� Script ���g�k���� --%>

<%
	OptVO optVO = (OptVO) request.getAttribute("optVO"); //�s�Jreq��optVO����

%>
<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />
<jsp:useBean id="petSvc" scope="page"
	class="com.MemberPet.model.MemberPetService" />
<html>
<head>
<%@ include file="/front-end/frontEndInclude/head.jsp"%>

<style>
table {
	width: 100%;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 1px solid #CCCCFF;
	font-size: 24px;
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

.mainTitle {
	font-size: 32px;
}

.memTable th {
	width: 300px;
}

.memTable td * {
	float: left;
	font-size: 24px;
}

.submit {
	/* margin-left:45%; */
	font-size: 24px;
}

.main {
	width: 80%;
	margin: 0 auto;
}
</style>


</head>
<body>

	<%@ include file="/front-end/frontEndInclude/header.jsp"%>

	<div class="main">
		<span class="mainTitle">�����w�����e</span>

		<!-- 	<a href="dispOpt.jsp">���s���</a> -->


		<hr class="mainTitlehr">

		<!-- 	<div>�ɬq�s��</div> -->
		<!-- 	<div>��ͽs��</div> -->
		<!-- 	<div>���E���</div> -->
		<!-- 	<div>���E�ɬq</div> -->
		<!-- 	<div>�w��</div> -->
		<!-- 	<div></div> -->
		

			<table>
				<tr>

					<!-- <th>�ɬq�s��</th> -->
					<th>���E���</th>
					<th>���E�ɬq</th>
					<th>��O</th>
					<th>�~��v</th>
					<th>�E��</th>
					<th>�w������</th>
					<!-- 			<th>�̤j�w����</th> -->
				</tr>
				<tr>

					<%-- 			<td>${optVO.sessionNo}</td> --%>
					<td><fmt:formatDate value="${optVO.optDate}"
							pattern="yyyy/MM/dd (E)" /></td>
					<td>${optVO.optSession}</td>
					<td>
						<!-- 			�Ȯɼg��3�Ӭ�O(��X��ק�) --> <c:forEach var="docVO"
							items="${docSvc.all}">
							<c:if test="${optVO.docNo==docVO.docno}">
	                    ${(docVO.divno == 'D01')?'����':(docVO.divno == 'D02')?'�߬�':'��L��'}
                    </c:if>
						</c:forEach>

					</td>
					<td><c:forEach var="docVO" items="${docSvc.all}">
							<c:if test="${optVO.docNo==docVO.docno}">
	                    ${docVO.docname}
                    </c:if>
						</c:forEach></td>
					<td><c:forEach var="docVO" items="${docSvc.all}">
							<c:if test="${optVO.docNo==docVO.docno}">
	                    ${docVO.roomno}�E
                    </c:if>
						</c:forEach></td>
					<td>${optVO.currentCount+1}</td>
					<%-- 			<td>${optVO.maximum}</td> --%>

				
				</tr>
			</table>
			<br>
			<div style="width: 100%" align="center">
				<img
					src="https://viacapo.com/wp-content/themes/GoDoCommerceTheme/img/successgif.gif"><br>
				<span style="font-size : 30px;color:#0E923A;"> �w�����\</span>
			</div>


			
		
		
	</div>



	<%@ include file="/front-end/frontEndInclude/footer.jsp"%>


</body>


<script>
	$(function() {

		function format_float(num, pos) {
			var size = Math.pow(10, pos);
			return Math.round(num * size) / size;
		}

		function preview(input) {

			if (input.files && input.files[0]) {
				var reader = new FileReader();

				reader.onload = function(e) {
					$('.preview').attr('src', e.target.result);
					var KB = format_float(e.total / 1024, 2);
					$('.size').text("�ɮפj�p�G" + KB + " KB");
				}

				reader.readAsDataURL(input.files[0]);
			}
		}

		$("body").on("change", ".upl", function() {
			console.log("123");
			preview(this);
		})

	})
</script>
</html>


<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doc.model.*"%>
<%@ page import="com.div.model.*"%>

<jsp:useBean id="listDocs_ByDivno" scope="request" type="java.util.Set<DocVO>" /> <!-- ��EL����i�ٲ� -->
<jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />


<!DOCTYPE html>
<html lang="en" >
<head>
  <meta charset="UTF-8">
  <title>CodePen - Fork this Card! #CodepenChallenge</title>
  <script src="https://kit.fontawesome.com/79c137b893.js"></script><link rel="stylesheet" href="style3.css">

</head>
<body>

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	<h6><a href="<%=request.getContextPath()%>/front-end/doc/select_page.jsp">��^�d�ߥD����</a></h6>
	<c:forEach var="docVO" items="${listDocs_ByDivno}" >
		<div class="container">
<div class="card">
		<tr>
			<td><c:forEach var="divVO" items="${divSvc.all}">
                    <c:if test="${docVO.divno==divVO.divno}">
	                    ${divVO.divno}${divVO.divname}</font>
                    </c:if>
                </c:forEach>
			</td>


	
		<h2>${docVO.docname}</h2>${docVO.docno}
  <br>
  <div class="desc">${docVO.intro}</div>
 <img src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=${docVO.docno}" height=200px width=200px; alt="meow">
    <div class="actions">
    <button class="actions__like">
            <c:forEach var="divVO" items="${divSvc.all}">
                    <c:if test="${docVO.divno==divVO.divno}">
	                    ${divVO.divno}${divVO.divname}</font>
                    </c:if>
                </c:forEach>
                 </button>
    <button class="actions__trade">�E�����X <br>${docVO.roomno}</button>
    <button class="actions__cancel">�~��<br>${docVO.seniority}�~</button>		
  </div>
</div>
	</c:forEach>
</div>
</div>
<!-- partial -->
</body>

</body>
</html>
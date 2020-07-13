<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.memlatestinfo.model.*"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">

<!DOCTYPE html>
<% 
	MemlatestinfoVO memliVO =(MemlatestinfoVO) request.getAttribute("mliVO");
%>

<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>

<div class="container">
<div class="row justify-content-center align-items-center">


<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" name="form1">

 <h3>會員最新訊息資料資料修改 </h3>

 <h3>資料修改:</h3>
<table class="table table-hover">
	<tr>
		<td>訊息編號:<font color=red><b>*</b></font></td>
		<td><%=memliVO.getMemLatestInfoNo()%></td>
	</tr>
	<tr>
		<td>會員編號:</td>
		<td>
<%-- 		<%= memSvc.getOneEmp(memliVO.getMemNo()).getMemName()%> --%>
		<input class="form form-control" type="TEXT" name="memNO" size="45" value="${mliVO.memNo}" />
		</td>
	</tr>
	<tr>
		<td>訊息內容:</td>
		<td>
	    <textarea class="form form-control" name="InfoContent" style="width:500px;height:200px;"><%=memliVO.getInfoContent()%></textarea>
		</td>
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="memliNO" value="<%=memliVO.getMemLatestInfoNo()%>">
<input type="submit" value="送出修改" class="btn btn-primary form form-control">
</FORM>
</div>
</div>
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
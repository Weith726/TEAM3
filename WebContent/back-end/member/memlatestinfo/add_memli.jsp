<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.memlatestinfo.model.*"%>

<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<%
	MemlatestinfoVO mliVO = (MemlatestinfoVO) request.getAttribute("mliVO");
%>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>



<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container">
<div class="row justify-content-center align-items-center">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" name="">
<h3>會員最新消息新增 </h3>

<h3>資料新增:</h3>
<table>

	<tr>
		<td>會員編號:</td>
		<td><input type="TEXT" name="memNO" class="form form-control"
			 value="<%= (mliVO==null)? "memNO" : mliVO.getMemNo()%>" /></td>
	</tr>
		<tr>
		<td>訊息內容:</td>
		<td>
			<textarea class="form form-control" name="InfoContent" style="width:500px;height:200px;"><%= (mliVO==null)? "InfoContent" : mliVO.getInfoContent()%></textarea>
		</td>
	</tr>

</table>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增"  class="btn btn-primary form form-control">

</FORM>

</div>
</div>

<hr  size=5 color=red >


<div class="container">
<div class="row justify-content-center align-items-center">
<!-- 為了新增給所有會員 -->
<form METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" name="">
<h3>資料新增給所有會員:</h3>
<table>
	<tr>
		<td><input type="hidden" name="memNO" size="45"
			 value="M0001" /></td>
	</tr>
		<tr>
		<td>訊息內容:</td>
		<td>
			 <textarea class="form form-control" name="InfoContent" style="width:500px;height:200px;"><%= (mliVO==null)? "InfoContent" : mliVO.getInfoContent()%></textarea>
		</td>
	</tr>
</table>
<input type="hidden" name="action" value="insertAll">
<input type="submit" value="新增給全部" class="btn btn-primary form form-control">
</form>
</div>
</div>

</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html>
<html>
<%
 MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>

<head>
<meta charset="utf-8">
<title>Insert title here</title>

<style type="text/css">
		body {
			background: #B3D9D9;
		}
		
		form {
			position:absolute;
			top: 100px;
			left:25em;
			font-size:20px;
		}
		input{
			border-radius:10px;	
			cursor:pointer;	
		}
		label{
			cursor:pointer;
		}
</style>
</head>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<body>
	<form method="post" ACTION="<%=request.getContextPath()%>/Puppy/mem.do" enctype="multipart/form-data">
	
		<label><input type="text" name="memName" value="<%= (memVO==null)? "你的名字" : memVO.getMemName()%>">名字<br></label>
		<label><input type="text" name="memAccount" value="<%= (memVO==null)? "帳號" : memVO.getMemAccount()%>">帳號<br></label> 
		<label><input type="text" name="memPassword" value="<%= (memVO==null)? "密碼" : memVO.getMemPassword()%>">密碼<br> </label>
		<label><input type="text" name="memCreditCardId" value="<%= (memVO==null)? "信用卡" : memVO.getMemCreditCardId()%>">信用卡<br></label>
        <label><input type="text" name="memPhone" value="<%= (memVO==null)? "手機" : memVO.getMemPhone()%>">電話<br></label>
        <label><input type="text" name="memEmail" value="<%= (memVO==null)? "信箱" : memVO.getMemEmail()%>">信箱<br></label>
        <label><input type="text" name="memAddress" value="<%= (memVO==null)? "地址" : memVO.getMemAddress()%>">住址<br> </label>
        <label><input type="text" name="memStatus" value="<%= (memVO==null)? "狀態" : memVO.getMemStatus()%>">狀態<br> </label>
        <label><input type="file" name="pic" value="照片"><br></label>
		<input type="hidden" name="action" value="picture">
		<input type="submit" value="送出">

	</form>
</body>
</html>
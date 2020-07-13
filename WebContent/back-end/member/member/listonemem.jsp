<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.mem.controller.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>
<%-- <% MemberVO memVO = (MemberVO) request.getAttribute("memVO");%> --%>
<% MemberVO memVO = (MemberVO) session.getAttribute("memVO");%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>

<table class="table table-hover col-12">
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>帳號</th>
		<th>密碼</th>
		<th>會員信用卡</th>
		<th>手機號碼</th>
		<th>信箱</th>
		<th>地址</th>
		<th>會員狀態</th>
		<th>會員照片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<tr>
		<td><%=memVO.getMemNo()%></td>
		<td><%=memVO.getMemName()%></td>
		<td><%=memVO.getMemAccount()%></td>
		<td><%=memVO.getMemPassword()%></td>
		<td><%=memVO.getMemCreditCardId()%></td>
		<td><%=memVO.getMemPhone()%></td>
		<td><%=memVO.getMemEmail()%></td>
		<td><%=memVO.getMemAddress()%></td>
		<td>
			<c:if test="${memVO.memStatus == 0}">
				<c:out value="未驗證"></c:out>
			</c:if>
			<c:if test="${memVO.memStatus == 1}">
				<c:out value="已驗證"></c:out>
			</c:if>
			<c:if test="${memVO.memStatus == 2}">
				<c:out value="已停權"></c:out>
			</c:if>
		</td>
		<td><img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memVO.memNo}" height="100px" width="170px"></td> 
<%-- 		最初嘗試版本<%= request.getQueryString() %> --%>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-primary">
			     <input type="hidden" name="memNO"  value="${memVO.memNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update_B"></FORM>
		</td>
		<td>
			 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mem.do" style="margin-bottom: 0px;">
			    <input type="submit" value="刪除" class="btn btn-primary form form-control">
			    <input type="hidden" name="memNO"  value="${memVO.memNo}">
			    <input type="hidden" name="action" value="delete"></FORM>
		</td>
	</tr>
</table>
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
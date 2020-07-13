<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.accusation.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>
<%
	AccusationVO accVO = (AccusationVO) request.getAttribute("accVO");
%>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<table id="table-1">
		<tr>
			<td>
				<h3>客訴資料 - ListOneacc.jsp</h3>
			</td>
		</tr>
	</table>

	<table class="table table-hover col-12">
		<tr>
			<th>客訴編號</th>
			<th>客訴類型</th>
			<th>客訴內容</th>
			<th>客訴時間</th>
			<th>客訴狀態</th>
			<th>修改</th>
			<th>刪除</th>
		</tr>
		<tr>
			<td><%=accVO.getAccusationNo()%></td>
			<td><%=accVO.getAccusationType()%></td>
			<td><%=accVO.getAccusationContent()%></td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="<%=accVO.getAccusationtime()%>"></fmt:formatDate></td>
			<td>
									<c:if test="${accVO.accusationStatue == 0}">
										<c:out value="待處理"></c:out>
									</c:if>
									<c:if test="${accVO.accusationStatue == 1}">
										<c:out value="處理中"></c:out>
									</c:if>
									<c:if test="${accVO.accusationStatue == 2}">
										<c:out value="已處理"></c:out>
									</c:if>
			</td>
			<td>
				<FORM METHOD="post"
					ACTION="<%=request.getContextPath()%>/Puppy/acc.do"
					style="margin-bottom: 0px;">
					<input type="submit" value="修改" class="btn btn-primary"> 
					<input type="hidden" name="accusationNo" value="${accVO.accusationNo}"> 
					<input type="hidden" name="action" value="getOne_For_Update">
				</FORM>
			</td>
			<td>
				<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" style="margin-bottom: 0px;">
					<input type="submit" value="刪除" class="btn btn-primary">
					<input type="hidden" name="accusationNo" value="${accVO.accusationNo}">
					<input type="hidden" name="action" value="delete">
				</FORM>
			</td>
		</tr>
	</table>
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
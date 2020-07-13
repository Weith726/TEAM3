<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.accusation.model.*"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>
<%
	AccusationVO accVO = (AccusationVO) request.getAttribute("accVO");
%>
<html>
<head>
<meta charset="UTF-8">
<title>修改</title>
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
<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" name="form1" >
<table class="table table-hover text-nowrap ">
<h3>資料修改:</h3>
	<tr>
		<td>客訴編號:<font color=red><b>*</b></font></td>
		<td><%=accVO.getAccusationNo()%></td>
	</tr>
	<tr>
		<td>客訴類型:</td>
		<td><input type="TEXT" name="accusationType" size="45" value="<%=accVO.getAccusationType()%>" /></td>
	</tr>
	<tr>
		<td>客訴內容:</td>
		<td><textarea name="accusationContent" style="width:100%;height:200px;"><%= (accVO==null)? "accusationContent" : accVO.getAccusationContent()%></textarea></td>
	</tr>
	<tr>
		<td>客訴狀態:</td>
		<td>
		<select class="form-control" name="accusationStatue">
				<option value="0" ${(accVO.accusationStatue==0)?'selected':'' }>待處理
				<option value="1" ${(accVO.accusationStatue==1)?'selected':'' }>處理中
				<option value="2" ${(accVO.accusationStatue==2)?'selected':'' }>已處理
		</select>
		</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update" >
<input type="hidden" name="accusationNo" value="<%=accVO.getAccusationNo()%>">
<input type="submit" value="送出修改" class="btn btn-primary form form-control"></FORM>
</div>
</div>
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
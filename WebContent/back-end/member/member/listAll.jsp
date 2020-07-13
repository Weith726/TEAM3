<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>
<html>
<%
	MemberService mSvc = new MemberService();
	List<com.mem.model.MemberVO> list = mSvc.getAll();
	pageContext.setAttribute("list",list);
%>


<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
<table id="table-1" >
	<tr ><td>
		 <h3>所有會員資料 - listAll.jsp</h3>
	</td></tr>
</table>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table-bordered table-hover col-12">
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
	<%@ include file="page1.file" %> 
	<c:forEach var="memVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${memVO.memNo}</td>
			<td>${memVO.memName}</td>
			<td>${memVO.memAccount}</td>
			<td>${memVO.memPassword}</td>
			<td>${memVO.memCreditCardId}</td> 
			<td>${memVO.memPhone}</td>
			<td>${memVO.memEmail}</td>
			<td>${memVO.memAddress}</td>
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
			<td><img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memVO.memNo}" style="height:100px;width:170px"></td>		
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mem.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-primary form form-control">
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
	</c:forEach>
</table>
<%@ include file="page2.file" %>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
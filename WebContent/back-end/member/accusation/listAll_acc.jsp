<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.accusation.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>

<%
AccusationService aSvc = new AccusationService();
	List<AccusationVO> list = aSvc.getAll();
	pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<table class="table table-hover">
	<tr>
		<th>客訴編號</th>
		<th>客訴類型</th>
		<th>客訴內容</th>
		<th>客訴時間</th>
		<th>客訴狀態</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="/back-end/member/accusation/page1.file" %> 
	<c:forEach var="accVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		
		<tr>
			<td>${accVO.accusationNo}</td>
			<td>${accVO.accusationType}</td>
			<td>${accVO.accusationContent}</td>
			<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${accVO.accusationtime}"></fmt:formatDate></td>
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
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn-primary">
			     <input type="hidden" name="accusationNo"  value="${accVO.accusationNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update" class="btn btn-primary"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn-primary">
			     <input type="hidden" name="accusationNo"  value="${accVO.accusationNo}">
			     <input type="hidden" name="action" value="delete" ></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="/back-end/member/accusation/page2.file" %>





<h3>資料查詢:</h3>
	
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<ul>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" >
        <b>輸入客訴編號 (如M0001):</b>
        <input type="text" name="accusationNo">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn btn-primary">
    </FORM>
  </li>

  <jsp:useBean id="accSvc" scope="page" class="com.accusation.model.AccusationService" />  
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" >
       <b>選擇訊息編號:</b>
       <select size="1" name="accusationNo">
         <c:forEach var="accVO" items="${accSvc.all}" > 
          <option value="${accVO.accusationNo}">${accVO.accusationNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display" >
       <input type="submit" value="送出" class="btn btn-primary">
    </FORM>
  </li>
</ul>



</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
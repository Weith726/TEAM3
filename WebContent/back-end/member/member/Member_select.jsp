<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
 
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/sockettest.css">  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<%
 // 取得登入成功時拿到的姓名跟照片
	EmpVO empVONav = (EmpVO) session.getAttribute("empVONav");
%>

</head>

<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<table id="table-1">
   <tr><td><h3>會員資料查詢 </h3><h4>( MVC )</h4></td></tr>
	</table>

	<p>This is the Home page for Member: Home</p>

	<h3>資料查詢:</h3>
	<c:if test="${not empty errorMsgs }">
		<font style="color:red">請修正以下錯誤:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
	</c:if>
	
	<jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />
	<form method="Get" ACTION="<%=request.getContextPath()%>/Puppy/mem.do">
		<b>會員編號</b>
		<input type = "text" name= "memNo">
		<input type="hidden" name="action" value="getOne_For_Display">
		<input type="submit" value="送出">
	</form>
	
	 <FORM METHOD="Get" ACTION="<%=request.getContextPath()%>/Puppy/mem.do">
       <b>選擇會員姓名:</b>
       <select size="1" name="memNo">
         <c:forEach var="memVO" items="${memSvc.all}" >
          <option value="${memVO.memNo}">${memVO.memName}  
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出">
    </FORM>
    
    
    <form method="post" action="<%=request.getContextPath()%>/Puppy/mem.do">
    	<b><font color=blue>萬用複合查詢:</font></b> <br>
        <b>輸入會員編號:</b>
        <input type ="text" name= "memNo"><br>
        
        <b>輸入會員姓名:</b>
        <input type="text" name="memName" ><br>
         
        <b>輸入會員帳號:</b>
		<input type="text" name="memAccount" ><br>
		
		<b>輸入會員密碼:</b>
		<input type="text" name="memPassword" ><br>
		
		<b>輸入會員信用卡號:</b>
		<input type="text" name="memCreditCardId" ><br>
		
		<b>輸入會員手機號碼:</b>
		<input type="text" name="memPhone" ><br>
		
		<b>輸入會員信箱:</b>
		<input type="text" name="memEmail" ><br>
		
		<b>輸入會員地址:</b>
		<input type="text" name="memAddress" ><br>
		
	   <input type="hidden" name="action" value="list_Query">
       <input type="submit" value="送出">
		
    </form>
 <%@ include file="/back-end/member/member/websocketjs-back.jsp"%>  
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
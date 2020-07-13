<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.accusation.model.*"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
<table id="table-1">
   <tr><td><h3>Accusation: Home</h3><h4>( MVC )</h4></td></tr>
</table>

<p>This is the Home page for Accusation: Home</p>

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
  <li><a href='<%=request.getContextPath()%>/back-end/accusation/listAll_acc.jsp'>List</a> all Accusation.  <br><br></li>
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
  <li>
  
<!--   取出會員編號 -->
<%--   <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" /> --%>
<%--      <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" > --%>
<!--        <b>選擇會員編號:</b> -->
<!--        <select size="1" name="mli"> -->
<%--          <c:forEach var="mliVO" items="${memSvc.all}" > --%>
<%--           	<option value="${mliVO.memNo}">${mliVO.memNo} --%>
<%--          </c:forEach>    --%>
<!--        </select> -->
<!--        <input type="hidden" name="action" value="getAll_For_Display"> -->
<!--        <input type="submit" value="送出"> -->
<!--      </FORM> -->
<!--   </li> -->
<!-- </ul> -->


<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/accusation/add_acc.jsp'>Add</a> a new Accusation.</li>
</ul>	
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
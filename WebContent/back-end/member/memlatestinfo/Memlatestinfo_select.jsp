<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.memlatestinfo.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
 <link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
<table id="table-1">
   <tr><td><h3>會員最新訊息查詢</h3></td></tr>
</table>


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
  <li><a href='<%=request.getContextPath()%>/Puppy/back/listAll_memli.jsp'>查全部</a> all Memlatestinfo.  <br><br></li>
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" >
        <b>輸入訊息編號 (如1001):</b>
        <input type="text" name="mli">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn-primary">
    </FORM>
  </li>

  <jsp:useBean id="mliSvc" scope="page" class="com.memlatestinfo.model.MemlatestinfoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" >
       <b>選擇訊息編號:</b>
       <select size="1" name="mli">
         <c:forEach var="mliVO" items="${mliSvc.all}" > 
          <option value="${mliVO.memLatestInfoNo}">${mliVO.memLatestInfoNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn-primary">
    </FORM>
  </li>
  <li>
  
<!--   取出會員編號 -->
  <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mli">
         <c:forEach var="mliVO" items="${memSvc.all}" >
          	<option value="${mliVO.memNo}">${mliVO.memNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getAll_For_Display">
       <input type="submit" value="送出" class="btn-primary">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/Puppy/back/add_memli.jsp'>新增會員最新消息</a> add a new Memlatestinfo.</li>
</ul>
</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
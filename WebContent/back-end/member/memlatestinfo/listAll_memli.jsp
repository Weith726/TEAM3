<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.memlatestinfo.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<%
	MemlatestinfoService mSvc = new MemlatestinfoService();
	List<MemlatestinfoVO> list = mSvc.getAll();
	pageContext.setAttribute("list",list);
%>

<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
<table id="table-1">
	<tr><td>
		 <h3>會員最新訊息資料</h3>
	</td></tr>
</table>

<table class="table table-hover col-12">
	<tr>
		<th>訊息編號</th>
		<th>會員編號</th>
		<th>訊息內容</th>
		<th>訊息修改</th>
		<th>訊息刪除</th>
	</tr>
	
	<%@ include file="page1.file" %>
	<c:forEach var="mliVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<c:if test="${mliVO.memNo == mli}">
		<tr>
			<td>${mliVO.memLatestInfoNo}</td>
			<td>${mliVO.memNo}</td>
			<td>${mliVO.infoContent}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-primary">
			     <input type="hidden" name="memliNO"  value="${mliVO.memLatestInfoNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
						<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-primary">
			     <input type="hidden" name="memLatestInfoNo"  value="${mliVO.memLatestInfoNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		</c:if>		
		<c:if test="${mli == null}">
		<tr>
			<td>${mliVO.memLatestInfoNo}</td>
			<td>${mliVO.memNo}</td>
			<td>${mliVO.infoContent}</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
			     <input type="submit" value="修改" class="btn btn-primary">
			     <input type="hidden" name="memliNO"  value="${mliVO.memLatestInfoNo}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-primary">
			     <input type="hidden" name="memLatestInfoNo"  value="${mliVO.memLatestInfoNo}">
			     <input type="hidden" name="action" value="delete_B"></FORM>
			</td>
		</tr>
		</c:if>	
	</c:forEach>
</table>
<%@ include file="page2.file" %>



<br>
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
  <li>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" >
        <b>輸入訊息編號 (如1001):</b>
        <input type="text" name="mli" class="form">
        <input type="hidden" name="action" value="getOne_For_Display">
        <input type="submit" value="送出" class="btn btn-primary">
    </FORM>
  </li>

  <jsp:useBean id="mliSvc" scope="page" class="com.memlatestinfo.model.MemlatestinfoService" />
   
  <li>
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" >
       <b>選擇訊息編號:</b>
       <select size="1" name="mli">
         <c:forEach var="mliVO" items="${mliSvc.all}" > 
          <option value="${mliVO.memLatestInfoNo}" class="btn btn-secondary dropdown-toggle">${mliVO.memLatestInfoNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="送出" class="btn btn-primary">
    </FORM>
  </li>
  <li>
  
<!--   取出會員編號 -->
  <jsp:useBean id="memSvc" scope="page" class="com.mem.model.MemberService" />
     <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" >
       <b>選擇會員編號:</b>
       <select size="1" name="mli">
         <c:forEach var="mliVO" items="${memSvc.all}" >
          	<option value="${mliVO.memNo}" class="btn btn-secondary dropdown-toggle">${mliVO.memNo}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getAll_For_Display">
       <input type="submit" value="送出" class="btn btn-primary">
     </FORM>
  </li>
</ul>


<h3>員工管理</h3>

<ul>
  <li><a href='<%=request.getContextPath()%>/back-end/memlatestinfo/add_memli.jsp'>新增會員最新消息</a> add a new Memlatestinfo.</li>
</ul>



</body>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
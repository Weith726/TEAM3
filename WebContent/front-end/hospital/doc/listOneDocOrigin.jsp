<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.doc.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  DocVO docVO = (DocVO) request.getAttribute("docVO"); //DocServlet.java(Concroller), 存入req的docVO物件
%>

<!DOCTYPE html>
<html>
<head>

	
</head>
<body>

	

	<!-- ****************************以下為實際功能頁變更區域**************************** -->
<span class="mainTitle">醫師檢索</span>

	<hr class="mainTitlehr">
		 
	<h6><a href="select_page.jsp"><img src="images/back.png" width="32" height="32" border="0">返回查詢主頁面</a></h6>

<table>
	<tr>
		<th>醫生編號</th>
		<th>科別編號</th>
		<th>醫生姓名</th>
		<th>診間號碼</th>
		<th>年資</th>
		<th>介紹</th>
		<th>醫生照片</th>
		<th>醫生在職狀態</th>

	</tr>
	<tr>
		<td><%=docVO.getDocno()%></td>
		<td><%=docVO.getDivno()%></td>
		<td><%=docVO.getDocname()%></td>
		<td><%=docVO.getRoomno()%></td>
		<td><%=docVO.getSeniority()%></td>
		<td><%=docVO.getIntro()%></td>
		<td>
			<img alt="" src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=${docVO.docno}" id="display">
		</td>
		<td><%=docVO.getDocstatus()%></td>
	
	</tr>
</table>

	<!-- ****************************以上為實際功能頁變更區域*************************** -->

<%@ include file="/front-end/frontEndInclude/footer.jsp" %>

</body>
</html>
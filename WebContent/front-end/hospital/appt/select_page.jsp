<%@page import="com.doc.model.DocService"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.doc.model.*"%>
<%@ page import="org.json.*"%>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page import="com.google.gson.JsonArray" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>


<% 

DocService docSvc = new DocService();
List<DocVO> d01 = docSvc.getAllByDiv("D01");
Gson gson = new Gson();
String jsonStr1 = gson.toJson(d01);
List<DocVO> d02 = docSvc.getAllByDiv("D02");
String jsonStr2 = gson.toJson(d02);
List<DocVO> d03 = docSvc.getAllByDiv("D03");
String jsonStr3 = gson.toJson(d03);

%>

<html>
<head>
<%@ include file="/front-end/frontEndInclude/head.jsp"%>


<style type="text/css">

.mainTitle{
font-size:40px;
font-family: 'Noto Sans TC', sans-serif;

}

/* form{ */
/* display:inline; */
/* margin:0px; */
/* } */
.divno{

width: 300px;
font-size:28px;
}
.doc{
width: 300px;
font-size:28px;
}

#startBtn{
font-size:28px;
}

.sel{
font-size:28px;
}

.allsel{
margin:0px auto;

}
  .main {
	width: 80%;
	margin: 0 auto;
	font-family: 'Noto Sans TC', sans-serif;
}


</style>



</head>

<body>

<%@ include file="/front-end/frontEndInclude/header.jsp"%>
	<div class="main">

	<span class="mainTitle">門診預約</span>


	<hr class="mainTitlehr">

<FORM METHOD="post" ACTION="dispOpt.jsp">


<div class="allsel" style="width: 100%" align="center">

<div class="sel">科別<br>
	<select id="divno" class="divno" name="divno">
		<option value="ALL" selected>請選擇
		<option value="D01">犬科</option>
		<option value="D02">貓科</option>
		<option value="D03">其他科</option>
	</select></div>

<div class="sel">醫生<br>
	<select id="doc" class="doc" name="doc">


	</select></div>
	<br>
	<br>
<!-- <input type="hidden" name="action" value="displayOpt">  -->
		
		<button type="submit" class="btn btn-primary" id="startBtn">開始預約</button>
	</FORM>

</div>

</div>

<%@ include file="/front-end/frontEndInclude/footer.jsp"%>
</body>
<script type="text/javascript">
$(function() {
	$( "#divno" ).trigger( "onchange" );
});
	


	//json轉成 JS json陣列
	var json1 = '<%=jsonStr1%>'
	var json2 = '<%=jsonStr2%>'
	var json3 = '<%=jsonStr3%>'
	
	var divno =  document.getElementById("divno");
	var str ='';
	//option發生改變
	
	divno.onchange=function (){
			str = divno.value;
			if(str==='D01'){
				var obj = JSON.parse(json1);
			$('#doc').html("");
			$.each(obj, function (index,item) {
	            var docno = obj[index].docno;
	            var docname = obj[index].docname;
	            //構造動態option
	            
	            $('#doc').append("<option value='"+docno+"'>"+docname+"</option>");
	        });
			}
			if(str==='D02'){
				var obj = JSON.parse(json2);
			$('#doc').html("");
			$.each(obj, function (index,item) {
	            var docno = obj[index].docno;
	            var docname = obj[index].docname;
	            //構造動態option
	            
	            $('#doc').append("<option value='"+docno+"'>"+docname+"</option>");
	        });
			}
			
			if(str==='D03'){
				var obj = JSON.parse(json3);
			$('#doc').html("");
			$.each(obj, function (index,item) {
	            var docno = obj[index].docno;
	            var docname = obj[index].docname;
	            //構造動態option
	            
	            $('#doc').append("<option value='"+docno+"'>"+docname+"</option>");
	        });
			}
			if(str==="ALL"){
				$('#doc').html("");
			}

	}



</script>

</html>
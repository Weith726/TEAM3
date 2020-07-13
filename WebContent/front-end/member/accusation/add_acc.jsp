<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.accusation.model.*"%>

<!DOCTYPE html>

<% 
	AccusationVO accVO = (AccusationVO) request.getAttribute("accVO");
	String memNO = (String) session.getAttribute("memNO");  //判斷會員登入
	String memName = (String) session.getAttribute("memName"); //判斷會員名稱
	
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>萌寵家族</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
        integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
        integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
   <link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/member/accusation/sockettest.css">
    <!-- bootstrap、FontAwesome、googleFont -->
</head>

<body background="<%= request.getContextPath()%>/images/534557.jpg" style="background-size:auto;">
				
    <header>
        <nav class="navbar navbar-expand-lg navbar-light ">
            <a href="#" class="navbar-brand ml-3">Cute:)<span style="color:#00E8E8;">Family</span></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu"
                aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse"></div>
            <div class="collapse navbar-collapse" id="navbarMenu">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a href="index.html" class="nav-link">首頁</a>
                    </li>
                    <!-- <li class="nav-item dropdown">
                        <a href="#" class="nav-link">會員專區</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">門診專區</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">寵物旅館</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">寵物商城</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">領養專區</a>
                    </li> -->
                    <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            會員專區
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">會員登入</a>
                            <form action="<%=request.getContextPath()%>/Puppy/mem.do" METHOD="post" style="margin-bottom: 0px;">
                            <input type="submit" value="編輯會員資料" class="dropdown-item">
			    			<input type="hidden" name="memNO"  value="${memNO}">
			    			<input type="hidden" name="action"	value="getOne_For_Update">
<!--    先不用,以後可以清掉 <a class="dropdown-item" href="#">編輯會員資料</a> -->
 							</form>
                            <a class="dropdown-item" href="#">管理您的寵物</a>
                      		<form METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
                            <input type="submit" value="瀏覽最新通知" class="dropdown-item">
			    			<input type="hidden" name="mli"  value="${memNO}">
			    			<input type="hidden" name="action"	value="getAll_For_Display">
			    			</form>
                           
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            門診專區
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">門診預約</a>
                            <a class="dropdown-item" href="#">門診查詢</a>
                            <a class="dropdown-item" href="#">瀏覽看診進度</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            寵物旅館
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">瀏覽房型</a>
                            <a class="dropdown-item" href="#">旅館預約</a>
                            <a class="dropdown-item" href="#">旅館預約查詢</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            寵物商城
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">購物車</a>
                            <a class="dropdown-item" href="#">搜尋商品資訊</a>
                            <a class="dropdown-item" href="#">瀏覽商品資訊</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                            aria-haspopup="true" aria-expanded="false">
                            領養專區
                        </a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <a class="dropdown-item" href="#">互動預約</a>
                            <a class="dropdown-item" href="#">互動預約查詢</a>
                            <a class="dropdown-item" href="#">瀏覽流浪動物</a>
                            <a class="dropdown-item" href="#">領養流浪動物</a>
                        </div>
                    </li>
                </ul>        
               
                <div style="<%= (memNO ==null)? "visibility:hidden":"visibility:"%>"><img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memNO}" style="height:50px" id="mempic"> <%= memName%>您好~</div>
           
                <form class="form-inline my-2 my-lg-0">
                    <button class="btn menu-right-btn border" type="submit" id="login" style="<%= (memNO ==null)? "display:":"display:none"%>">
	 					<a href="<%=request.getContextPath()%>/front-end/member/login.jsp"> 登入 </a> 
                    </button>
                </form>
               
                 <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/Puppy/logout.do">
                    <button class="btn menu-right-btn border" type="submit" id="logout" style="<%= (memNO !=null)? "display:":"display:none"%>">
                        登出
                    </button>
                </form>
                <!-- <button class="btn menu-right-btn border" type="submit">
                    註冊
                </button> -->
            </div>
        </nav>
    </header>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="wrapper container mt-5">
	<div class="row justify-content-center align-items-center">

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/acc.do" name="">
<!-- 上方的圖 -->
		 <h3>客訴新增 - addacc.jsp</h3></td><td>

<h3>客訴內容:</h3>

<table class="table ">
	<tr>
<!-- 		<td>客訴編號:</td> -->
		<td><input type="hidden" name="accusationNo" size="45"
			 value="<%= (accVO==null)? "accusationNo" : accVO.getAccusationNo()%>" /></td>
	</tr>
	<tr>
		<td>客訴類型:</td>
		<td>
		<select name="accusationType" style="width:100%" class="form form-control">
			<option value="商城">商城
			<option value="診所">診所
			<option value="旅館">旅館
			<option value="領養">領養
			<option value="會員">會員
			<option value="網頁">網頁
			<option value="員工">員工
			<option value="其他">其他
			</select></td>
	</tr>	
<!-- 	原先的input先不用因為只有單行,可刪	<input type="TEXT" name="accusationContent" size="45" style="height:200px" -->
<%-- 			 value="<%= (accVO==null)? "accusationContent" : accVO.getAccusationContent()%>" /> --%>
	<tr>
		<td>客訴內容:</td>
		<td><textarea class="form form-control" name="accusationContent" style="width:500px;height:200px;"><%= (accVO==null)? "退一步海闊天空喔~" : accVO.getAccusationContent()%></textarea></td>
	</tr>
	<tr>
<!-- 		<td>客訴狀態:</td> -->
		<td><input type="hidden" name="accusationStatue" size="45"
			 value="<%= (accVO==null)? "1" : accVO.getAccusationStatue()%>" /></td>
	</tr>

</table>
	
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn btn-primary form form-control">
</FORM>
</div>
</div>


	<%@ include file="/front-end/member/accusation/websocketjs.jsp"%>
	
	
<!--     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" -->
<!--         integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" -->
<!--         crossorigin="anonymous"></script> -->
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
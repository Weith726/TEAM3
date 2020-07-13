<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.memlatestinfo.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
        integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
        integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style2.css">
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<%
	MemlatestinfoService mSvc = new MemlatestinfoService();
	List<MemlatestinfoVO> list = mSvc.getAll();
	pageContext.setAttribute("list",list);
	String memNO = (String) session.getAttribute("memNO");  //判斷會員登入
	String memName = (String) session.getAttribute("memName"); //判斷會員名稱
%>

<html>
<head>
<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>萌寵家族</title>

    
</head>
<body>
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
	 					<a href="<%=request.getContextPath()%>/front-end/member/member/login.jsp"> 登入 </a> 
                    </button>
                </form>
               
                 <form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/Puppy/logout.do">
                    <button class="btn menu-right-btn border" type="submit" id="logout" style="<%= (memNO !=null)? "display:":"display:none"%>">
                        登出
                    </button>
                </form>
            </div>
        </nav>
    </header>


<div class="container">
<div class="row justify-content-center align-items-center">
<table class="table table-hover text-nowrap ">
		 <h3>最新消息 - ListALLmli.jsp</h3>
	<tr>
		<th>訊息編號</th>
		<th>會員編號</th>
		<th>訊息內容</th>
<!-- 		<th>訊息修改</th> -->
		<th>訊息刪除</th>
	</tr>
	
	<c:forEach var="mliVO" items="${list}">
		<c:if test="${mliVO.memNo == mli}">
		<tr>
			<td>${mliVO.memLatestInfoNo}</td>
			<td>${mliVO.memNo}</td>
			<td>${mliVO.infoContent}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="memliNO"  value="${mliVO.memLatestInfoNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
			     <input type="submit" value="刪除" class="btn btn-primary form form-control">
			     <input type="hidden" name="memLatestInfoNo"  value="${mliVO.memLatestInfoNo}">
			     <input type="hidden" name="memNO" value="${mliVO.memNo}"> <%--為了讓控制器跳轉後能知道是哪一個會員 --%>
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		</c:if>		
		<c:if test="${mli == null}">
		<tr>
			<td>${mliVO.memLatestInfoNo}</td>
			<td>${mliVO.memNo}</td>
			<td>${mliVO.infoContent}</td>
<!-- 			<td> -->
<%-- 			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;"> --%>
<!-- 			     <input type="submit" value="修改"> -->
<%-- 			     <input type="hidden" name="memliNO"  value="${mliVO.memLatestInfoNo}"> --%>
<!-- 			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM> -->
<!-- 			</td> -->
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
			     <input
			      type="submit" value="刪除" class="btn btn-primary form form-control">
			     <input type="hidden" name="memLatestInfoNo"  value="${mliVO.memLatestInfoNo}">
			     <input type="hidden" name="action" value="delete"></FORM>
			</td>
		</tr>
		</c:if>	
	</c:forEach>
</table>
</div>
</div>

<c:if test="${not empty delete}">
		<script>
			swal("刪除成功", "", "success");
		</script>
</c:if>




	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"
        integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI"
        crossorigin="anonymous"></script>
</body>
</html>
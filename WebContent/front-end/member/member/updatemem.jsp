<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"  integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
 <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"   integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V" crossorigin="anonymous">
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/style2.css">
 <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
 <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<!DOCTYPE html>

<% 
	MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>
<% 
String memNO = (String)session.getAttribute("memNO");
String memName = (String) session.getAttribute("memName");
MemberVO member = (MemberVO) request.getAttribute("member");
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
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style2.css">
    <!-- bootstrap、FontAwesome、googleFont -->
</head>
<style>


</style>
<body background="<%= request.getContextPath()%>/images/534557.jpg" style="background-size:cover">
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
                
                <a href="<%=request.getContextPath() %>/front-end/member/memberlogin.jsp" style="<%= (memNO ==null)? "display:":"display:none"%>"> 
                     登入	
                </a>
                
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
<div class="container" style="background-color:rgba(210,210,210,0.6);">
<div class="row  justify-content-center align-items-center ">
	<div class="col-3"></div>
	<div class="col-8" >
		<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mem.do" name="form1" enctype="multipart/form-data" class=""  >
  <h3>資料修改:</h3>  
  	<div class="form-group row ">
    <label for="inputEmail3" class="col-sm-2 col-form-label" >會員編號:<font color=red><b>*</b></font></label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="<%=memVO.getMemNo()%>" disabled="true">
    </div>
  	</div>
  	<div class="form-group  row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">會員姓名:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="姓名"  name="memname" value="<%=memVO.getMemName()%>">
    </div>
    </div>
    <div class="form-group  row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">會員帳號:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="帳號"  name="account"	value="<%=memVO.getMemAccount()%>">
    </div>
    </div>
    
    <div class="form-group row  ">
    <label for="inputPassword3" class="col-sm-2 col-form-label">會員密碼:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="密碼"  name="password"	 value="<%=memVO.getMemPassword()%>">
    </div>
    </div>
    
    <div class="form-group row  ">
    <label for="inputPassword3" class="col-sm-2 col-form-label">信用卡號:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="信用卡號"  name="creditCardId"	 value="<%=memVO.getMemCreditCardId()%>"">
    </div>
    </div>
    
    <div class="form-group row ">
    <label for="inputPassword3" class="col-sm-2 col-form-label">手機號碼:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="手機號碼"  name="phone"	 value="<%=memVO.getMemPhone()%>">
    </div>
    </div>
    
    <div class="form-group row  ">
    <label for="inputPassword3" class="col-sm-2 col-form-label">會員信箱:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="會員信箱"  name="email"	 value="<%=memVO.getMemEmail()%>">
    </div>
    </div>
	
	<div class="form-group row  ">
    <label for="inputPassword3" class="col-sm-2 col-form-label">會員地址:</label>
    <div class="col-sm-10">
      <input type="TEXT" class="form-control col-sm-7" id="" placeholder="會員地址"  name="address"	 value="<%=memVO.getMemAddress()%>">
    </div>
    </div>
    
	<div class="form-group  row">
    <label for="inputPassword3" class="col-sm-2 col-form-label">會員照片:</label>
    <div class="col-sm-10">
      <img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memVO.memNo}" class="preview" height="300px" width="400px">
      <input type="file"    name="mempic"	 class="upl" accept="img/*">
    </div>
    </div>
    
<!--     <div class="form-group row "> -->
<!--     <label for="inputPassword3" class="col-sm-2 col-form-label">會員狀態:</label> -->
<!--     <div class="col-sm-10"> -->
<%--       <input type="TEXT" class="form-control col-sm-7" id="" placeholder="會員狀態"  name="status"	 value="<%=memVO.getMemStatus()%>"> --%>
<!--     </div> -->
<!--     </div> -->
<br>
<div class="form-group row ">
<label for="inputPassword3" class="col-sm-2 col-form-label"></label>
<div class="col-sm-10">
<input type="hidden" name="action" value="update">
<input type="hidden" name="memNO" value="<%=memVO.getMemNo()%>">
<input type="submit" value="送出修改" class="btn btn-primary form form-control col-sm-5">
</div>
</div>
</FORM>
	</div>
	<div class="col-1"></div>
</div>
</div>

					
<script type="text/javascript">
		$(function (){

    function format_float(num, pos)
    {
        var size = Math.pow(10, pos);
        return Math.round(num * size) / size;
    }

    function preview(input) {

        if (input.files && input.files[0]) {
            var reader = new FileReader();
            
            reader.onload = function (e) {
                $('.preview').attr('src', e.target.result);
                var KB = format_float(e.total / 1024, 2);
                // $('.size').text("檔案大小：" + KB + " KB");
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("body").on("change", ".upl", function (){
        preview(this);
    })
    
})

</script>


</body>
</html>
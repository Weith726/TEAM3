<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
	String memNO = (String) session.getAttribute("memNO");
	String memName = (String) session.getAttribute("memName");
%>
<!--                 	讓下面的style可以判斷有沒有登入      -->
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>萌寵家族</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css"
	integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.13.0/css/all.css"
	integrity="sha384-Bfad6CLCknfcloXFOyFnlgtENryhrpZCe29RTifKEixXQZ38WheV+i/6YWSzkz3V"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Puppy/style2.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/Puppy/login2.css">
<!-- bootstrap、FontAwesome、googleFont -->
<style>
.panel {
	border: 2px solid #0078ae;
	border-radius: 5px;
	width: 30%;
}

.message-area {
	height: 35%;
	resize: none;
	box-sizing: border-box;
	overflow: auto;
	background-color: #ffffff;
	position: fixed;
	bottom: 25%;
}

.input-area {
	background: #0078ae;
	box-shadow: inset 0 0 10px #00568c;
	position: fixed;
	bottom: 15%;
}

.input-area input {
	margin: 0.5em 0em 0.5em 0.5em;
}

.text-field {
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
}

h1 {
	font-size: 1.5em;
	padding: 5px;
	margin: 5px;
}

#message {
	min-width: 50%;
	max-width: 60%;
}

.statusOutput {
	background: #0078ae;
	text-align: center;
	color: #ffffff;
	border: 1px solid grey;
	padding: 0.2em;
	box-shadow: 0 0 5px #000000;
	width: 30%;
	margin-top: -25%;
	margin-left: 60%;
}

#row {
	/* 	float:left; */
	width: 50%;
	position: absolute;
	top: 10%;
	left: 0%;
}

.column {
	float: right;
	/*   width: 50%; */
	/*   padding: 5%; */
	margin-bottom: 5px;
	background-color: #ffffff;
}

ul {
	list-style: none;
	margin: 0;
	padding: 0;
}

ul li {
	display: inline-block;
	clear: both;
	padding: 20px;
	border-radius: 30px;
	margin-bottom: 2px;
	font-family: Helvetica, Arial, sans-serif;
}

.friend {
	background: #eee;
	float: left;
}

.me {
	float: right;
	background: #0084ff;
	color: #fff;
}

.friend+.me {
	border-bottom-right-radius: 5px;
}

.me+.me {
	border-top-right-radius: 5px;
	border-bottom-right-radius: 5px;
}

.me:last-of-type {
	border-bottom-right-radius: 30px;
}
</style>
</head>

<body
	background="<%=request.getContextPath()%>/Puppy/front_end/images/534557.jpg"
	style="background-size: cover">
	<header>
		<nav class="navbar navbar-expand-lg navbar-light ">
			<a href="#" class="navbar-brand ml-3">Cute:)<span
				style="color: #00E8E8;">Family</span></a>
			<button class="navbar-toggler" type="button" data-toggle="collapse"
				data-target="#navbarMenu" aria-controls="navbarMenu"
				aria-expanded="false" aria-label="Toggle Navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse"></div>
			<div class="collapse navbar-collapse" id="navbarMenu">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active"><a href="index.html"
						class="nav-link">首頁</a></li>
					<li class="nav-item dropdown"><a class="nav-link " href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 會員專區 </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">會員登入</a>
							<form action="<%=request.getContextPath()%>/Puppy/mem.do"
								METHOD="post" style="margin-bottom: 0px;">
								<input type="submit" value="編輯會員資料" class="dropdown-item">
								<input type="hidden" name="memNO" value="${memNO}"> <input
									type="hidden" name="action" value="getOne_For_Update">
							</form>
							<a class="dropdown-item" href="#">管理您的寵物</a>
							<form METHOD="post"
								ACTION="<%=request.getContextPath()%>/Puppy/mli.do"
								style="margin-bottom: 0px;">
								<input type="submit" value="瀏覽最新通知" class="dropdown-item">
								<input type="hidden" name="mli" value="${memNO}"> <input
									type="hidden" name="action" value="getAll_For_Display">
							</form>
							<!--    先不用,以後可以清掉 <a class="dropdown-item" href="#">編輯會員資料</a> 
					  <a class="dropdown-item" href="#">瀏覽最新通知</a>
-->
						</div></li>
					<li class="nav-item dropdown"><a class="nav-link " href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 門診專區 </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">門診預約</a> <a
								class="dropdown-item" href="#">門診查詢</a> <a class="dropdown-item"
								href="#">瀏覽看診進度</a>
						</div></li>
					<li class="nav-item dropdown"><a class="nav-link " href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 寵物旅館 </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">瀏覽房型</a> <a
								class="dropdown-item" href="#">旅館預約</a> <a class="dropdown-item"
								href="#">旅館預約查詢</a>
						</div></li>
					<li class="nav-item dropdown"><a class="nav-link " href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 寵物商城 </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">購物車</a> <a
								class="dropdown-item" href="#">搜尋商品資訊</a> <a
								class="dropdown-item" href="#">瀏覽商品資訊</a>
						</div></li>
					<li class="nav-item dropdown"><a class="nav-link " href="#"
						id="navbarDropdownMenuLink" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> 領養專區 </a>
						<div class="dropdown-menu"
							aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">互動預約</a> <a
								class="dropdown-item" href="#">互動預約查詢</a> <a
								class="dropdown-item" href="#">瀏覽流浪動物</a> <a
								class="dropdown-item" href="#">領養流浪動物</a>
						</div></li>
				</ul>
				<div
					style="<%=(memNO == null) ? "visibility:hidden" : "visibility:"%>">
					<img alt=""
						src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memNO}"
						style="height: 50px"><%=memName%>您好~
				</div>
				<form class="form-inline my-2 my-lg-0">
					<button class="btn menu-right-btn border" type="submit" id="login"
						style="<%=(memNO == null) ? "display:" : "display:none"%>">
						登入</button>
				</form>
				<form class="form-inline my-2 my-lg-0" action="logout">
					<button class="btn menu-right-btn border" type="submit" id="logout"
						style="<%=(memNO != null) ? "display:" : "display:none"%>">
						登出</button>
				</form>
				<!-- <button class="btn menu-right-btn border" type="submit">
                    註冊
                </button> -->
			</div>
		</nav>
	</header>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

	<div class="wrapper container ">
		<div class="row justify-content-center align-items-center">
			<div class="col-6">
				<form class="login-form" action="javascript:void(0);">
					<h1>Login</h1>
					<div class="form-input-material">
						<input type="text" name="username" id="username" placeholder=" "
							autocomplete="off" class="form-control-material" required /> <label
							for="username">Username</label>
					</div>
					<div class="form-input-material">
						<input type="password" name="password" id="password"
							placeholder=" " autocomplete="off" class="form-control-material"
							required /> <label for="password">Password</label>
					</div>
					<button type="submit" class="btn btn-primary btn-ghost">Login</button>
				</form>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red; font-size: 20px">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red; font-size: 20px"">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.HotelOrder.model.*"%>
<%@ page import="com.MemberPet.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%
	String memNO = (String) session.getAttribute("memNO");
	String memName = (String) session.getAttribute("memName");

	MemberPetService memberPetSvc = new MemberPetService();
	List<MemberPetVO> list = memberPetSvc.getPetsFromThisMember(memNO);
	pageContext.setAttribute("list", list);
%>
<html>
<head>
<title>萌寵家族旅館</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">

<!-- bootstrap、FontAwesome、googleFont -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/Hotel/style.css">

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px;
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px;
}

.card-img-top {
	max-width: 348px;
	max-height: 231px;
}

.room {
	display: none;
}

.show {
	animation: showup 1s linear;
	display: inline-block;
}

.div_pic {
	width: 900px;
	height: 300px;
	margin: 60px auto;
}

.section-6 h1 {
	font-family: 'Noto Sans TC';
}

@keyframes showup {
			0% {
				opacity: 0;
			}

			50% {
				opacity: .5;
			}

			100% {
				opacity: 1;
			}
		}

</style>

</head>

<body>
	<header>
		<nav class="navbar navbar-expand-lg navbar-light ">
			<a href="<%=request.getContextPath()%>/front-end/frontEndIndex/index.jsp" class="navbar-brand ml-3">
				Cute:)
				<span style="color: #00E8E8;">Family</span>
			</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu" aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse"></div>
			<div class="collapse navbar-collapse" id="navbarMenu">
				<ul class="navbar-nav mr-auto">
					<li class="nav-item active">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelIndex.jsp" class="nav-link">首頁</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 會員專區 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
							<a class="dropdown-item" href="#">會員登入</a>
							<form action="<%=request.getContextPath()%>/Puppy/mem.do" METHOD="post" style="margin-bottom: 0px;">
								<input type="submit" value="編輯會員資料" class="dropdown-item">
								<input type="hidden" name="memNO" value="${memNO}">
								<input type="hidden" name="action" value="getOne_For_Update">
							</form>
							<a class="dropdown-item" href="#">管理您的寵物</a>
							<form METHOD="post" ACTION="<%=request.getContextPath()%>/Puppy/mli.do" style="margin-bottom: 0px;">
								<input type="submit" value="瀏覽最新通知" class="dropdown-item">
								<input type="hidden" name="mli" value="${memNO}">
								<input type="hidden" name="action" value="getAll_For_Display">
							</form>
						</div>
					</li>
					<li class="nav-item">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelIndex.jsp#aboutUs" class="nav-link">關於我們</a>
					</li>
					<li class="nav-item">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelIndex.jsp#petHotel" class="nav-link">寄宿服務</a>
					</li>
					<li class="nav-item">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelOrderInfo/hotelOrderInfo.jsp" class="nav-link">訂單查詢</a>
					</li>
					<li class="nav-item">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelIndex.jsp#contactUs" class="nav-link">聯絡我們</a>
					</li>

				</ul>
				<div style="<%=(memNO == null) ? "visibility:hidden" : "visibility:"%>">
					<img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memNO}" style="height: 50px" id="mempic">
					<%=memName%>您好~
				</div>
				<a href="#">
					<button class="btn menu-right-btn border" type="button" style="<%=(memNO == null) ? "display:" : "display:none"%>">註冊</button>
				</a>
				<a href="<%=request.getContextPath()%>/front-end/member/login.jsp">
					<button class="btn menu-right-btn border" type="submit" id="login" style="<%=(memNO == null) ? "display:" : "display:none"%>">登入</button>
				</a>
				<form class="form-inline my-2 my-lg-0" action="<%=request.getContextPath()%>/Puppy/logout.do">
					<button class="btn menu-right-btn border" type="submit" id="logout" style="<%=(memNO != null) ? "display:" : "display:none"%>">登出</button>
				</form>
			</div>
		</nav>
	</header>

	<main>
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="d-flex justify-content-center">
						<img src="<%=request.getContextPath()%>/front-end/Hotel/images/thumb-1920-938573 (1).jpg" alt="">
					</div>
				</div>
			</div>
		</div>
	</main>

	<a name="aboutUs">
		<main class="section-1">
			<div class="container">
				<div class="row">
					<div class="col-12">
						<div class="text-center" id="intro">
							<h1>- 關於萌寵家族旅館 -</h1>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-6">
						<div class="d-flex justify-content-end">
							<img src="<%=request.getContextPath()%>/front-end/Hotel/images/06.png" alt="">
						</div>
					</div>
					<div class="col-4" id="p1">
						<p>這裡就是一間旅店，一間在目前台灣少見的以毛小孩視角出發的寵物旅館。 有別於傳統的寵物店，是將寄宿的毛小孩，像是被處罰一樣被限制在陌生的空間裡。 在萌寵家族旅館。規劃有毛小孩的互動專區，及寬敞開放式不關籠的住宿空間。並且注重通風、採光。讓毛小孩們可以有健康的休息環境。 毛爸毛媽們可以安心地將寶貝們，安親在這裡。 一起享受快樂的外出之旅。</p>
					</div>
				</div>
			</div>
		</main>
		<a name="petHotel">
			<main class="section-2">
				<div class="container">
					<div class="row">
						<div class="col-12">
							<div class="text-center">
								<h1>- 寄宿服務 -</h1>
							</div>
						</div>
					</div>

					<jsp:useBean id="hotelRoomTypeSvc" scope="page" class="com.HotelRoomType.model.HotelRoomTypeService" />

					<div class="row mt-3">
						<c:forEach var="hotelRoomTypeVO" items="${hotelRoomTypeSvc.all}" varStatus="s">
							<c:if test="${hotelRoomTypeVO.roomTypeStatus == 0}">
								<div class="col-4">
									<div class="card-deck">
										<div class="card">
											<img class="card-img-top" src="<%=request.getContextPath()%>/back-end/HotelRoomType/HotelRoomPic.do?roomTypeNo=${hotelRoomTypeVO.roomTypeNo}" alt="Card image cap">
											<div class="card-body">
												<h5 class="card-title">${hotelRoomTypeVO.roomTypeName}</h5>
												<p class="card-text">${hotelRoomTypeVO.roomTypeDescribe}</p>
											</div>
											<div class="card-footer">
												<div class="d-flex justify-content-center">
													<button value="${hotelRoomTypeVO.roomTypeNo}" class="btn btn-success">住房趣</button>
												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
						<jsp:useBean id="hotelRoomSvc" scope="page" class="com.HotelRoom.model.HotelRoomService" />
						<div class="container-fluid section-4">
							<div class="row d-flex justify-content-center">
								<div class="col-12 text-center">
									<c:forEach var="HotelRoomVO" items="${hotelRoomSvc.all}">
										<c:if test="${HotelRoomVO.petNo == null}">
											<button class="${HotelRoomVO.roomTypeNo} room empty btn  btn-light mb-5" data-toggle="modal" data-target="#exampleModal${HotelRoomVO.roomTypeNo}" data-whatever="@mdo" value="${HotelRoomVO.roomNo}" style="width: 150px; height: 150px; font-size: 30px;" onclick="checkMemNO()">${HotelRoomVO.roomNo}</button>
										</c:if>
										<c:if test="${HotelRoomVO.petNo != null}">
											<button disabled class="${HotelRoomVO.roomTypeNo} room empty btn btn-secondary mb-5" data-toggle="modal" data-target="#exampleModal${HotelRoomVO.roomTypeNo}" data-whatever="@mdo" value="${HotelRoomVO.roomNo}" style="width: 150px; height: 150px; font-size: 30px;">${HotelRoomVO.roomNo}</button>
										</c:if>
									</c:forEach>
									<c:forEach var="hotelRoomTypeVO" items="${hotelRoomTypeSvc.all}" varStatus="s">
										<div class="modal fade" id="exampleModal${hotelRoomTypeVO.roomTypeNo}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div class="modal-dialog" role="document">
												<div class="modal-content">
													<div class="modal-header">
														<h5 class="modal-title" id="exampleModalLabel">
															<b>- 快速訂房 -</b>
														</h5>
														<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															<span aria-hidden="true">&times;</span>
														</button>
													</div>
													<div class="modal-body text-left">
														<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" name="form1">
															<div class="form-group">
																<input type="hidden" class="form-control" name="orderNo" value="1">
																<!-- 訂單編號 -->
															</div>
															<div class="form-group">
																<label class="col-form-label">會員姓名:</label>
																<input type="text" class="form-control" value="${memName}" readonly unselectable="on">
																<input type="hidden" class="form-control" name="memNo" value="${memNO}">
																<input type="hidden" class="form-control" name="memName" value="${memName}">
																<input type="hidden" class="form-control" name="memEmail" value="${member.memEmail}">
															</div>
															<div class="form-group">
																<label class="col-form-label">寵物姓名:</label>
																<select size="1" name="petNo" class="form-control">
																	<c:forEach var="memberPetVO" items="${list}">
																		<option value="${memberPetVO.petNo}">${memberPetVO.petName}
																	</c:forEach>
																</select>
															</div>
															<div class="form-group">
																<label class="col-form-label">房型名稱:</label>
																<input type="text" class="form-control" value="${hotelRoomTypeVO.roomTypeName}" readonly unselectable="on">
																<input type="hidden" class="form-control" name="roomTypeNo" value="${hotelRoomTypeVO.roomTypeNo}">
																<!-- 房型編號 -->
															</div>
															<div class="form-group">
																<label class="col-form-label">房間編號:</label>
																<input type="text" class="form-control roomNo" name="roomNo" value="${HotelRoomVO.roomNo}" required>
															</div>
															<div class="form-group">
																<label class="col-form-label">房型價格:</label>
																<input type="text" class="form-control roomPrice" readonly unselectable="on" id="roomPrice${s.count}" name="roomTypePrice" value="${hotelRoomTypeVO.roomTypePrice}">
															</div>
															<div class="form-group">
																<label class="col-form-label">入住日期:</label>
																<input type="text" class="form-control Time1" id="start_dateTime${s.count}" name="checkInDate" value="${hotelOrderVO.checkInDate}" required>
															</div>
															<div class="form-group">
																<label class="col-form-label">退房日期:</label>
																<input type="text" class="form-control Time2" id="end_dateTime${s.count}" name="checkOutDate" required>
															</div>
															<div class="form-group">
																<label class="col-form-label">訂單總價:</label>
																<input type="text" class="form-control totalPrice" id="totalPrice${s.count}" name="totalPrice" readonly unselectable="on">
															</div>
															<div class="form-group">
																<!--訂單狀態 -->
																<input type="hidden" class="form-control" name="hotelOrderStatus" value="0">
															</div>
															<div class="modal-footer">
																<button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
																<input type="hidden" name="action" value="insert">
																<button type="submit" class="btn btn-primary">送出訂單</button>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
						</div>
					</div>
				</div>
			</main>


			<div class="section-6">
				<div class="container">
					<div class="row">
						<div class="col-12 text-center">
							<h1 class="text-center mt-5 mb-5">- 生活錦集 -</h1>
							<div class="div_pic">
								<img class="p p1" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker1.jpg">
								<img class="p p2" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker2.jpg">
								<img class="p p3" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker3.jpg">
								<img class="p p4" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker4.jpg">
								<img class="p p5" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker5.jpg">
								<img class="p p6" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker6.jpg">
								<img class="p p6" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker7.jpg">
								<img class="p p8" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker8.jpg">
								<img class="p p9" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker9.jpg">
								<img class="p p9" src="<%=request.getContextPath()%>/front-end/Hotel/images/flicker10.jpg">
							</div>
						</div>
					</div>
				</div>



				<a name="contactUs">
					<footer>
						<div class="section-5 text-center">
							<h4 style="margin-top: 5%;">最完善的寵物平台</h4>
							<h4 class="my-4">如果您有需要 請聯絡我們</h4>

							<div class="form-inline justify-content-center">
								<input type="text" name="Email" id="email" placeholder="Email" size="40" class="form-control px-4 py-2">
								<input type="button" value="Contact US" class="btn btn-danger px-4 py-2 ml-1">
							</div>
							<a href="">
								<button class="btn btn-outline-secondary" style="margin-top: 2%;">
									<h5>我要客訴</h5>
								</button>
							</a>
							<div class="social" style="margin: 5%;">
								<div class="d-flex flex-row justify-content-center">
									<i class="fab fa-facebook-f m-2"></i>
									<i class="fab fa-twitter m-2"></i>
									<i class="fab fa-instagram m-2"></i>
									<i class="fab fa-youtube m-2"></i>
								</div>
							</div>
							<hr>
							<h5 style="color: lightseagreen;">Cute Family &copy;</h5>
						</div>
					</footer>
					<!--BootStrap -->
					<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
					<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
					<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>


					<script>
						endTimeInMill = null;
						startTimeInMill = null;

						$('.Time1').blur(function() {
							startTime = $(this).val();
							console.log(startTime);

							startTimeInMill = Date.parse(startTime);
							console.log(startTimeInMill);

						});

						<c:forEach var="hotelRoomTypeVO" items="${hotelRoomTypeSvc.all}" varStatus="s">
						$('#end_dateTime${s.count}')
								.blur(
										function() {
											endTime = $(this).val();
											console.log(endTime);

											endTimeInMill = Date.parse(endTime);
											console.log(endTimeInMill);

											totalDays = Math
													.ceil((endTimeInMill - startTimeInMill)
															/ (24 * 60 * 60 * 1000));
											console.log(totalDays);

											roomPrice = $(
													'#roomPrice${s.count}')
													.val();
											console.log(roomPrice);

											totalPrice = roomPrice * totalDays;
											$('.totalPrice').val(totalPrice);

										});
						</c:forEach>
					</script>
					<script>
						$(function() {
							$(".btn").click(function() {
								// $(".empty").addClass("room");
								$(".empty").removeClass("show");
								var id = $(this).val();
								// $(`.` + id).removeClass("room");
								$(`.` + id).addClass("show");
							});
						});
					</script>
					<script>
						$(".empty").click(function() {
							roomNo = $(this).val();
							console.log(roomNo);
							$('.roomNo').val(roomNo);
						});
					</script>

					<script>
					//鎖定快速訂房按鈕(沒有登入就導到登入頁面)
					function checkMemNO(){
						var memid = '${memNO}';
						console.log(memid);
						if(memid==""){
							 location.href = "<%=request.getContextPath()%>/front-end/member/member/login.jsp"
					}
				}
			</script>
</body>

<!-- datetimepicker -->
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/Hotel/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/Hotel/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/Hotel/datetimepicker/jquery.datetimepicker.full.js"></script>
<script>
	$.datetimepicker.setLocale('zh'); // kr ko ja en
	<c:forEach var="hotelRoomTypeVO" items="${hotelRoomTypeSvc.all}" varStatus="s">
	$(function() {
		$('#start_dateTime${s.count}').datetimepicker(
				{
					format : 'Y-m-d H:i',
					onShow : function() {
						this.setOptions({
							maxDate : $('#end_dateTime${s.count}').val() ? $(
									'#end_dateTime${s.count}').val() : false
						})
					},
					timepicker : true,
					step : 60
				});

		$('#end_dateTime${s.count}').datetimepicker(
				{
					format : 'Y-m-d H:i',
					onShow : function() {
						this.setOptions({
							minDate : $('#start_dateTime${s.count}').val() ? $(
									'#start_dateTime${s.count}').val() : false
						})
					},
					timepicker : true,
					step : 60
				});
	});
	</c:forEach>
</script>

</html>
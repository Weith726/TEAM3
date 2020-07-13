<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<%
	String memNO = (String) session.getAttribute("memNO");
	String memName = (String) session.getAttribute("memName");
%>

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>萌寵家族</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.13.0/css/all.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/frontEndIndex/style.css">
<!-- bootstrap、FontAwesome、googleFont -->
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
						<a href="index.html" class="nav-link">首頁</a>
					</li>
					<li class="nav-item dropdown">
						<a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 會員專區 </a>
						<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
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
					<li class="nav-item dropdown">
						<a href="<%=request.getContextPath()%>/front-end/hospital/appt/select_page3.jsp" class="nav-link">門診專區</a>
					</li>
					<li class="nav-item dropdown">
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelIndex.jsp" class="nav-link">寵物旅館</a>
					</li>
					<li class="nav-item dropdown">
						<a href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp" class="nav-link">寵物商城</a>
					</li>
					<li class="nav-item dropdown">
						<a href="<%=request.getContextPath()%>/front-end/adopt/adoptedpets/listAllPets.jsp" class="nav-link">領養專區</a>
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
		<div class="container-fluid p-0">
			<div class="site-content">
				<div class="d-flex justify-content-end mr-5 mb-auto"></div>
			</div>
		</div>
		<div class="section-1">
			<div class="container text-center">
				<h1 class="heading-1">門診專區</h1>
				<br>
				<h1 class="heading-2">最優質的醫療團隊，讓您的愛寵倍感呵護~啾咪^.^</h1>
				<p class="para-1">本著誠信、謹慎、敬業的態度，提供愛犬與愛貓族先進專業的醫療服務，歡迎惠顧。</p>
				<div class="row justify-content-center">
					<div class="col-8 col-md-4">
						<div class="card">
							<img src="images/doctor1.jpg" alt="Image1" class="card-img-top" style="border-radius: 10px;">
							<div class="card-body">
								<h4 class="card-title">張國彬</h4>
								<p class="card-text">華人第一位犬貓血液透析治療專家，曾任台北市獸醫師公會理事長及中華民國獸醫師公會全國聯合會副理事長，現任世界小動物獸醫師會WSAVA代表及繼續教育講師，發起創立亞洲小動物獸醫師會FASAVA。</p>
								<a href="<%=request.getContextPath()%>/front-end/hospital/appt/select_page.jsp">
									<button class="btn btn-primary">我要預約</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-8 col-md-4">
						<div class="card">
							<img src="images/doctor2.jpg" alt="Image1" class="card-img-top" style="border-radius: 10px;">
							<div class="card-body">
								<h4 class="card-title">李美玲</h4>
								<p class="card-text">畢業於頻東科技大學碩士，特別對於貓、鼠、兔有豐富的經驗；尤其擅長免疫疾病、實驗診斷、急重加護等。曾任台北市獸醫師公會監事，現為台灣動物人道協會理事長，於專業及動物保護工作獲得推崇。</p>
								<a href="<%=request.getContextPath()%>/front-end/hospital/appt/select_page.jsp">
									<button class="btn btn-primary">我要預約</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-8 col-md-4">
						<div class="card">
							<img src="images/doctor4.jpg" alt="Image1" class="card-img-top" style="border-radius: 10px;">
							<div class="card-body">
								<h4 class="card-title">蔡旻烜</h4>
								<p class="card-text">畢業於中興大學獸醫所臨床組，並接受兩年小動物住院醫師完整訓練。長期以來對臨床診療充滿熱忱，細心專業，持續推出創新的治療模式，尤其擅長腫瘤科及超音波影像診斷。現任內科醫學會員。&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</p>
								<a href="<%=request.getContextPath()%>/front-end/hospital/appt/select_page.jsp">
									<button class="btn btn-primary">我要預約</button>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section-2">
			<div class="container-fluid">
				<div class="d-flex justify-content-end">
					<div class="d-flex flex-column m-4">
						<h1 class="heading-1">領養浪浪</h1>
						<p class="para">本平台的浪浪需要您的愛心將其領回~</p>
						<div class="text-center">
							<a href="<%=request.getContextPath()%>/front-end/adopt/adoptedpets/listAllPets.jsp">
								<button class="btn btn-info btn-lg">領養去</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="section-3">
			<div class="container">
				<h1 class="heading-1 text-center">熱門商品</h1>
				<br>
				<h1 class="heading-2 text-center">各式各樣的寵物精品~ &nbsp&nbsp&nbsp讓您購買不停歇&nbsp，&nbsp結帳不手軟喔~</h1>
				<p class="para-1 text-center">商城一般出貨天數為3-5日工作日(不包含六、日、國定假日)，商城無24小時出貨服務，敬請見諒。</p>
				<div class="row justify-content-center text-center">
					<div class="col-md-4">
						<div class="card">
							<img src="images/catPro6.jpg" alt="Image1" class="card-img-top">
							<div class="card-body">
								<h4 class="card-title">席夢思床墊</h4>
								<p class="card-text">一夜好眠，一覺到天亮</p>
								<a href="<%=request.getContextPath()%>/">
									<button class="btn btn-danger">前往商城</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<img src="images/catPro5.jpg" alt="Image1" class="card-img-top">
							<div class="card-body">
								<h4 class="card-title">貓咪豪宅</h4>
								<p class="card-text">下雨天不能沒有家，體驗富豪貓生</p>
								<a href="<%=request.getContextPath()%>/">
									<button class="btn btn-danger">前往商城</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="card">
							<img src="images/catPro3.jpg" alt="Image1" class="card-img-top">
							<div class="card-body">
								<h4 class="card-title">優炭貓砂</h4>
								<p class="card-text">一夜好眠，一覺到天亮</p>
								<a href="<%=request.getContextPath()%>/">
									<button class="btn btn-danger">前往商城</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="margin-top: 2%;">
						<div class="card">
							<img src="images/dogPro4.jpg" alt="Image1" class="card-img-top">
							<div class="card-body">
								<h4 class="card-title">牛排西沙</h4>
								<p class="card-text">◆營養完整均衡的西莎，乃特選最上等的新鮮材料，並保留原汁精華烹調而成，柔嫩可口，風味絕佳 ◆更多肉塊的西莎精緻軟罐系列產品，鮮嫩真肉，每一口都是極致享受，一餐一罐，新鮮又美味，是狗狗最愛的選擇</p>
								<a href="<%=request.getContextPath()%>/">
									<button class="btn btn-danger">前往商城</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="margin-top: 2%;">
						<div class="card">
							<img src="images/catFish.PNG" alt="Image1" class="card-img-top">
							<div class="card-body">
								<h4 class="card-title">貓草跳跳魚</h4>
								<p class="card-text">◆韓國超人氣熱銷跳跳魚 ◆超擬真尾巴擺動，隨機超現實尾部擺動模式 ◆觸碰就會跳動，放置不玩幾分鐘後，進入待機模式節省電量 ◆打開魚肚子，即可將貓薄荷放入內袋 ◆超逼真外觀可立即吸引貓的興趣</p>
								<a href="<%=request.getContextPath()%>/">
									<button class="btn btn-danger">前往商城</button>
								</a>
							</div>
						</div>
					</div>
					<div class="col-md-4" style="margin-top: 2%;">
						<div class="card">
							<img src="images/catAutoFeed.PNG" alt="Image1" class="card-img-top">
							<div class="card-body">
								<h4 class="card-title">自動餵食器+循環活水機</h4>
								<p class="card-text">◆(PETWANT 自動寵物餵食器 PF-102-TW) ◆4.3L飼料桶、飼料桶紅外線裝置提醒餵食狀況 ◆簡單操作餵食設定、10秒錄音功能 ◆可使用電源線、電池雙供電方式 ◆適用於直徑為0.5-1.5cm的乾性顆粒飼料</p>
								<a href="<%=request.getContextPath()%>/">
									<button class="btn btn-danger">前往商城</button>
								</a>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="section-4">
			<div class="container">
				<div class="row">
					<div class="col-md-7">
						<img src="images/roomType.jpg" alt="Images" width="590px" class="img-fluid" style="border-radius: 10px;">
					</div>
					<div class="col-md-5">
						<h1 class="text-secondary">寵&nbsp&nbsp物&nbsp&nbsp旅&nbsp&nbsp館</h1>
						<a href="<%=request.getContextPath()%>/front-end/Hotel/hotelIndex.jsp">
							<button class="btn btn-success btn-lg" style="font-size: 25px;">住房趣</button>
						</a>
						<p class="para-1">小班制的寵物旅館，採不關籠方式讓毛孩能自由活動，遊戲與休息時間的完美安排，讓您的毛孩在毛逗擁有平衡穩定的生活作息。24HR寵物保母陪伴，依照毛孩的個性給予適當照護，讓出遠門的您也能好放心！</p>
					</div>
				</div>
			</div>
		</div>
	</main>

	<footer>
		<div class="section-5 text-center">
			<h4 style="margin-top: 5%;">最完善的寵物平台</h4>
			<h4 class="my-4">如果您有需要 請聯絡我們</h4>
			<div class="form-inline justify-content-center">
				<input type="text" name="Email" id="email" placeholder="Email" size="40" class="form-control px-4 py-2">
				<input type="button" value="Contact US" class="btn btn-danger px-4 py-2 ml-1">
			</div>
			<a href="<%=request.getContextPath()%>/front-end/member/accusation/add_acc.jsp">
				<button class="btn btn-outline-secondary" style="margin-top: 2%;">
					<h5>我要客訴</h5>
				</button>
			</a>
			<div class="social" style="margin: 3%;">
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


	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</body>
</html>
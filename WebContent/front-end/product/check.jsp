<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/check.css">
	<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/style.css">
	<script src="<%= request.getContextPath()%>/front-end/product/address.js"></script>
</head>
<body id="mybody">
	<div class="container"> 
		<div class="row ">
			<div class="col-lg-9 mx-auto mt-2">
				<a href="<%= request.getContextPath()%>/front-end/product/shopindex.jsp" class="navbar-brand ml-3">Cute:)<span style="color:#3F9028;">Family-寵物商城</span></a>
			</div>
			<div class="col-lg-9 mx-auto mt-2">
				<table class="table ">
							<thead class="thead">
								<tr id="head">
									<th>商品名稱</th>
									<th>商品圖片</th>
									<th>數量</th>
									<th>單價</th>
									<th>小計</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="proVO" items="${shoppingcart}">		
									<tr class="carttr bg-light">
										<th>${proVO.productname}</th>
										<td><img src="<%=request.getContextPath()%>/product/getPic?proid=${proVO.productid}" alt=""></td>
										<th class="text-danger">${proVO.quantity}</th>
										<th class="text-danger">NT$${proVO.productprice}</th>
										<td class="price">NT$${proVO.productprice*proVO.quantity}</td>							
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<label><h4 id="total">總金額NT$${total}</h4></label>
			</div>
			<div class="col-lg-9 mx-auto mb-2">
				<div class="card  mx-auto bg-light">
					<div class="card-body bg-light">
						<div class="container">
							<form id="contact-form" role="form" method="POST" action="<%=request.getContextPath()%>/product/cart.do">
								<div class="controls">
									<div class="row">
										<div class="col-md-4">
											<div class="form-group"><label for="form_need">選擇縣市</label> 
											<select id="縣市1" class="form-control" name="country"></select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group"><label for="form_need">選擇市區</label> 
											<select id="鄉鎮市區1" class="form-control" name="city"></select>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group"><label for="form_need">地址</label> 
											<input type="text" class="form-control" placeholder="輸入地址" name="road">
											</div>
										</div>
										<div class="col-md-6">
											<div class="form-group"><label for="form_need">信用卡號</label>
												<input type="text" value=""  class="form-control" placeholder="輸入信用卡號" >
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label for="form_need">到期月</label>
												<select class="form-control" name="cardmonth">
													<option value="1">1</option>
													<option value="2">2</option>
													<option value="3">3</option>
													<option value="4">4</option>
													<option value="5">5</option>
													<option value="6">6</option>
													<option value="7">7</option>
													<option value="8">8</option>
													<option value="9">9</option>
													<option value="10">10</option>
													<option value="11">11</option>
													<option value="12">12</option>
												</select>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group">
												<label for="form_need" class="d-flex align-content-lg-center">到期年</label>
													<select class="form-control" name="cardyear">
													<option value="2021">2021</option>
													<option value="2022">2022</option>
													<option value="2023">2023</option>
													<option value="2024">2024</option>
													<option value="2025">2025</option>
													<option value="2026">2026</option>
												</select>
											</div>
										</div>
										<div class="col-md-2">
											<div class="form-group"><label for="form_need">檢核碼</label>
												<input type="text" name="" class="form-control" maxlength="3">
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-md-3"><button type="button" class="btn btn-info btn-send  btn-block ">同會員資料</button></div>
										<div class="col-md-9">						
											<input type="submit" class="btn btn-success btn-send  btn-block " value="送出訂單">
											<input type="hidden" class="btn btn-success btn-send  btn-block " value="check" name="action">
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div> <!-- /.8 -->
			</div> <!-- /.row-->
		</div>
	</div>
	<script type="text/javascript">
		var MyPoint = "/socketformember/${memNO}";
		var host = window.location.host;
		var path = window.location.pathname;
		var webCtx = path.substring(0, path.indexOf('/', 1));
		var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
		var websocket = null;
		
		if("WebSocket" in window){
		websocket = new WebSocket(endPointURL);
		}
		websocket.onmessage = function(e){
			swal(e.data);
		}
	   window.onload = function () {
	       //當頁面載完之後，用AddressSeleclList.Initialize()，
	       //傳入要綁定的縣市下拉選單ID及鄉鎮市區下拉選單ID
	       AddressSeleclList.Initialize('縣市1', '鄉鎮市區1');
	       //後面四個參數分別是兩個下拉選單的預設文字跟值
	      AddressSeleclList.Initialize('縣市2', '鄉鎮市區2', 'Please Select City', '0', 'Please Select Area', '0');
	  }
	  function show() {
	       //取出指定縣市及鄉鎮市區的下拉選單的值
	       alert(AddressSeleclList.ReturnSelectAddress('縣市1', '鄉鎮市區1'));
	   }
	</script>
</body>
</html>
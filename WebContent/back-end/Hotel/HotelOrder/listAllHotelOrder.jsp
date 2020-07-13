<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.HotelOrder.model.*"%>

<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	HotelOrderService hotelOrderSvc = new HotelOrderService();
	List<HotelOrderVO> list = hotelOrderSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
#section-1 {
	position: relative;
	top: -10px;
}

#table td, #table th {
    padding: .65rem; 
    vertical-align: middle;
    border-top: 1px solid #dee2e6;
}
</style>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
</head>
<body>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>
	<div class="container-fluid" id="section-1">
		<div class="row">
			<div class="col-12">
				<h1 class="topTitle text-center">
					<span class="badge badge-warning">- 旅館訂單管理 -(全部資料)</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelOrder/addHotelOrder.jsp" role="button">新增訂單</a>
					</div>
				</h1>
			</div>
		</div>
		<div class="row">
			<div class="col-4"></div>
			<div class="col-4">
				<!-- 錯誤表列 -->
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>

		<div class="row">
			<div class="col-12">
				<table class="table table-striped text-center" id="table">
					<thead>
						<tr>
							<th scope="col">訂單編號</th>
							<th scope="col">會員編號</th>
							<th scope="col">寵物編號</th>
							<th scope="col">房型編號</th>
							<th scope="col">房間編號</th>
							<th scope="col">房型價格</th>
							<th scope="col">訂單時間</th>
							<th scope="col">入住時間</th>
							<th scope="col">退房時間</th>
							<th scope="col">訂單總價</th>
							<th scope="col">訂單狀態</th>
							<th scope="col">訂單修改</th>
							<th scope="col">訂單驗證</th>
							<th scope="col">訂單取消</th>
						</tr>
					</thead>
					<tbody>
						<%@ include file="page1.file"%>
						<c:forEach var="hotelOrderVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${hotelOrderVO.orderNo}</td>
								<td>${hotelOrderVO.memNo}</td>
								<td>${hotelOrderVO.petNo}</td>
								<td>${hotelOrderVO.roomTypeNo}</td>
								<td>${hotelOrderVO.roomNo}</td>
								<td>${hotelOrderVO.roomTypePrice}</td>
								<td>
									<fmt:formatDate value="${hotelOrderVO.orderDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:formatDate value="${hotelOrderVO.checkInDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>
									<fmt:formatDate value="${hotelOrderVO.checkOutDate}" pattern="yyyy-MM-dd HH:mm:ss" />
								</td>
								<td>${hotelOrderVO.totalPrice}</td>
								<td>
									<c:if test="${hotelOrderVO.hotelOrderStatus == 0}">
										<c:out value="待確認"></c:out>
									</c:if>
									<c:if test="${hotelOrderVO.hotelOrderStatus == 1}">
										<c:out value="已完成"></c:out>
									</c:if>
									<c:if test="${hotelOrderVO.hotelOrderStatus == 2}">
										<c:out value="已取消"></c:out>
									</c:if>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" style="margin-bottom: 0px;">
										<button type="submit" class="btn btn-primary">修改</button>
										<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" style="margin-bottom: 0px;">
										<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
										<input type="hidden" name="hotelOrderStatus" value="1">
										<input type="hidden" name="action" value="confirm">
										<button type="submit" class="btn btn-info">驗證</button>								
									</FORM>
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do" style="margin-bottom: 0px;">
										<input type="hidden" name="orderNo" value="${hotelOrderVO.orderNo}">
										<input type="hidden" name="action" value="cancel">
										<button type="submit" class="btn btn-secondary">取消</button>
									</FORM>
								</td>
							</tr>
						</c:forEach>
					</tbody>

				</table>
				<div class="d-flex justify-content-end">
				<%@ include file="page2.file"%>
				</div>
			</div>
			<jsp:useBean id="hotelOrderSvc2" scope="page" class="com.HotelOrder.model.HotelOrderService" />
			<div class="col-3">
				<form METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelOrder/HotelOrder.do">
					<div class="form-group font-weight-bold">
						<label>- 查詢單筆訂單資料 -</label>
						<select class="form-control" id="exampleFormControlSelect1" name="orderNo">
							<c:forEach var="hotelOrderVO" items="${hotelOrderSvc2.all}">
								<option value="${hotelOrderVO.orderNo}">${hotelOrderVO.orderNo}
							</c:forEach>
						</select>
						<input type="hidden" name="action" value="getOne_For_Display">
						<div class="d-flex justify-content-center">
							<button type="submit" class="btn btn-primary">送出</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<c:if test="${not empty success}">
		<script>
			swal("新增成功", "", "success");
		</script>
	</c:if>
	<c:if test="${not empty fail}">
		<script>
			swal("刪除成功", "", "success");
		</script>
	</c:if>
	<c:if test="${not empty statusChangeOK}">
		<script>
			swal("驗證成功", "", "success");
		</script>
	</c:if>
	<c:if test="${not empty cancelOK}">
		<script>
			swal("訂單取消成功", "", "success");
		</script>
	</c:if>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
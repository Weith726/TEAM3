<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<%@ page import="com.HotelRoomType.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
	HotelRoomTypeService hotelRoomTypeSvc = new HotelRoomTypeService();
	List<HotelRoomTypeVO> list = hotelRoomTypeSvc.getAll();
	pageContext.setAttribute("list", list);
%>


<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style>
#section-1 {
	position: relative;
	top: -10px;
}

.table tr {
	vertical-align: middle;
	font-size: 14px;
}

.table tr td {
	vertical-align: middle;
	font-size: 14px;
}

#roomTypeDescribe{
	width:300px;
}

.images {
	width: 270px;
	height: 180px;
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
					<span class="badge badge-warning">- 房型管理 -(全部資料)</span>
					<div class="d-flex justify-content-end">
						<a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/Hotel/HotelRoomType/addHotelRoomType.jsp" role="button">新增房型</a>
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
				<table class="table table-striped text-center">
					<thead>
						<tr>
							<th scope="col">房型編號</th>
							<th scope="col">房型名稱</th>
							<th scope="col">房型簡介</th>
							<th scope="col">房型空間</th>
							<th scope="col">房型價格</th>
							<th scope="col">房型服務</th>
							<th scope="col">房型狀態</th>
							<th scope="col">房型圖片</th>
							<th scope="col">房型修改</th>
						</tr>
					</thead>
					<tbody>
						<%@ include file="page1.file"%>
						<c:forEach var="hotelRoomTypeVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr>
								<td>${hotelRoomTypeVO.roomTypeNo}</td>
								<td>${hotelRoomTypeVO.roomTypeName}</td>
								<td id="roomTypeDescribe">${hotelRoomTypeVO.roomTypeDescribe}</td>
								<td>${hotelRoomTypeVO.roomTypeSpace}</td>
								<td>${hotelRoomTypeVO.roomTypePrice}</td>
								<td>${hotelRoomTypeVO.roomTypeService}</td>
								<td>
									<c:if test="${hotelRoomTypeVO.roomTypeStatus == 0}">
										<c:out value="已存在"></c:out>
									</c:if>
									<c:if test="${hotelRoomTypeVO.roomTypeStatus == 1}">
										<c:out value="已移除"></c:out>
									</c:if>
								</td>
								<td>
									<img alt="房型圖片" class="images" src="<%=request.getContextPath()%>/back-end/HotelRoomType/HotelRoomPic.do?roomTypeNo=${hotelRoomTypeVO.roomTypeNo}">
								</td>
								<td>
									<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/HotelRoomType/HotelRoomType.do" style="margin-bottom: 0px;">
										<button type="submit" class="btn btn-primary">修改</button>
										<input type="hidden" name="roomTypeNo" value="${hotelRoomTypeVO.roomTypeNo}">
										<input type="hidden" name="action" value="getOne_For_Update">
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
		</div>
	</div>

	<!-- sweatAlert -->
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

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>
</html>
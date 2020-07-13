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
	<%@include file="/back-end/backEndInclude/head.jsp" %>
	<style type="text/css">
table tr {
	color: black;
	line-height: 30px;
	text-align: center;
}
select:disabled {
	color:red;
	font-family:bold;
	font-size:25px;
	
}
.check{
	display:none;
}
#total{
	color:red;
	font-size:20px;
	font-weight:bold;
}
#memno, #date, #address{
	color:#07060B;
	font-size:20px;
	font-weight:bold;
}
#id{
	color:red;
	font-size:20px;
	font-weight:bold;
}
.proimg{
	height:40px;
	width:40px;
}
.isDisabled {
  pointer-events: none;
}
#notice{
	display:none;
	background-color:#F5F6F7;
	width:270px;
	overflow-y:scoll;
	word-break:break-all;
  	padding: 10px;
 	border-radius: 25px;
}
#historymsg{
	font-weight:bold;
	z-index:2; 
	position: fixed; 
	top:155px; 
	color:#22153D;
	right:30px;
	margin-left:60px;
}
.newmsg{
	padding:10px;
 	border-radius: 25px;
}
#mybody{
	background-color:#E5E8E9;
}
#mytr{
	background-color:#6276B9;
}
.mycontainer{
	top:0px;
}
</style>
</head>
<jsp:useBean id="pordersvc" scope="page" class="com.productorder.model.PorderService" />
<jsp:useBean id="memsvc" scope="page" class="com.mem.model.MemberService" />
<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService" />


<body id="mybody">
			<%@include file="/back-end/backEndInclude/header.jsp" %>	
		<div class="modal fade" id="mydetail" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content" id="detail">
				<div class="modal-header" id="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">�q�����</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="modal-body">
					<table class="table table-striped">
						<thead class="thead-dark">
							<tr>
								<th>�q��s��</th>
								<th>�ӫ~�W�n</th>
								<th>�ӫ~�Ϥ�</th>
								<th>�ƶq</th>
								<th>�ӫ~���</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach var="detailvo" items="${detail}">
									<tr>
										<td>${detailvo.orderid}</td>
										<td>${prosvc.getOneproduct(detailvo.proid).productname}</td>
										<td><img class="proimg" src="<%=request.getContextPath()%>/product/getPic?proid=${detailvo.proid}"></td>
										<td>${detailvo.quantity}</td>
										<td>NT$${detailvo.unitprice}</td>
									</tr>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="modal-footer" id="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Close</button>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		<c:if test="${not empty detail}">
			$("#mydetail").modal({show: true});
		</c:if>
	</script>
	
	
	<div class="container-fluid mycontainer">
		<div class="row ml-3 mr-3">
			<div class="col-12 d-flex justify-content-end mt-2 mb-2"><button class="btn btn-info mr-1" id="noticebtn">�t�γq��</button></div>	
			<div class="col-12 d-flex justify-content-end mt-2" id="historymsg">
				<div id="notice">
					<ol class="mb-1 list-group list-group-flush">
						<li id="myol" class="mt-1"><h3 class="text-dark ml-3 font-weight-bold">�q��</h3></li>
					</ol>
				</div>
			</div>
			<div class="col-12 mb-2 d-flex justify-content-center" style="z-index:1; ">
				<a class="isDisabled btn btn-danger mr-1 w-25 " href="<%=request.getContextPath()%>/back-end/productorder/checking.jsp">�f�֤�</a>
				<a class="btn btn-secondary mr-1 w-25" href="<%=request.getContextPath()%>/back-end/productorder/notfinish.jsp">������</a>
				<a class="btn btn-secondary mr-1 w-25" href="<%=request.getContextPath()%>/back-end/productorder/finish.jsp">�w����</a>
				<a class="btn btn-secondary mr-1 w-25" href="<%=request.getContextPath()%>/back-end/productorder/cancel.jsp">�w����</a>
			</div>
			<div class="col-12 table-responsive" style="z-index:1;">
				<table class="table table-hover" id="mytable">
					<thead class="">
						<tr id="mytr">
							<th>�q��s��</th>
							<th>�|���s��</th>
							<th>�q����</th>
							<th>�q���`��</th>
							<th>�t�e�a�}</th>
							<th>�q�檬�A</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="pordervo" items="${pordersvc.all}">
							<c:if test="${pordervo.orderstatus==4}">
								<tr id="show${pordervo.orderid}">
								<td id="id">${pordervo.orderid}</td>
								<td id="memno">${pordervo.memno}-${memsvc.getOneEmp(pordervo.memno).memName}</td>
								<td id="date">${pordervo.orderdate}</td>
								<td id="total">NT$${pordervo.ordertotal}</td>
								<td id="address">${pordervo.deliveryaddress}</td>
								<td><select disabled class="select" name="orderstatus"
									id="orderstatus">
										<option value="0" ${(pordervo.orderstatus==0)? 'selected':''}>���X�f</option>
										<option value="1" ${(pordervo.orderstatus==1)? 'selected':''}>�w�X�f</option>
										<option value="2" ${(pordervo.orderstatus==2)? 'selected':''}>�w����</option>
										<option value="3" ${(pordervo.orderstatus==3)? 'selected':''}>�w����</option>
										<option value="4" ${(pordervo.orderstatus==4)? 'selected':''}>�f�֤�</option>
								</select>
									<button class="btn btn-info update" type="button">�ק�</button> <input
									id="orderid" type="hidden" name="orderid"
									value="${pordervo.orderid}"> <input type="hidden"
									name="action" value="updateorder">
									<button class="btn btn-danger check">�T�w�ק�</button>
									<form  method="POST" action="<%=request.getContextPath()%>/order/porder.do" class="d-inline">
									<input type="hidden" name="action" value="getdetail">
									<input type="hidden" name="URI" value="<%=request.getServletPath()%>">
									<input type="hidden" name="orderid" value="${pordervo.orderid}">
									<input type="submit" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" value="�d��">
									</form>
									</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>		
		</div>
	</div>
	<%@include file="/back-end/backEndInclude/footer.jsp" %>
		<script>
		$(document).ready(function(){
			$("#noticebtn").click(function(){
				$(".newmsg").remove();
				$.ajax({
					url:"<%=request.getContextPath()%>/order/porder.do",
					type:"POST",
					data:{action:"getcancelmsg"},
					dataType:"json",
					success:function(data){
						for(var key in data){
							 $("#myol").append(`<li class='mt-2 mb-2 list-group-item newmsg'>�q��s��:<strong class='text-danger d-inline'>`+key+`</strong><br>`+data[key]+`</li>`);
						}
					}
				});				
			});	
			var MyPoint = "/socket";
			var MyPoint2 = "/cancelsocket";
			var host = window.location.host;
			var path = window.location.pathname;
			var webCtx = path.substring(0, path.indexOf('/', 1));
			var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
			var endPointURL2 = "ws://" + window.location.host + webCtx + MyPoint2;
			var websocket = null;
			var websocket2 = null;
			if("WebSocket" in window){
			websocket = new WebSocket(endPointURL);
			websocket2=new WebSocket(endPointURL2);
			}
			websocket.onmessage = function(e){
				swal("�s�W�@���q��:�s��-"+e.data).then((value) =>{
					  location.reload();
				});			
			}
			websocket2.onmessage = function(e){
				swal("�q��q��:�s��-"+e.data).then((value) =>{
					  location.reload();
				});
			}
			$("#noticebtn").click(function(){
				$("#notice").fadeToggle();
			});
			$("#reload").click(function(){
				location.reload();
			})
			$(".update").on('click', function() {
				$(this).hide();
				$(this).closest("td").find(".check").show();
				$(this).closest("td").find(".select").removeAttr("disabled");
			});
			$(".check").click(function(){
				$(this).hide();
				$(this).closest("td").find(".update").show();
				$(this).closest("td").find(".select").attr("disabled",true);
				$.ajax({
					url:"<%=request.getContextPath()%>/order/porder.do",
					type:"POST",
					data:{ action:"updateorder" , orderid: $(this).closest("td").find("#orderid").val() ,orderstatus: $(this).closest("td").find("#orderstatus").val() },
					success:function(data){
						swal(data, "���\��s1�����~", "success").then((value) => {
							  location.reload();
						});
						
					}
				})
			});
		});
	</script>
</body>
</html>
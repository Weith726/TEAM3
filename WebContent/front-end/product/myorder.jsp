<%@page import="com.promotionDetail.model.PromoDetailService"%>
<%@page import="com.productorder.model.PorderVO"%>
<%@page import="com.productorder.model.PorderService"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/style.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/product/myorder.css">
<link rel="stylesheet" href="<%= request.getContextPath()%>/front-end/product/shopindex.css">
</head>
<body id="mybody">
	<%
		String memno = (String) session.getAttribute("memNO");
		PorderService svc = new PorderService();
		List<PorderVO> list = svc.getbymemno(memno);
		request.setAttribute("orderlist", list);
	%>
	
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">�����q��</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	        <form action="<%=request.getContextPath()%>/order/porder.do" method="POST" >
	          <div class="form-group">
	            <label for="message-text" class="col-form-label">������]</label>
	            <textarea class="form-control" id="message-text" placeholder="�п�J��]" name="textarea"></textarea>
	          </div>
	          <input disabled type="submit" value="�e�X" class="btn btn-primary w-100" id="cancelbtn">
	          <input type="hidden" value="cancelorder" name="action">
	          <input type="hidden" name="orderid" id="modalorderid" value="">
	          <input type="hidden" name="url"  value="<%=request.getServletPath()%>">
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<c:if test="${empty orderlist}">
		<div class="container-fluid">
				<div class="row ml-3 mr-3 mt-2">
					<div class="col-12">
						<div class="jumbotron" id="myjumbotron">
						  <h1 class="display-4">�z�S������q��!!</h1>
						  <hr class="my-4">
						  <p class="lead">
						    <a role="button" class="btn btn-primary btn-lg" href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp">��^�ʪ�</a>
						  </p>
						</div>
					</div>	
				</div>
			</div>
	</c:if>
	<c:if test="${not empty orderlist}">
	  <header>
    <nav class="navbar navbar-expand-lg navbar-light ">
      <a href="#" class="navbar-brand ml-3">Cute:)<span style="color:#7CE3A6;">Family</span></a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarMenu"
      aria-controls="navbarMenu" aria-expanded="false" aria-label="Toggle Navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse"></div>
    <div class="collapse navbar-collapse" id="navbarMenu">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a href="index.html" class="nav-link">����</a>
        </li>
                    <!-- <li class="nav-item dropdown">
                        <a href="#" class="nav-link">�|���M��</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">���E�M��</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">�d�����]</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">�d���ӫ�</a>
                    </li>
                    <li class="nav-item dropdown">
                        <a href="#" class="nav-link">��i�M��</a>
                      </li> -->
                      <li class="nav-item dropdown">
                        <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                        aria-haspopup="true" aria-expanded="false">
                        �|���M��
                      </a>
                      <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                        <a class="dropdown-item" href="#">�|���n�J</a>
                        <a class="dropdown-item" href="#">�s��|�����</a>
                        <a class="dropdown-item" href="#">�޲z�z���d��</a>
                        <a class="dropdown-item" href="#">�s���̷s�q��</a>
                      </div>
                    </li>
                    <li class="nav-item dropdown">
                      <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                      aria-haspopup="true" aria-expanded="false">
                      ���E�M��
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                      <a class="dropdown-item" href="#">���E�w��</a>
                      <a class="dropdown-item" href="#">���E�d��</a>
                      <a class="dropdown-item" href="#">�s���ݶE�i��</a>
                    </div>
                  </li>
                  <li class="nav-item dropdown">
                    <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                    aria-haspopup="true" aria-expanded="false">
                    �d�����]
                  </a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="#">�s���Ы�</a>
                    <a class="dropdown-item" href="#">���]�w��</a>
                    <a class="dropdown-item" href="#">���]�w���d��</a>
                  </div>
                </li>
                <li class="nav-item dropdown">
                  <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">
                  �d���ӫ�
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                  <a class="dropdown-item" href="#">�ʪ���</a>
                  <a class="dropdown-item" href="#">�j�M�ӫ~��T</a>
                  <a class="dropdown-item" href="#">�s���ӫ~��T</a>
                </div>
              </li>
              <li class="nav-item dropdown">
                <a class="nav-link " href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                ��i�M��
              </a>
              <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <a class="dropdown-item" href="#">���ʹw��</a>
                <a class="dropdown-item" href="#">���ʹw���d��</a>
                <a class="dropdown-item" href="#">�s���y���ʪ�</a>
                <a class="dropdown-item" href="#">��i�y���ʪ�</a>
              </div>
            </li>
          </ul>
          <form class="form-inline my-2 my-lg-0">
            <button class="btn menu-right-btn border" type="submit">
              �n�J
            </button>
          </form>
                <!-- <button class="btn menu-right-btn border" type="submit">
                    ���U
                  </button> -->
                </div>
              </nav>
            </header>
			
	<div class="container mb-0">
		<div class="row ml-3 mr-3 mt-2" id="mybody">
			<div class="col-12 mt-1">
				   <div class="nav-scroller py-1 mb-2">
                <nav class="nav d-flex justify-content-end mynavbar">
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/shopindex.jsp">�ӫ�����</a> 
				<a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/dogindex.jsp">�����M��</a>
                  <a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/catindex.jsp">�߫}�M��</a>
                  <a class="p-2  ml-2" href="<%=request.getContextPath()%>/front-end/product/anotherindex.jsp">��L�M��</a>
                  <a class="p-2  ml-2 dropdown" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown"
                  aria-haspopup="true" aria-expanded="false">�ӤH���</a>
                  <a class="p-2  ml-2 text-danger" href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp">�ʪ���</a>
                  <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/product/myorder.jsp">�d�ݾ��v�q��</a>
                    <a class="dropdown-item" href="<%=request.getContextPath()%>/front-end/product/shopcart.jsp">�ڪ��ʪ���</a>
                    <a class="dropdown-item" href="#">�s���̷s�q��</a>
                  </div>
                </nav>
              </div>
				<c:forEach var="orderVO" items="${orderlist}">
					<table class="table">
						<thead class="thead">
							<tr class="ordertr">
								<th colspan="5">�ڪ��q��</th>
							</tr>
						</thead>
						<tbody>
							<tr class="carttr table-Default">
								<th>${orderVO.orderid}</th>
								<th>�q����:${orderVO.orderdate}</th>
								<th>�q���`�B:NT$${orderVO.ordertotal}</th>
								<th>�q�檬�A:${(orderVO.orderstatus==0)?'���X�f':(orderVO.orderstatus==1)?'�w�X�f':(orderVO.orderstatus==2)?'�w����':(orderVO.orderstatus==3)?'�w����':(orderVO.orderstatus==4)?'�f�֤�':''}</th>
								<th>
									<button class="btn btn-info showmodal" value="${orderVO.orderstatus}">�����q��</button>
									<input type="hidden" value="${orderVO.orderid}">
								</th>
							</tr>
						</tbody>
					</table>
					<div class="container mt-0 p-0">
						<div class="row mt-0">
							<div class="col-8 mb-3">
								<button class="btn btn-info border-radius getdetail">�d�ݩ���</button>
								<table class="table detail mt-2">
									<thead class="thead">
										<tr class="detailtr">
											<th>�ӫ~�W��</th>
											<th>�ӫ~�Ϥ�</th>
											<th>�ʶR�ƶq</th>
											<th>�ӫ~���</th>
										</tr>
									</thead>
									<tbody>	
										<jsp:useBean id="detailsvc" scope="page" class="com.porderDetail.model.PorderDetailService"></jsp:useBean> 
										<jsp:useBean id="prosvc" scope="page" class="com.product.model.ProService"></jsp:useBean> 
										<c:forEach var="detailVO" items="${detailsvc.getdetailbyid(orderVO.orderid)}">
											<tr class="carttr table-Default">
												<th>${prosvc.getOneproduct(detailVO.proid).productname}</th>
												<th><img class="pimg" src="<%=request.getContextPath()%>/product/getPic?proid=${detailVO.proid}"></th>
												<th>${detailVO.quantity}</th>
												<th>${detailVO.unitprice}</th>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	</c:if>
	
			<div class="toast" data-autohide="true" style="position:fixed; left: 10px; bottom: 20px; width:250px " data-delay="2000">
		    <div class="toast-header">
		      <svg class=" rounded mr-2" width="20" height="20" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img">
		                    <rect fill="#007aff" width="100%" height="100%" /></svg>
		      <strong class="mr-auto">�T���q��</strong>
		      <small class="text-muted">now</small>
		      <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
		                    <span aria-hidden="true">&times;</span>
		                  </button>
		    </div>
		    <div class="toast-body" style="font-size:15px; color:red;">
		    </div>
		  </div>
	
		<c:if test="${not empty memNO}">
			<script>
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
					$(".toast-body").text(e.data);
					$('.toast').toast('show');
				}
			</script>
		</c:if>
	<script>
		$(function(){
				$(".showmodal").click(function(){
					$("#modalorderid").val($(this).siblings("input").val());
					$("#message-text").val("");
					$("#myModal").modal({show: true});
					$("#cancelbtn").attr("disabled",true);
					$("#message-text").change(function() {
					 if($("#message-text").val().trim()!=""){
						$("#cancelbtn").removeAttr("disabled");
					}
				});		
			});
			$(".getdetail").click(function(){
				$(this).siblings(".detail").toggle("slow");
			});
		});
	</script>
</body>
</html>
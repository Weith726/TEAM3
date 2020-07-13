<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>
<link rel="stylesheet" href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">
<!DOCTYPE html>
<% 
	MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>
<% session.getAttribute("memNO");%>
<html>
<head>
<meta charset="UTF-8">
<%@ include file="/back-end/backEndInclude/head.jsp"%>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>


<div class="container">
<div class="row justify-content-center align-items-center">
<FORM METHOD="post" ACTION="mem.do" name="form1" enctype="multipart/form-data" class="">
<h3>資料修改:</h3>
<table class="table table-hover text-nowrap ">
	<tr>
		<td>會員編號:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMemNo()%></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input class="form form-control" type="TEXT" name="memname" size="45" value="<%=memVO.getMemName()%>" /></td>
	</tr>
	<tr>
		<td>員工帳號:</td>
		<td><input class="form form-control" type="TEXT" name="account" size="45"	value="<%=memVO.getMemAccount()%>" /></td>
	</tr>
	<tr>
		<td>員工密碼:</td>
		<td><input class="form form-control" type="TEXT" name="password" size="45"	value="<%=memVO.getMemPassword()%>" /></td>
	</tr>
	<tr>
		<td>信用卡號:</td>
		<td><input class="form form-control" type="TEXT" name="creditCardId" size="45"	value="<%=memVO.getMemCreditCardId()%>" /></td>
	</tr>
	<tr>
		<td>手機號碼:</td>
		<td><input class="form form-control" type="TEXT" name="phone" size="45" value="<%=memVO.getMemPhone()%>" /></td>
	</tr>

	<tr>
		<td>會員信箱:</td>
		<td><input class="form form-control" type="TEXT" name="email" size="45" value="<%=memVO.getMemEmail()%>" /></td>
	</tr>
	
	<tr>
		<td>會員地址:</td>
		<td><input class="form form-control" type="TEXT" name="address" size="45" value="<%=memVO.getMemAddress()%>" /></td>
	</tr>
	
	<tr>
		<td>會員照片:</td>
		<td>
		<img alt="" src="<%=request.getContextPath()%>/Puppy/pic.do?memNo=${memVO.memNo}" class="preview" height="200px" width="400px">
		<input type="file" name="mempic" size="45" class="upl" accept="img/*">
		</td>  
	</tr>
	<tr>
		<td>會員狀態:</td>
		<td>
			<select name="status" style="width:100%" class="form form-control">
			<option value=0 ${(memVO.memStatus==0)?'selected':'' }>未驗證
			<option value=1 ${(memVO.memStatus==1)?'selected':'' }>已驗證
			<option value=2 ${(memVO.memStatus==2)?'selected':'' }>已停權
			</select>
		</td>
	</tr>
	
</table>
<br>
<input type="hidden" name="action" value="update_B">
<input type="hidden" name="memNO" value="<%=memVO.getMemNo()%>">
<input type="submit" value="送出修改" class="btn btn-primary form form-control" ></FORM>
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
<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</html>
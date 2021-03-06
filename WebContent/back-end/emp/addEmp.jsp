<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.emp.model.*"%>

<%
	EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>


<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>

<%
	// 	String empGender = null;
	// 	try {
	// 		empGender = empVO.getEmpGender(); 
	// 	} catch (Exception e) {
	// 		empGender = "男"; //空值給男

	// 	}
%>

<style>

table {
	width:100%;
	margin-top: 5px;
	margin-bottom: 5px;
	border: 7px solid;
	border-color:rgb(100,100,100,0.2);
	
}

th {


	text-align: right;
	width:100px;
}

th, td {

	padding: 5px;
/* 	border: 1px solid black; */
	height:40px;
}

.mainTitle {
	letter-spacing: 8px;
	color: #42454C;
	font-weight: bold;
	font-size: 28px;
	padding-left: 20px;
}

.mainTitlehr {
	border: 2px solid lightcoral;
}
</style>

</head>


<body>

<%@ include file="/back-end/backEndInclude/header.jsp"%>

	<span class="mainTitle">員工資料新增</span>

	<a href="listAllEmp.jsp">返回員工資料</a>

	<hr class="mainTitlehr">


	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="emp.do" name="form1" id="form1"
		enctype="multipart/form-data">
		<table class="table table-striped">
			

<!-- 密碼不該讓管理者輸入 -->
<!-- 			<tr> -->
<!-- 				<th>密碼</th> -->
<!-- 				<td><input type="password" name="empPwd" size="20" -->
<%-- 					value="<%=(empVO == null) ? "" : empVO.getEmpPwd()%>" /></td> --%>
<!-- 			</tr> -->
		
		
			<tr>
				<th>員工姓名</th>
				<td><input type="TEXT" name="empName" size="20" maxlength="20"
					value="<%=(empVO == null) ? "" : empVO.getEmpName()%>" /></td>
			</tr>

			<tr>
				<th>性別</th>
				<td><input type="radio" id="male" name="empGender" value="男"
					${(empVO.empGender=='男')?'checked':'' }> <label for="male">男</label>
					<input type="radio" id="female" name="empGender" value="女"
					${(empVO.empGender=='女')?'checked':'' }> <label
					for="female">女</label><br> 
					
					<!-- 欲使用下面寫法要先判斷empVO == null -->

					<%-- 				<input type="radio" id="male" name="empGender" value="男" <%=(empVO.getEmpGender()=="男")?"checked":""%> />  --%>
					<!-- 				<label for="male">男生</label>  --> <%-- 	<input type="radio" id="male" name="empGender" value="女" <%=(empVO.getEmpGender()=="女")?"checked":""%> />  --%>
					<!-- 				<label for="female">女</label><br> -->
					</td>
			</tr>

			<tr>
				<th>Email</th>
				<td><input type="email" name="empAcc" size="20" maxlength="30"
					value="<%=(empVO == null) ? "" : empVO.getEmpAcc()%>" /></td>
			</tr>
			<tr>
				<th>生日</th>
				<td><input name="empBirth" id="f_date1" type="text" autocomplete="off" 
				value="<%=(empVO == null) ? "" : ((empVO.getEmpBirth() == null) ? "" : empVO.getEmpBirth())%>" /></td>
			</tr>

			<tr>
				<th>職位</th>
				<td><input type="TEXT" name="empJob" size="20" maxlength="6"
					value="<%=(empVO == null) ? "" : empVO.getEmpJob()%>" /></td>
			</tr>

			<tr>
				<th>電話</th>
				<td><input type="TEXT" name="empPhone" size="20" maxlength="11"
					value="<%=(empVO == null) ? "" : empVO.getEmpPhone()%>" /></td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input type="TEXT" name="empAddress" size="40"
					value="<%=(empVO == null) ? "" : empVO.getEmpAddress()%>" /></td>
			</tr>

			

			<tr>
				<th>到職日</th>
				<td><input name="hiredate" id="f_date2" type="text"></td>
			</tr>



			<tr>
				<th>員工狀態</th>
				<td>
<!-- 				<input type="text" name="empStatus" size="4" -->
<%-- 					value="<%=(empVO == null) ? "1" : empVO.getEmpStatus()%>" /> --%>
					<select name="empStatus">
　						<option value="1" ${(empVO.empStatus=='1')? 'selected':''}>在職中</option>
　						<option value="2" ${(empVO.empStatus=='2')? 'selected':''}>休假中</option>
　						<option value="3" ${(empVO.empStatus=='3')? 'selected':''}>已離職</option>

					</select>
					
					</td>
			</tr>

			<tr>
				<th>員工照片</th>
				<td><input type="file" name="empPic" class="upl">
					<div>
						<img class="preview" style="max-width: 150px; max-height: 150px;">
						<div class="size"></div>
					</div></td>
			</tr>




		</table>
		<br> <input type="hidden" name="action" value="insert"> 
		<button type="submit" class="btn btn-primary">送出新增</button>
	</FORM>
	 <span id="lblMsg"></span>
	
	<%@ include file="/back-end/backEndInclude/footer.jsp"%>
</body>


<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%
	java.sql.Date empBirth = null;
	try {
		empBirth = empVO.getEmpBirth(); //非空值存到hiredate
	} catch (Exception e) {
		empBirth = null; //空值給null

	}
%>

<%
	java.sql.Date hiredate = null;
	try {
		hiredate = empVO.getHiredate(); //非空值存到hiredate
	} catch (Exception e) {
		hiredate = new java.sql.Date(System.currentTimeMillis()); //空值給今天日期
	}
%>


<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/datetim	picker/jquery.js"></script>
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}
</style>

<script>
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
                $('.size').text("檔案大小：" + KB + " KB");
            }
 
            reader.readAsDataURL(input.files[0]);
        }
    }
 
    $("body").on("change", ".upl", function (){

        preview(this);
    })
    
})

        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',          //format:'Y-m-d H:i:s',
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           startDate:	            '1980/01/01',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $.datetimepicker.setLocale('zh');
        $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=hiredate%>'
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
	//startDate:	            '2017/07/10',  // 起始日
	//minDate:               '-1970-01-01', // 去除今日(不含)之前
	//maxDate:               '+1970-01-01'  // 去除今日(不含)之後
	});

	// ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

	//      1.以下為某一天之前的日期無法選擇
	//      var somedate1 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      2.以下為某一天之後的日期無法選擇
	//      var somedate2 = new Date('2017-06-15');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	//      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
	//      var somedate1 = new Date('2017-06-15');
	//      var somedate2 = new Date('2017-06-25');
	//      $('#f_date1').datetimepicker({
	//          beforeShowDay: function(date) {
	//        	  if (  date.getYear() <  somedate1.getYear() || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
	//		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
	//		             ||
	//		            date.getYear() >  somedate2.getYear() || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
	//		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
	//              ) {
	//                   return [false, ""]
	//              }
	//              return [true, ""];
	//      }});

	
	
	 	 $(function () {
            $(":submit").click(function () {
                $("#lblMsg").text("處理中，請稍候...");
                $(this).prop("disabled", true);
                $("#form1").submit();
             
            });
        });
	    
// 	        $("#send").click(function(){
// 	        	$("#send").attr("disabled", true);
// 	            $("#form1").submit();
// 	        });
	
	
	</script>
</html>
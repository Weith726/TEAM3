<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.util.*"%>







<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>

<style type="text/css">

.select{
/* border-style: solid; */
/*   border-color: red; */
  width:900px;

}
.select td{
/* border-style: solid; */
/*   border-color: red; */
}
.select th{
text-align: right;
/* border-style: solid; */
/*   border-color: red; */
}

/* form{ */
/* display:inline; */
/* margin:0px; */
/* } */

</style>





</head>

<body>
	<%@ include file="/back-end/backEndInclude/header.jsp"%>

	<span class="mainTitle">�w���޲z</span>


	<hr class="mainTitlehr">

	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" />

	<FORM METHOD="post" ACTION="appt.do">

				���
				<input name="optDate" id="f_date1" type="text">
				
				������
				<select size="1" name="docname">
						<option value="">�����
							<c:forEach var="docVO" items="${docSvc.all}">
								<option value="${docVO.docname}">${docVO.docname}
							</c:forEach>
				</select>
				
				��ܮɬq
				
				<input type="radio" id="morning" name="optSession"value="10:00~12:00"> 
				<label for="morning">10:00~12:00</label>
				<input type="radio" id="afternoon" name="optSession"value="14:00~17:00"> 
				<label for="afternoon">14:00~17:00</label>
				<input type="radio" id="night" name="optSession" value="18:00~20:00">
				<label for="night">18:00~20:00</label>
				

			
<!--        <th>��ܮɬq:</th> -->
				<!--        <td> --> <!--        <select size="1" name="optSession" > -->
				<!--           <option value="10:00~12:00">10:00~12:00 --> <!--           <option value="14:00~17:00">14:00~17:00 -->
				<!--           <option value="18:00~20:00">18:00~20:00 --> <!--        </select> -->
				<!--        </td> -->
		</table>
		<input type="hidden" name="action" value="listAppt"> 
		<button type="submit" class="btn btn-primary">�d��</button>
	</FORM>
	
	<%if (request.getAttribute("listAppt")!=null){%>
       <jsp:include page="listAppt.jsp" />
<%} %>







	<%@ include file="/back-end/backEndInclude/footer.jsp"%>

</body>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.datetimepicker.css" />
<script
	src="<%=request.getContextPath()%>/back-end/datetimepicker/jquery.js"></script>
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
	$.datetimepicker.setLocale('zh');
	$('#f_date1').datetimepicker({
		theme : '', //theme: 'dark',
		timepicker : false, //timepicker:true,
		step : 1, //step: 60 (�o�Otimepicker���w�]���j60����)
		format : 'Y-m-d', //format:'Y-m-d H:i:s',
		value : new Date()
	//disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // �h���S�w���t
	//startDate:	            '2017/07/10',  // �_�l��
	//minDate:               '-1970-01-01', // �h������(���t)���e
	//maxDate:               '+1970-01-01'  // �h������(���t)����
	});

	// ----------------------------------------------------------�H�U�ΨӱƩw�L�k��ܪ����-----------------------------------------------------------

	//      1.�H�U���Y�@�Ѥ��e������L�k���
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

	//      2.�H�U���Y�@�Ѥ��᪺����L�k���
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

	//      3.�H�U����Ӥ�����~������L�k��� (�]�i���ݭn������L���)
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
</script>


</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>

</head>

<title>IBM Emp: Home</title>

<%--�۰ʰe�X --%>
<body onload="document.form1.submit()"> 
<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
<ul>  
  <li>   
  <%--�۰ʨ�s--%>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" name="form1">
        
        
           
<!--        <b>�]�w�s�����:</b> -->
       <input type="hidden" name="apptno" id="f_date1" type="text" autocomplete="off"><br>
       
  
<!--   �ɬq����      -->
<%--   <jsp:useBean id="optSvc" scope="page" class="com.opt.model.OptService" /> --%>
<%--   <jsp:useBean id="docSvc" scope="page" class="com.doc.model.DocService" /> --%>

<!--  	<td style="text-align:left;">  -->
<!--   	<li> -->
<!--      <b><font>��ܮɬq:</font></b> -->
<!--        <select size="1" name="sessionno"> -->
<%--          <c:forEach var="optVO" items="${optSvc.all}" >  --%>
<%--          <c:forEach var="docVO" items="${docSvc.all}" >  --%>
<%--          <c:if test="${(optVO.docNo==docVO.docno)}"> --%>
<%--           <option value="${optVO.sessionNo}">${docVO.docname} --%>
<%--           </c:if> --%>
<%--          </c:forEach> --%>
<%--          </c:forEach>     --%>
<!--        </select> -->

    
    
<!--        <b>�]�w�ݶE���A��1�w�ݶE:</b> -->
       <input type="hidden" name="optstate" value="1"><br>
           
       
		        
<!--         <input type="submit" value="�e�X"> -->
        <input type="hidden" name="action" value="listAppt_ByCompositeQuery">
     </FORM>
  </li>
</ul>


<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
</body>
<script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
 	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
	       format:'Ymd',         //format:'Y-m-d H:i:s',
		   value: 'new Date()',              // value:   new Date(),
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
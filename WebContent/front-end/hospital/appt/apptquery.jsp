<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>



<%-- �U�νƦX�d��-�H�U���-�i�H�N�W�� --%>
<ul>  
  <li>   
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/appt/appt.do" name="form1">
        <b><font color=blue>���E�w���d��:</font></b> <br>
        
           
<!--        <b>�]�w�|���s��:</b> -->
       <input type="hidden" name="memno" value="M0004"><br>
       
       
      
    
<!--        <b>�]�w�ݶE���A��0���ݶE:</b> -->
       <input type="hidden" name="optstate" value="0"><br>
           
       
		        
        <input type="submit" value="���E�w���d��">
        <input type="hidden" name="action" value="listAppt_ByCompositeQuery2">
     </FORM>
  </li>
</ul>



</html>
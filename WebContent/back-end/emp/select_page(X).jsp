<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>IBM Emp: Home</title>

<style>
  table#table-1 {
	width: 450px;
	background-color: #CCCCFF;
	margin-top: 5px;
	margin-bottom: 10px;
    border: 3px ridge Gray;
    height: 80px;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
     text-align: center;
  }
  h4 {
    color: blue;
    display: inline;
     text-align: center;
  }
  
     .mainTitle{
     letter-spacing: 8px;
     color: #42454C;
     font-weight: bold;
     font-size: 28px;
     padding-left: 20px;

   }

   /*�D���e���D�U���j�u*/
   .mainTitlehr {
     border: 2px solid lightcoral;


   }
</style>

</head>
<body bgcolor='white'>


   <span class="mainTitle">���u�޲z</span>

	<hr class="mainTitlehr">



	
<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
	    <c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<ul>
  <li><a href='listAllEmp.jsp'>�d�ߥ������u  </a><br><br></li>
  
  


  <jsp:useBean id="empSvc" scope="page" class="com.emp.model.EmpService" />
   
  
  
  <li>
     <FORM METHOD="post" ACTION="emp.do" >
       <b>�̭��u�m�W�d��:</b>
       <select size="1" name="empID">
         <c:forEach var="empVO" items="${empSvc.all}" > 
          <option value="${empVO.empID}">${empVO.empName}
         </c:forEach>   
       </select>
       <input type="hidden" name="action" value="getOne_For_Display">
       <input type="submit" value="�e�X">
     </FORM>
  </li>
</ul>




<ul>
  <li><a href='addEmp.jsp'>�s�W���u</a></li>
</ul>



</body>
</html>
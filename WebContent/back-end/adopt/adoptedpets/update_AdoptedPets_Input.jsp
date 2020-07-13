<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.adoptedpets.model.*"%>

<%
  AdoptedPetsVO adoptedpetsVO = (AdoptedPetsVO) request.getAttribute("adoptedpetsVO");
%>

<jsp:useBean id="petShelterSvc" scope="page" class="com.petshelter.model.PetShelterService" />
<jsp:useBean id="adopterSvc" scope="page" class="com.adopter.model.AdopterService" />    
    
<!DOCTYPE html>
<html>
<head>
<%@ include file="/back-end/backEndInclude/head.jsp"%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/back-end/backEndInclude/style.css">

<title>Update Adopted Pets</title>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js" integrity="sha384-OgVRvuATP1z7JjHLkuOU7Xw704+h835Lr+6QL9UvYjZE3Ipu6Tp75j7Bh/kR0JKI" crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<style>
.rounded{
	height: 100px;
}
.col-2, .col-3{
	padding-left: 0px;
}
</style>
</head>
<body>
<%@ include file="/back-end/backEndInclude/header.jsp"%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
<div class="container">
	<div class="row">
		<div class="col">
			<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/adoptedpets/adoptedpets.do" runat="server">
				<fieldset disabled>
					<div class="form-group row">
						<label for="petNo">���e�d���s��</label>
      					<input type="text" name="petNo" id="petNo" class="form-control" value="${adoptedpetsVO.petNo}">
					</div>
				</fieldset>
				<div class="form-group row">
					<div class="col-2">
							��i�H�s��
					</div>
					<div class="col">
						<select class="custom-select" name="adopterNo" id="adopterNo">
							<option selected value="${adoptedpetsVO.adopterNo}">${adoptedpetsVO.adopterNo}</option>
							<c:forEach var="adopterVO" items="${adopterSvc.all}">		
								<option value="${adopterVO.adopterNo}">${adopterVO.adopterNo}</option>
							</c:forEach>
								<option value=""></option>
						</select>
					</div>
				</div>
				
				<div class="form-group row">
					<div class="col-2">
							��ҽs��
					</div>
					<div class="col">
						<select class="custom-select" name="shelterNo" id="shelterNo">
							<option selected value="${adoptedpetsVO.shelterNo}">${adoptedpetsVO.shelterNo}</option>
							<c:forEach var="petShelterVO" items="${petShelterSvc.all}">		
								<option value="${petShelterVO.shelterNo}"}>${petShelterVO.shelterNo}</option>
							</c:forEach>
								<option value=""></option>
						</select>
					</div>
				</div>
				
				<div class="form-group row">
					<label for="petBreed">�d���~��</label>
   					<input type="text" class="form-control" name="petBreed" id="petBreed" value="${adoptedpetsVO.petBreed}">
				</div>
				<div class="form-group row">
					<label for="adoptedDate">���e���</label>
   					<input type="text" class="form-control" name="adoptedDate" id="adoptedDate" value="${adoptedpetsVO.adoptedDate}">			
				</div>
				<div class="form-group row">
					<div class="col-3">
						<label for="petPic">���e�d���Ϥ�</label>
   						<input type="file" class="form-control-file" name="petPic" id="petPic">
   					</div>
   					<div class="col uploadimg" >
   					<img id="picPreview" src="<%=request.getContextPath()%>/adoptedpetspic.do?petNo=${adoptedpetsVO.petNo}" class="rounded d-block">				
					</div>
				</div>
				<div class="form-group row">
					<label for="adoptDate">��i���</label>
   					<input type="text" class="form-control" name="adoptDate" id="adoptDate" value='${adoptedpetsVO.adoptDate}'>
				</div>
				<div class="form-group row">
					<label for="interviewDate">�X�d���</label>
   					<input type="text" class="form-control" name="interviewDate" id="interviewDate"   value='${adoptedpetsVO.interviewDate}'>
				</div>
				<div class="form-group row">
					<label for="interviewInfo">�X�d���e</label>
    				<textarea class="form-control" name="interviewInfo" id="interviewInfo" rows="3"></textarea>
				</div>
				<div class="form-group row">
					<div class="col-2">
						�d������
					</div>
					<div class="col-10">	
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="petSpecies" id="petSpecies1" value="��">
						  <label class="form-check-label" for="petSpecies1">��</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="petSpecies" id="petSpecies2" value="��">
						  <label class="form-check-label" for="petSpecies2">��</label>
						</div>
						<div class="form-check form-check-inline">
						  <input class="form-check-input" type="radio" name="petSpecies" id="petSpecies3" value="��L">
						  <label class="form-check-label" for="petSpecies3">��L</label>
						</div>
					</div>
				</div>
				<div class="form-group row">
					<div class="col-2">
						�d���ʧO
					</div>
					<div class="col">
						<div class="form-check form-check-inline">
						 	<input class="form-check-input" type="radio" name="petGender" id="petGender1" value="��">
					  		<label class="form-check-label" for="petGender1">��</label>
						</div>
						<div class="form-check form-check-inline">
					  		<input class="form-check-input" type="radio" name="petGender" id="petGender2" value="��">
					  		<label class="form-check-label" for="petGender2">��</label>
						</div>
					</div>
				</div>				
				<div class="form-group row">
					<div class="col-2">
							��i���A
					</div>
					<div class="col">
						<select class="custom-select" name="adoptStatus" id="adoptStatus">
							<option selected id="adoptStatusd4"></option>
							<option value="0">����i</option>
							<option value="1">�w��i</option>
							<option value="2">�w����</option>						  
						</select>
					</div>
					<script>
						if('${adoptedpetsVO.adoptStatus}' === '0' ){			
							$('#adoptStatusd4').attr("value","0");
							$('#adoptStatusd4').text("����i");
						}else if('${adoptedpetsVO.adoptStatus}' === '1' ){
							$('#adoptStatusd4').attr("value","1");
							$('#adoptStatusd4').text("�w��i");
						}else if('${adoptedpetsVO.adoptStatus}' === '2' ){
							$('#adoptStatusd4').attr("value","2");
							$('#adoptStatusd4').text("�w����");
						}
					</script>
				</div>
				<input type="hidden" name="petNo" value="${adoptedpetsVO.petNo}">
				<input type="hidden" name="action" value="update">
				<div class="form-group row">
					<div class="col-auto mx-auto input-group text-center">       				
        				<button type="submit" class="btn btn-primary ">�e�X</button>	       		
					</div>
				</div>

			</form>
		</div>
	</div>
</div>
<%@ include file="/back-end/backEndInclude/footer.jsp"%>	
</body>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
        $.datetimepicker.setLocale('zh');
        
        
        $('#adoptedDate').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',		   
        });

        $('#adoptDate').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',   
         });
        
        $('#interviewDate').datetimepicker({
           theme: '',              //theme: 'dark',
  	       timepicker:false,       //timepicker:true,
  	       step: 1,                //step: 60 (�o�Otimepicker���w�]���j60����)
  	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
         });
        
        function readURL(input) {
        	  if (input.files && input.files[0]) {
        	    var reader = new FileReader();
        	    
        	    reader.onload = function(e) {
        	      $('#picPreview').attr('src', e.target.result);
        	    }
        	    
        	    reader.readAsDataURL(input.files[0]); // convert to base64 string
        	  }
        	}

        $("#petPic").change(function() {
        	  readURL(this);
        });
        		
		
		var species = '${adoptedpetsVO.petSpecies}';
		if(species === "��"){
			$('#petSpecies1').attr("checked", "checked");
		}else if(species === "��"){
			$('#petSpecies2').attr("checked", "checked");
		}else{
			$('#petSpecies3').attr("checked", "checked");
		}
		
		
		var gender = '${adoptedpetsVO.petGender}';
		if(gender === "��"){
			$('#petGender1').attr("checked", "checked");
		}else{
			$('#petGender2').attr("checked", "checked");
		}
	
		
		
 
</script>
</html>
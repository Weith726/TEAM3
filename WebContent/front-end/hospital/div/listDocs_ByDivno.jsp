<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.doc.model.*"%>
<%@ page import="com.div.model.*"%>

<jsp:useBean id="listDocs_ByDivno" scope="request" type="java.util.Set<DocVO>" /> <!-- ��EL����i�ٲ� -->
<jsp:useBean id="divSvc" scope="page" class="com.div.model.DivService" />


<!DOCTYPE html>
<html lang="en">

    <!-- Basic -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">   
   
    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
 
     <!-- Site Metas -->
    <title>OnNext - One Page Parallax Template Bootstrap 4</title>  
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon" />
    <link rel="apple-touch-icon" href="images/apple-touch-icon.png">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <!-- Site CSS -->
    <link rel="stylesheet" href="style5.css">
    <!-- Responsive CSS -->
    <link rel="stylesheet" href="css/responsive.css">
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/custom.css">
	<script src="js/modernizr.js"></script> <!-- Modernizr -->

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body id="page-top" class="politics_version">
  
	<div id="team" class="section wb">
		<div class="container">
			<div class="section-title text-center">
				<h3>Our Team</h3>
					<c:forEach var="docVO" items="${listDocs_ByDivno}" >
				<p><c:forEach var="divVO" items="${divSvc.all}" begin="1" end="4">
                    <c:if test="${docVO.divno==divVO.divno}">
	                    ${divVO.divno}${divVO.divname}</font>
                    </c:if>
                </c:forEach></c:forEach></p>
			</div><!-- end title -->
		
			<div class="row">
				
			<!---->

<%-- ���~��C --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">�Эץ��H�U���~:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
	
	<c:forEach var="docVO" items="${listDocs_ByDivno}" >
			<div class="col-md-3 col-sm-6">
					<div class="our-team">
						<div class="pic">
							<img src="<%=request.getContextPath()%>/back-end/doc/ShowDocPic.do?docno=${docVO.docno}" height=200px width=200px; alt="meow">
							<div class="date">
								<h4>  <b>��v�s��</b> ${docVO.docno} <br> <b>�E�����X</b> ${docVO.roomno}</h4>
							</div>
						</div>
						<div class="team-content">
							<h3 class="title"> ${docVO.docname}</h3>
							<span class="post">�~�� ${docVO.seniority}�~</span>
							${docVO.intro}
						</div>
					</div>
				</div>
</c:forEach>

<!---->





			</div>
		</div>
	</div>
	
	

    <div class="copyrights">
        <div class="container">
            <div class="footer-distributed">
				
                    </p>
                    <p class="footer-company-name">All Rights Reserved. &copy; 2020 <a href="#">CuteFamily</a> 
                </div>
            </div>
        </div><!-- end container -->
    </div><!-- end copyrights -->

    <a href="#" id="scroll-to-top" class="dmtop global-radius"><i class="fa fa-angle-up"></i></a>

    <!-- ALL JS FILES -->
    <script src="js/all.js"></script>
	<!-- Camera Slider -->
	<script src="js/jquery.mobile.customized.min.js"></script>
	<script src="js/jquery.easing.1.3.js"></script> 
	<script src="js/parallaxie.js"></script>
	<script src="js/headline.js"></script>
	<!-- Contact form JavaScript -->
    <script src="js/jqBootstrapValidation.js"></script>
    <script src="js/contact_me.js"></script>
    <!-- ALL PLUGINS -->
    <script src="js/custom.js"></script>
    <script src="js/jquery.vide.js"></script>

</body>
</html>
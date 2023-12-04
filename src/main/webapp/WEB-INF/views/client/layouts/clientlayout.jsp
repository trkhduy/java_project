<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title><tiles:insertAttribute name="title" /></title>


<link rel="shortcut icon"
	href="${pageContext.servletContext.contextPath}/resources/images/favicon.png" />

<!-- GOOGLE FONT -->
<link
	href="https://fonts.googleapis.com/css?family=Hind:400,300,500,600%7cMontserrat:400,700"
	rel='stylesheet' type='text/css'>

<!-- CSS LIBRARY -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/font-awesome.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/font-lotusicon.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/owl.carousel.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/jquery-ui.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/magnific-popup.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/settings.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/lib/bootstrap-select.min.css">

<!-- MAIN STYLE -->
<link rel="stylesheet" type="text/css"
	href="${pageContext.servletContext.contextPath}/resources/css/style.css">

<!--[if lt IE 9]>
        <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <script src="http://css3-mediaqueries-js.googlecode.com/svn/trunk/css3-mediaqueries.js"></script>
    <![endif]-->

</head>

<body>
 	<!-- PRELOADER -->
    <div id="preloader">
        <span class="preloader-dot"></span>
    </div>
    <!-- END / PRELOADER -->
	<div id="page-wrap">
		<tiles:insertAttribute name="header" />

		<tiles:insertAttribute name="body" />

		<tiles:insertAttribute name="footer" />
	</div>
	<script
		src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.3.1.slim.min.js"></script>

	<script
		src="${pageContext.servletContext.contextPath}/resources/js/popper.min.js" /></script>

	<script
		src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js" /></script>


	<!-- LOAD JQUERY -->
	<script data-cfasync="false"
		src="${pageContext.servletContext.contextPath}/resources/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery-1.11.0.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery-ui.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/bootstrap-select.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?v=3.exp&amp;signed_in=true"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/isotope.pkgd.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.themepunch.tools.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/owl.carousel.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.appear.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.countTo.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.countdown.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.parallax-1.1.3.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.magnific-popup.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/SmoothScroll.js"></script>
	<!-- validate -->
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.form.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/lib/jquery.validate.min.js"></script>
	<!-- Custom jQuery -->
	<script type="text/javascript"
		src="${pageContext.servletContext.contextPath}/resources/js/scripts.js"></script>
</body>

</html>
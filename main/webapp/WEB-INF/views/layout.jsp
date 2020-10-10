<%@page import="com.fasterxml.jackson.annotation.JsonInclude.Include"%>
<%@ page pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Online Shopping Mall</title>
	<tiles:insertAttribute name="head"/>
</head>
<body>

	<div class="container">
		<header class ="row">
			<h1 class="alert alert-success">Online Shopping Mall</h1>
		</header>
		<nav class ="row">
			<tiles:insertAttribute name="menu"/>
		</nav>
		<div class ="row">
			<aside class="col-sm-3">
				<tiles:insertAttribute name="aside"/>
			</aside>
			<article class= "col-sm-9">
				<tiles:insertAttribute name="body"/>
			</article>
			
		</div>
		<footer class ="row">&copy;2020.Bai Tap Lon WWW.</footer>
	</div>

</body>
</html>

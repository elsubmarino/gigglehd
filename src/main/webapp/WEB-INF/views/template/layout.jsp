<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<head>
<meta charset="UTF-8">
<meta name="X-UA-Compatible" content="IE=Edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1"/>
<title>asdfsdf</title>
<link rel="stylesheet" type="text/css"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resources/custom.css"/>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container" id="container">
		<tiles:insertAttribute name="header"></tiles:insertAttribute>
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
		<tiles:insertAttribute name="footer"></tiles:insertAttribute>
	</div>
	<div style="position:fixed;right:0;bottom:0;margin:0 10px 5px 0;background-attachment:fixed;">
	<a href="#container">
	<svg width="50px" height="50px">
	<rect width="50px" height="50px" fill="#cccccc" ></rect>
	<text font-size="25px" x=19 y=30>&uarr;</text>
	</svg>
	</a>
	</div>
<script>
</script>
</body>
</html>
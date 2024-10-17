<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>Sample : <tiles:insertAttribute name="title" /></title>
</head>
<body>
	<div id="wrap">
		<div class="header">
			<tiles:insertAttribute name="header" />
		</div>		
		<div class="content">
			<tiles:insertAttribute name="body" />
		</div>
		<div class="footer">
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>
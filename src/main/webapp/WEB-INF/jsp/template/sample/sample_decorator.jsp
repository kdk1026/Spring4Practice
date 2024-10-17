<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="utf-8">
<title>Sample : <sitemesh:write property='title' /></title>
<sitemesh:write property='head'/>
</head>
<body>
	<div id="wrap">
		<div class="header">
			<jsp:include page="/WEB-INF/jsp/template/sample/sample_header.jsp" />
		</div>
		<div class="content">
			<sitemesh:write property='body'/>
		</div>
		<div class="footer">
			<jsp:include page="/WEB-INF/jsp/template/sample/sample_footer.jsp" />
		</div>
	</div>
</body>
</html>
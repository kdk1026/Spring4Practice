<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>Insert title here</title>
</head>
<body>
	<div>Status:<br/>${pageContext.response.getStatus()}</div>
	<div>Message:<br/>${exception.message}</div>
</body>
</html>
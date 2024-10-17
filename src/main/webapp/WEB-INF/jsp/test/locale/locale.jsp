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
	<div>${msg}</div>
	<div>
		<c:choose>
			<c:when test="${param.lang eq 'ko'}">
				<c:set var="name" value="사용자 이름" />
			</c:when>
			<c:when test="${param.lang eq 'en'}">
				<c:set var="name" value="username" />
			</c:when>
			<c:when test="${param.lang eq 'ja'}">
				<c:set var="name" value="ユーザー名" />
			</c:when>
		</c:choose>
		<spring:message code="errors.minlengh" arguments="${name}, 2"></spring:message>
	</div>
</body>
</html>
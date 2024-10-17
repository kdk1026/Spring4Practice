<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Insert title here</title>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
</head>
<body>
	<div>${message}</div>
	<div id="name"></div>	

	<script type="text/javascript">
	$(document).ready(function(){
		$.ajax({
			url: "http://localhost:8080/jsonp/res",
			type: 'get',
			data: {lastName: 'kim', firstName: 'daekwang'},
			dataType: 'jsonp',
			jsonp: 'stone',
			jsonpCallback: 'myCallback'
		});
	});
	
	function myCallback(data) {
		$('#name').html(data.name);
	}
	</script>
	
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>

<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/css/bootstrap-theme.min.css" rel="stylesheet">

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/dist/jquery.min.js"></script>

<!-- Bootstrap -->
<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/js/bootstrap.min.js"></script>

<script type="text/javascript">
function fn_go(formId, url, pageNo) {
	var $frm = $('#'+formId);
	$frm.attr({action: url+'/'+pageNo, method: 'post'});
	$frm.submit();
}
</script>
</head>
<body>
	<div class="container">
		<nav>
			<ul class="pagination">
				<c:set var="page" value="${paging}" scope="request" />
				<c:set var="formId" value="testFrm" scope="request" />
				<jsp:include page="/WEB-INF/jsp/template/base_paging.jsp" />
			</ul>
		</nav>
	</div> <!-- /container -->
	
	<form:form id="testFrm" action="" method="post">
	</form:form>
	
</body>
</html>
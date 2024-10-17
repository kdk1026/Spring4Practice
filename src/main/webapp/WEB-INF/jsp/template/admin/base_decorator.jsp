<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>

<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset=UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Sample : <sitemesh:write property='title'/></title>

<jsp:include page="/WEB-INF/jsp/fragment/admin/head_lib.jsp" flush="false" />
<sitemesh:write property='head'/>
</head>
<body id="page-top">

	<jsp:include page="/WEB-INF/jsp/fragment/admin/body_lib.jsp" flush="false" />
	<jsp:include page="/WEB-INF/jsp/fragment/admin/body_script.jsp" flush="false" />
	
	<div id="wrapper">
		<jsp:include page="/WEB-INF/jsp/fragment/admin/body_nav.jsp" flush="false" />
		<sitemesh:write property='body'/>
		<jsp:include page="/WEB-INF/jsp/fragment/admin/body_footer.jsp" flush="false" />
	</div>
	<!-- /#wrapper -->
	
	<form:form name="comm_frm" method="post">
		<input type="hidden" name="username" value="<sec:authentication property="principal.name"/>">
		<input type="hidden" name="ver" value="">
		<input type="hidden" name="title" value="">
		<input type="hidden" name="url" value="">
		<input type="hidden" name="type" value="">
		<input type="hidden" name="order" value="">
		<input type="hidden" name="idx" value="">
	</form:form>
	
</body>
</html>
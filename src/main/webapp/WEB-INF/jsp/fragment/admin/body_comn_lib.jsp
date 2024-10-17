<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
	
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/webjars/jquery/3.2.1/dist/jquery.min.js"></script>
	
	<!-- Bootstrap -->
	<script src="${pageContext.request.contextPath}/webjars/bootstrap/3.3.7/dist/js/bootstrap.min.js"></script>
	
	<!-- Custom scripts for all pages-->
	<script src="${pageContext.request.contextPath}/resources/js/commonJquery.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/cookieUtils.js"></script>
	
	<jsp:include page="/WEB-INF/jsp/fragment/rsa_lib.jsp" flush="false" />

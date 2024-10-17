<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
	
	<jsp:include page="/WEB-INF/jsp/fragment/admin/body_comn_lib.jsp" flush="false" />
	
	<!-- Metis Menu -->
    <script src="${pageContext.request.contextPath}/webjars/metisMenu/2.7.0/dist/metisMenu.min.js"></script>
	
	<!-- DataTables -->
	<script src="${pageContext.request.contextPath}/webjars/datatables/1.10.16/media/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/datatables/1.10.16/media/js/dataTables.bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/webjars/datatables-responsive/2.2.1/js/dataTables.responsive.js"></script>
	
	<!-- Jquery Mask -->
	<script src="${pageContext.request.contextPath}/webjars/jquery-mask-plugin/1.14.12/dist/jquery.mask.min.js"></script>
	
	
	<!-- Custom scripts for all pages-->
	<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/dateTimeUtils.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/fileUtils.js"></script>
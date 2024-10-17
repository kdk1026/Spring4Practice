<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>

<jsp:include page="/WEB-INF/jsp/fragment/admin/head_comn_lib.jsp" flush="false" />

<!-- MetisMenu -->
<link href="${pageContext.request.contextPath}/webjars/metisMenu/2.7.0/dist/metisMenu.min.css" rel="stylesheet">

<!-- DataTables -->
<link href="${pageContext.request.contextPath}/webjars/datatables/1.10.16/media/css/dataTables.bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/webjars/datatables-responsive/2.2.1/css/responsive.dataTables.scss" rel="stylesheet">


<style type="text/css">
.fa-1_5 {font-size: 1.5em;}
.fa-3 {font-size: 3em;}
.fa-asterisk {color: #df0000; font-size: 8px;}
.form-horizontal .control-label {text-align: left;}
.form-group .plain-text {padding-top: 7px;}
.username_msg {color: #df0000;}
.modal-title {font-weight: bold;}
.modal-body {display: table; max-height: auto; overflow-y: auto;}
.modal-body .message {display: table-cell; vertical-align: middle; font-size: 16px; padding-left: 20px;}
.modal-body .icon {display: table-cell; vertical-align: middle;}
#dataTable th {text-align: center;}
#dataTable .dt-center {text-align: center; vertical-align: middle;}
#dataTable .dt-right {text-align: right; vertical-align: middle;}
#dataTable .dt-left {vertical-align: middle;}
</style>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>

<c:if test="${page.firstPage > 1}">
	<li><a href="${page.linkUrl}/1"><span class="glyphicon glyphicon-step-backward"></span></a></li>
	<li>
		<a href="${page.linkUrl}" onclick="fn_go('${formId}', this.href, '${page.prevPage}'); return false;">
			<span class="glyphicon glyphicon-chevron-left"></span>
		</a>
	</li>
</c:if>
<c:forEach var="i" begin="${page.firstPage}" end="${page.lastPage}" step="1">
	<c:choose>
		<c:when test="${i == page.currentPage}">
			<li class="active"><a href="${page.linkUrl}" onclick="return false;">${i}</a></li>
		</c:when>
		<c:otherwise>
			<li><a href="${page.linkUrl}" onclick="fn_go('${formId}', this.href, '${i}'); return false;">${i}</a></li>
		</c:otherwise>
	</c:choose>
</c:forEach>
<c:if test="${page.lastPage != page.totalPage}">
	<li>
		<a href="${page.linkUrl}" onclick="fn_go('${formId}', this.href, '${page.nextPage}'); return false;">
			<span class="glyphicon glyphicon-chevron-right"></span>
		</a>
	</li>
	<li>
		<a href="${page.linkUrl}" onclick="fn_go('${formId}', this.href, '${page.totalPage}'); return false;">
			<span class="glyphicon glyphicon-step-forward"></span>
		</a>
	</li>
</c:if>

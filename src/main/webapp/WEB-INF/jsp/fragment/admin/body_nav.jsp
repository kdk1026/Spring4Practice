<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>

        <!-- Navigation -->
        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <div style="margin-left: 30px;">
                	<a class="navbar-brand" href="${pageContext.request.contextPath}/admin/manager">
	                	<i class="fa fa-home fa-1_5" aria-hidden="true"></i> Home
                	</a>
                </div>
            </div>
            <!-- /.navbar-header -->

		<sec:authorize access="isAuthenticated()">
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span style="margin-right: 20px; font-weight: bold;"><sec:authentication property="principal.name"/></span>
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li>
                        	<a data-toggle="tooltip" data-placement="left" title="정보수정 버튼" style="cursor: pointer;" id="my-info">
                        		<i class="fa fa-user fa-fw"></i> 정보수정
                        	</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                        	<a data-toggle="tooltip" data-placement="left" title="로그아웃 버튼" style="cursor: pointer;">
                        		<span data-toggle="modal" data-target="#logoutModal">
                        			<i class="fa fa-sign-out fa-fw"></i> 로그아웃
                        		</span>
                        	</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->
        </sec:authorize>

            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li>
                        	<a href="${pageContext.request.contextPath}/admin/manager.do" data-toggle="tooltip" data-placement="right" title="관리자 관리">
                            	<i class="fa fa-user" aria-hidden="true"></i> 관리자 관리
                            </a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
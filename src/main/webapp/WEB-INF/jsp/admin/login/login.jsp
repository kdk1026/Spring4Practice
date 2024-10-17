<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>Sample : 로그인</title>

<jsp:include page="/WEB-INF/jsp/fragment/admin/head_comn_lib.jsp" flush="false" />
</head>
<body style="background-color: #f8f8f8;">
	<jsp:include page="/WEB-INF/jsp/fragment/admin/body_comn_lib.jsp" flush="false" />

    <div class="container">
        <div class="row">
            <div class="col-md-4 col-md-offset-4 col-sm-7 col-sm-offset-3">
                <div class="login-panel panel panel-default">
                    <div class="panel-body">
                        <form:form role="form" name="frm" action="${pageContext.request.contextPath}/admin/login/auth" method="post">
                            <fieldset>
                            	<input type="hidden" id="rsaModulus" value="${publicKeyModulus}"/>
        						<input type="hidden" id="rsaExponent" value="${publicKeyExponent}"/>
                                <div class="form-group">
                                    <input class="form-control" placeholder="아이디" name="username" type="text" title="아이디 입력" required autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="비밀번호" name="password_plain" type="password" title="비밀번호 입력" required>
                                    <input class="form-control" name="password" type="hidden">
                                </div>
						        <c:if test="${not empty vo}">
							        <div class="form-group">
							        	<div class="alert alert-${vo.alert} alert-dismissible">${vo.message}</div>
							        </div>	        	
						        </c:if>
                                <div class="checkbox">
                                    <label>
                                        <input name="remember-me" type="checkbox">로그인 상태 유지
                                    </label>
                                </div>
                                <a data-toggle="tooltip" title="로그인 버튼" id="btnLogin" class="btn btn-success btn-block">로그인</a>
                                <button type="submit" id="frmSubmit" style="display: none;"></button>
                            </fieldset>
                        </form:form>
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.login-panel -->
            </div>
            <!-- /.col-md-4 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
    
	<script type="text/javascript">
	var $frm = $('form[name="frm"]');
	var $username = $frm.find('input[name="username"]'),
		$pwd = $frm.find('input[name="password_plain"]');
	
	// enter event
	$.enterEventFocus($username);
	$.enterEventCallback($pwd, fn_login);

	// login
	$('#btnLogin').click(function(){
		fn_login();
	});
	
	$frm.submit(function(e) {
		var rsa = new RSAKey();
		rsa.setPublic($('#rsaModulus').val(), $('#rsaExponent').val());
		
		$frm.find('input[name="password"]').val(rsa.encrypt($pwd.val()));
		$pwd.val('');
	});
	
	function fn_login() {
		$('#frmSubmit').trigger("click");
	}
	</script>
</body>
</html>
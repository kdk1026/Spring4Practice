<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>관리자 수정</title>
</head>
<body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">관리자 수정</h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-bell" aria-hidden="true"></i>
                            <a href="${pageContext.request.contextPath}/admin/manager">Home</a>
                        </li>
                        <li>
                            <i class="fa fa-user" aria-hidden="true"></i> 관리자 관리</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-user" aria-hidden="true"></i> 관리자 수정
                        </li>
                    </ol>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            
			<!-- Custom Search Condition -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        	<span>
                        		<i class="fa fa-chevron-circle-right" aria-hidden="true"></i> 관리자 정보입력
                        	</span>
                        	<span style="float: right;">
                        		(<i class="fa fa-asterisk" aria-hidden="true"></i>) 필수 입력 항목
                        	</span>
                        </div>
                        <div class="panel-body">
							<form:form class="form-horizontal" id="frm" name="frm" action="${pageContext.request.contextPath}/admin/manager/modifyProc" method="post">
								<fieldset>
	                            	<input type="hidden" id="rsaModulus" value="${publicKeyModulus}"/>
	        						<input type="hidden" id="rsaExponent" value="${publicKeyExponent}"/>
									<div class="form-group">
										<label for="input1" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">아이디</label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="hidden" name="username" value="${info.username}">
											<div class="plain-text">${info.username}</div>
										</div>
									</div>	
									<div class="form-group">
										<label for="input2" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">비밀번호</label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<div class='input-group'>
												<input class="form-control" name="password" type="hidden">
												<input type="password" class="form-control input-sm" id="input2" name="password_plain" pattern="[^가-힣]+" title="비밀번호 입력(한글 제외)">
												<span class="input-group-addon">
													<i class="fa fa-lock" aria-hidden="true"></i>
												</span>
											</div>
										</div>
									</div>	
									<div class="form-group">
										<label for="input3" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">이름 <i class="fa fa-asterisk" aria-hidden="true"></i></label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm" id="input3" name="fullname" pattern="[가-힣]+" title="이름 입력(한글)" required value="${info.fullname}">
										</div>
									</div>
									<div class="form-group">
										<label for="input4" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">소속</label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm" id="input4" name="belong" pattern="[가-힣]+" title="소속 입력(한글)" value="${info.belong}">
										</div>
									</div>
									<div class="form-group">
										<label for="input5" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">휴대폰</label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm cell_phone" id="input5" name="tel" pattern="(01[016789])-(\d{3,4})-(\d{4})" title="연락처(휴대폰) 입력" placeholder="___-____-____" value="${info.tel}">
										</div>
									</div>
									<div class="form-group">
										<label for="input6" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">계정 상태 <i class="fa fa-asterisk" aria-hidden="true"></i></label>
										<div class="col-lg-5 col-md-4 col-sm-6 col-xs-8">
											<div>
										<c:if test="${info.username eq 'admin'}">
											<c:set var="disabled_admin" value="disabled" />
										</c:if>
										<c:choose>
											<c:when test="${info.enabled eq 1}">
												<c:set var="enabled_1" value="checked" />
											</c:when>
											<c:otherwise>
												<c:set var="enabled_0" value="checked" />
											</c:otherwise>
										</c:choose>
												<input type="hidden" id="input6">	
												<label class="radio-inline"><input type="radio" name="enabled" id="input6_1" value="1" title="계정 사용 선택" ${enabled_1}>계정 사용</label>
												<label class="radio-inline"><input type="radio" name="enabled" id="input6_2" value="0" title="계정 잠김 선택" ${enabled_0} ${disabled_admin}>계정 잠김</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="input7" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">신청사유</label>
										<div class="col-lg-6 col-md-6 col-sm-9 col-xs-8">
											<textarea class="form-control" rows="3" name="etc" id="input7" title="신청사유 입력" style="overflow: hidden;">${info.etc}</textarea>
										</div>
									</div>
									<div class="form-group">
										<label for="input8" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">최근 접속 일시</label>
										<div class="col-lg-6 col-md-6 col-sm-9 col-xs-8">
											<div class="plain-text">${info.last_login_dt}</div>
										</div>
									</div>
									<input type="hidden" name="isCheckUSerName">
								</fieldset>
								<div style="margin: 0; text-align: center;">
										<button type="submit" id="frmSubmit" style="display: none;"></button>
										<button type="button" class="btn btn-success" name="save_btn" data-toggle="tooltip" title="저장 버튼">저장</button>
										<button type="button" class="btn btn-default" name="cancel_btn" data-toggle="tooltip" title="목록 버튼">목록</button>
								</div>
							</form:form>
                        </div>
					</div>
				</div>
			</div>
		</div>
		<!-- /#page-wrapper -->
		<form name="list_frm">
			<input type="hidden" name="srch_gbn" value="${param.srch_gbn}">
			<input type="hidden" name="srch_type" value="${param.srch_type}">
			<input type="hidden" name="srch_txt" value="${param.srch_txt}">
		</form>
		
		<script type="text/javascript">
		var $check_btn = $('button[name="check_btn"]'),
			$save_btn = $('button[name="save_btn"]'),
			$cancel_btn = $('button[name="cancel_btn"]');
		
		var $frm = $('form[name="frm"]');
		var $pwd = $frm.find('input[name="password_plain"]');
		var $enabled = $frm.find('input[name="enabled"]');
		var $isCheckUSerName = $frm.find('input[name="isCheckUSerName"]');
		
		// enter event
		$.enterEventFocus($frm.find('input[name="password"]'));
		$.enterEventFocus($frm.find('input[name="fullname"]'));
		$.enterEventFocus($frm.find('input[name="belong"]'));
		$.enterEventFocus($frm.find('input[name="tel"]'));
		$.enterEventFocus($enabled);
		$.enterEventCallback($enabled, fn_save);
		
		$save_btn.click(function(){
			fn_save();
		});
		
		$cancel_btn.click(function(){
			var queryString = $('form[name="list_frm"]').serialize();
			var url = "${pageContext.request.contextPath}/admin/manager?"+queryString;
			$(location).attr('href', url);
		});
		
		function fn_save() {
			fn_confirmModal(msgObj.upConfirm);
			modalConfirm(function(confirm){
				if (confirm) {
					$('#frmSubmit').trigger("click");
				}
			});
		}
		
		$frm.submit(function() {
			$(':required', this).parent().show();
			
			var invalidInputs = $(":invalid", this);
			if(invalidInputs.length > 0) {
				return false;
			}
			
			if(event.originalEvent) {
				return false;
			}
			
			var rsa = new RSAKey();
			rsa.setPublic($('#rsaModulus').val(), $('#rsaExponent').val());
			
			$frm.find('input[name="password"]').val(rsa.encrypt($pwd.val()));
			$pwd.val('');
		});
		</script>
		
</body>
</html>
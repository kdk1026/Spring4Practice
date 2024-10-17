<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>관리자 등록</title>
</head>
<body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">관리자 등록</h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-bell" aria-hidden="true"></i>
                            <a href="${pageContext.request.contextPath}/admin/manager">Home</a>
                        </li>
                        <li>
                            <i class="fa fa-user" aria-hidden="true"></i> 관리자 관리</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-user" aria-hidden="true"></i> 관리자 등록
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
							<form:form class="form-horizontal" id="frm" name="frm" action="${pageContext.request.contextPath}/admin/manager/writeProc" method="post">
								<fieldset>
	                            	<input type="hidden" id="rsaModulus" value="${publicKeyModulus}"/>
	        						<input type="hidden" id="rsaExponent" value="${publicKeyExponent}"/>
									<div class="form-group">
										<label for="input1" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">아이디 <i class="fa fa-asterisk" aria-hidden="true"></i></label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm" id="input1" name="username" pattern="[a-zA-Z0-9]+" title="아이디 입력(영문, 숫자)" required>
										</div>
										<div class="col-lg-2 col-md-2 col-sm-2 col-xs-2">
											<button class="btn" type="button" name="check_btn" data-toggle="tooltip" title="중복확인" disabled>중복확인</button>
										</div>
										<div class="col-lg-5 col-lg-offset-0 col-md-4 col-md-offset-0 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2">
											<div id="username_msg_wrap" style="display: none;">
												<i class="fa fa-asterisk" aria-hidden="true"></i> <span class="username_msg"></span>
											</div>
										</div>
									</div>	
									<div class="form-group">
										<label for="input2" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">비밀번호 <i class="fa fa-asterisk" aria-hidden="true"></i></label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<div class='input-group'>
												<input class="form-control" name="password" type="hidden">
												<input type="password" class="form-control input-sm" id="input2" name="password_plain" pattern="[^가-힣]+" title="비밀번호 입력(한글 제외)" required>
												<span class="input-group-addon">
													<i class="fa fa-lock" aria-hidden="true"></i>
												</span>
											</div>
										</div>
									</div>	
									<div class="form-group">
										<label for="input3" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">이름 <i class="fa fa-asterisk" aria-hidden="true"></i></label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm" id="input3" name="fullname" pattern="[가-힣]+" title="이름 입력(한글)" required>
										</div>
									</div>
									<div class="form-group">
										<label for="input4" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">소속</label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm" id="input4" name="belong" pattern="[가-힣]+" title="소속 입력(한글)">
										</div>
									</div>
									<div class="form-group">
										<label for="input5" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">휴대폰</label>
										<div class="col-lg-3 col-md-4 col-sm-6 col-xs-8">
											<input type="text" class="form-control input-sm cell_phone" id="input5" name="tel" pattern="(01[016789])-(\d{3,4})-(\d{4})" title="연락처(휴대폰) 입력" placeholder="___-____-____">
										</div>
									</div>
									<div class="form-group">
										<label for="input6" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">계정 상태 <i class="fa fa-asterisk" aria-hidden="true"></i></label>
										<div class="col-lg-5 col-md-4 col-sm-6 col-xs-8">
											<div>
										<c:choose>
											<c:when test="${info.enabled eq 1}">
												<c:set var="enabled_1" value="checked" />
											</c:when>
											<c:otherwise>
												<c:set var="enabled_0" value="checked" />
											</c:otherwise>
										</c:choose>
												<input type="hidden" id="input6">	
												<label class="radio-inline"><input type="radio" name="enabled" id="input6_1" value="1" title="계정 사용 선택">계정 사용</label>
												<label class="radio-inline"><input type="radio" name="enabled" id="input6_2" value="0" title="계 정잠김 선택" checked>계정 잠김</label>
											</div>
										</div>
									</div>
									<div class="form-group">
										<label for="input7" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 control-label">신청사유</label>
										<div class="col-lg-6 col-md-6 col-sm-9 col-xs-8">
											<textarea class="form-control" rows="3" name="etc" id="etc" title="신청사유 입력" style="overflow: hidden;"></textarea>
										</div>
									</div>
									<div class="form-group">
										<input type="hidden" name="isCheckUSerName">
									</div>
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
		
		<script type="text/javascript">
		var $check_btn = $('button[name="check_btn"]'),
			$save_btn = $('button[name="save_btn"]'),
			$cancel_btn = $('button[name="cancel_btn"]');
		
		var $frm = $('form[name="frm"]');
		var $username = $frm.find('input[name="username"]');
		var $pwd = $frm.find('input[name="password_plain"]');
		var $enabled = $frm.find('input[name="enabled"]');
		var $isCheckUSerName = $frm.find('input[name="isCheckUSerName"]');
		
		// check button enabled/disabled
		$username.on('blur keyup', function(){
			$isCheckUSerName.val('');
			$('#username_msg_wrap').hide();
			
			$check_btn.attr('disabled', true);
			if ( !$.isBlank($username.val()) ) {
				$check_btn.attr('disabled', false);
			}
		});
		
		// check button
		$check_btn.click(function(){
			var url = "${pageContext.request.contextPath}/admin/manager/checkUserName",
				key = '',
				params = {
					username: $username.val() 
				};
			
			params[key + $frm.find('input:last').attr('name')] = $frm.find('input:last').val();

			var retObj = $.ajaxSync(url, params);
			if (retObj.resp_cd != '0000') {
				$username.val('');
				fn_alertModal(retObj.resp_msg);
			} else {
				$('#username_msg_wrap .username_msg').text(retObj.resp_msg);
				$('#username_msg_wrap').show();
				
				if (retObj.being === 'N') {
					$isCheckUSerName.val(retObj.being);
				}
			}
		});
		
		// enter event
		$.enterEventFocus($username);
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
			var url = "${pageContext.request.contextPath}/admin/manager";
			$(location).attr('href', url);
		});
		
		function fn_save() {
			fn_confirmModal(msgObj.svConfirm);
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
			
			var $isCheckUSerName = $frm.find('input[name="isCheckUSerName"]');
			if ( $.isBlank($isCheckUSerName.val()) ) {
				fn_alertModal(msgObj.duplNotChk);
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
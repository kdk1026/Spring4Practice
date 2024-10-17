<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>

	<script type="text/javascript">
	var msgObj = {};
	msgObj.duplNotChk = '아이디 중복확인을 해주세요.';
	msgObj.delNotChk = '삭제할 항목이 선택되지 않았습니다.';
	msgObj.delConfirm = '삭제하시겠습니까?';
	msgObj.upConfirm = '수정하시겠습니까?';
	msgObj.svConfirm = '저장하시겠습니까?';
	
	//----------------------------
	var today = new Date();	
	var year = today.getFullYear();
	var month = today.getMonth();
	var day = today.getDate(); 
	var h = today.getHours();
	var m = today.getMinutes() + 4;
	var s = today.getSeconds() + 50;

	var countDown = new Date(year, month, day, h, m, s).getTime();
	
	function fn_sessionTimeOut() {
		var now = new Date().getTime();
		var distance = countDown - now;
		
		if (distance < 0) {
			clearInterval(x);
			
			var url = "${pageContext.request.contextPath}/admin/login/logout?expired=Y";
			location.href = url;
		}
	}
	
	var x = setInterval(function(){
		fn_sessionTimeOut();
	}, 1000);
	//----------------------------
	
	
	$(function() {
		/** jQuery Mask Plugin */	
		$('.cell_phone').mask('000-0000-0000');
		
		var S_VO = '${vo}';
		if ( S_VO ) {
			var obj = $.jsonToObj(S_VO);
			if (obj.resp_cd != '0000') {
				fn_alertModal(obj.resp_msg);
			}
		}
		
		$('#my-info').click(function(e){
			e.preventDefault();
			var $frm = $('form[name="comm_frm"]');
			$frm.attr("action", "${pageContext.request.contextPath}/admin/manager/modify");
			$frm.submit();
			return false;
		});
		
		$('.nav-tabs li').click(function(e){
			e.preventDefault();
			$('.nav-tabs li').removeClass();
			$(this).addClass('active');
			return false;
		});
	});
	
	function fn_alertModal(message) {
		var $alertModal = $('#alertModal');
		$alertModal.find('.modal-body .message').html(message);
		$alertModal.modal('show');
	}
	
	function fn_confirmModal(message) {
		var $confirmModal = $('#confirmModal');
		$confirmModal.find('.modal-body .message').html(message);
		$confirmModal.modal('show');
	}
	
	var modalConfirm = function(callback) {
		var $confirmModal = $('#confirmModal');
		$confirmModal.find('button[name="ok"]').unbind('click');
		$confirmModal.find('button[name="ok"]').click(function(){
			callback(true);
			$confirmModal.modal('hide');
		});
	}
	</script>
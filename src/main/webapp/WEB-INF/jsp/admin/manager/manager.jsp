<%@page import="java.net.URLDecoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/taglib.jspf" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>관리자 관리</title>
</head>
<body>
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">관리자 관리</h1>
                    <ol class="breadcrumb">
                        <li>
                            <i class="fa fa-bell" aria-hidden="true"></i>
                            <a href="${pageContext.request.contextPath}/admin/manager">Home</a>
                        </li>
                        <li class="active">
                            <i class="fa fa-user" aria-hidden="true"></i> 관리자 관리
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
							<form:form name="srch_frm">
								<div class="form-group row">
									<label for="srch_type" class="col-lg-2 col-md-2 col-sm-3 col-xs-4 col-form-label">관리자 상태</label>
									<div class="col-lg-10 col-md-10 col-sm-9 col-xs-8">
										<div>
										<c:choose>
											<c:when test="${param.srch_type eq '1'}"><c:set var="chk_1" value="checked" /></c:when>
											<c:when test="${param.srch_type eq '0'}"><c:set var="chk_0" value="checked" /></c:when>
											<c:otherwise><c:set var="chk_99" value="checked" /></c:otherwise>
										</c:choose>
											<label class="radio-inline"><input type="radio" name="srch_type" id="srch_type1" value="99" data-toggle="tooltip" title="전체 선택" ${chk_99}>전체</label>
											<label class="radio-inline"><input type="radio" name="srch_type" id="srch_type2" value="1" data-toggle="tooltip" title="계정 사용 선택" ${chk_1}>계정 사용</label>
											<label class="radio-inline"><input type="radio" name="srch_type" id="srch_type3" value="0" data-toggle="tooltip" title="계정 잠김 선택" ${chk_0}>계정 잠김</label>
										</div>
									</div>
								</div>
								<div class="form-group row">
									<label for="srch_gbn" class="col-lg-2 col-md-2  col-sm-3 col-xs-4 col-form-label">관리자 검색</label>
									<div class="col-lg-3 col-md-3 col-sm-4 col-xs-8">
										<select class="form-control" name="srch_gbn" id="srch_gbn">
										<c:choose>
											<c:when test="${param.srch_gbn eq 'id'}"><c:set var="sel_id" value="selected" /></c:when>
											<c:when test="${param.srch_gbn eq 'name'}"><c:set var="sel_name" value="selected" /></c:when>
											<c:otherwise><c:set var="sel_all" value="selected" /></c:otherwise>
										</c:choose>
											<option value="all" ${sel_all}>전체</option>
											<option value="id" ${sel_id}>아이디</option>
											<option value="name" ${sel_name}>이름</option>
										</select>
									</div>
								</div>
								<div class="form-group row">
									<div class="col-sm-9">
										<div class="input-group">
											<input type="text" class="form-control input" name="srch_txt" data-toggle="tooltip" data-placement="bottom" title="검색어 입력" placeholder="검색어 입력" value="${param.srch_txt}">
							                <span class="input-group-btn">
							                    <button class="btn btn btn-primary" type="button" name="srch_btn" data-toggle="tooltip" title="검색 버튼">
							                    	<i class="fa fa-search" aria-hidden="true"></i>
							                    </button>
							                    <button class="btn btn-default" type="button" name="clear_btn" data-toggle="tooltip" title="초기화 버튼">
								                    <i class="fa fa-eraser" aria-hidden="true"></i>
							                    </button>
							                </span>
										</div>
									</div>
								</div>
								<input type="hidden" name="username">
							</form:form>
                        </div>
					</div>
				</div>
			</div>

			<c:set var="isAdmin" value="N" />
			
			<sec:authorize access="hasRole('ROLE_ADMIN')">
				<c:set var="isAdmin" value="Y" />
			</sec:authorize>
			
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                        	<div style="margin: 0; text-align: right;">
                        		<button type="button" class="btn btn-success" name="add_btn" data-toggle="tooltip" title="등록 버튼">등록</button>
						<c:choose>
							<c:when test="${isAdmin eq 'Y'}">
								<button type="button" class="btn btn-danger" name="del_btn" data-toggle="tooltip" title="삭제 버튼">삭제</button>
							</c:when>
							<c:otherwise>	
								<button type="button" class="btn btn-danger disabled" name="del_btn" data-toggle="tooltip" title="삭제 버튼(admin 계정에서만 활성)">삭제</button>
							</c:otherwise>
						</c:choose>
                        	</div>
                        	<table width="100%" class="table table-striped table-bordered table-hover" id="dataTable">
					          <thead>
					          	<tr>
					              <th width="3%">No</th>
					              <th width="2%">
							<c:choose>
								<c:when test="${isAdmin eq 'Y'}">
									<input type="checkbox" class="form-control" id="check-all">
								</c:when>
								<c:otherwise>	
									<input type="checkbox" class="form-control" id="check-all" disabled>
								</c:otherwise>
							</c:choose>
					              </th>
					              <th width="15%">아이디</th>
					              <th width="12%">이름</th>
					              <th>소속</th>
					              <th width="18%">관리자 상태</th>
					              <th width="8%">관리</th>
					            </tr>
					          </thead>
                        	</table>
                        	<!-- /.table-responsive -->
                        </div>
                        <!-- /.panel-body -->
					</div>
					<!-- /.panel -->
				</div>
				<!-- /.col-lg-12 -->
			</div>
			<!-- /.row -->
		</div>
		<!-- /#page-wrapper -->
		
		<script type="text/javascript">
		var $srch_frm = $('form[name="srch_frm"]');
		var $srch_gbn = $('select[name="srch_gbn"]'),
			$srch_type = $(':radio[name="srch_type"]'),
			$srch_txt = $('input[name="srch_txt"]');
		
		var $srch_btn = $('button[name="srch_btn"]'),
			$clear_btn = $('button[name="clear_btn"]');
		
		var $table = $('#dataTable');
		var oTable;
		
		$(document).ready(function(){
			oTable = fn_datatable();
			
			$srch_type.change(function(){
				$srch_txt.val('');
			});
			
			$srch_btn.click(function(){
				oTable = fn_datatable();
			});
			
			$clear_btn.click(function(){
				var currentURL = $(location).attr('href');
				var url = currentURL.substring(0, currentURL.indexOf('?'));
				$(location).attr('href', url);
			});
			
			$table.children('tbody').on( 'click', 'button', function () {
				var data = oTable.row( $(this).parents('tr') ).data();
				var url = "${pageContext.request.contextPath}/admin/manager/modify/"+data['id'];
				$(location).attr('href', url);
			});
			
			$('button[name="add_btn"]').click(function(){
				var url = "${pageContext.request.contextPath}/admin/manager/write";
				$(location).attr('href', url);
			});
			
			$('#check-all').click(function(){
				var rows = oTable.rows({ 'search': 'applied' }).nodes();
				$('input[type="checkbox"]', rows).prop('checked', this.checked);
			});
			
			$('button[name="del_btn"]').click(function(){
				if ( $(this).attr('class').indexOf('disabled') > -1 ) {
					return false;
				}
				
				var rowcollection =  oTable.$(":checked", {"page": "all"});
				if (rowcollection.length == 0) {
					fn_alertModal(msgObj.delNotChk);
				} else {
					fn_confirmModal(msgObj.delConfirm);
					modalConfirm(function(confirm){
						if (confirm) {
							var items = [];
							rowcollection.each(function(index,elem){
								items.push($(elem).val());
							});
							
							var url = "${pageContext.request.contextPath}/admin/manager/removeProc",
								key = '',
								params = { items: items };
							
							var $comm_frm = $('form[name="comm_frm"]');
							params[key + $comm_frm.find('input:last').attr('name')] = $comm_frm.find('input:last').val();
							
							var retObj = $.ajaxSync(url, params);
							if (retObj.resp_cd != '0000') {
								fn_alertModal(retObj.resp_msg);
							} else {
								oTable.ajax.reload(null, false);
								$('#check-all').prop('checked', false);
							}
						}
					});
				}
			});
		});
		
		function fn_datatable() {
			var params = $.formSerializeObject($('form[name="srch_frm"]'));
			var loginUser = $('form[name="comm_frm"]').find('input[name="username"]').val();
			
			oTable = $table.DataTable({
				"language": {
					"url": "${pageContext.request.contextPath}/resources/datatables_korean.json"
				},
			    destroy: true,
			    serverSide: true,
			    processing: true,
			    autoWidth: true,
			    responsive: true,
			    paging: true,
			    pageLength: 10,
			    lengthChange: true,
			    info: true,
			    searching: false,
			    ordering: false,
			    "ajax": {
					"url": "${pageContext.request.contextPath}/admin/manager/dataSetList",
					"type": "post",
					"contentType": "application/x-www-form-urlencoded",
					"data": params
			    },
				columns: [
					{ data: "rnum", className: "dt-center" },
					{
						render: function ( data, type, row ) {
							if (row.id != 'admin') {
								if (loginUser === 'admin') {
									return '<input type="checkbox" name="chk_'+row.no+'" value="'+row.id+'">';
								} else {
									return '<input type="checkbox" name="chk_'+row.no+'" value="'+row.id+'" disabled>';
								}
							} else {
								return '';
							}
						}
					},
					{ data: "id", className: "dt-center" },
					{ data: "name", className: "dt-center" },
					{ data: "belong", className: "dt-left" },
					{ data: "state", className: "dt-center" },
					{ 
						data: null, className: "dt-center", 
						render: function ( data, type, row ) {
							if (row.id != 'admin') {
								if ( (loginUser === row.id) || (loginUser === 'admin') ) {
									return "<button type='button' class='btn btn-warning btn-sm' data-toggle='tooltip' title='수정 버튼'>수정</button>";
								} else {
									return "<button type='button' class='btn btn-warning btn-sm' data-toggle='tooltip' title='수정 버튼' disabled>수정</button>";
								}
							} else {
								if (loginUser === 'admin') {
									return "<button type='button' class='btn btn-warning btn-sm' data-toggle='tooltip' title='수정 버튼'>수정</button>";
								} else {
									return "" ;
								}
							}
						}
					}
				],
				'rowCallback': function(row, data, index){
					switch (data['state']) {
					case 0:
						$(row).find('td:eq(5)').text('계정 잠김');
						$(row).find('td:eq(5)').css('color', 'red');
						break;
					case 1:
						$(row).find('td:eq(5)').text('계정 사용');
						break;
					default:
						break;
					}
				}
			});
			return oTable;
		}
		</script>
</body>
</html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>상품 조회</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/css/sb-admin.css"
	rel="stylesheet">
	

 
<link href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/lightbox.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>


<script type="text/javascript">

$(document).ready(function() {
	
		
		<!-- 업로드 다이알로그 모달 팝업띄우기 -->
		var dialogUpload = $( "#dialog-upload-form" ).dialog({
			autoOpen: false,
			height: 280,
			width: 300,
			modal: true,
			buttons: {
				"업로드": function() {
					$( this ).dialog( "close" );
				},
				"취소" : function() {
					$( this ).dialog( "close" );
				}
			},
		});
		
		<!-- 업로드 파일 링크 클릭 -->
		$("#upload-image").click( function(event) {
			event.preventDefault();
			dialogUpload.dialog( "open" );
		});
		
		<!-- text editor 설정 -->
		$('#summernote').summernote({
            height: 300,                 // set editor height
            minHeight: null,             // set minimum height of editor
            maxHeight: null,             // set maximum height of editor
            focus: false                 // set focus to editable area after initializing summernote
    	});
		
		<!-- jquery로 파일 첨부 추가 --> 
	    //add more file components if Add is clicked
	    $('#addFile').click(function() {
	        var fileIndex = $('[name=multipartPhoto]').length;
	        console.log(fileIndex);
	        $('#fileview').append(
	        		' <br/>'+
	                '   <input type="file" class="multpartPhoto" name="multiPartPhoto" style="width: 250px;" />'+
	                '   <input type="checkbox" name="is_main['+fileIndex+']">');
	    });
	    
	    
	    <!-- 옵션 목록 더하기 버튼 클릭-->
		var option_naming_group_idx=0;
		$('#option_add').click(function() {
			option_naming_group_idx = $('.option_naming_group').length;

		    $('#option_add_list').append(
		    		'<div class="col-md-4">'+
		            '<div class="form-label-group option_naming_group" >'+
		              '<input type="text" id="option_naming['+option_naming_group_idx+']" name="option_naming['+option_naming_group_idx+']" class="form-control" placeholder="option_naming">'+
		              '<label for="option_naming['+option_naming_group_idx+']">옵션명</label>'+
		              '</div>'+
		              '</div>'+
		              '<div class="col-md-8">'+
		              '<div class="form-label-group">'+
		              '<input type="text" id="option_list['+option_naming_group_idx+']" name="option_list" class="form-control option_list" placeholder="option_list" >'+
		              '<label for="option_list['+option_naming_group_idx+']">옵션 목록들 (","로 구분)</label>'+
		              '</div>'+
		              '</div>');
		});

		
		<!-- 옵션 조합하기 -->
	    var product_option_group_idx
		$('#option_generator').click(function() {
			// 초기화
			$('#real_option_list').empty();
			product_option_group_idx = $('.product_option_group').length;
		    var option_list_array = new Array();
		    var product_option_idx=0;
			
		    for(var i=0; i<option_naming_group_idx+1; i++){
		    	var option_list = $('[name=option_list]').eq(i).val().split(',');
		    	
		    	product_option_idx = product_option_idx + option_list.length;
		    	option_list_array[i] = option_list
		    };
		   	<!-- 옵션을 위한 조합 함수  -->
		   	function allPossibleCases(arr) {
		   	  if (arr.length == 1) {
		   	    return arr[0];
		   	  } else {
		   	    var result = [];
		   	    var allCasesOfRest = allPossibleCases(arr.slice(1));  // recur with the rest of array
		   	    for (var i = 0; i < allCasesOfRest.length; i++) {
		   	      for (var j = 0; j < arr[0].length; j++) {
		   	        result.push(arr[0][j]+"/" + allCasesOfRest[i]);
		   	      }
		   	    }
		   	    return result;
		   	  }
		   	}
		   	allPossibleCases(option_list_array).forEach(function(item, index, array1){
		       	 $('#real_option_list').append(
		       			'<div class="col-md-6">'+
		       			'<div class="form-label-group product_option_group">'+
		       			'<input type="text" id="product_option['+index+']" name="product_detail_list['+index+'].product_option" class="form-control" placeholder="option_naming" value='+item+'>'+
		       			'<label for="product_option['+index+']">옵션</label>'+
		       			'</div>'+
		       			'</div>'+
		       			'<div class="col-md-3">'+
		       			'<div class="form-label-group product_option_group">'+
		       			'<input type="text" id="price['+index+']" name="product_detail_list['+index+'].price" class="form-control" placeholder="0">'+
		       			'<label for="price['+index+']">0</label>'+
		       			'</div>'+
		       			'</div>'+
		       			'<div class="col-md-3">'+
		       			'<div class="form-label-group">'+
		       			'<input type="text" id="stock_cnt['+index+']" name="product_detail_list['+index+'].stock_cnt" class="form-control" placeholder="option_list" >'+
		       			'<label for="stock_cnt['+index+']">재고</label>'+
		       			'</div>'+
		       			'</div>');
		   	});
		});

});
</script>
</head>
<body id="page-top">
	<c:import url="/WEB-INF/views/admin/include/navigation.jsp"></c:import>
	<div id="wrapper">
		<c:import url="/WEB-INF/views/admin/include/sidebar.jsp"></c:import>
		<div id="content-wrapper">
			<div class="container-fluid">
				<div class="container">
					<div class="card card-register mx-auto mt-5">
						<div class="card-header">상품 등록</div>
						<div class="card-body">
							<form:form 
							action="${pageContext.request.contextPath }/admin/product/add" 
							modelAttribute="productvo" method="POST" class="form" enctype="multipart/form-data">
														
								<div class="form-group">
									<div class="form-label-group">
										<input type="text" id="name" name="name" class="form-control" placeholder="상품내용" required="required">
										<label for="name">상품명</label>
									</div>
								</div>
								<!--  카테고리 목록 보여주기  -->
								<div class="form-group">
									<div class="form-label-group">
										<select name="category_no" id="product_option_list" class="form-control">
											<c:forEach items='${category_list}' var='category_vo' varStatus='status'>
												<c:if test="${category_vo.parent_no==null }">
													<option value="${category_vo.no }" 
														data-price="${category_vo.name}">
															${category_vo.name }
													</option>
												</c:if>
											</c:forEach>
										</select>
									</div>
								</div>			
								<!-- 옵션 설정 -->
								<input type="button" id="option_add" class="btn btn-primary btn-block" value="옵션목록 더하기"/>		
								<div class="form-group">
						            <div class="form-row" id="option_add_list">
						              <div class="col-md-4">
						                <div class="form-label-group option_naming_group" >
						                  <input type="text" id="option_naming[0]" name="option_naming[0]" class="form-control" placeholder="option_naming" >
						                  <label for="option_naming[0]">옵션명</label>
						                </div>
						              </div>
						              <div class="col-md-8">
						                <div class="form-label-group">
						                  <input type="text" id="option_list[0]" name="option_list"  class="form-control option_list" placeholder="option_list" >
						                  <label for="option_list[0]">옵션 목록들 (","로 구분)</label>
						                </div>
						              </div>
						            </div>
						          </div>

								<!-- 실제 옵션들이 셋팅되는 곳 -->
								<input type="button" id="option_generator" class="btn btn-primary btn-block" value="옵션 조합하기"/>	
								<div class="form-group">
						            <div class="form-row" id="real_option_list">
						              
						            </div>
						          </div>		
						          		
								<!-- 이미지 올리기 modal -->
								<!--  
								<div>	
									<a href="" id="upload-image">이미지 올리기</a>
								</div>
								-->			
								<div id="fileview">
									<!-- <input id="addFile" type="button" value="파일 추가" /> -->  
									<br/>
									<input type="file" multiple="multiple" class="multipartPhoto" name="multipartPhoto" style="width: 250px;"/>
								</div>		
								
								<!-- 텍스트에디터 -->
								<div class="form-group">
									<div class="form-label-group">
										<textarea rows="10" cols="30" id="summernote" name="description" class="form-control" placeholder="상품내용">
										</textarea>
									</div>
								</div>
								
								<!-- 
								<div id="dialog-upload-form" title="이미지 업로드" style="display:none">
										<input type="button" tabindex="-1" style="position:absolute; top:-1000px">
										<table id="fileview">
											<tbody>
												<tr>
													<td><input type="file" name="multipartPhoto" style="width: 250px;"/></td>
												</tr>
											</tbody>
										</table>
								</div>
 								-->
								<input type="submit" id="save" class="btn btn-primary btn-block" value="저장"/>
								<input type="button" class="btn btn-primary btn-block" value="취소"/>
							</form:form>					
						</div>
					</div>
				</div>
		     		
						<!-- /.container-fluid -->
					<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
			</div>
		</div>
		<!-- /.content-wrapper -->
	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Bootstrap core JavaScript-->
	<%-- <script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/jquery/jquery.min.js"></script>
		--%>
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
 
	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/js/sb-admin.min.js"></script>

	
	
</body>
	
</html>

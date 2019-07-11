<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/guestbook.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/timeline.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		// DELETE 다이알로그
		var dialogDelete = $("#dialog-delete-form").dialog({
			autoOpen : false,
			height : 170,
			width : 300,
			modal : true,
			buttons : {
				"삭제" : function() {
					var vo = {
						 "no" : $("#hidden-no").val(),
						 "password" : $("#password-delete").val(),	 
					}		
					
					$.ajax({
						url:"${pageContext.request.contextPath}/api/guestbook/delete",
						type:"delete",
						contentType:"application/json", //Post 방식 json
						dataType:"json",
						data: JSON.stringify(vo),
						success:function(response){
							console.log(response);
							if(response.result != "success"){
								console.error(response.message);
								return
							}		
							// li 엘리먼트 삭제
							$("li[data-no='"+response.data+"']").remove();
							dialogDelete.dialog('close');
						},
						error: function(jqXHR, status, e){
							console.error(status + ":" + e);			
						}
					});
				},
				"취소" : function() {
					$(this).dialog("close");
				}
			},
			close : function() {
				$("#dialog-delete-form form").get(0).reset();
			}
		});

		
	});
	
	// guestbook에 넣기
	var userName = '${authUser.name}';
	var render = function(vo, mode){
		// 실제로는 template library를 사용한다.
		// -> ejs, underscore, mustache
		var html = 
			"<li class='guestbook' data-no='"+vo.no+"'>" +
			"<span style='background:url(${pageContext.request.contextPath }/assets/images/user.png) no-repeat' class='profile'></span>"+
			"<a href='' data-no='"+vo.no+"' id='del_btn' class='profile'>"+
			"<img src='${pageContext.request.contextPath }/assets/images/delete.png'/></a>"+
			"<strong class='guestbook_tit'>" + vo.name + "</strong>" +
			"<p class='guestbook_content'>" + vo.contents.replace(/\n/gi, "<br>")+ "</p>"
			"<strong></strong>"+
			"<a href='#' data-no='"+vo.no+"'>삭제</a>"+
			"</li>";	
			
		if(mode){
			$("#list-guestbook").prepend(html);
		}else{
			$("#list-guestbook").append(html);

		}
	};
	var isEnd = false


	var fetchList = function(){
		var lastNo=lastNo = $('li[data-no]').last().attr("data-no") || 0;

		if(isEnd){
			return;
		}

		$.ajax({
			url:"${pageContext.request.contextPath}/api/guestbook/list/"+lastNo,
			type:"get",
			//contentType:"application/json" //Post 방식 json
			dataType:"json",
			data: "",
			success:function(response){

				if(response.result != "success"){
					console.error(response.message);
					return
				}
				
				//끝페이지 검출  isEnd 검증, detect end
				if(response.data.length == 0){
					isEnd == true;
					$("#btn-next").prop("disabled", true);
					return;
				}
				
				//rendering 
				$.each(response.data, function(index, vo){
					render(vo);
				});				
			},
			error: function(jqXHR, status, e){
				console.error(status + ":" + e);			
			}
		})
	}
	
	$(function(){
		fetchList();
		$("#btn-next").click(function(){
			fetchList();
		});
		$(window).scroll(function(){
			var $window = $(this);
			var scrollTop = $window.scrollTop();
			var windowHeight = $window.height();
			var documentHeight = $(document).height();
			if(scrollTop + windowHeight + 10 > documentHeight){
				fetchList();
			}
		});
		
		// live Event ==> delegation 방식 사용 		
		$(document).on('click', "#del_btn", function(event){
			event.preventDefault();
			$("#hidden-no").val($(this).data('no'));
			$("#dialog-delete-form").dialog("open");
		});
		
		//ajax submit 
		$("#add-form").submit(function(e){
			event.preventDefault();
			var vo = {
			//validation (client side);
			//플러그인으로 처리 필수, 우선 생략 					
				"name" : $("#input-name").val(),
				"password" : $("#input-password").val(),
				"contents" : $("#tx-content").val(),
			}
			//query string 만들어서 보낼 때 
			//console.log(JSON.stringify(vo))
			
			$.ajax({
				url:"${pageContext.request.contextPath}/api/guestbook/list",
				type:"post",
				contentType:"application/json", //Post 방식 json
				dataType:"json",
				data: JSON.stringify(vo),
				success:function(response){
					if(response.result != "success"){
						console.error(response.message);
						return
					}		
					
					//rendering
					render(response.data, true);
					
					//reset form
					//$("#add-form").get(0)
					$("#add-form")[0].reset();
					
				},
				error: function(jqXHR, status, e){
					console.error(status + ":" + e);			
				}
			});
		});
	});
	
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/header.jsp" />
		<div id="content">
			<div id="guestbook-timeline">
				<h1>방명록</h1>
				<form id="add-form" action="${pageContext.servletContext.contextPath }/ass" method="post">
					<input type="text" id="input-name" name="name" placeholder="이름"> <input
						type="password" id="input-password" name="password" placeholder="비밀번호">
					<textarea id="tx-content" name="contents" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" id="submit-btn" />
				</form>
				<div id="list-guestbook">
					<ul id="list-guestbook">
						
					</ul>	
				</div>
						<input type=button value="next-page" id="btn-next">


			</div>
			<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
				<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
				<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
				<form>
					<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all"> 
					<input type="hidden" id="hidden-no" value="${vo.no}"> 
					<input type="submit" tabindex="-1" style="position: absolute; top: -1000px">
				</form>
			</div>
			<div id="dialog-message" title="" style="display: none">
				<p></p>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/navigation.jsp">
			<c:param name="menu" value="guestbook-timeline" />
		</c:import>
		<c:import url="/WEB-INF/views/includes/footer.jsp" />
	</div>
</body>
</html>
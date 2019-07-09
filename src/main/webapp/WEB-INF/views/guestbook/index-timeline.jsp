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
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
$(function(){
	// 업로드 다이알로그
	var dialogUpload = $( "#dialog-delete-form" ).dialog({
		autoOpen: false,
		height: 280,
		width: 300,
		modal: true,
		buttons: {
			"삭제": function() {
				$( "#dialog-delete-form form" ).submit();
				$( this ).dialog( "close" );
			},
			"취소" : function() {
				$( this ).dialog( "close" );
			}
		},
		close: function() {
			$( "#dialog-delete-form form" ).get(0).reset();	
		}
	});
		
	$("#del_btn").click( function(event) {
		event.preventDefault();
		dialogUpload.dialog( "open" );
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
				<form id="add-form" action="" method="post">
					<input type="text" id="input-name" placeholder="이름"> <input
						type="password" id="input-password" placeholder="비밀번호">
					<textarea id="tx-content" placeholder="내용을 입력해 주세요."></textarea>
					<input type="submit" value="보내기" id="submit-btn" />
				</form>
				<div id="list-guestbook">
					<ul>
						
						<li class='guestbook'>
							<span style="background:url(${pageContext.request.contextPath }/assets/images/user.png) no-repeat" class='profile'>123213</span>
							<a href='' data-no='' id='del_btn' class='profile'><img src="${pageContext.request.contextPath }/assets/images/delete.png"/></a>
							<strong class='guestbook_tit'>지나가다가</strong>
							<p class='guestbook_content'>
								별루입니다.<br> 비번:1234 -,.-
							</p> 
						</li>
						
						
						<li class='guestbook'>
							<span style="background:url(${pageContext.request.contextPath }/assets/images/user.png) no-repeat" class='profile'>123213</span>
							<a href='' data-no='' id='del_btn' class='profile'><img src="${pageContext.request.contextPath }/assets/images/delete.png"/></a>
							
							<strong class='guestbook_tit'>둘리</strong>
							<p class='guestbook_content'>
								안녕하세요<br> 홈페이지가 개 굿 입니다.
						</li>
						<li>
							<span style="background:url(${pageContext.request.contextPath }/assets/images/user.png) no-repeat" class='profile'>123213</span>
							<a href='' data-no='' id='del_btn' class='profile'><img src="${pageContext.request.contextPath }/assets/images/delete.png"/></a>
							<strong class='guestbook_tit'>주인</strong>
							<p class='guestbook_content'>
								아작스 방명록 입니다.<br> 테스트~
						</li>
					</ul>
				</div>


		</div>
		<div id="dialog-delete-form" title="메세지 삭제" style="display: none">
			<p class="validateTips normal">작성시 입력했던 비밀번호를 입력하세요.</p>
			<p class="validateTips error" style="display: none">비밀번호가 틀립니다.</p>
			<form>
				<input type="password" id="password-delete" value="" class="text ui-widget-content ui-corner-all"> 
				<input type="hidden" id="hidden-no" value=""> 
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
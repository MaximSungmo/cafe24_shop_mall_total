<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-login.css" rel="stylesheet">
	
	

<script type="text/javascript" src="https://static.nid.naver.com/js/naveridlogin_js_sdk_2.0.0.js" charset="utf-8"></script>
	
	
</head>

<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="login" />
	</c:import>
	<!-- /.Navigation -->

 	<div class="container">
 		<div class="card card-container">
        	<img id="profile-img" class="profile-img-card" src="//ssl.gstatic.com/accounts/ui/avatar_2x.png" />
            <p id="profile-name" class="profile-name-card"></p>
            <c:if test="${result=='fail'}">
                	<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0">
                		로그인에 실패하였습니다.<br/> 다시 시도하여 주세요.
                	</p>
            </c:if>
            <form method="post" action="${pageContext.servletContext.contextPath }/customer/auth" class="form-signin" name="loginForm">
                <span id="reauth-email" class="reauth-email"></span>
                <input type="email" id="inputEmail" class="form-control" placeholder="이메일" name="email" required autofocus>
                <input type="password" id="inputPassword" class="form-control" placeholder="비밀번호" name="password" required>
                
                
				<!-- 네이버아이디로로그인 버튼 노출 영역 -->
				<div id="naverIdLogin"></div>
				<!-- //네이버아이디로로그인 버튼 노출 영역 -->
				
				<!-- 네이버아디디로로그인 초기화 Script -->
				<script type="text/javascript">
					var naverLogin = new naver.LoginWithNaverId(
						{
							clientId: "LJUphYN28JQqObTwLTuY",
							callbackUrl: "http://localhost:8089/customer/loginPostNaver",
							isPopup: false, /* 팝업을 통한 연동처리 여부 */
							loginButton: {color: "green", type: 3, height: 60} /* 로그인 버튼의 타입을 지정 */
						}
					);

					/* 설정정보를 초기화하고 연동을 준비 */
					naverLogin.init();

				</script>
				
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> 자동 로그인
                    </label>
                </div>
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">로그인</button>
                
            </form><!-- /form -->
            <a href="javascript:loginForm.submit();" class="forgot-password">
               	 비밀번호를 잊으셨습니까?
            </a>
        </div>
        <!-- /.card-container -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
	
	
</body>
</html>
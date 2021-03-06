<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container">
		<a class="navbar-brand" href="#">&nbsp;</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">

				<c:choose>
					<c:when test='${param.active == "login" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/">홈</a>
						</li>
						<sec:authorize access="!isAuthenticated()">
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/login">로그인<span class="sr-only">(current)</span></a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/join">회원가입</a>
						</li>
						</sec:authorize>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
					</c:when>
					<c:when test='${param.active == "join" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/">홈</a>
						</li>
						<sec:authorize access="!isAuthenticated()">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/login">로그인</a>
						</li>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/join">회원가입<span class="sr-only">(current)</span></a>
						</li>
						</sec:authorize>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
					</c:when>
					<c:when test='${param.active == "cs" }'>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/">홈</a>
						</li>
						<sec:authorize access="!isAuthenticated()">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/login">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/join">회원가입</a>
						</li>
						</sec:authorize>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터<span class="sr-only">(current)</span></a>
						</li>
					</c:when>					
					<c:otherwise>
						<li class="nav-item active">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/">홈<span class="sr-only">(current)</span></a>
						</li>
						
						<sec:authorize access="!isAuthenticated()">
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/login">로그인</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/join">회원가입</a>
						</li>
						</sec:authorize>
						
						<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/cs">고객센터</a>
						</li>
					</c:otherwise>	
						
				</c:choose>
				
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath }/admin">관리자 페이지</a>
					</li>
					
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item">
						<a class="nav-link" href="${pageContext.servletContext.contextPath }/customer/logout">로그아웃</a>
					</li>
					<sec:authentication property="principal.No" var="user_no" />
           			<input type="hidden" id="user_no" value="${user_no }">
           			<li class="nav-item">
							<a class="nav-link" href="${pageContext.servletContext.contextPath }/product/cart/${user_no }">장바구니</a>
					</li>           			
					
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- Sidebar -->
<ul class="sidebar navbar-nav">
	<li class="nav-item active">
		<a class="nav-link" href="${pageContext.request.contextPath }/admin">
			<i class="fas fa-fw fa-tachometer-alt"></i> 
			<span>Dashboard</span>
		</a>
	</li>
	
	<li class="nav-item dropdown"><a class="nav-link dropdown-toggle"
		href="#" id="pagesDropdown" role="button" data-toggle="dropdown"
		aria-haspopup="true" aria-expanded="false"> <i
			class="fas fa-fw fa-folder"></i> <span>Pages</span>
	</a>
		<div class="dropdown-menu" aria-labelledby="pagesDropdown">
			<h6 class="dropdown-header">Login Screens:</h6>
			<a class="dropdown-item" href="login.html">Login</a> 
			<a class="dropdown-item" href="register.html">Register</a> 
			<a class="dropdown-item" href="forgot-password.html">Forgot Password</a>
			<div class="dropdown-divider"></div>
			<h6 class="dropdown-header">Other Pages:</h6>
			<a class="dropdown-item" href="404.html">404 Page</a> 
			<a class="dropdown-item" href="blank.html">Blank Page</a>
		</div>
	</li>
	
	<li class="nav-item">
		<a class="nav-link" href="charts.html"> 
			<i class="fas fa-fw fa-chart-area"></i>
			<span>Charts</span>
		</a>
	</li>
	<!-- 
	<li class="nav-item"><a class="nav-link"
		href="${pageContext.request.contextPath }/admin/category"> <i
			class="fas fa-fw fa-table"></i> <span>카테고리 관리</span></a>
	</li>
 	-->
	<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
			<i class="fas fa-fw fa-folder"></i> 
			<span>카테고리 관리</span>
		</a>
		<div class="dropdown-menu" aria-labelledby="pagesDropdown">
			<h6 class="dropdown-header">Category Screens:</h6>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/category">카테고리 조회 및 등록</a> 
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/category/update">카테고리 수정 및 삭제</a>
		</div>
	</li>
	
	<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
			<i class="fas fa-fw fa-folder"></i> 
			<span>상품 관리</span>
		</a>
		<div class="dropdown-menu" aria-labelledby="pagesDropdown">
			<h6 class="dropdown-header">Product Screens:</h6>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/product">상품 조회</a> 
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/product/add">상품 등록</a> 
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/product/update">상품 수정 및 삭제</a>
		</div>
	</li>
	
	<li class="nav-item dropdown">
		<a class="nav-link dropdown-toggle" href="#" id="pagesDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
			<i class="fas fa-fw fa-folder"></i> 
			<span>회원 관리</span>
		</a>
		<div class="dropdown-menu" aria-labelledby="pagesDropdown">
			<h6 class="dropdown-header">Customer Screens:</h6>
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/customer">회원 조회</a> 
			<a class="dropdown-item" href="${pageContext.request.contextPath }/admin/customer/update">회원 수정</a>
		</div>
	</li>

</ul>

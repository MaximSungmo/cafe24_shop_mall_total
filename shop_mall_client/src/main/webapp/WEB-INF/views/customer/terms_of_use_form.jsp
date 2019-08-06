<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>cafe24_shoppingmall_KSM</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="${pageContext.servletContext.contextPath }/assets/css/user.css"	rel="stylesheet" type="text/css">
<script	src="${pageContext.servletContext.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
</head>
<body>
	<form:form modelAttribute="userVo" id="join-form" name="joinForm"
		method="post"
		action="${pageContext.servletContext.contextPath}/user/join">
		<label class="block-label" for="name">TITLE</label>
		<input id="title" name="title" type="text" value="${terms_of_use_template.title }">
		
		<label class="block-label" for="etc">CONTENT</label>
		<input id="content" name="content" type="text" value="${terms_of_use_template.content }">
		<h1>${terms_of_use_template}</h1>
		

		<fieldset>
			<legend>동의여부</legend>
			<label>동의</label>
			<form:radiobutton path="AGREEMENT_FL" value="Y" checked="checked" />
			<label>미동의</label>
			<form:radiobutton path="AGREEMENT_FL" value="N" />
		</fieldset>

		<input type="submit" value="제출하기">

	</form:form>
</body>
</html>
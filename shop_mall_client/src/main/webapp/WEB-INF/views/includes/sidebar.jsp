<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-lg-3">
	<h1 class="my-4"><a href="${pageContext.servletContext.contextPath }/">MySite</a></h1>
	<div class="list-group">
		<c:forEach items="${category_list }" var="vo" varStatus="status">
			<c:if test="${vo.parent_no == null }">
				<a href="${pageContext.servletContext.contextPath }/${vo.no}"
					class="list-group-item">${vo.name}</a>
			</c:if>
			<c:forEach items="${category_list }" var="vo2" varStatus="status">
				<c:if test="${vo.no== vo2.parent_no }">
					<a href="${pageContext.servletContext.contextPath }/${vo2.no}"
					class="list-group-item">  >> ${vo2.name}</a>
				</c:if>
			</c:forEach>
			
		</c:forEach>
	</div>
</div>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="orderdetailvo" action="${pageContext.request.contextPath }/test/ab" method="post">
	     <input type="hidden" name="order_detail_list[0].product_option" value="1200/화이트">
	     <input type="hidden" name="order_detail_list[1].product_option" value="1300/화이트">
	     <input type="hidden" name="order_detail_list[2].product_option" value="1400/화이트">
        <input type="submit" value="전송" />
    </form:form>
</body>
</html>
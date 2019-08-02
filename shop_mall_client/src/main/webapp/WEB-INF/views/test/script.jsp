<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>

</script>
</head>
<body>

<form action="" method="post">
	<input type="text" value="" placeholder="src" id="src" name="src"/>
	<input type="text" value="order_basket" placeholder="display_location" id="display_location" name="display_location"/>
	<input type="submit" value="script tags 넣기"/>
<h1>${map.name }</h1>
<button type="button" onclick = "location.href='${pageContext.servletContext.contextPath }/api/scripttag'">Select All script tags</button>
<button value="https://ksm53182.cafe24api.com/api/v2/admin/scripttags">Create script tags</button>
<button type="button" onclick = "location.href='${pageContext.servletContext.contextPath }/api/scripttag/${script_no}'">Delete script tags</button>
</form>
${vo}
<br/>

</body>
</html>
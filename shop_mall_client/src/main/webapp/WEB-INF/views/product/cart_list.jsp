<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>장바구니 확인 페이지</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
	
		
<script type="text/javascript">

function itemSum(frm)
{
   var sum = 0;
   var count = frm.chkbox.length;
   var req_count = 0;
   var price = 0;
   for(var i=0; i < count; i++ ){
       if( frm.chkbox[i].checked == true ){
    	price = frm.chkbox[i].value.split('/')[0];
    	req_count = frm.chkbox[i].value.split('/')[1];
	    sum += parseInt(price * req_count);
       }
   }
   frm.total_sum.value = sum;
}
</script>

</head>


<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->

	<!-- Page Content -->
	<div class="container">

		<div class="row">
			<!-- /.col-lg-3 -->
			<c:import url='/WEB-INF/views/includes/sidebar.jsp'/>
			<div class="col-lg-9">
				<h1> 장바구니 목록 </h1>
				<div id="cart-lists-div">
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.No" var="customer_no" />
					</sec:authorize>					
					<form:form action="${pageContext.servletContext.contextPath }/product/order/${customer_no }">
						<table class="table table-hover">
							<thead>
								<tr>
									<td>상품번호</td>
									<td>상품옵션명</td>
									<td>상품가격</td>
									<td>장바구니 수량</td>
									<td>장바구니 추가 일자</td>
								</tr>
							</thead>
							<tbody id="cart_list">
								
							<c:forEach items="${cart_vo_list }" var="cart_vo" varStatus="status">
								<tr>
									<td>${cart_vo.productDetailVo.no }</td>
									<td>${cart_vo.productDetailVo.product_option }</td>
									<td><input name="chkbox" type="checkbox" value="${cart_vo.productDetailVo.price }/${cart_vo.count }" onClick="itemSum(this.form);">${cart_vo.productDetailVo.price }</td>
									<td>${cart_vo.count }</td>
									<td>${cart_vo.register_dt }</td>
								</tr>
							</c:forEach>
								
							</tbody>	
						</table>
					<input class="form-control" type="text" value="총 결제금액" readonly style = "text-align:center;">
					<input name="total_sum" class="form-control" type="text" size="20" readonly style = "text-align:center;">
					<button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">주문하기</button>
					</form:form>
					
				</div>
			</div>
			<!-- /.col-lg-9 -->
		</div>
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>

</html>
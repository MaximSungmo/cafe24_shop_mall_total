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
	<title>주문 확인 페이지</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
	
		
<script type="text/javascript">
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
				<h1> 주문 목록 </h1>
				<div id="cart-lists-div">
					<sec:authorize access="isAuthenticated()">
						<sec:authentication property="principal.No" var="customer_no" />
					</sec:authorize>					
					<form:form action="${pageContext.servletContext.contextPath }/product/order/${customer_no }/add" >
					
						<input type="text" class="form-control" placeholder="수취인이름" name="receiver_nm">
                		<input type="text" class="form-control" placeholder="수취인주소" name="address">	
						<input type="text" class="form-control" placeholder="수취인전화번호" name="phone_no">
                		<input type="text" class="form-control" placeholder="요청사항" name="customer_request">
                		<input type="text" class="form-control" placeholder="결제방식" name="payment_method" value="무통장입금"/>
                		<input type="hidden" class="form-control" placeholder="결제번호" name="payment_no" value="1"/>
                		<input type="hidden" value="${customer_no }" name="customer_no"/>
                			
										
						<table class="table table-hover">
							<thead>
								<tr>
									<td>상품번호</td>
									<td>상품옵션명</td>
									<td>상품가격</td>
									<td>주문 수량</td>
								</tr>
							</thead>
							<tbody id="cart_list">
								
							<c:forEach items="${cart_vo_list }" var="cart_vo" varStatus="status">
								<tr>
									<td><input type="hidden" name="order_detail_list[${status.index }].product_detail_no" value="${cart_vo.productDetailVo.no }"/>${cart_vo.productDetailVo.no }</td>
									<td><input type="hidden" name="order_detail_list[${status.index }].product_option" value="${cart_vo.productDetailVo.product_option }"/>${cart_vo.productDetailVo.product_option }</td>
									<td><input type="hidden" name="order_detail_list[${status.index }].order_product_price" value="${cart_vo.productDetailVo.price }"/>${cart_vo.productDetailVo.price }</td>
									<td><input type="hidden" name="order_detail_list[${status.index }].order_product_cnt" value="${cart_vo.count }"/>${cart_vo.count }</td>
								</tr>
							</c:forEach>
								
							</tbody>	
						</table>
					<input type="text" name="price" class="form-control"  size="20" readonly value="${total_sum }" style = "text-align:center;">
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
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>

<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">

<script type="text/javascript"> 

$(document).ready(function(){ 
    $(document).ready(function(){
        $('.slider').bxSlider({
        	auto:true,
        	pause:2000
        });
      });
}); 
</script>

</head>

<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="shopping" />
	</c:import>
	<!-- /.Navigation -->
	
	<div class="container">
		<div class="row">

			<c:import url='/WEB-INF/views/includes/sidebar.jsp'/>
			
			<!-- /.col-lg-3 -->

			<div class="col-lg-9">
				<div id="carouselExampleIndicators" class="carousel slide my-4"
					data-ride="carousel">
					<ol class="carousel-indicators">
						<li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
						<li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
					</ol>
					<div class="carousel-inner" role="listbox">
						<div class="carousel-item active slider">
							<img class="d-block img-fluid" src="${pageContext.servletContext.contextPath }/assets/images/main1.jpg" alt="main1">
							<img class="d-block img-fluid" src="${pageContext.servletContext.contextPath }/assets/images/main2.jpg" alt="main2">
							<img class="d-block img-fluid" src="${pageContext.servletContext.contextPath }/assets/images/main3.jpg" alt="main3">
						</div>
						
					</div>
					<a class="carousel-control-prev" href="#carouselExampleIndicators"
						role="button" data-slide="prev"> <span
						class="carousel-control-prev-icon" aria-hidden="true"></span> <span
						class="sr-only">Previous</span>
					</a> <a class="carousel-control-next" href="#carouselExampleIndicators"
						role="button" data-slide="next"> <span
						class="carousel-control-next-icon" aria-hidden="true"></span> <span
						class="sr-only">Next</span>
					</a>
				</div>

				<div class="row">
					<!-- 상품 1개 설명 -->
					<c:forEach items="${product_list }" var="vo" varStatus="status">
					<div class="col-lg-4 col-md-6 mb-4">
						<div class="card h-100">
							<a href="${pageContext.servletContext.contextPath}/product/${vo.no}/detail"></a>
							<div class="slider">
							<!-- product 이미지 표시 -->
							<c:forEach items="${vo.product_image_list}" var="product_image">
								<img  width="300" height="400" class="card-img-top" src="${pageContext.servletContext.contextPath }/assets/image/${product_image.url }">
							</c:forEach>
								
							</div>
							
							<div class="card-body">
								<h4 class="card-title">
									<a href="${pageContext.servletContext.contextPath}/product/${vo.no}/detail">${vo.name}</a>
								</h4>
								<h5></h5>
								<p class="card-text">
									${vo.description}				
									<br/>
									<select name="product_detail_no">
														
									<c:forEach items="${vo.product_detail_list }" var="product_detail">
										<option value="${product_detail.no}">${product_detail.product_option}</option>
										
									</c:forEach>
									
									</select>
								</p>
							</div>
							<div class="card-footer">
								<small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
							</div>
						</div>
					</div>
					</c:forEach>
				
					
				</div>
				<!-- /.row -->
			</div>
			<!-- /.col-lg-9 -->
			
		</div>
		<!-- /.row -->
	</div>
	<!-- /.container -->

	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
	
	
</body>

</html>

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
	<title>Shop Homepage - Start Bootstrap Template</title>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  	<script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
	
		
<script type="text/javascript">
       $(document).ready(function () {
    	   $('.slider').bxSlider({
             	auto:true,
             	pause:2000
             });
    	   
           // 콤보박스가 변경될 때 
           $('#product_option_list').change(function () {
               // 드롭다운리스트에서 선택된 값을 텍스트박스에 출력
               var idx = $("#product_option_list option").index( $("#product_option_list option:selected") );

               var selectedText =  $("#product_option_list option:selected").text();
               
               $('#product_price').val($("#product_option_list option:selected").attr("data-price"));
               $('#product_stock').val($("#product_option_list option:selected").attr("data-stock"));
           });
           
           
           
           
	   		$('#cart_add').click(function() {
	   			var cart_idx = $("#cart_list tr").length;
	   			console.log(cart_idx);
	   			alert('장바구니에 상품이 추가되었습니다.');
	   		 	$('#cart_product_no').text($("#product_option_list option:selected").attr("data-price"));
	   		    $('#cart_list').append(
	   		    		'<tr>'+
	   		    		'<td id="name" data-no="'+$("#product_option_list option:selected").val() +'">'+$("#product_option_list option:selected").text() +'</td>'+
	   		    		'<td id="price">'+$("#product_option_list option:selected").attr("data-price") +'</td>'+
	   		    		'<td id="count" name="count">'+$("#count").val() +'</td>'+
	   		    		'</tr>'
	   		    );
	   		 	
	   		 	
	   		    
	   		 	$("#hidden_cart_list").append(
	   		    	'<input type="hidden" value='+$("#product_option_list option:selected").val()+' name="cartvo_list['+cart_idx+'].product_detail_no" id="product_detail_no['+cart_idx+']"/>'+
	   		    	'<input type="hidden" value='+$("#count").val()+'  name="cartvo_list['+cart_idx+'].count" id="count['+cart_idx+']"/>'
	   		    );
	   		 	$("#count").val('');

	   		});
           
           
       });
       
</script>

<style type="text/css">
	a.image {
		display: block;
		width: 100%;
		height: 100%;
		background-size: 120px 100%;
		background-repeat: no-repeat;
	}
</style>

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

			<c:import url='/WEB-INF/views/includes/sidebar.jsp'/>

			<!-- /.col-lg-3 -->
	
			<div class="col-lg-9">

				<div class="card mt-4">
					<div class="slider">
							<!-- product 이미지 표시 -->
							<c:forEach items="${product_list[0].product_image_list}" var="product_image">
								<img  width="300" height="400" class="card-img-top" src="${pageContext.servletContext.contextPath }/assets/image/${product_image.url }">
							</c:forEach>
					</div>
					<div class="card-body">
						<h3 class="card-title">${product_list[0].name }</h3>
						<table>
							<tr>
								<td><input type="hidden" size="60" name="title"> 
									<label for="product_option_list">옵션</label>
									<select name="product_option_list" id="product_option_list">
										<c:forEach items='${product_list[0].product_detail_list }' var='product_detail_vo' varStatus='status'>
											<option id ="product_option"
												value="${product_detail_vo.no }" 
												data-price="${product_list[0].product_detail_list[status.index].price }"
												data-stock="${product_list[0].product_detail_list[status.index].stock_cnt }" >
												${product_detail_vo.product_option }
											</option>
										</c:forEach>
									</select>
									<label for="product_price">가격</label>
									<input type="text" id="product_price" class="product_detail" value="${product_list[0].product_detail_list[0].price }" disabled="disabled"/>
									<label for="product_stock">재고</label>
									<input type="text" id="product_stock" class="product_detail" value="${product_list[0].product_detail_list[0].stock_cnt }" disabled="disabled"/>
									
								</td>
							</tr>
						</table>
						<sec:authorize access="isAuthenticated()">
						<!-- 장바구니 추가하기 -->
						<input type="text" id="count" name="count" placeholder="요청 개수"/>									
						<button id=cart_add type="button" class="btn btn-sm btn-primary">장바구니 추가</button>
							<sec:authentication property="principal.No" var="customer_no" />
	           				
						<!-- 장바구니 주문하기  -->
						<form:form modelAttribute="cartvo" action="${pageContext.servletContext.contextPath }/product/cart/${customer_no}" method="post">
						<div id="cart-lists-div">
							<table class="table table-hover">
								<thead>
									<tr>
										<td>상품명</td>
										<td>상품가격</td>
										<td>수량</td>
									</tr>
								</thead>
								<tbody id="cart_list">
								
								
								
								</tbody>	
							</table>
						</div>
						<div id="hidden_cart_list">
							<input type="hidden" name="cartvo_list.customer_no" value="${customer_no }"/>
						</div>
						
		                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">장바구니에 담기</button>
						</form:form>
						</sec:authorize>
						<!-- 상품 상세 설명  -->
						<p class="card-text">
						<br/>
							${product_list[0].description }
						</p>
						<span class="text-warning">&#9733; &#9733; &#9733; &#9733;
							&#9734;</span> 4.0 stars
					</div>
				</div>
				<!-- /.card -->

				<div class="card card-outline-secondary my-4">
					<div class="card-header">Product Reviews</div>
					<div class="card-body">
						<p>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.
						</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small>
						<hr>
						<p>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.
						</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small>
						<hr>
						<p>
							Lorem ipsum dolor sit amet, consectetur adipisicing elit.
							Omnis et enim aperiam inventore, similique necessitatibus neque
							non! Doloribus, modi sapiente laboriosam aperiam fugiat laborum.
							Sequi mollitia, necessitatibus quae sint natus.
						</p>
						<small class="text-muted">Posted by Anonymous on 3/1/17</small>
						<hr>
						<a href="#" class="btn btn-success">Leave a Review</a>
					</div>
				</div>
				<!-- /.card -->

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
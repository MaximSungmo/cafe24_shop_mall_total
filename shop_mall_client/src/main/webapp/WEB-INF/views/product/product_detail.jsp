<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>  
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>Shop Homepage - Start Bootstrap Template</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-homepage.css" rel="stylesheet">
</head>
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.9.0.js"></script>
<script type="text/javascript">
       $(document).ready(function () {
           // 콤보박스가 변경될 때 
           $('#product_option_list').change(function () {
               // 드롭다운리스트에서 선택된 값을 텍스트박스에 출력
               var idx = $("#product_option_list option").index( $("#product_option_list option:selected") );
               

               var selectedText =  $("#product_option_list option:selected").text();
               
               
               var hidden_text = $("#hidden_text").text();
               $('#product_price').val($("#product_option_list option:selected").attr("data-price"));
               $('#product_stock').val($("#product_option_list option:selected").attr("data-stock"));

           });
       });
       
       



</script>
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
					<img class="card-img-top img-fluid"
						src="http://placehold.it/900x400" alt="">
					<div class="card-body">
						<h3 class="card-title">${product_list[0].name }</h3>
						<h4>$24.99</h4>
						<table>
							<tr>
								<td><input type="hidden" size="60" name="title"> 
									<select name="product_option_list" id="product_option_list">
										<c:forEach items='${product_list[0].product_detail_list }' var='product_detail_vo' varStatus='status'>
											<option value="${product_detail_vo.no }" 
												data-price="${product_list[0].product_detail_list[status.index].price }"
												data-stock="${product_list[0].product_detail_list[status.index].stock_cnt }" >
												
												${product_detail_vo.product_option }
											</option>
										</c:forEach>
									</select>
									<input type="text" id="product_price" class="product_detail" value="${product_list[0].product_detail_list[0].price }" disabled="disabled"/>
									<input type="text" id="product_stock" class="product_detail" value="${product_list[0].product_detail_list[0].stock_cnt }" disabled="disabled"/>
								</td>
							</tr>
						</table>
						
						<p class="card-text">
							${product_list[0] }
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
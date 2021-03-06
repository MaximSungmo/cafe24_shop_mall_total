<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>주문 조회</title>

<!-- Custom fonts for this template-->
<link
	href="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/fontawesome-free/css/all.min.css"
	rel="stylesheet" type="text/css">

<!-- Page level plugin CSS-->
<link
	href="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/datatables/dataTables.bootstrap4.css"
	rel="stylesheet">

<!-- Custom styles for this template-->
<link
	href="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/css/sb-admin.css"
	rel="stylesheet">


</head>

<body id="page-top">

	<c:import url="/WEB-INF/views/admin/include/navigation.jsp"></c:import>


	<div id="wrapper">

		<c:import url="/WEB-INF/views/admin/include/sidebar.jsp"></c:import>


		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="#">Dashboard</a></li>
					<li class="breadcrumb-item active">Tables</li>
				</ol>

				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> 주문 목록
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%"
								cellspacing="0">
								<thead>
									<tr>
										<th>order_no</th>
										<th>product_detail_no</th>
										<th>product_option</th>
										<th>order_product_price</th>
										<th>order_product_cnt</th>
										<th>합계 금액</th>
										
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${order_list }" var="order_vo">
										<c:if test="${order_vo.no == order_no }"> 
										<c:forEach items="${order_vo.orders_detail_list }" var="order_detail_vo">
										<tr>
											<td>${order_detail_vo.order_no}</td>
											<td>${order_detail_vo.product_detail_no}</td>
											<td>${order_detail_vo.product_option }</td>
											<td>${order_detail_vo.order_product_price }</td>
											<td>${order_detail_vo.order_product_cnt }</td>
											<td>${order_detail_vo.order_product_price } * ${order_detail_vo.order_product_cnt }</td>
										</tr>
										</c:forEach>
										</c:if>
									</c:forEach>
								</tbody>
								<tfoot>
									<tr>
										<th>order_no</th>
										<th>product_detail_no</th>
										<th>product_option</th>
										<th>order_product_price</th>
										<th>order_product_cnt</th>
										<th>합계 금액</th>
									</tr>
								</tfoot>
								
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted">Updated yesterday
						at 11:59 PM</div>
				</div>

				<p class="small text-center text-muted my-5">
					<em>More table examples coming soon...</em>
				</p>

			</div>
			<!-- /.container-fluid -->
			<c:import url="/WEB-INF/views/admin/include/footer.jsp"></c:import>
		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top"> <i
		class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
				<div class="modal-body">Select "Logout" below if you are ready
					to end your current session.</div>
				<div class="modal-footer">
					<button class="btn btn-secondary" type="button"
						data-dismiss="modal">Cancel</button>
					<a class="btn btn-primary" href="login.html">Logout</a>
				</div>
			</div>
		</div>
	</div>

	<!-- Bootstrap core JavaScript-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

	<!-- Core plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/jquery-easing/jquery.easing.min.js"></script>

	<!-- Page level plugin JavaScript-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/datatables/jquery.dataTables.js"></script>
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/vendor/datatables/dataTables.bootstrap4.js"></script>

	<!-- Custom scripts for all pages-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/js/sb-admin.min.js"></script>

	<!-- Demo scripts for this page-->
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/js/demo/datatables-demo.js"></script>

</body>

</html>

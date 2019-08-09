<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>카테고리 관리</title>

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
				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form:form action="${pageContext.servletContext.contextPath }/admin/category/add" modelAttribute="categoryvo" method="POST" class="form">
					<form:input path="name" cssClass="form-control input-lg" placeholder="카테고리명"/>
					<form:input path="parent_no" cssClass="form-control input-lg" placeholder="상위 카테고리"/>
					<button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">카테고리 추가</button>
				</form:form>
				<!-- DataTables Example -->
				<div class="card mb-3">
					<div class="card-header">
						<i class="fas fa-table"></i> 카테고리 목록
					</div>
					<div class="card-body">
						<div class="table-responsive">
							<table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
								<thead>
									<tr>
										<th>No</th>
										<th>카테고리명</th>
										<th>상위 카테고리</th>
									</tr>
								</thead>
								<tfoot>
									<tr>
										<th>No</th>
										<th>카테고리명</th>
										<th>상위 카테고리</th>
									</tr>
								</tfoot>
								<tbody>
									<c:forEach items="${category_list}" var="vo">								
									<tr>
										<td>${vo.no }</td>
										<td>${vo.name }</td>
										<td>${vo.parent_no }</td>				
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<div class="card-footer small text-muted">
						Updated yesterday at 11:59 PM
					</div>
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
	<script
		src="${pageContext.request.contextPath }/assets/startbootstrap-sb-admin-gh-pages/js/demo/chart-area-demo.js"></script>

</body>

</html>

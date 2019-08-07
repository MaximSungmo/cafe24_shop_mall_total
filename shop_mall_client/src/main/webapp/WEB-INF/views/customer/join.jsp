<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">
	<title>회원가입</title>
	<!-- Bootstrap core CSS -->
	<link href="${pageContext.servletContext.contextPath }/assets/bootstrap/css/bootstrap.min.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.servletContext.contextPath }/assets/css/shop-join.css" rel="stylesheet">
<script>
$(function(){
	$('#email').change(function(){
		$('#check-button').show();
		$('#check-image').hide();
	});
	
	$('#check-button').click(function(){
		var email = $('#email').val();
		if(email == ''){
			return;
		}
			
		/* ajax 통신 */
		$.ajax({
			url: "${pageContext.servletContext.contextPath }/user/api/checkemail?email=" + email, 
			type: "get", 
			dataType: "json",
			data: "",
			success: function(response){
				if(response.result != "success"){
					console.log(response);
					//console.error(response.message);
					return;
				}
				
				if(response.data == true){
					alert('이미 존재하는 이메일입니다.\n다른 이메일을 사용해 주세요.');
					$("#email").focus();
					$("#email").val("");
					return;
				}

				$('#check-button').hide();
				$('#check-image').show();
				
			},
			error: function(xhr, error){
				console.error("error:" + error)
			}
		});
		
		console.log(email);
	});	
});

</script>
</head>
<body>
	<!-- Navigation -->
	<c:import url='/WEB-INF/views/includes/navigation.jsp'>
		<c:param name="active" value="join" />
	</c:import>
	<!-- /.Navigation -->
	<div class="container">
 		<div class="card card-container">
        	<form:form action="${pageContext.servletContext.contextPath }/customer/join" 
        	 modelAttribute="customervo" method="POST" class="form">
        	<legend>회원가입</legend>
			<h4>회원 가입 후 많은 서비스를 누려보세요.</h4>
			<div class="row">
				<div class="col-xs-12 col-md-12">
					<form:input path="name" cssClass="form-control input-lg" placeholder="Name"/>
					<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
						<form:errors path="name" />
					</p>				
                </div>
			</div>
			<form:input path="email" cssClass="form-control input-lg" placeholder="Email"/>		
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
				<form:errors path="email" />
			</p>	
			<form:password path="password" cssClass="form-control input-lg" placeholder="Password"/>
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
				<form:errors path="password" />
			</p>							
			<input type="password" name="confirm_password" value="" class="form-control input-lg" placeholder="Confirm Password"  />
			<fieldset>
				<legend>성별</legend>
				<label>여</label> <form:radiobutton path="gender" value="F" checked="checked" />
				<label>남</label> <form:radiobutton path="gender" value="M" />
			</fieldset>
			<form:input path="phone" cssClass="form-control input-lg" placeholder="Phone"/>	
			<p style="font-weight:bold; color:#f00; text-align:left; padding:0; margin:0 ">
				<form:errors path="phone" />
			</p>		
			<form:input path="recommender_id" cssClass="form-control input-lg" placeholder="추천인"/>		
			<br />
			<c:forEach items="${terms_of_use_template_list }" var="terms_of_use" varStatus="status">
				<legend>${terms_of_use.title }</legend>
				<input type="hidden" name="termsofusevolist[${status.index }].title" value="${terms_of_use.title }">
				
				<input type="hidden" name="termsofusevolist[${status.index }].no" value="${terms_of_use.no }" />
				<textarea name="terms_of_use" class="form-control input-lg" readonly="readonly">
					${terms_of_use.content }
				</textarea>
				<input type="hidden" name="termsofusevolist[${status.index }].content" value="${terms_of_use.content }">
				<label class="radio-inline">
				<input type="radio" name="termsofusevolist[${status.index }].use_fl" value="Y" /> 동의</label>
				<label class="radio-inline">
				<input type="radio" name="termsofusevolist[${status.index }].use_fl" value="N" /> 미동의</label>
			</c:forEach>
			<br/>
						
			<span class="help-block">By clicking Create my account, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use.</span>
			<button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">Create my account</button>
			</form:form>
			<!-- /form -->
            <a href="javascript:loginForm.submit();" class="forgot-password">비밀번호를 잊으셨습니까?</a>
        </div>
        <!-- /.card-container -->
	</div>
	<!-- /.container -->
	<!-- Footer -->
	<c:import url='/WEB-INF/views/includes/footer.jsp' />
	<!-- /.Footer -->
</body>
</html>
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
        	<form:form action="${pageContext.servletContext.contextPath }/customer/join" method="POST" class="form" accept-charset="UTF-8">
        	<legend>회원가입</legend>
			<h4>회원 가입 후 많은 서비스를 누려보세요.</h4>
			<div class="row">
				<div class="col-xs-12 col-md-12">
					<input type="text" name="name" value="" class="form-control input-lg" placeholder="Name"  />
                </div>
			</div>
			
			<input type="text" name="email" value="" class="form-control input-lg" placeholder="Your Email"  />
			<input type="password" name="password" value="" class="form-control input-lg" placeholder="Password"  />
			<input type="password" name="confirm_password" value="" class="form-control input-lg" placeholder="Confirm Password"  />
			<label>Birth Date</label>
			<div class="row">
				<div class="col-xs-4 col-md-4">
					<select name="month" class = "form-control input-lg">
						<option value="01">Jan</option>
						<option value="02">Feb</option>
						<option value="03">Mar</option>
						<option value="04">Apr</option>
						<option value="05">May</option>
						<option value="06">Jun</option>
						<option value="07">Jul</option>
						<option value="08">Aug</option>
						<option value="09">Sep</option>
						<option value="10">Oct</option>
						<option value="11">Nov</option>
						<option value="12">Dec</option>
					</select>                        
				</div>
				<div class="col-xs-4 col-md-4">
					<select name="day" class = "form-control input-lg">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
						<option value="16">16</option>
						<option value="17">17</option>
						<option value="18">18</option>
						<option value="19">19</option>
						<option value="20">20</option>
						<option value="21">21</option>
						<option value="22">22</option>
						<option value="23">23</option>
						<option value="24">24</option>
						<option value="25">25</option>
						<option value="26">26</option>
						<option value="27">27</option>
						<option value="28">28</option>
						<option value="29">29</option>
						<option value="30">30</option>
						<option value="31">31</option>
					</select>                        
				</div>
				<div class="col-xs-4 col-md-4">
					<select name="year" class = "form-control input-lg">
						<option value="1935">1935</option>
						<option value="1936">1936</option>
						<option value="1937">1937</option>
						<option value="1938">1938</option>
						<option value="1939">1939</option>
						<option value="1940">1940</option>
						<option value="1941">1941</option>
						<option value="1942">1942</option>
						<option value="1943">1943</option>
						<option value="1944">1944</option>
						<option value="1945">1945</option>
						<option value="1946">1946</option>
						<option value="1947">1947</option>
						<option value="1948">1948</option>
						<option value="1949">1949</option>
						<option value="1950">1950</option>
						<option value="1951">1951</option>
						<option value="1952">1952</option>
						<option value="1953">1953</option>
						<option value="1954">1954</option>
						<option value="1955">1955</option>
						<option value="1956">1956</option>
						<option value="1957">1957</option>
						<option value="1958">1958</option>
						<option value="1959">1959</option>
						<option value="1960">1960</option>
						<option value="1961">1961</option>
						<option value="1962">1962</option>
						<option value="1963">1963</option>
						<option value="1964">1964</option>
						<option value="1965">1965</option>
						<option value="1966">1966</option>
						<option value="1967">1967</option>
						<option value="1968">1968</option>
						<option value="1969">1969</option>
						<option value="1970">1970</option>
						<option value="1971">1971</option>
						<option value="1972">1972</option>
						<option value="1973">1973</option>
						<option value="1974">1974</option>
						<option value="1975">1975</option>
						<option value="1976">1976</option>
						<option value="1977">1977</option>
						<option value="1978">1978</option>
						<option value="1979">1979</option>
						<option value="1980">1980</option>
						<option value="1981">1981</option>
						<option value="1982">1982</option>
						<option value="1983">1983</option>
						<option value="1984">1984</option>
						<option value="1985">1985</option>
						<option value="1986">1986</option>
						<option value="1987">1987</option>
						<option value="1988">1988</option>
						<option value="1989">1989</option>
						<option value="1990">1990</option>
						<option value="1991">1991</option>
						<option value="1992">1992</option>
						<option value="1993">1993</option>
						<option value="1994">1994</option>
						<option value="1995">1995</option>
						<option value="1996">1996</option>
						<option value="1997">1997</option>
						<option value="1998">1998</option>
						<option value="1999">1999</option>
						<option value="2000">2000</option>
						<option value="2001">2001</option>
						<option value="2002">2002</option>
						<option value="2003">2003</option>
						<option value="2004">2004</option>
						<option value="2005">2005</option>
						<option value="2006">2006</option>
						<option value="2007">2007</option>
						<option value="2008">2008</option>
						<option value="2009">2009</option>
						<option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
					</select>                     
				</div>
			</div>
			<label>Gender : </label>                    
			<label class="radio-inline">
			<input type="radio" name="gender" value="M" id=male /> Male</label>
			<label class="radio-inline">
			<input type="radio" name="gender" value="F" id=female /> Female</label>
			<br />
			<c:forEach items="${terms_of_use_template_list }" var="terms_of_use">
				<legend>${terms_of_use.title }</legend>
				<input type="hidden" name="terms_of_use_no" value="${terms_of_use.no }" />
				
				<textarea name="terms_of_use" class="form-control input-lg" readonly="readonly">
					${terms_of_use.content }
				</textarea>
				<label class="radio-inline">
				<input type="radio" name="terms_agree" value="Y" id=agree /> 동의</label>
				<label class="radio-inline">
				<input type="radio" name="terms_agree" value="N" id=disagree /> 미동의</label>
			</c:forEach>
			<br/>
						
			<span class="help-block">By clicking Create my account, you agree to our Terms and that you have read our Data Use Policy, including our Cookie Use.</span>
			<button class="btn btn-lg btn-primary btn-block signup-btn" type="submit">Create my account</button>
			</form:form><!-- /form -->
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
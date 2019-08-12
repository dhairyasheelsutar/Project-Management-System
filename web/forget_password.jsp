<!DOCTYPE html>
<html>
<head>
	<title>Forget Password</title>
	<%@include file = "style.jsp" %>
</head>
<body>
	<div class="container">
		<div class="align-center">
			<div class="row">
				<div class="col-md-3 col-lg-3"></div>
				<div class="col-md-6 col-lg-6">
					<p class="text-danger text-center">${error}</p>
					<form class="card" style="background: #f2f2f2;" action="forget_password" method="post">
						<div class="p-3 bg-primary">
							<h3 class="card-title text-white text-center text-uppercase" style="transform: translateY(15px)">Forget Password</h3>
						</div>
						<div class="card-body">
							<div class="mb-2">
								<label for="email">Email</label>
								<input type="email" name="email" class="form-control form-control-alternative" required /> 
							</div>
							<div class="mt-3 text-center">
								<input type="submit" name="submit" value="Submit" class="btn btn-warning" />
							</div>
							<div class="mt-3 d-flex flex-wrap justify-content-between">
								<a href="index.jsp">Go to Login?</a>
								<a href="register_student">Go to registration</a>
							</div>
						</div>
					</form>
				</div>
				<div class="col-md-3 col-lg-3"></div>
			</div>
		</div>
	</div>
	<%@include file = "script.jsp" %>
</body>
</html>
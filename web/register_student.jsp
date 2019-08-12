
<!DOCTYPE html>
<html>
<head>
	<title>Register Page</title>
	<%@include file = "style.jsp" %>
</head>
<body>
	
	<div class="container">
		<div class="align-center">
			<div class="row">
				<div class="col-md-3 col-lg-3"></div>
				<div class="col-md-6 col-lg-6">
					<p class="text-danger text-center">${error}</p>
					<form class="card" style="background: #f2f2f2;" action="register_student" method="post">
						<div class="p-3 bg-primary">
							<h3 class="card-title text-white text-center text-uppercase" style="transform: translateY(15px)">Register</h3>
						</div>
						<div class="card-body">
							<div class="mb-2">
								<label for="username">Username</label>
								<input type="text" name="username" class="form-control form-control-alternative" required /> 
							</div>
							<div class="mb-2">
								<label for="password">Password</label>
								<input type="password" value="${password}" name="password" class="form-control form-control-alternative" required />
							</div>
							<div class="mb-2">
								<label for="name">Name</label>
								<input type="text" name="name" value="${name}" class="form-control form-control-alternative" required />
							</div>
							<div class="mb-2">
								<label for="email">Email</label>
								<input type="email" name="email" value="${email}" class="form-control form-control-alternative" required />
							</div>
							<div class="mb-2">
								<label for="mobileno">Mobile Number</label>
								<input type="text" name="mobileno" value="${mobileno}" class="form-control form-control-alternative" required />
							</div>
							<div class="mt-3 text-center">
								<input type="submit" name="register" value="Register" class="btn btn-warning" />
							</div>
							
							<div class="mt-3 text-center">
								<a href="index.jsp">Go to login form</a>
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
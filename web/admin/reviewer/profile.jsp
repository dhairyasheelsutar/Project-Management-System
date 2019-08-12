<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Student Panel</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
    	<h1 class="text-white text-uppercase">Update profile</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<div class="row">
						<div class="col-md-3 col-lg-3"></div>
						<div class="col-md-6 col-lg-6">
							<p class="text-danger text-center">${result}</p>
							<form class="card shadow-lg p-3" action="update_student_profile" method="post">
								<div class="card-body">
									<div class="mb-2">
										<input type="hidden" name="username" value="${username}" />
										<label for="username">User name</label>
										<input type="text" value="${username}" class="form-control " disabled required /> 
									</div>
									<div class="mb-2">
										<label for="password">Password</label>
										<input type="text" name="password" value="${password}" class="form-control " required />
									</div>
									<div class="mb-2">
										<label for="name">Name</label>
										<input type="text" name="name" value="${name}" class="form-control " required/>
									</div>
									<div class="mb-2">
										<label for="email">Email</label>
										<input type="email" name="email" value="${email}" class="form-control "  required />
									</div>
									<div class="mb-2">
										<label for="mobno">Mobile No</label>
										<input type="text" name="mobno" value="${mobno}" class="form-control"  required />
									</div>
									<div class="mt-3 text-center">
										<input type="submit" name="register" value="Update" class="btn btn-warning" />
										</div>
									</div>
							</form>
						</div>
						<div class="col-md-3 col-lg-3"></div>
					</div>	
    	</div>
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Student Panel</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>

  <div class="main-content">
	<%@include file="navbar.jsp" %>
	   <!-- Header -->
	    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
	    	<h1 class="font-weight-bold text-white text-uppercase">Add project members</h1>
    	</div>
	    
	    
	    <div class="container-fluid mt--7">
	    	<div class="row">
						<div class="col-md-3 col-lg-3"></div>
				<div class="col-md-6 col-lg-6">
					<p class="text-danger text-center">${error}</p>
					<form class="card shadow-lg p-3" action="insert_teacher" method="post">
						<div class="card-body">
							<div class="mb-2">
								<label for="username">Username</label>
								<input type="text" name="username" class="form-control " required /> 
							</div>
							<div class="mb-2">
								<label for="password">Password</label>
								<input type="password" name="password" class="form-control " required />
							</div>
							<div class="mb-2">
								<label for="name">Name</label>
								<input type="text" name="name" class="form-control " required />
							</div>
							<div class="mb-2">
								<label for="email">Email</label>
								<input type="email" name="email" class="form-control " required />
							</div>
							<div class="mb-2">
								<label for="mobileno">Mobile Number</label>
								<input type="text" name="mobileno" class="form-control " required />
							</div>
							<label>Register as</label>
							<div class="d-flex flex-wrap">
								<div class="custom-control custom-checkbox mb-3">
  									<input class="custom-control-input" name="teacher" value="0" id="coordinator" type="checkbox">
  									<label class="custom-control-label" for="coordinator">Coordinator</label>
								</div>
								<div class="custom-control custom-checkbox mb-3 ml-2">
  									<input class="custom-control-input" name="teacher" value="1" id="member" type="checkbox">
  									<label class="custom-control-label" for="member">Committee-member</label>
								</div>
								<div class="custom-control custom-checkbox mb-3 ml-2">
  									<input class="custom-control-input" name="teacher" value="2" id="assistant" type="checkbox">
  									<label class="custom-control-label" for="assistant">Committee-assistant</label>
								</div>
								<div class="custom-control custom-checkbox mb-3 ml-2">
  									<input class="custom-control-input" name="teacher" value="3" id="guide" type="checkbox">
  									<label class="custom-control-label" for="guide">Guide</label>
								</div>
								<div class="custom-control custom-checkbox mb-3 ml-2">
  									<input class="custom-control-input" name="teacher" value="4" id="reviewer" type="checkbox">
  									<label class="custom-control-label" for="reviewer">Reviewer</label>
								</div>
							</div>
							
							<div class="mt-3 text-center">
								<input type="submit" name="register" value="Register" class="btn btn-warning" />
							</div>
							
							
						</div>
					</form>
				</div>
				<div class="col-md-3 col-lg-3"></div>
			</div>	
	    </div>
	    
    </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
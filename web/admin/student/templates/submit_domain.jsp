<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Student Panel</title>
  	<%@include file="../../../style.jsp" %>
</head>

<body>
  <!-- Main content -->
  <div class="main-content">
	<%@include file="../navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
    	<h1 class="text-white text-uppercase">Submit project domain and information</h1>
    </div>
    <!-- Page content -->
    <div class="container mt--7">
    	<div>
    		<div class="row">
						<div class="col-md-2 col-lg-2"></div>
						
						<div class="col-md-8 col-lg-8">
							<p class="text-danger text-center">${error}</p>
							<p class="text-center">${output}</p>
					<form class="card shadow-lg p-3" action="submit_project_info" method="post">

						<div class="card-body row">
								
							<div class="col-md-6 col-lg-6">
								<div class="mb-2">
									<label for="title">Project title</label>
									<input type="text" name="title" class="form-control" required /> 
								</div>
								<div class="mb-2">
									<label for="project_domain">Project domain</label>
									${project_domain}
								</div>
								<div>
									<div class="font-weight-bold">Sponsorship Details</div>
									<div class="mb-2">
										<label for="company_name">Company Name</label>
										<input type="text" name="company_name" class="form-control"  /> 
									</div>
									<div class="mb-2">
										<label for="external_guide">Name of external guide</label>
										<input type="text" name="external_guide" class="form-control" /> 
									</div>
								</div>
							</div>
							<div class="col-md-6 col-lg-6">
								<div class="mb-2">
									<label>Choose Domains</label>
									${domain_list}
								</div>
							</div>
						</div>
						<div class="mt-3 text-center">
								<input type="submit" name="login" value="Submit" class="btn btn-warning" />
							</div>
					</form>
						</div>
						<div class="col-md-2 col-lg-2"></div>
					</div>
    	</div>
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../../script.jsp" %>
 
</body>

</html>
<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Student Panel</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>

	<%@include file="sidebar.jsp" %>
	
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-gradient-primary pb-8 pt-5 pt-lg-8">
    	<h1 class="text-white text-uppercase text-center">Create a task and attach a template to it.</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<div class="row">
							<div class="col-md-3 col-lg-3"></div>
							<div class="col-md-6 col-lg-6">
								<p class="text-danger text-center">${output}</p>
								<form class="card shadow-lg" action="insert_schedule" method="post">
									<div class="card-body">
										<input type = "hidden" class="template_file_src" name = "template_file_src" value = ""/>
										<div class="mb-2">
											<label for="start_date">Start date</label>
											<input type="date" name="start_date" class="form-control" required /> 
										</div>
										<div class="mb-2">
											<label for="end_date">Expiry date</label>
											<input type="date" name="end_date" class="form-control" required />
										</div>
										<div class="mb-2">
											<label for="message">Description</label>
											<textarea name="message" class="form-control"></textarea>
										</div>
										<div class="mb-2">
											<a href="#" data-toggle="modal" data-target="#myModal">Add template</a>
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
      <!-- Footer -->
    </div>
  </div>
 
			<div class="modal fade" id="myModal">
    			<div class="modal-dialog modal-dialog-centered modal-lg" style="width: 100vw; height: 90vh; max-width: 100vw;">
      				<div class="modal-content" style="width: 100vw; height: 100vh; max-width: 100vw;"> 
	        			<!-- Modal Header -->
	        			<div class="modal-header">
	        				<h4>Select template to preview</h4>
	          				<button type="button" class="close" data-dismiss="modal">&times;</button>
	        			</div>
	        
	        			<!-- Modal body -->
	        			<div class="modal-body">
	          				<div class="row">
	          					<div class="col-md-3 col-lg-3">
	          						<h4>Select template</h4>
	          						${files}
	          					</div>
	          					<div class="col-md-9 col-lg-9">
	          						<div class="embed-responsive embed-responsive-16by9" style="border: 3px solid #d3d3d3;">
	          							<iframe class="template_files embed-responsive-item" src = ""></iframe>
	          						</div>
	          					</div>
	          				</div>
	        			</div>
	        
	        			<!-- Modal footer -->
	        			<div class="modal-footer">
	          				<button type="button" class="btn btn-warning select_template">Select template</button>
	        			</div>
      				</div>
    			</div>
  			</div>
		</div>
		<%@include file = "../../script.jsp" %>
	</body>
</html>
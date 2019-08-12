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
	    <div class="header bg-gradient-primary pb-8 pt-5 pt-lg-8 d-flex align-items-center">
	      <!-- Header container -->
	      <div class="container-fluid">
	        <div class="row justify-content-center">
	        	<div class="text-center">
	          		<p class="text-white display-4">List of staff members</p>
	          	</div>
	        </div>
	      </div>
	    </div>
	    
	    <div class="container-fluid mt--7">
		    <div class="card shadow-lg py-4">
		    	<div class="d-flex flex-wrap justify-content-center">
					<h4 class="text-center mx-1">Domain</h4>
					<a data-toggle="modal" href="#" data-target="#add_domain" class="btn btn-warning mx-1">Add new</a>
				</div>
				<p>${domain_result}</p>
				<div class="table-responsive">
					<table class="table table-flush">
						<thead>
							<tr class="text-center text-uppercase">
								<th>ID</th>
								<th>DOMAIN</th>
								<th>Options</th>
							</tr>
						</thead>
						<tbody>
							${domain}
						</tbody>
					</table>
				</div>
		    </div>
	    </div>
    
    </div>
    
    
		 <!-- The Modal -->
  		<div class="modal fade" id="update_domain">
    		<div class="modal-dialog modal-dialog-centered modal-lg">
      			<div class="modal-content">
		        	<!-- Modal Header -->
				    <div class="modal-header">
				       	<h4 class="modal-title">Update Domain</h4>
				          	<button type="button" class="close" data-dismiss="modal">&times;</button>
				        </div>
		        
				        <!-- Modal body -->
				        <div class="modal-body">
				        	<form action="update_domain" method="post">
								<div class="card-body">
									<input type="hidden" id="did" name="did" />
									<div class="mb-2">
										<label for="dname">Domain name</label>
										<input type="text" id="dname" name="dname" class="form-control" required /> 
									</div>
									<div class="mt-3 text-center">
										<input type="submit" name="update_val" value="update" class="btn btn-warning" />
									</div>
								</div>
							</form>
					</div>
      			</div>
    		</div>
  		</div>
  		
  		<div class="modal fade" id="add_domain">
    		<div class="modal-dialog modal-dialog-centered modal-lg">
      			<div class="modal-content">
		        	<!-- Modal Header -->
				    <div class="modal-header">
				       	<h4 class="modal-title">Add Domain</h4>
				          	<button type="button" class="close" data-dismiss="modal">&times;</button>
				        </div>
		        
				        <!-- Modal body -->
				        <div class="modal-body">
				        	<form action="add_domain" method="post">
								<div class="card-body">
									<input type="hidden" id="subdid" name="subdid" />
									<div class="mb-2">
										<label for="domain">Domain name</label>
										<input type="text" id="domain" name="domain" class="form-control" required /> 
									</div>
									<div class="mt-3 text-center">
										<input type="submit" name="add_domain" value="Add domain" class="btn btn-warning" />
									</div>
								</div>
							</form>
					</div>
      			</div>
    		</div>
  		</div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
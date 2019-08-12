<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Committee Assistant</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
	   <!-- Header -->
	    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
	    	<h1 class="font-weight-bold text-white text-uppercase">Committee Assistant</h1>
    	</div>
    </div>
    
    <div class="container-fluid mt--7">
    	<div class="row">
    		<div class="col-md-6 col-lg-6">
    			<div class="card shadow-lg">
    				<div class="card-header">
    					<div class="d-flex flex-wrap justify-content-between px-3">
    						<div class="header-text">Domains</div>
    						<a data-toggle="modal" href="#" data-target="#add_domain" class="btn btn-warning mx-1">Add new</a>
    					</div>	
    				</div>
    				<div class="card-body">
    					<table class="table table-flush">
							<thead>
								<tr class="text-center">
									<th>ID</th>
									<th>Domain</th>
									<th>Options</th>
								</tr>
							</thead>
							<tbody>${domain}</tbody>
						</table>
    				</div>
    			</div>
    		</div>
    		<div class="col-md-6 col-lg-6">
    			<div class="card shadow-lg">
    				<div class="card-header">
    					<div class="d-flex flex-wrap justify-content-between px-3">
    						<div class="header-text">Staff Members</div>
    						<a href="insert_teacher" class="btn btn-warning mx-1">Add new</a>
    					</div>
    				</div>
    				<div class="card-body">
    					<table class="table table-flush">
							<thead>
								<tr class="text-center">
									<th>Name</th>
									<th>Email</th>
									<th>Mobile No</th>
								</tr>
							</thead>
							<tbody>${output}</tbody>
						</table>
    				</div>
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
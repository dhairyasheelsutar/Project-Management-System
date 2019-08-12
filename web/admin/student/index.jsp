<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Welcome student</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
    	<h1 class="text-white text-uppercase">Welcome Student</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card shadow-lg">
    		<div class="card-header">
    			<div class="text-uppercase text-danger font-weight-bold" style="font-size: 13px; letter-spacing: 1.3px;">Group logs</div>
    		</div>
    		<div class="card-body">
    			${group_logs}
    		</div>
    	</div>
    	<br>
    	
    	<div class="row">
    		<div class="col-md-6 col-lg-6">
    			<div class="card shadow-lg">
    				<div class="card-header">
    					<div class="text-uppercase text-danger font-weight-bold" style="font-size: 13px; letter-spacing: 1.3px;">Project Management system Schedule</div>
    				</div>
    				<div class="card-body">
    					<table class="table table-flush">
							<thead>
								<tr class="text-center">
									<th>Start date</th>
									<th>End date</th>
									<th>Description</th>
								</tr>
							</thead>
							<tbody>${schedule}</tbody>
						</table>
    				</div>
    			</div>
    		</div>
    		<div class="col-md-6 col-lg-6">
    			<div class="card shadow-lg">
    				<div class="card-header">
    					<div class="text-uppercase text-danger font-weight-bold" style="font-size: 13px; letter-spacing: 1.3px;">Staff areas of interest</div>
    				</div>
    				<div class="card-body">
    					<table class="table table-flush">
							<thead>
								<tr class="text-center">
									<th>Name</th>
									<th>Domains</th>
								</tr>
							</thead>
							<tbody>${staff_list}</tbody>
						</table>
    				</div>
    			</div>
    		</div>
    	</div><br>
    	
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
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
    	<h1 class="text-white text-uppercase text-center">Manage Project Schedule</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card text-center py-5 shadow-lg">
    		<div class="d-flex flex-wrap justify-content-center">
				<h4 class="text-center header-text mr-2">Schedule</h4>
				<a href="insert_schedule" class="btn btn-warning ml-2" style="transform: translateY(-15px)">Add new</a>
			</div>
			<p class="text-center">${error_msg}</p>
			<table class="table table-flush">
				<thead>
					<tr class="text-center">
						<th>ID</th>
						<th>Start date</th>
						<th>End date</th>
						<th>Template</th>
						<th>Description</th>
						<th>Options</th>
					</tr>
				</thead>
				<tbody>${output}</tbody>
			</table>
    	</div>
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Coordinator Panel</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>

	<%@include file="sidebar.jsp" %>
	
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-gradient-primary pb-8 pt-5 pt-lg-8">
    	<h1 class="text-white text-uppercase text-center">Staff Areas of interest</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card text-center shadow-lg">
    		<p class="text-center">${error}</p>
			<h4 class="text-center header-text mt-4">Staff Areas of interest</h4><br>
			<table class='table table-flush'>
				<thead>
					<tr class="text-center">
						<th>Name</th>
						<th>Email</th>
						<th>Mobile no</th>
						<th>Domains</th>
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
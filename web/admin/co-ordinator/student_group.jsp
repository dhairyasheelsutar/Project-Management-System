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
    	<h1 class="text-white text-uppercase text-center">Manage project group</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card text-center shadow-lg">
    		<p class="text-center">${error}</p>
			<h4 class="text-center header-text mt-4">Student groups</h4><br>

			<table class='table table-flush'>
				<thead>
					<tr class="text-center">
						<th>Group ID</th>
						<th>Project Title</th>
						<th>Members</th>
						<th>Guide</th>
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
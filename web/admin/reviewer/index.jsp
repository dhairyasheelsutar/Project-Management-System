<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Welcome Reviewer</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
    	<h1 class="text-white text-uppercase">Groups alloted</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card text-center py-5 shadow-lg">
			<table class="table table-flush">
				<thead>
					<tr class="text-center">
						<th>Group ID</th>
						<th>Project Title</th>
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
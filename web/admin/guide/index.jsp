<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Welcome guide</title>
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
    
    <div class="container-fluid mt--7">
    	<div class="card py-4 shadow-lg">
    		<p class="text-center">${error}</p>
    		<h4 class="text-center">Group Alloted</h4><br>
			<table class='table table-flush'>
				<thead>
					<tr class="text-center">
						<th>Group ID</th>
						<th>Project Title</th>
						<th>Project Domain</th>
						<th>Options</th>
					</tr>
				</thead>
				<tbody>${output}</tbody>
			</table>
    	</div>
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
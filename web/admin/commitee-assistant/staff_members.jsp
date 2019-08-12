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
	    	<div class="card text-center py-5 shadow-lg">
	    		<div class="d-flex flex-wrap justify-content-center">
				<h4 class="text-center mr-2">staff members</h4>
				<a href="insert_teacher" class="btn btn-warning ml-2" style="transform: translateY(-15px)">Add new</a>
			</div>
			<table class="table table-flush">
				<thead>
					<tr class="text-center">
						<th>Name</th>
						<th>Email</th>
						<th>Mobile no</th>
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
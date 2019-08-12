
<!DOCTYPE html>
<html>
	<head>
		<title>Register Staff Page</title>
		<%@include file = "../../style.jsp" %>
	</head>
	<body>
		<div class="d-flex flex-wrap">
			<div class="sidebar shadow-sm">
				<ul class="list-group list-admin">
					<li class="list-group-item list-admin-item"><a href="index_member">DashBoard</a></li>
				</ul>
			</div>
			<div class="active-area">
				<nav class="navbar navbar-expand-md shadow-sm bg-white">
				 	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
				    	<span class="navbar-toggler-icon"></span>
				  	</button>
	  				<div class="collapse navbar-collapse" id="collapsibleNavbar">
	    				<ul class="navbar-nav ml-auto ">
	      					<li class="nav-item">
	        					<div class="dropdown">
	  								<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
	    								${name} 
	  								</button>
								  	<div class="dropdown-menu">
								    	<a class="dropdown-item" href="#">Profile</a>
								    	<a class="dropdown-item" href="logout">Logout</a>
								  	</div>
								</div>
	      					</li>
	    				</ul>
	  				</div>  
				</nav>
				<div class="container-fluid">
					<div class="padding-div">
						<h1 class="text-center">Welcome to committee-member panel</h1>	
					</div>
				</div>
			</div>
			
		</div>
	
	
	
		
		<%@include file = "../../script.jsp" %>
	</body>
</html>
<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Welcome student</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>

	
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-primary pb-8 pt-5 pt-lg-8 d-flex align-items-center">
      <!-- Header container -->
      <div class="container">
        <div class="row justify-content-center">
        	<div class="text-center">
          		<p class="text-white display-4">All updates will be listed here</p>
          	</div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card text-center p-5 shadow-lg">
    		${output}
    	</div>
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
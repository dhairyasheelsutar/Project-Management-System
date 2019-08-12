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
    <div class="header bg-gradient-primary pb-8 pt-5 pt-lg-8 d-flex align-items-center">
   		
      <div class="container-fluid">
      <h1 class="text-white text-uppercase text-center">Project co-ordinator</h1><br><br>
        <div class="header-body">
          <!-- Card stats -->
          <div class="row">
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Students</h5>
                      <span class="h2 font-weight-bold mb-0">${student}</span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-danger text-white rounded-circle shadow">
                        <i class="fa fa-chart-bar"></i>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Staff</h5>
                      <span class="h2 font-weight-bold mb-0">${staff}</span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-warning text-white rounded-circle shadow">
                        <i class="fa fa-chart-pie"></i>
                      </div>
                    </div>
                  </div>
             
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Group</h5>
                      <span class="h2 font-weight-bold mb-0">${group}</span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-yellow text-white rounded-circle shadow">
                        <i class="fa fa-users"></i>
                      </div>
                    </div>
                  </div>
                
                </div>
              </div>
            </div>
            <div class="col-xl-3 col-lg-6">
              <div class="card card-stats mb-4 mb-xl-0">
                <div class="card-body">
                  <div class="row">
                    <div class="col">
                      <h5 class="card-title text-uppercase text-muted mb-0">Domains</h5>
                      <span class="h2 font-weight-bold mb-0">${domain}</span>
                    </div>
                    <div class="col-auto">
                      <div class="icon icon-shape bg-info text-white rounded-circle shadow">
                        <i class="fa fa-percent"></i>
                      </div>
                    </div>
                  </div>
               
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div class="card p-5 shadow-lg">
	    	<div class="d-flex flex-wrap justify-content-center">
	    		<p class="header-text text-center mr-2 mt-3">Search here:</p>
	    		<form method="get" action="search" class="text-right">
	    			<div class="input-group">
	  					<select name="activity" class="form-control">
	  						<option value="null">Select option</option>
	  						<option value="1">Students not formed group</option>
	  						<option value="2">Groups not submitted domain</option>
	  						<option value="3">Groups not submitted guide preferences</option>
	  						<option value="4">Groups not submitted project synopsis</option>
	  						<option value="5">Groups not reviewed-I</option>
	  						<option value="6">Groups not reviewed-II</option>
	  						<option value="7">Groups not reviewed-III</option>
	  						<option value="8">Groups not reviewed-IV</option>
	  					</select>
	  					<div class="input-group-append">
	    					<button class="btn btn-warning" type="submit"><span class="fa fa-search"></span></button> 
	  					</div>
					</div>
	    		</form>
	    	</div>
	    	
	    	<div>
	    		${output}
	    	</div>
    	</div><br>
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
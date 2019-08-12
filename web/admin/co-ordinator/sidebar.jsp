
  	<nav class="navbar navbar-vertical fixed-left navbar-expand-md navbar-light bg-white" id="sidenav-main">
    	<div class="container-fluid">
      	<!-- Toggler -->
      	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle navigation">
        	<span class="navbar-toggler-icon"></span>
     	 </button>
      	
      	<!-- User -->
      	<ul class="nav align-items-center d-md-none">
        <li class="nav-item dropdown">
	          <div class="dropdown-menu dropdown-menu-arrow dropdown-menu-right">
	            	<div class=" dropdown-header noti-title">
	              		<h6 class="text-overflow m-0">Welcome!</h6>
	            	</div>
	            	<a href="staff_profile" class="dropdown-item">
	              		<i class="ni ni-single-02"></i>
	              		<span>My profile</span>
	            	</a>
		            <div class="dropdown-divider"></div>
		            <a href="logout" class="dropdown-item">
		              	<i class="ni ni-user-run"></i>
		              	<span>Logout</span>
		            </a>
	          </div>
        </li>
      </ul>
      <!-- Collapse -->
      <div class="collapse navbar-collapse" id="sidenav-collapse-main">
        	<!-- Collapse header -->
        	<div class="navbar-collapse-header d-md-none">
          	<div class="row">

            <div class="col-12 collapse-close">
              	<button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#sidenav-collapse-main" aria-controls="sidenav-main" aria-expanded="false" aria-label="Toggle sidenav">
                	<span></span>
                	<span></span>
              	</button>
            </div>
          </div>
        </div>
        
        <!-- Navigation -->
        <ul class="navbar-nav">
          	${menu1}
          	${menu2}
          	${menu3}
          	${menu4}
          	${menu5}
        </ul>
     
			</div>
    	</div>
 	</nav>
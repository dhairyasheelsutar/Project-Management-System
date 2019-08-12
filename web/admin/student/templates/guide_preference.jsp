<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Student Panel</title>
  	<%@include file="../../../style.jsp" %>
</head>

<body>

  <!-- Main content -->
  <div class="main-content">
	<%@include file="../navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-gradient-primary pb-8 pt-5 pt-lg-8 d-flex align-items-center">
      <!-- Header container -->
      <div class="container-fluid">
        <div class="row justify-content-center">
        	<div class="text-center">
          		<p class="text-white display-4">Guide preference form</p>
          	</div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<div class="row">
						<div class="col-md-3 col-lg-3"></div>
						<div class="col-md-6 col-lg-6">
							<p class="text-danger text-center">${error}</p>
							<p class="text-center">${result}</p>
							<form class="card shadow-lg p-3" action="submit_guide_preference" method="post">
								<div class="card-body">
									<div class="mb-2">
										<label for="pref1">Preference 1</label>
										<select name="pref1" class="form-control" required>
											${guidelist}
										</select>
										
									</div>
									<div class="mb-2">
										<label for="pref2">Preference 2</label>
										<select name="pref2" class="form-control" required>
											${guidelist}
										</select>
									</div>
									<div class="mb-2">
										<label for="pref3">Preference 3</label>
										<select name="pref3" class="form-control" required>
											${guidelist}
										</select>
									</div>
									<div class="mt-3 text-center">
										<input type="submit" name="register" value="submit" class="btn btn-warning" />
									</div>
								</div>
							</form>
						</div>
						<div class="col-md-3 col-lg-3"></div>
					</div>
    	</div>
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../../script.jsp" %>
 
</body>

</html>
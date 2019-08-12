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
          		<p class="text-white display-4">Submit project abstract</p>
          	</div>
        </div>
      </div>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<div class="row">
						<div class="col-md-2 col-lg-2"></div>
						<div class="col-md-8 col-lg-8">
							<p class="text-danger text-center">${error}</p>
							<p class="text-center">${result}</p>
							
							<form class="card p-3 shadow-lg" action="abstract_submit" method="post" id="abs-form">
								<div class="card-body row">
									<div class="col-md-6 col-lg-6">
										<div class="mb-2">
											<label for="background">Background Of Project</label>
											<input type="text" class="form-control" name="background" required>
										</div>
										<div class="mb-2">
											<label for="methods">Method Used</label>
											<input type="text" class="form-control" name="methods" required>
										</div>
										<div class="mb-2">
											<label for="conclusion">Conclusion</label>
											<input type="text" class="form-control" name="conclusion" required>
										</div>
									</div>
									<div class="col-lg-6 col-md-6">
											<div class="mb-2">
											<label for="purpose">Aim/Purpose Of Project</label>
											<textarea rows="4" class="form-control" name="purpose" form="abs-form"></textarea>
										</div>
									
										<div class="mb-2">
											<label for="result">Findings/Results</label>
											<textarea rows="4" class="form-control" name="results" form="abs-form"></textarea>
										</div>
									</div>
									
									
						
								</div>
								<div class="mt-3 text-center">
										<input type="submit" name="register" value="submit" class="btn btn-warning" />
									</div>
							</form>
						</div>
						<div class="col-md-2 col-lg-2"></div>
					</div>
    	</div>
      <!-- Footer -->
    </div>
  </div>
 
 	<%@include file="../../../script.jsp" %>
 
</body>

</html>
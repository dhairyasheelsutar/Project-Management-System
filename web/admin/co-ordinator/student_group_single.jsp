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
    	<h1 class="text-white text-uppercase text-center">Individual Group information</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<form class="card shadow-lg p-5" action="update_student_group" method="post">
					<div>
						<div class="col-offset-1">${output}</div>
						<div class="row">
							
							<div class="col-md-6 col-lg-6">
										
											<div class="mb-2">
												<input type="hidden" name="sponsor_id" value="${sponsor_id}" required />
												<input type="hidden" name="groupid" value="${grpid}" required />
												<label>Group ID</label>
												<input type="text" value="${grpid}" disabled class="form-control" />
											</div>

											<div class="mb-2">
												<label for="project_title">Pbs ID</label>
												<input type="text" name="pbs_id" value="${pbs_id}" class="form-control" required />
											</div>

											<div class="mb-2">
												<label for="project_title">Project Title</label>
												<input type="text" name="project_title" value="${project_title}" class="form-control" required />
											</div>
											<div class="mb-2">
												<label for="project_domain">Project Domain</label>
												${select_domain}									
											</div>
											<div class="mb-2">
												<label for="guide">Choose Guide</label>
												${select_guide}
											</div>
											<p class="header-text">Group members</p>
											
											<table class="table table-flush">
												<thead>
													<tr>
														<th>Roll No</th>
													    <th>Name</th>
													    <th>Email</th>
													    <th>Mobile number</th>
													</tr>
												</thead>
												<tbody>
													${group_members}
												</tbody>
											</table>
									</div>
									<div class="col-md-6 col-lg-6">
											<p class="header-text">Sponsorship Details</p>
											<div class="mb-2">
												<label for="company_name">Company Name</label>
												<input type="text" name="company_name" value="${company_name}" class="form-control" /> 
											</div>
											<div class="mb-2">
												<label for="external_guide">External Guide</label>
												<input type="text" name="external_guide" value="${external_guide}" class="form-control" /> 
											</div>
											
											<div class="mb-2">
												<label for="review1">Select Reviewer 1</label>
												${select_reviewer1}
											</div>
											
											<div class="mb-2">
												<label for="review2">Select Reviewer 2</label>
												${select_reviewer2}
											</div>
											<div class="mt-3 text-right">
												<input type="submit" name="submit" value="save" class="btn btn-warning" />
											</div>
									</div>
								</div>
								
						
						</div>
					</form>
    	</div>
      <!-- Footer -->
    </div>
 </div><br>
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
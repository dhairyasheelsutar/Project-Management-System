<!DOCTYPE html>
<html>

<head>
  	<meta charset="utf-8">
  	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  	<title>Reviewer Panel</title>
  	<%@include file="../../style.jsp" %>
</head>

<body>
  <!-- Main content -->
  <div class="main-content">
	<%@include file="navbar.jsp" %>
   <!-- Header -->
    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
    	<h1 class="text-white text-uppercase">Review GroupID : ${grpid}</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<div class="card shadow-lg p-5">
					<div>
						<div class="col-offset-1">${output}</div>
						<div class="row">
							
							<div class="col-md-6 col-lg-6">
								<p class="header-text">Project Information</p>
								<div class="mb-2">
									<input type="hidden" name="groupid" value="${grpid}" required />
									<label>Group ID</label>
									<input type="text" value="${grpid}" disabled class="form-control" />
								</div>
					
								<div class="mb-2">
									<label for="project_title">Project Title</label>
									<input type="text" disabled name="project_title" value="${project_title}" class="form-control" required />
								</div>
								<div class="mb-2">
									<label for="project_domain">Project Domain</label>
									${select_domain}									
								</div>
								<div class="mb-2">
									<label for="guide">Choose Guide</label>
									${select_guide}
								</div>
							</div>
									<div class="col-md-6 col-lg-6">
											<p class="header-text">Group Members</p>
											<div class="table-responsive">
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
									</div>
								</div>
								
						</div><br>
						
						<div class="row">
							<div class="col-md-8 col-lg-8">
								<div class="tab-content">
									<form action="review_1_add_comment" class="tab-pane active" id="comment1" method="post">
										<p>${comment1}</p>
										<input type="hidden" name="groupid" value="${grpid}" />
										<div class="mb-2">
											<label for="comment1">Comment for review 1</label>
											<textarea name="comment1" class="form-control" rows="7">${review1}</textarea>
										</div>
										
										<div class="mt-3">
											<input type="submit" class="btn btn-warning" value="Submit" />
										</div>
									</form>
									<form action="review_2_add_comment" class="tab-pane fade" id="comment2" method="post">
										<p>${comment2}</p>
										<input type="hidden" name="groupid" value="${grpid}" />
										<div class="mb-2">
											<label for="comment2">Comment for review 2</label>
											<textarea name="comment2" class="form-control" rows="7">${review2}</textarea>
										</div>
										
										<div class="mt-3">
											<input type="submit" class="btn btn-warning" value="Submit" />
										</div>
									</form>
									<form action="review_3_add_comment" class="tab-pane fade" id="comment3" method="post">
										<p>${comment3}</p>
										<input type="hidden" name="groupid" value="${grpid}" />
										<div class="mb-2">
											<label for="comment3">Comment for review 3</label>
											<textarea name="comment3" class="form-control" rows="7">${review3}</textarea>
										</div>
										
										<div class="mt-3">
											<input type="submit" class="btn btn-warning" value="Submit" />
										</div>
									</form>
									<form action="review_4_add_comment" class="tab-pane fade" id="comment4" method="post">
										<p>${comment4}</p>
										<input type="hidden" name="groupid" value="${grpid}" />
										<div class="mb-2">
											<label for="comment4">Comment for review 4</label>
											<textarea name="comment4" class="form-control" rows="7">${review4}</textarea>
										</div>
										
										<div class="mt-3">
											<input type="submit" class="btn btn-warning" value="Submit" />
										</div>
									</form>
								</div>
							</div>
							<div class="col-md-4 col-lg-4">
								<div class="nav-wrapper " >
						   			<ul class="nav nav-tabs flex-column" id="tabs-icons-text" role="tablist">
						        		<li class="nav-item my-2">
						            		<a class="nav-link mb-sm-3 mb-md-0 active text-center" id="tabs-icons-text-1-tab" data-toggle="tab" href="#comment1" role="tab" aria-controls="tabs-icons-text-1" aria-selected="true"><i class="ni ni-cloud-upload-96 mr-2"></i>Comment 1</a>
						        		</li>
						        		<li class="nav-item my-2 ">
						            		<a class="nav-link mb-sm-3 mb-md-0 text-center" id="tabs-icons-text-2-tab" data-toggle="tab" href="#comment2" role="tab" aria-controls="tabs-icons-text-2" aria-selected="false"><i class="ni ni-bell-55 mr-2"></i>Comment 2</a>
						        		</li>
						        		<li class="nav-item my-2 ">
						            		<a class="nav-link mb-sm-3 mb-md-0 text-center" id="tabs-icons-text-3-tab" data-toggle="tab" href="#comment3" role="tab" aria-controls="tabs-icons-text-3" aria-selected="false"><i class="ni ni-calendar-grid-58 mr-2"></i>Comment 3</a>
						        		</li>
						        		<li class="nav-item my-2 ">
						            		<a class="nav-link mb-sm-3 mb-md-0 text-center" id="tabs-icons-text-3-tab" data-toggle="tab" href="#comment4" role="tab" aria-controls="tabs-icons-text-3" aria-selected="false"><i class="ni ni-chart-bar-32 mr-2"></i>Comment 4</a>
						        		</li>
						    		</ul>
								</div>
							</div>
						</div>
					</div>
					
					
    	</div>
      <!-- Footer -->
    </div>
 </div>
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
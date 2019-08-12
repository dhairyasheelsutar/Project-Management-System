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
    	<h1 class="text-white text-uppercase">Individual group information</h1>
    </div>
    <!-- Page content -->
    <div class="container-fluid mt--7">
    	<div>
    		<form class="card shadow-lg p-5" action="update_student_group_guide" method="post">
					<div>
						<div class="col-offset-1">${output}</div>
						<div class="row">
							
							<div class="col-md-6 col-lg-6">
											<p class="lead header-text">Project Details</p>
											<div class="mb-2">
												<input type="hidden" name="groupid" value="${grpid}" required />
												<input type="hidden" name="guide" value="${guide}" />
												<label>Group ID</label>
												<input type="text" value="${grpid}" disabled class="form-control" />
											</div>

											<div class="mb-2">
												<label for="pbs_id">PBS ID</label>
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
											
											<p class="lead header-text">Group members</p>
											
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
									<div class="col-md-6 col-lg-6">
											<p class="lead header-text">Sponsorship Details</p>
											<div class="mb-2">
												<label for="company_name">Company Name</label>
												<input type="text" name="company_name" value="${company_name}" class="form-control" /> 
											</div>
											<div class="mb-2">
												<label for="external_guide">External Guide</label>
												<input type="text" name="external_guide" value="${external_guide}" class="form-control" /> 
											</div>
											<p class="lead header-text">Group logs</p>
											${group_logs}
									</div>
								</div><br>
								<div class="text-center">
									<input type="submit" name="submit" value="save" class="btn btn-warning" />
									<button type="button" class="btn btn-success" data-toggle="modal" data-target="#myModal">Project Synopsis</button>
								</div>
						</div>
					</form>
    	</div><br>
    	
    	<div class="card p-5 shadow-lg">
    	
    		<ul class="nav nav-pills nav-fill flex-column flex-sm-row" id="tabs-text" role="tablist">
  				<li class="nav-item">
    				<a class="nav-link mb-sm-3 mb-md-0 active" id="tabs-text-1-tab" data-toggle="tab" href="#tabs-text-1" role="tab" aria-controls="tabs-text-1" aria-selected="true">Review 1</a>
  				</li>
  				<li class="nav-item">
    				<a class="nav-link mb-sm-3 mb-md-0" id="tabs-text-2-tab" data-toggle="tab" href="#tabs-text-2" role="tab" aria-controls="tabs-text-2" aria-selected="false">Review 2</a>
  				</li>
  				<li class="nav-item">
    				<a class="nav-link mb-sm-3 mb-md-0" id="tabs-text-3-tab" data-toggle="tab" href="#tabs-text-3" role="tab" aria-controls="tabs-text-3" aria-selected="false">Review 3</a>
  				</li>
  				<li class="nav-item">
    				<a class="nav-link mb-sm-3 mb-md-0" id="tabs-text-3-tab" data-toggle="tab" href="#tabs-text-4" role="tab" aria-controls="tabs-text-3" aria-selected="false">Review 4</a>
  				</li>
			</ul><br><br>
    	
    		<div>${review_result}</div>
	    	
	    	<div class="tab-content">
	    		<div class="tab-pane active" id="tabs-text-1">
		    		<div class="row">
		    			<div class="col-md-5 col-lg-5">
		    				<p class="header-text text-center">review-I checklist: Finalization of scope</p>
		    				<form action="review_1_checklist" method="post">
		    					<input type="hidden" name="grpid" value="${grpid}" />
		    					${checklist1}
		    				</form>
		    			</div>
		    			<div class="col-md-7 col-lg-7">
			    			<form action="review_1_submit" method="post">
			    				${input_types}
			    				<p class="header-text text-center">Project review-I</p>
			    				<div class="table-responsive">
			    					<table class="table table-flush">
			    						<thead>
			    							${tbl_head}
			    						</thead>
			    						<tbody>
			    							${review1_tbl}
			    						</tbody>
			    					</table>
		    				</div>
		    				<div class="mt-3 text-center">
		    					<input type="submit" class="btn btn-warning" value="submit" />
		    				</div>
			    			</form>
		    			</div>
		    		</div>
	    		</div>
	    		
	    		<div class="tab-pane fade" id="tabs-text-2">
		    		<div class="row">
		    			<div class="col-md-5 col-lg-5">
		    				<p class="header-text text-center">review-II checklist: Design</p>
		    				<form action="review_2_checklist" method="post">
		    					<input type="hidden" name="grpid" value="${grpid}" />
		    					${checklist2}
		    				</form>
		    			</div>
		    			<div class="col-md-7 col-lg-7">
			    			<form action="review_2_submit" method="post">
			    				${input_types}
			    				<p class="header-text text-center">Project review-II</p>
			    				<div class="table-responsive">
			    					<table class="table table-flush">
			    						<thead>
			    							${tbl_head}
			    						</thead>
			    						<tbody>
			    							${review2_tbl}
			    						</tbody>
			    					</table>
		    				</div>
		    				<div class="mt-3 text-center">
		    					<input type="submit" class="btn btn-warning" value="submit" />
		    				</div>
			    			</form>
		    			</div>
		    		</div>
	    		</div>
	    		
	    		<div class="row tab-pane fade" id="tabs-text-3">
	    			<div class="row">
	    				<div class="col-md-5 col-lg-5">
		    				<p class="header-text text-center">review-III checklist: Implementation</p>
		    				<form action="review_3_checklist" method="post">
		    					<input type="hidden" name="grpid" value="${grpid}" />
		    					${checklist3}
		    				</form>
		    			</div>
		    			<div class="col-md-7 col-lg-7">
			    			<form action="review_3_submit" method="post">
			    				${input_types}
			    				<p class="header-text text-center">Project review-III</p>
			    				<div class="table-responsive">
			    					<table class="table table-flush">
			    						<thead>
			    							${tbl_head}
			    						</thead>
			    						<tbody>
			    							${review3_tbl}
			    						</tbody>
			    					</table>
		    				</div>
		    				<div class="mt-3 text-center">
		    					<input type="submit" class="btn btn-warning" value="submit" />
		    				</div>
			    			</form>
		    			</div>
	    			</div>
	    		</div>
	    		
	    		<div class="tab-pane fade" id="tabs-text-4">
	    			<div class="row">
	    				<div class="col-md-5 col-lg-5">
		    				<p class="header-text text-center">review-IV checklist</p>
		    				<form action="review_4_checklist" method="post">
		    					<input type="hidden" name="grpid" value="${grpid}" />
		    					${checklist4}
		    				</form>
		    			</div>
		    			<div class="col-md-7 col-lg-7">
			    			<form action="review_4_submit" method="post">
			    				${input_types}
			    				<p class="header-text text-center">Project review-IV</p>
			    				<div class="table-responsive">
			    					<table class="table table-flush">
			    						<thead>
			    							${tbl_head}
			    						</thead>
			    						<tbody>
			    							${review4_tbl}
			    						</tbody>
			    					</table>
		    				</div>
		    				<div class="mt-3 text-center">
		    					<input type="submit" class="btn btn-warning" value="submit" />
		    				</div>
			    			</form>
		    			</div>
	    			</div>
	    		</div>
	    		
	    	</div>
    		
    		
    	</div><br>
      	
      	
      
    </div>
 </div>
 
  <!-- The Modal -->
<div class="modal fade" id="myModal">
	<div class="modal-dialog modal-dialog-centered modal-lg">
	<div class="modal-content">
      	<form action="aproove_abstract" method="post">
        <!-- Modal Header -->
        <div class="modal-header">
         	<h4 class="modal-title">Project Synopsis</h4>
         	<button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
        	  ${abstract_project}
        	  
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        	${modal_footer}
         	
        </div>
        </form>
      </div>
    </div>
  </div>
 
 	<%@include file="../../script.jsp" %>
 
</body>

</html>
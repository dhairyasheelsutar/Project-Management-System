<%
	String rollno = (String)request.getAttribute("rollno");
%>

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
    <div class="header bg-primary pb-8 pt-5 pt-lg-6 d-flex justify-content-center">
    	<h1 class="text-white text-uppercase">Invite members to join project group</h1>
    </div>
    <!-- Page content -->
    <div class="container mt--7">
    	<div>
    		<div class="row">
						<div class="col-md-3 col-lg-3"></div>
						<div class="col-md-6 col-lg-6">
							<p class="text-danger text-center">${result}</p>
							<form class="card shadow-lg p-3" action="form_group" method="post">
								<div class="card-body">
									<div class="mb-2">
										<input type="hidden" name="roll1" value="<%=rollno %>"/>
										<label for="roll2">Member 1 Roll no</label>
										<select name="roll2" class="form-control" required>
											<option value="null">Select Roll no</option>
											${roll_numbers}
										</select>
									</div>
									<div class="mb-2">
										<label for="roll3">Member 2 Roll no</label>
										<select name="roll3" class="form-control" required>
											<option value="null">Select Roll no</option>
											${roll_numbers}
										</select>
									</div>
									<div class="mb-2">
										<label for="roll4">Member 3 Roll no(if group is of 4 members)</label>
										<select name="roll4" class="form-control" required>
											<option value="null">Select Roll no</option>
											${roll_numbers}
										</select>
									</div>
									<div class="mt-3 text-center">
										<input type="submit" name="register" value="Send Request" class="btn btn-warning" />
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
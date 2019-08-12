

$(document).ready(function(){
	$(".schedule_template").click(function(){
		var template = $(this).attr("data-file");
		$(".template_files").attr("src", "http://localhost:8080/admin/student/templates/" + $(this).attr("data-file"));
		$(".select_template").off('click').on("click", function(){
			$(".template_file_src").val(template);
			console.log($(".template_file_src").val());
			$("#myModal").modal("hide");
		});
	});
	
	$(".open_domain_modal").click(function(){
		var did = $(this).attr("data-id");
		var value = $(this).attr("data-val");
		$("#did").val(did);
		$("#dname").val(value);
	});
	
	$(".student_group_single").click(function(){
		var grpid = $(this).attr("data-src");
	});
	
	$(window).load(function(){
		if(typeof(Storage) !== "undefined") {
	        if (sessionStorage.clickcount) {
	            sessionStorage.clickcount = Number(sessionStorage.clickcount)+1;
	        } else {
	            sessionStorage.clickcount = 1;
	        }
	    }
	});
	
});


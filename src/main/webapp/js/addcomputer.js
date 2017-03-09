//var name = false;
//var date = false;
//
//$(function() {
//	$('#computerName').on('input', function() {
//		var input=$(this);
//		var regex = /^[a-zA-Z0-9\\s._ -/]+$/;
//		var is_valid = regex.test(input.val());
//		if(is_valid) {
//			$("#inputName").css("display", "none");
//	         name = true;
//	    }
//		else {
//			$("#inputName").css("display", "inline");
//			$("#inputName").css("color", "red");
//			name = false;
//		}
//		validate();
//	});
//	
//	$('#discontinued').on('input', function() {
//		var input=$(this);
//		if(!($('#introduced').val()) || new Date($('#introduced').val()).getTime() > new Date($('#discontinued').val()).getTime()) {
//				$("#inputIntroduced").css("display", "inline");
//				$("#inputIntroduced").css("color", "red");
//				date = false;
//			}
//			else {
//				$("#inputIntroduced").css("display", "none");
//				date = true;
//			}
//		validate();
//	});
//});
//
//function validate() {
//
//	if (name && date) {
//        $("#addComputerButton").removeClass("disabled");
//    } else {
//        $("#addComputerButton").addClass("disabled");
//    }
//}
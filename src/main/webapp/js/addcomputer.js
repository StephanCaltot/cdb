//On load
$(function() {    

	$("#computerName").on('input', function() {
        var input = $(this);
        var regex = /^[a-zA-Z0-9-_ ./]*$/;
        var regexRes = regex.test(input.val());

        if ( regexRes ) {
        	$("#inputOfComputer").text("");
        } else{
        	$("#inputOfComputer").text("Name have to matches letters, numbers or '-' , '_', '.' and '/' characters.");
        }
    });
});



$(function() {    

	$("#introduced").on('input', function() {
        var input = $(this);
        var regex = /^d{2}-d{2}-d{4}$/;
        var regexRes = regex.test(input.val());

        if ( regexRes ) {
        	$("#inputIntroduced").text("");
        } else{
        	$("#inputIntroduced").text("Date have to matches dd/mm/aaaa format.");
        }
    });
});

$(function() {    

	$("#discontinued").on('input', function() {
        var input = $(this);
        var regex = /^d{2}-d{2}-d{4}$/;
        var regexRes = regex.test(input.val());

        if ( regexRes ) {
        	$("#inputDiscontinued").text("");
        } else{
        	$("#inputDiscontinued").text("Date have to matches dd/mm/aaaa format.");
        }
    });
});


//
//$(document).ready(function() {
//	   $("#editComputer").validate({
//	      rules: {
//	         "computerName":{
//	            "required": true,
//	            "minlength": 2,
//	            "maxlength": 20
//	         },
//	         "introduced": {
//	            "required": false,
//	            "regex": /^d{4}-d{2}-d{2}$/
//	         },
//	         "discontinued": {
//	            "required": false,
//	            "regex": /^d{4}-d{2}-d{2}$/
//	         },
//	         "companyId": {
//	        	 "required":false
//	         }
//	      }
//	  })
//});
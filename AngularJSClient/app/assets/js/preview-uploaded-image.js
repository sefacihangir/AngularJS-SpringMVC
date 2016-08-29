$(document).ready(function () {

	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            document.getElementById('right-side').style.backgroundImage="url("+e.target.result+")";
	            document.getElementById('right-side').style.backgroundRepeat = "no-repeat";
	            document.getElementById('right-side').style.backgroundSize = "contain";
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}

	$("#img-input").change(function(){
    	readURL(this);
	});

});


	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	        	/* set the id or class of the <div> in which you want to view the uploaded photo to "photoContent" */
	            document.getElementById('photoContent').style.backgroundImage="url("+e.target.result+")";
	            document.getElementById('photoContent').style.backgroundRepeat = "no-repeat";
	            document.getElementById('photoContent').style.backgroundSize = "contain";
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}


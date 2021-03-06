(function(){

	var app = angular.module('uploadViewModule', []);

	/**
     * directive to upload file
     */
    app.directive('fileModel', ['$parse', function ($parse) {
      return {
          restrict: 'A',
          link: function(scope, element, attrs) {
              var model = $parse(attrs.fileModel);
              var modelSetter = model.assign;
              
              element.bind('change', function(){
                  scope.$apply(function(){
                      modelSetter(scope, element[0].files[0]);
                  });
              });
          }
      };
    }]);


    /**
     * service to upload file
     */
    app.service('fileUpload', ['$http', '$location', function ($http, $location) {
	    this.uploadFileToUrl = function(file, uploadUrl){
	        var fd = new FormData();
	        fd.append('file', file);
	        // temporary
	        fd.append('appUserId', 10);

	        var config = {
	            transformRequest: angular.identity,
	            headers: {'Content-Type': undefined}
	        };
	        // manually setting ‘Content-Type’: multipart/form-data will fail to fill in the boundary parameter of the request
	        // so set the Content-Type : undefined and the browser will set automtically
	        $http.post(uploadUrl, fd, config)
	        .success(function(data){
	        	if(data.appUserId != undefined){
	        		// redirect to account
	        		$location.path('/home.customer');
	        	} else{
	        			$.iGrowl({
							 type: 'error',
							 message: data.error.errorMessage,
							 icon: 'feather-cross',
							 placement : {
							  x: 	'center'
							 },
							 animShow: 'fadeInLeftBig',
							 animHide: 'fadeOutDown'
							});
	        	}
	        })
	        .error(function(error){
	        	console.log(error);
	        });
	    }
	}]);



	app.controller('UploadController', ['$scope', 'fileUpload','config','$location', function($scope, fileUpload, config, $location){
	    
	    $scope.uploadFile = function(){
	        var file = $scope.uploadedPhoto;
	        console.log('file is ' );
	        console.dir(file);
	        var uploadUrl = "http://localhost:8585/SpringREST/upload_control/upload";
	        fileUpload.uploadFileToUrl(file, uploadUrl);
	    };
	    
	}]);





})();
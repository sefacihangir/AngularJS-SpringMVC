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
    app.service('fileUpload', ['$http', function ($http) {
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
	        	console.log(data);
	        })
	        .error(function(error){
	        	console.log(error);
	        });
	    }
	}]);



	app.controller('UploadController', ['$scope', 'fileUpload','config', function($scope, fileUpload, config){
	    

	    $scope.uploadFile = function(){
	        var file = $scope.uploadedPhoto;
	        console.log('file is ' );
	        console.dir(file);
	        var uploadUrl = "http://localhost:8585/SpringREST/upload_control/upload";
	        fileUpload.uploadFileToUrl(file, uploadUrl);
	    };
	    
	}]);





})();
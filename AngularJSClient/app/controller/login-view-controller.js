var app = angular.module('AngularJSClientApp', []);


// CONTROLLER
app.controller('LoginController', function($scope, $location, $http, $rootScope){
	
	/***** SUBMIT *****/
	$scope.submit = function(){
		var email = $scope.email;
		var password = $scope.password;
		$rootScope.isLoggedIn = false;
		// server call
		$http({
			method: 'GET',
			url: 'http://localhost:8585/SpringREST/login_control/login?email=' + email 
		}).then(
				function successCallback(response){
					var responseData = response.data;
					if(responseData == undefined){
						// response came empty
						$.iGrowl({
								 type: 'error',
								 message: 'Something went wrong !',
								 placement : {
								  x: 	'center'
								 },
								 animShow: 'fadeInLeftBig',
								 animHide: 'fadeOutDown',
								 delay: 3000,
							 	 icon: 'feather-cross'
							});
					}
					else{
						 // response data contains JSON
						 var error;
						 var user;

						 if(responseData.user != undefined){
							 	var user = responseData.user;				// get the user object from JSON response

								if(user.password == password){
									$rootScope.isLoggedIn = true;		   // set global variable in order to secure the access to other pages
									window.open("http://localhost:8787/AngularJSClient/app/partials/home-partial.html","_self");
								}
								else{
									$rootScope.isLoggedIn = false;
									$.iGrowl({
										 type: 'error',
										 message: 'Invalid password !',
										 placement : {
										  x: 	'center'
										 },
										 animShow: 'fadeInLeftBig',
										 animHide: 'fadeOutDown',
										 delay: 3000,
									 	 icon: 'feather-cross'
									});
								}

						 }
						 else if(responseData.error != undefined && responseData.error.hasError){
							 	error = responseData.error;				    // get the error object from JSON response
							 	// show error message
							 	$.iGrowl({
									 type: 'error',
									 message: error.errorMessage,
									 placement : {
									  x: 	'center'
									 },
									 animShow: 'fadeInLeftBig',
									 animHide: 'fadeOutDown',
									 delay: 3000,
								 	 icon: 'feather-cross'
								});
						 }
				

					}

					// END OF successCallback() function
				},
				function errorCallback(response){
					console.log(response.data);
				}
			);
		

	};



});
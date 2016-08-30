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
			url: 'http://localhost:8585/SpringREST/user_controller/user?email=' + email 
		}).then(
				function successCallback(response){
					var user = response.data;
					if(user.password == password){
						console.log("SUCCES");
						$rootScope.isLoggedIn = true;		// set global variable in order to secure the access to other pages
						$location.path('/test');
					}else{
						console.log("INVALID CREDENTIALS");
						$rootScope.isLoggedIn = false;
					}
				},
				function errorCallback(response){
					console.log(response);
				}
			);
		

	};



});
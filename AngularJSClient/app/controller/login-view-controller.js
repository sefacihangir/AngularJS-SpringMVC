var app = angular.module('AngularJSClientApp', ['ngRoute']);

app.config(function($routeProvider){
	$routeProvider
	.when(
			'/',{}
	)
	.otherwise({
			redirectTo: '/'
	});


});



app.controller('LoginController', function($scope, $location){
	
	/***** SUBMIT *****/
	$scope.submit = function(){
		var email = $scope.email;
		var password = $scope.password;

		console.log("Email " + email + " Password " + password);
	}



});
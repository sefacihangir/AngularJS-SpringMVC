var app = angular.module('AngularJSClientApp');

app.controller('HomeController', function($scope){
	$scope.user = {firstName: "George", lastName: "Berar"};
});

var app = angular.module('AngularJSClientApp', ['ngRoute']);

// ROUTES
var app = angular.module('AngularJSClientApp', ['ngRoute']);
	app.config(function($routeProvider) {
		$routeProvider
		 .when("/login", { templateUrl : "view/login.html" })
		 .when("/test", { templateUrl : "test.html"  })
		 .otherwise({ redirectTo : "/login" });
});
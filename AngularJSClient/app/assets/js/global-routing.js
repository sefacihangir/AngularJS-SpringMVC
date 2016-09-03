
var app = angular.module('AngularJSClientApp', ['ngRoute']);

// ROUTES
var app = angular.module('AngularJSClientApp', ['ngRoute']);
	app.config(function($routeProvider) {
		$routeProvider
		 .when("/home", { templateUrl: "templates/home-template.html" })
		 .when("/profile", { templateUrl : "templates/profile-template.html"  })
		 .when("/requests", { templateUrl : "templates/requests-template.html" })
		 .when("/messages", { templateUrl : "templates/messages-template.html" })
		 .otherwise({ redirectTo : "/home" });
});
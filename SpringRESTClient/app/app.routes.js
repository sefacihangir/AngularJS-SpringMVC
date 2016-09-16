(function(){
	var app = angular.module('sboxAppRoutes', ['ngRoute', 'angularCSS']);

	app.config(function($routeProvider){
		$routeProvider
			.when("/home", { 
						templateUrl : "app/components/welcome/welcomeView.html", 
						css : "assets/css/welcomeView.css"
			     })
			.when("/test", { template : "Test page" })
			.otherwise({ redirectTo : "/home" });
	});

})();
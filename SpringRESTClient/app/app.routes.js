(function(){
	var app = angular.module('sboxAppRoutes', ['ngRoute', 'angularCSS']);

	app.config(function($routeProvider){
		$routeProvider
			.when("/home", { 
						templateUrl : "app/components/welcome/welcomeView.html", 
						css : "assets/css/welcomeView.css"
			     })
			.when("/login", {
						templateUrl : "app/components/login/loginView.html",
						css : "assets/css/loginView.css"
				})
			.when("/signup", {
						template : "Welcome to registration" 
				})
			.when("/test", { template : "Test page" })
			.otherwise({ redirectTo : "/home" });
	});

})();
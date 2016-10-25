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
						templateUrl : "app/components/signup/signupView.html" ,
						css : "assets/css/signupView.css"
				})
			.when("/test", { template : "Test page" })
			.otherwise({ redirectTo : "/home" });
	});

})();
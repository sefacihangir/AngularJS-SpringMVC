(function(){
	var app = angular.module('sboxAppRoutes', ['ngRoute', 'angularCSS']);

	app.config(function($routeProvider){
		$routeProvider
			.when("/welcome", { 
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
			.when("/upload_image", {
					   templateUrl : "app/components/upload/uploadView.html",
					   css : "assets/css/uploadView.css"
			})
			.when("/home.admin", {
					   template : "Welcome admin"
			})
			.when("/home.customer", {
					   template : "Welcome customer"
			})
			.when("/home.provider", {
					   template : "Welcome provider"
			})
			.when("/test", { template : "Test page" })
			.otherwise({ redirectTo : "/welcome" });
	});

})();
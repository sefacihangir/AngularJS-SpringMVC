var app = angular.module('AngularJSClientApp', ['ngRoute']);

// ROUTES
app.config(function($routeProvider){
	$routeProvider
	.when(
			'/',{
				templateURL : 'login.html',
				controller : 'LoginController'
			}
	)
	.when(
			'/test', {
				resolve: {
					"check": function($location, $rootScope){
						if(! $rootScope.isLoggedIn){
							console.log("NOT LOGGED IN! REDIRECT");
							$location.path('/');
						}
					}
				},
				templateURL: 'test.html'
			}
	)
	.otherwise({
			redirectTo: '/'
	});


});

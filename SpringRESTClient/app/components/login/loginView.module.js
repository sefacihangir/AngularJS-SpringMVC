(function(){

	var app = angular.module('loginViewModule', []);

	app.controller('LoginController', ['$http', function($http){

		this.loginObj = {
						email : '',
						password : ''
						};


		this.login = function(){
			$http.post('http://localhost:8585/SpringREST/login_control/login?email='+this.loginObj.email)
				.success(function(data){
					console.log(data.user);
				});
		};

	} ]);

})();
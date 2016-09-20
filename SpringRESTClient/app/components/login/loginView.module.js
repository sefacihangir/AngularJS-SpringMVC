(function(){

	var app = angular.module('loginViewModule', []);

	app.controller('LoginController', ['$http', function($http){

		this.loginObj = {
						email : '',
						password : ''
						};


		this.login = function(){
			// $http.post('http://localhost:8585/SpringREST/login_control/login?email='+this.loginObj.email)
			// 	.success(function(data){
			// 		console.log(data.user);
			// 	});
		console.log(this.loginObj);
		};

	} ]);



	app.controller('InputAnimationController', function(){

		this.emailLabel = '';
		this.passwordLabel = '';

		this.showLabelOnClickForInput = function(inputName){
			if(inputName.toUpperCase() === 'EMAIL'){
				this.emailLabel = 'Email';
				this.passwordLabel = '';
			}
			else if(inputName.toUpperCase() === 'PASSWORD'){
				this.passwordLabel = 'Password';
				this.emailLabel = '';
			}
		};

	});





})();
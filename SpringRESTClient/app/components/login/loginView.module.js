(function(){

	var app = angular.module('loginViewModule', []);

	app.controller('LoginController', ['$http','$location', function($http, $location){

		this.loginObj = {
						email : '',
						password : ''
						};


		this.login = function(){
			var data = $.param({
						   		email : this.loginObj.email,
						   		password : this.loginObj.password
							  });

			var config = {
				           headers : {
                    				'Content-Type': 'application/x-www-form-urlencoded;charset=utf-8;'
                		   }
						 };

			$http.post('http://localhost:8585/SpringREST/login_control/login', data, config)
				 .success(function(data){
						if(data.user != undefined){
							//console.log(data.user);
							switch(data.user.role.roleName.toUpperCase()){
								case 'ROLE_ADMIN' : 
									$location.path('/home.admin');
								break;

								case 'ROLE_CUSTOMER' : 
									$location.path('/home.customer');
								break;

								case 'ROLE_PROVIDER' : 
									$location.path('/home.provider');
								break;
							}

						} else{
							// Error
							//console.log(data.error);
							$.iGrowl({
							 type: 'error',
							 message: data.error.errorMessage,
							 icon: 'feather-cross',
							 placement : {
							  x: 	'center'
							 },
							 animShow: 'fadeInLeftBig',
							 animHide: 'fadeOutDown'
							});
						}
					
				 })
				 .error(function(error){
				 		console.log(error);
				 });
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
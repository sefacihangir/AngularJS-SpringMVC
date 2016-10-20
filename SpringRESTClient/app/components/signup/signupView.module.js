(function(){

	var app = angular.module('signupViewModule', []);


	app.controller('SignupController', ['$http', function($http){

		/* object used to define an address */
		this.addressObj = {description : '', county : '', city : ''};
		
		/* object used to send signup details */
		this.signupObj = {
							email : '',
							password : '',
							firstName : '',
							lastName : '',
							phone : '',
							primaryAddress : {description : '', county : '', city : ''},
							otherAddresses : []
						 };

		/**
		 * addressObject will have the format defined above {description, county, city}
		 */
		this.addAnotherAddress = function(){
			this.signupObj.otherAddresses.put(this.addressObject);
			this.addressObject.description = '';
			this.addressObject.county = '';
			this.addressObject.city = '';
		};


		this.signup = function(){
			console.log(this.signupObj);
		};

	}]);


	/**
	 * used to control the animation for the input fields
	 * .signupViewModule used to avoid naming collision with another InputAnimationController controller 
	 */
	app.controller('InputAnimationController-signupViewModule', function(){

		this.emailLabel = '';
		this.passwordLabel = '';
		this.retypePasswordLabel = '';
		this.firstNameLabel = '';
		this.lastNameLabel = '';
		this.phoneLabel = '';
		this.primaryAddressLabel = '';

		this.showLabelOnClickForInput = function(inputName){
			switch(inputName.toUpperCase()){
				case 'EMAIL': 
					this.emailLabel = 'Email';
				break;
				case 'PASSWORD': 
					this.passwordLabel = 'Password';
				break;
				case 'RETYPE PASSWORD': 
					this.retypePasswordLabel = 'Retype password';
				break;
				case 'FIRST NAME': 
					this.firstNameLabel = 'First Name';
				break;
				case 'LAST NAME': 
					this.lastNameLabel = 'Last Name';
				break;
				case 'PHONE': 
					this.phoneLabel = 'Phone';
				break;
				case 'PRIMARY ADDRESS': 
					this.primaryAddressLabel = 'Primary Address';
				break;
				case '': break;
			}
		};

		this.resetLabelOnClickForInput = function(inputName){
			switch(inputName.toUpperCase()){
				case 'EMAIL': 
					this.emailLabel = '';
				break;
				case 'PASSWORD': 
					this.passwordLabel = '';
				break;
				case 'RETYPE PASSWORD': 
					this.retypePasswordLabel = '';
				break;
				case 'FIRST NAME': 
					this.firstNameLabel = '';
				break;
				case 'LAST NAME': 
					this.lastNameLabel = '';
				break;
				case 'PHONE': 
					this.phoneLabel = '';
				break;
				case 'PRIMARY ADDRESS': 
					this.primaryAddressLabel = '';
				break;
				case '': break;
			}
		}

	});

})();
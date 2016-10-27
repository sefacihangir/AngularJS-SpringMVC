(function(){

	var app = angular.module('sboxApp', ['ui.bootstrap','sboxAppRoutes','welcomeViewModule','loginViewModule', 'signupViewModule', 'uploadViewModule']);

    /**
     * global constants
     */
    app.constant('config', {
        PHONE_PREFIX: '+40',
        PRIMARY_ADDRESS: 'primary',
        OTHER_ADDRESS: 'other',
        LANGUAGE: 'EN'
    });



	/**
     * go to location when using buttons   
     */
    app.controller('RedirectToController', ['$location', function($location){
        this.redirectTo = function(routePath){
            $location.path(routePath);
        };
    } ]);



    /**
     * directive to verify if the passwords match
     */
    app.directive('passwordMatch', function(){
    	return {
    		restrict: 'A',
    		scope: true,
            require: 'ngModel',
            link: function(scope, elem, attrs, control){
                var firstPassword = '#' + attrs.passwordMatch;
                $(elem).add(firstPassword).on('keyup', function(){
                    scope.$apply(function(){
                        var v = elem.val() === $(firstPassword).val();
                        control.$setValidity('match', v);
                    });
                });
            }
    	};
    });



    /**
     * directive to validate the phone number
     */
    app.directive('phoneValidation', function(){
        return{
            restrict: 'A',
            scope: true,
            require: 'ngModel',
            link: function(scope, elem, attrs, control){
                var checker = function(){
                    var RE = /^[\d\.\-]+$/;
                    var phone;
                    if((phone = scope.$eval(attrs.ngModel)) != undefined){
                        // check the length
                        if(phone.length >= 0 && phone.length < 9)
                            return false;
                        if(!RE.test(phone))
                            return false;

                        return true;
                    } else{
                        return false;
                    }
                };

                scope.$watch(checker, function(n){
                    control.$setValidity("valid", n);
                });
            }
        };
    });



    /**
     * used to open any modal and assign a controller to it
     * controllerAs used as an alias for ModalInstanceController; in order to use the methods from it type
     * modalInstanceCtrl.ok() || modalInstanceCtrl.cancel()
     */
    app.controller('ModalController', function($uibModal){

		 this.openModal = function(urlForModalTemplate, size){
		 	var uibModalInstance = $uibModal.open({
		 		templateUrl: urlForModalTemplate,
		 		controller: 'ModalInstanceController',
		 		controllerAs: 'modalInstanceCtrl',
		 		size: size
		 	});
		 };

	});


    /**
     * used to handle the dismiss of the opened modal
     */
	app.controller('ModalInstanceController', function($uibModalInstance){
		
		this.ok = function(){
			$uibModalInstance.close();
		};

		this.cancel = function(){
			$uibModalInstance.dismiss();
		};

	});




})();
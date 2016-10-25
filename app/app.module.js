(function(){

	var app = angular.module('sboxApp', ['ui.bootstrap','sboxAppRoutes','welcomeViewModule','loginViewModule', 'signupViewModule']);

	/**
     * go to location when using buttons   
     */
    app.controller('RedirectToController', ['$location', function($location){
        this.redirectTo = function(routePath){
            $location.path(routePath);
        };
    } ]);




    app.directive('passwordMatch', function(){
    	return {
    		restrict: 'A',
    		
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
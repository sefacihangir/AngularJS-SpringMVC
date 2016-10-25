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


})();
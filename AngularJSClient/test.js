angular.module('formExample', [])
	.controller('ExampleController', ['$scope', function($scope){

		$scope.user = new User();
		$scope.users = [];

		/*************************** GET ALL *************************/     
        $scope.fetchAllUsers = function(){
              $scope.users = User.query();
        };

        $scope.fetchAllUsers();                   // call the method

}]);
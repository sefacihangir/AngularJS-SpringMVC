'use strict';
 
App.controller('UserController', ['$scope', 'User', function($scope, User) {
          var self = this;
          
          self.user = new User();                 // one user
          self.users=[];                          // list of user
          


          /*************************** GET ALL *************************/     
          self.fetchAllUsers = function(){
              self.users = User.query();
          };

          self.fetchAllUsers();                   // call the method


          /*************************** GET BY ID *************************/
          self.edit = function(userId){
            for(var i = 0; i < self.users.length; i++){
               if(self.users[i].userId === userId){
                  self.user = angular.copy(self.users[i]);
                  break;
               }
            }

            // set the values in the inputs of the update modal
            var InputUserId    = angular.element(document.querySelector('#InputUserId'));
                InputUserId.val( self.user.userId );
            var InputFirstName = angular.element(document.querySelector('#InputFirstName'));
                InputFirstName.val( self.user.firstName );
            var InputLastName  = angular.element(document.querySelector('#InputLastName'));
                InputLastName.val( self.user.lastName );
            var InputAge       = angular.element(document.querySelector('#InputAge'));
                InputAge.val( self.user.age );
          };


          /*************************** UPDATE *************************/
          self.update = function(){

            console.log(self.user.userId + " " + self.user.firstName);
            console.log(self.user);

            if( (self.user.userId == null) || (self.user.userId <= 0) ){
                console.log("Failed !");
            }else{
                // self.user.$update(function(){
                //     self.fetchAllUsers();         // get all users again
                // });
                console.log("Updated !");
            }
          };


 
}]);
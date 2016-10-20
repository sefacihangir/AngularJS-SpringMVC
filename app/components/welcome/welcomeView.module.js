(function () {

    var app = angular.module('welcomeViewModule', []);

    /**
     * navbar controller to show views based on the navbar option selected
     */
    app.controller('NavbarHomeController', function () {
        this.optionSelected = 1; // default is HOME

        this.setOptionSelected = function (optionSelected) {
            this.optionSelected = optionSelected;
        };

        this.isOptionSelected = function (option) {
            return this.optionSelected === option;
        };
    });


})();

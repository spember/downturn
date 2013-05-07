namespace("downturn.controllers");
(function(window, downturn) {
    'use strict';

    /**
     * Handles the sending and receving of Quote information to the server
     *
     * @param $scope
     * @param $http
     * @param utils
     * @param quoteService
     */
    downturn.controllers.quoteCtrl = function($scope, $http, utils, quoteService) {

        $scope.utils = utils;
        $scope.service = quoteService;
        //$scope.messages = messageService.retrieve();

        // #/quote/search
        $scope.findStocks = function ($query) {
            $scope.searching = true;
            $http({
                url: "stock/query",
                data : "query="+$query,
                method : 'POST',
                headers : {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data) {
                $scope.searching = false;
                $scope.service.quotes = data;
            });
        };
    };

    /**
     * Manages the detailed view of a Quote
     *
     * @param $scope
     * @param $routeParams
     * @param $http
     * @param utils
     * @param quoteService
     * @param messageService
     */
    downturn.controllers.quoteDetailCtrl = function($scope, $routeParams, $http, utils, quoteService, messageService) {
        $scope.utils = utils;

        if(quoteService.quotes.length > 0) {
            $scope.quote = quoteService.quotes[$routeParams.id];
        } else {
            window.location = "/downturn/";
        }

        $scope.addToWL = function () {
            $http({
                url: "stock/add",
                data: $scope.quote,
                method: "POST",
                headers: {'Content-Type': "application/json; charset=UTF-8"}
            }).success(function(data) {
                messageService.add("Added " + $scope.quote.name);
                window.location = "#/quote/search";
            }).error(function(data){
               //error message?
            });
        };
    };

})(window, window.downturn);

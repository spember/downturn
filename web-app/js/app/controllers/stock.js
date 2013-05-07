namespace("downturn.controllers");
(function(downturn) {
    'use strict';
    /**
     * Responsible for fetching stocks from the User's watch list and setting them in scope
     *
     * @param $scope
     * @param $http
     * @param messageService
     * @param stockService
     */
    downturn.controllers.stockListCtrl = function($scope, $http, messageService, stockService) {

        $scope.noStocks = "";

        $http({
            url: "stock/list",
            method : 'GET',
            headers : {'Content-Type':'application/json; charset=UTF-8', 'X-Requested-With': 'XMLHttpRequest'}
        }).success(function(data) {
                $scope.stocks = data;
                stockService.stocks = data;
            });

        $scope.remove = function(index) {
            $http({
                url: "stock/delete",
                data : "symbol=" + index.symbol,
                method : 'POST',
                headers : {'Content-Type':'application/x-www-form-urlencoded; charset=UTF-8'}
            }).success(function(data) {
                    messageService.add("Removed " +index.symbol);
                    $scope.stocks = data;
                    stockService.stocks = data;
                });
        };
    };

    /**
     * Manages the detailed view of a particular Stock
     *
     * @param $scope
     * @param $routeParams
     * @param $http
     * @param messageService
     * @param stockService
     */
    downturn.controllers.stockCtrl = function ($scope, $routeParams, $http, messageService, stockService) {

        $scope.stock = stockService.stocks[$routeParams.id];

    };

})(window.downturn);



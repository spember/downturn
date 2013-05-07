/*global angular, downturn */

angular.module("downturn", [])
    .service("utils", window.downturn.services.utils)
    .service("quoteService", downturn.services.quoteService)
    .service("messageService", downturn.services.messageService)
    .service("stockService", downturn.services.stockService)
    .directive("onEnter", downturn.directives.applyOnEnter)
    .directive("messageBlock", downturn.directives.messages)
    .directive("content", downturn.directives.contentContainer)
    .config(['$routeProvider', function($routeProvider) {
        var ctrl = downturn.controllers;
        $routeProvider.
            // Consider migrating to using compiled handlebars or mustache templates instead of fetching from the server
            when('/quote/search', {templateUrl: 'quote/search', controller: ctrl.quoteCtrl}).
            when('/quote/detail/:id', {templateUrl: 'quote/detail', controller: ctrl.quoteDetailCtrl}).
            when('/stock/list', {template: downturn.templates.stocks.list, controller: ctrl.stockListCtrl}).
            when('/stock/detail/:id', {template: downturn.templates.stocks.detail, controller: ctrl.stockCtrl}).
            when('/home', {template: downturn.templates.home}).
            otherwise({redirectTo: '/home'});

    }]);


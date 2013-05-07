/*global setTimeout */
namespace("downturn.directives");
(function(downturn) {
    'use strict';
    downturn.directives.messages = function (messageService) {

        return {
            priority: 10,
            template: '<ul class="message-container"><li ng-repeat="message in messages">{{message}}</li></ul>',
            replace: true,
            transclude: false,
            restrict: 'E',
            scope: true,

           link: function postLink($scope, element, attrs) {
               //downturn.utils.addClass(element[0], "inactive");
               $scope.messages = messageService.messages;
               $scope.$watch("messages", function (a, b) {
                   // watch appears to be called multiple times when the watched item is updated. Difficult for tracking
                   // an item you only want to check once for
                   if ($scope.messages !== undefined && $scope.messages.length >= 1) {
                       downturn.utils.removeClass(element[0], "inactive");
                       downturn.utils.addClass(element[0], "active");
                       setTimeout(function () {
                           // clear messages after one second. Future link clicks will have them appear cleared
                           messageService.retrieve();
                       }, 1000);

                   } else {
                       downturn.utils.removeClass(element[0], "active");
                       downturn.utils.addClass(element[0], "inactive");
                   }
               }, true);

           }
        };
    };
})(window.downturn);

namespace("downturn.directives");
(function(downturn) {
    'use strict';
    downturn.directives.applyOnEnter = function () {
        return function(scope, elm, attrs) {
            elm.bind("keyup", function(event) {
                if(event.which === 13) {
                    scope.$apply(attrs.onEnter);
                }
            });
        };
    };

    /**
     * Used to wrap a section of content with global classes and additional content
     */
    downturn.directives.contentContainer = function () {
        return {
            priority: 5,
            template: "<section><message-block></message-block>" +
                "<figure class=\"content-container container\" ng-transclude></figure></section>",
            replace: true,
            transclude: true,
            restrict: 'A',
            scope: true

        };
    };
})(window.downturn);

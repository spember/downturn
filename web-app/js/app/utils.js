namespace("downturn.utils");
(function (downturn) {

    /**
     * Adds a className to an element. Will not add the class if already
     * present, and will add a padding whitespace if classes are already
     * present.
     *
     * @param element
     * @param className
     */
    downturn.utils.addClass = function(element, className) {
        var re = new RegExp("\\b" + className + "(?=[^\\w-]|$)");

        if (!re.test(element.className)) {
            // add check to see if we need a leading space
            if (element.className.length > 0) {
                className = " " + className;
            }
            element.className += className;
        }
    };

    downturn.utils.removeClass = function(element, className) {
        var re = new RegExp("\\b" + className + "(?=[^\\w-]|$)");
        //strip the className, and clean up any multiple spacing
        element.className = element.className.replace(re, "").replace(/\s\s*/, " ");
    };

})(window.downturn);

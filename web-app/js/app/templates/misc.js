namespace("downturn.templates");
//TODO: convert these away from static strings and into Handlebars or Mustache templates
(function(downturn) {
    downturn.templates.home = "<div content><p>Welcome to Downturn. The goals of this app are (1) to track stocks in \n" +
        "order to alert you when the price is falling (Is this a good time to buy? I know nothing of money), and (2) \n" +
        "for me to try out <a href=\"http://angularjs.org/\">Angular JS</a>.</p>\n\n" +
        "<p>This app was thrown together during several train rides during the morning commute. It is also nowhere near complete</p></div>";

})(window.downturn);

namespace("downturn.templates.stocks");
(function(downturn) {

    downturn.templates.stocks.list = "<div content> \n" +
    "<h3>Your Currently Tracked Stocks: ({{stocks.length}})</h3>\n" +
    "   <ul> \n" +
    "       <li ng-repeat=\"stock in stocks\"><a href=\"#/stock/detail/{{$index}}\" class=\"symbol-link\">{{stock.symbol}}</a> <button class=\"btn\" ng-click=\"remove({{stock}})\">Delete</button></li> \n" +
    "   </ul> \n" +
    "</div>";

    downturn.templates.stocks.detail = "<div content class=\"detail\"> \n" +
    "<p>{{stock.name}}</p>\n" +
    "<p>{{stock.symbol}}</p>\n" +
    "</div>\n";

})(window.downturn);

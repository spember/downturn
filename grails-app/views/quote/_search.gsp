<div content>
    <h3>Search for Stocks by Symbol:</h3>
    <input class="search-input" ng-model="query" placeholder="Type Symbols separated by a space" on-enter="findStocks(query)"/>
    <button class="btn btn-primary search" ng-click="findStocks(query)"> Search</button>
    <img src="${resource(dir: 'images', file: 'ajax-loader.gif')}" class="search" alt="Searching..." ng-show="searching"/>
    <h4>Your results ({{service.quotes.length}}):</h4>
    <ul>
        <li ng-repeat="quote in service.quotes"><a href="#quote/detail/{{$index}}">Name: {{quote.name}}</a> ({{quote.symbol}}): {{quote.ask}}</li>
    </ul>
</div>
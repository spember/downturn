<div content>
    <h2>Details for:{{quote.name}}</h2>
    <div class="detail-item"><span class="title">Symbol:</span>{{quote.symbol}}</div>
    <div class="detail-item"><span class="title">Bid:</span>{{quote.bid}}</div>
    <div class="detail-item"><span class="title">Ask:</span>{{quote.ask}}</div>
    <div class="detail-item"><span class="title">P/E Ratio:</span>{{quote.peRatio}}</div>
    <div class="detail-item"><span class="title">Percent Change from 50-day Moving Average:</span> {{quote.percentChangeFifty}}</div>
    <div class="detail-item"><span class="title">Percent Change from 200-day Moving Average:</span> {{quote.percentChangeTwoHundred}}</div>
    <div class="detail-item"><span class="title">Percent Change from Year Low:</span> {{quote.percentChangeYearLow}}</div>

    <button class="btn btn-primary" ng-click=addToWL()>Add To Watch List</button>
</div>
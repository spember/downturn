package com.pember.downturn.utils.yahoo

/**
 * User: spember
 *
 * Quote acts as a wrapper around a single Quote JSON object that is returned from the yahoo finance API
 */
class Quote {
    /*
        Sample:
        {
            "AfterHoursChangeRealtime": "N/A - N/A",
            "AnnualizedGain": null,
            "Ask": "839.93",
            "AskRealtime": "839.93",
            "AverageDailyVolume": "2413340",
            "Bid": "833.50",
            "BidRealtime": "833.50",
            "BookValue": "217.332",
            "Change": "0.00",
            "ChangeFromFiftydayMovingAverage": "+57.032",
            "ChangeFromTwoHundreddayMovingAverage": "+109.449",
            "ChangeFromYearHigh": "-12.62",
            "ChangeFromYearLow": "+274.86",
            "ChangePercentRealtime": "N/A - 0.00%",
            "ChangeRealtime": "0.00",
            "Change_PercentChange": "0.00 - 0.00%",
            "ChangeinPercent": "0.00%",
            "Commission": null,
            "DaysHigh": null,
            "DaysLow": null,
            "DaysRange": "N/A - N/A",
            "DaysRangeRealtime": "N/A - N/A",
            "DaysValueChange": "- - 0.00%",
            "DaysValueChangeRealtime": "N/A - N/A",
            "DividendPayDate": null,
            "DividendShare": "0.00",
            "DividendYield": null,
            "EBITDA": "16.278B",
            "EPSEstimateCurrentYear": "45.58",
            "EPSEstimateNextQuarter": "10.91",
            "EPSEstimateNextYear": "53.48",
            "EarningsShare": "32.214",
            "ErrorIndicationreturnedforsymbolchangedinvalid": null,
            "ExDividendDate": null,
            "FiftydayMovingAverage": "774.348",
            "HighLimit": null,
            "HoldingsGain": null,
            "HoldingsGainPercent": "- - -",
            "HoldingsGainPercentRealtime": "N/A - N/A",
            "HoldingsGainRealtime": null,
            "HoldingsValue": null,
            "HoldingsValueRealtime": null,
            "LastTradeDate": "3/6/2013",
            "LastTradePriceOnly": "831.38",
            "LastTradeRealtimeWithTime": "N/A - <b>831.38</b>",
            "LastTradeTime": "4:00pm",
            "LastTradeWithTime": "Mar  6 - <b>831.38</b>",
            "LowLimit": null,
            "MarketCapRealtime": null,
            "MarketCapitalization": "274.1B",
            "MoreInfo": "cnprmiIed",
            "Name": "Google Inc.",
            "Notes": null,
            "OneyrTargetPrice": "855.77",
            "Open": null,
            "OrderBookRealtime": null,
            "PEGRatio": "1.35",
            "PERatio": "25.81",
            "PERatioRealtime": null,
            "PercebtChangeFromYearHigh": "-1.50%",
            "PercentChange": "0.00%",
            "PercentChangeFromFiftydayMovingAverage": "+7.37%",
            "PercentChangeFromTwoHundreddayMovingAverage": "+15.16%",
            "PercentChangeFromYearLow": "+49.39%",
            "PreviousClose": "831.38",
            "PriceBook": "3.83",
            "PriceEPSEstimateCurrentYear": "18.24",
            "PriceEPSEstimateNextYear": "15.55",
            "PricePaid": null,
            "PriceSales": "5.46",
            "SharesOwned": null,
            "ShortRatio": "1.50",
            "StockExchange": "NasdaqNM",
            "Symbol": "GOOG",
            "TickerTrend": "&nbsp;======&nbsp;",
            "TradeDate": null,
            "TwoHundreddayMovingAverage": "721.931",
            "Volume": "0",
            "YearHigh": "844.00",
            "YearLow": "556.52",
            "YearRange": "556.52 - 844.00",
            "symbol": "GOOG"
        }
     */

    public Quote() {}

    public Quote (Map data) {
        symbol = data["symbol"]
        name = data["Name"]
        peRatio = data["PERatio"]
        bid = Double.parseDouble(data["Bid"]?: "0")
        ask = Double.parseDouble(data["Ask"]?: "0")
        percentChangeFifty = data["PercentChangeFromFiftydayMovingAverage"]
        percentChangeTwoHundred = data["PercentChangeFromTwoHundreddayMovingAverage"]
        percentChangeYearLow = data["PercentChangeFromYearLow"]
    }

    String name
    String symbol
    Double bid
    Double ask
    String peRatio
    String percentChangeFifty
    String percentChangeTwoHundred
    String percentChangeYearLow

    public String toString() {
        "${name} (${symbol}): ${ask}"
    }
}

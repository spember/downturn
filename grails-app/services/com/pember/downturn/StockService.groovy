package com.pember.downturn

import com.pember.downturn.utils.yahoo.Quote

class StockService {

    def grailsApplication
    StockLookupService stockLookupService

    Stock add(Map data, User user) {

        Stock stock = Stock.findBySymbol(data.symbol)
        if (!stock) {
            stock = new Stock()
            stock.symbol = data.symbol
            stock.name = data.name
            stock.user = user
            if (stock.validate()) {
                stock.save(flush:true)
                log.info "added stock $stock for user $user"
            }
        }
        stock
    }

    /**
     * Main Job method; will query for all symbols in the system
     *
     * @return Count for stocks updated
     */
    def updateAllStocks() {
        List stocks = Stock.list()
        List quotes = stockLookupService.lookupStocks(stocks.collect {it.symbol})
        int updated = 0
        stocks.each { Stock stock ->
            Quote quote = quotes.find {it.symbol == stock.symbol}
            if (quote) {
                log.info "Updating $stock"
                updateStock(stock, quote)
                updated++
            }
        }
        updated
    }

    /**
     * Given a new Quote and Stock, updates the dailyClosings for the Stock
     *
     * @param quote
     */
    def updateStock(Stock stock, Quote quote) {
        stock.addToDailyClosings(quote.ask)
        // trim old records
        if (stock.dailyClosings.size() > grailsApplication.config.downturn.numRecordsToSave) {
            stock.dailyClosings = stock.dailyClosings.toList()[1..-1]
        }

        stock.save(flush: true)
        log.info "Updated $stock with daily closing of ${quote.ask}"
    }

    /**
     * calculate the percent change over the time period specified by the day window setting
     *
     * @param stock
     * @return
     */
    Double percentChangeWindow(Stock stock) {
        List closings = stock.dailyClosings.toList()
        int window = grailsApplication.config.downturn.daysWindow
        if (closings.size() < window) {
            log.warn "Downturn has not been running long enough to calculate the percentChangeWindow. Asked to look $window days back, but we have only ${closings.size()}"
            return 0.0
        }
        def start = closings[-window]
        def end = closings[-1]
        return new Double(((end-start) / start) * 100).round(2)
    }

    /**
     * Calculates the number days in a row going backwards from the most recent day that the stock's ask price has been
     * declining.
     *
     * @param Stock
     * @return
     */
    int daysDown(Stock stock) {
        List closings = stock.dailyClosings.toList()
        int daysDown = 0
        for (int i = closings.size() - 1; i >= 1; i--) {
            if(closings[i] < closings[i-1]) {
                daysDown++
            } else {
                break
            }
        }
        daysDown
    }

    /**
     * Determine whether a stock notification is worthy of being emailed to the user
     *
     * @param stock
     * @return
     */
    boolean judgeStock(Stock stock) {
        daysDown(stock) >= grailsApplication.config.downturn.thresholds.daysDown || percentChangeWindow(stock) < grailsApplication.config.downturn.thresholds.percentDown
    }



}

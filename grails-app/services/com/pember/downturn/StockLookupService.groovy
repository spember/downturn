package com.pember.downturn

import com.pember.downturn.utils.yahoo.Quote
import grails.converters.JSON
import groovy.json.JsonSlurper

/**
 * Looks up stock information using Yahoo's stock api. So, I suppose it should really be called 'YahooLookupService'.
 *
 */
class StockLookupService {

    def lookupStocks(List symbols) {
        parseYahooResponse(queryYahoo(symbols))
    }

    /**
     * Loads a test response instead of querying the Yahoo service; they will throttle you for a while if you query
     * them too often
     *
     * @return
     */
    def testLookupStocks() {
        parseYahooResponse(new File("test/data/sample_yahoo_response.json").text)
    }

    /**
     * Execute a query against Yahoo's finance API, given a list of Stock symbols
     *
     * @param symbols
     * @return
     */
    def queryYahoo(List symbols) {
        def q = buildYahooQuery(symbols)
        //URL url = new URL("http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22GOOG%22%2C%22BRKB%22)%0A%09%09&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=cbfunc")
        URL url = new URL(q)
        url.text
    }

    /**
     * Builds the query to used by the queryYahoo method
     *
     * @param symbols
     * @return
     */
    String buildYahooQuery(List symbols) {
        //"http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22GOOG%22%2C%22BRKB%22)%0A%09%09&format=json&diagnostics=true&env=http%3A%2F%2Fdatatables.org%2Falltables.env&callback=cbfunc"
        def query = null
        if (symbols) {
            def symbol_query = '"' + symbols.join('","') + '"'
            def uri = URLEncoder.encode("select * from yahoo.finance.quotes where symbol in (${symbol_query})", "utf-8")
            query = "http://query.yahooapis.com/v1/public/yql?q=${uri}&format=json&diagnostics=true&env=http://datatables.org/alltables.env"
        }
        query
    }

    /**
     * Parses the relevant bits from the response into a List of Quote command objects
     *
     * @param data
     * @return
     */
    List parseYahooResponse(String data) {
        // Remove the function wrapper around the json response
        Map json = JSON.parse data
        int count = json["query"]["count"]
        List quotes = []
        if (count == 1) {
            quotes.add(new Quote(json["query"]["results"]["quote"]))
        } else {
            quotes = json["query"]["results"]["quote"].collect { new Quote(it) }
        }
        quotes
    }



}

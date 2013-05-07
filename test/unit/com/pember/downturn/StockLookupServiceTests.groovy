package com.pember.downturn

import com.pember.downturn.utils.yahoo.Quote
import grails.converters.JSON
import grails.test.mixin.*
import org.junit.*

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(StockLookupService)
class StockLookupServiceTests {

    void testParseYahooResponse() {
        def file = new File("test/data/sample_yahoo_response.json")
        def data = service.parseYahooResponse(file.text)

        assert data instanceof List
        assert 2 == data.size()
        //assertTrue data.containsKey("query")
        data.each {
            assert it instanceof Quote
        }
    }

    void testBuildYahooQuery() {
        assert service.buildYahooQuery([]) == null
        assert service.buildYahooQuery(null) == null
        assert "http://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.quotes+where+symbol+in+%28%22GOOG%22%29&format=json&diagnostics=true&env=http://datatables.org/alltables.env" == service.buildYahooQuery(["GOOG"])
        def url = service.buildYahooQuery(["GOOG", "CSCO", "TEST"])
        assert "http://query.yahooapis.com/v1/public/yql?q=select+*+from+yahoo.finance.quotes+where+symbol+in+%28%22GOOG%22%2C%22CSCO%22%2C%22TEST%22%29&format=json&diagnostics=true&env=http://datatables.org/alltables.env" == url

    }
}

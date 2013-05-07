package com.pember.downturn

import com.pember.downturn.utils.yahoo.Quote
import grails.test.mixin.*
import org.junit.*

@Mock([Stock, User, StockLookupService])
@TestFor(StockService)
class StockServiceTests {

    Stock stock // test stock

    @Before
    void init() {
        User user = new User(username: "tester", password: "password" )
        user.save()
        stock = new Stock(symbol: "TEST", name: "Testing Co", user: user)
    }

    @Test
    void testAdd() {
        def data = ["symbol": "goog", "name": "Google", bid: 846]
        Stock newstock = service.add(data, User.list()[0])
        assert newstock.validate()
        assert "goog" == newstock.symbol
        assert "Google" == newstock.name
    }

    @Test
    void testAddBadData() {
        def data = ["symbol": "gooooooooooog", name: "Google"]
        User user = User.list()[0]
        Stock newstock = service.add(data, user)
        assert !newstock.validate()

        data = [:]
        stock = service.add(data, user)
        assert !newstock.validate()

    }

    @Test
    void testAggregateDailyClosingsTooFew() {
        stock.addToDailyClosings(800.00)
        stock.addToDailyClosings(840.00)
        def change = service.percentChangeWindow(stock)
        assert 0.0 == change
    }

    @Test
    void testAggregateDailyClosings() {
        stock.addToDailyClosings(800.00)
        stock.addToDailyClosings(805.00)
        stock.addToDailyClosings(820.00)
        stock.addToDailyClosings(810.00)
        stock.addToDailyClosings(840.00)
        def change = service.percentChangeWindow(stock)
        assert 5.0 == change

        Stock down_stock = new Stock(symbol:  "TEST2", name: "Testing Co2")
        [900, 700, 905.5, 800, 750, 725, 715].each {closing->
            down_stock.addToDailyClosings closing
        }
        change = service.percentChangeWindow(down_stock)
        assert -21.04 == change
    }

    void testDaysDown() {
        stock.addToDailyClosings 60
        assert 0 == service.daysDown(stock)
        stock.addToDailyClosings 65
        assert 0 == service.daysDown(stock)
        stock.addToDailyClosings 66
        stock.addToDailyClosings 67
        assert 0 == service.daysDown(stock)
        stock.addToDailyClosings 64
        assert 1 == service.daysDown(stock)
        stock.addToDailyClosings 62
        stock.addToDailyClosings 61.9
        stock.addToDailyClosings 55
        assert 4 == service.daysDown(stock)
        stock.addToDailyClosings 57
        assert 0 == service.daysDown(stock)
        stock.addToDailyClosings 57
        assert 0 == service.daysDown(stock)
    }

    void testJudgeStock() {
        grailsApplication.config.downturn.thresholds.daysDown = 2
        grailsApplication.config.downturn.thresholds.percentDown = -3
        stock.dailyClosings = []
        assert false == service.judgeStock(stock)
    }

    void testUpdateStock() {
        Quote quote = new Quote()
        quote.name = "Testing Co"
        quote.symbol = "TEST"
        quote.ask = 65.0
        assert stock.dailyClosings == null
        service.updateStock(stock, quote)
        assert [65.0] as List == stock.dailyClosings.toList()
        quote.ask = 67.0
        service.updateStock(stock, quote)
        assert [65.0, 67.0] == stock.dailyClosings.toList()
    }

    void testUpdateStockDeletes() {
        // updateStocks should clear out old records
        Quote quote = new Quote()
        quote.name = "Testing Co"
        quote.symbol = "TEST"
        grailsApplication.config.downturn.numRecordsToSave = 3
        assert stock.dailyClosings == null
        [40.0, 50.0, 65.0, 60.0, 70.0].each { price->
            quote.ask = price
            service.updateStock(stock, quote)
        }
        assert [65.0, 60.0, 70.0] == stock.dailyClosings.toList()
    }

    void testUpdateAll() {
        stock.save()
        Stock stock2 = new Stock(symbol: "ACME", name: "ACME Co", user: User.list()[0])
        stock2.save()

        service.stockLookupService.metaClass.queryYahoo = { List symbols ->
            def file = new File("test/data/sample_yahoo_response.json")
            print "Rest assured, I'm in this mock"
            file.text
        }
        assert 0 == service.updateAllStocks()

        Stock stock3 = new Stock(symbol: "GOOG", name: "Google co", user: User.list()[0])
        stock3.save()
        assert 1 == service.updateAllStocks()
        assert 1 == stock3.dailyClosings.size()
    }
}

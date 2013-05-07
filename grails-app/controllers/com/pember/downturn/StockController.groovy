package com.pember.downturn

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['IS_AUTHENTICATED_FULLY'])
class StockController {

    def grailsApplication

    StockLookupService stockLookupService
    StockService stockService
    SpringSecurityService springSecurityService

    def index() { }

    def list() {

        if(request.xhr) {
            render Stock.list() as JSON
        } else {
            // In order to the Angular $http request to trigger as json on the server, have to add the
            // 'X-Requested-With': 'XMLHttpRequest' header
            render "Nope"
        }
    }

    def query() {
        if(params.query) {
            def symbols = params.query.split(" ")
            render stockLookupService.lookupStocks(symbols as List) as JSON
            return true
        }
    }

    def testQuery() {
        render stockLookupService.testLookupStocks() as JSON
    }


    def add() {
        def stock = stockService.add(request.JSON, User.findById(springSecurityService.principal.id))
        Map result = ["status": stock.hasErrors()]
        render result as JSON
    }

    def delete() {
        List stocks = Stock.findAllBySymbol(params["symbol"])
        stocks.each {stock ->
            stock.delete()
        }
        render Stock.list() as JSON
    }


    def test() {
        render stockLookupService.queryYahoo(["GOOG", "APPL"])
    }

    /*
        Some of these templates should ideally be Handlebars or Mustache templates.
     */
    /*
    def home() {
        render template: "home"
    }
    */

    def watchlist() {
        render template: "watchlist"
    }
}

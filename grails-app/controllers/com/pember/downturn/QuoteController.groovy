package com.pember.downturn

import grails.plugins.springsecurity.Secured

@Secured(["IS_AUTHENTICATED_FULLY"])
class QuoteController {

    def index() { }

    def search() {
        render template: "search"
    }
    def detail() {
        render template: "detail"
    }
}

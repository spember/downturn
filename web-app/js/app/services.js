/*global angular, downturn */
namespace("downturn.services");
    /**
     * Set up common utilities that controllers may share
     */
    downturn.services.utils = function() {
        // a little unnecessary, but just this as the foundation for getting services integrated
        this.back = function() {
            history.back();
        };
    };

    /**
    * Small service to help pass around quote objects without having to hit the server again
    */
    downturn.services.quoteService = function () {
        this.quotes = [];
    };

    /**
     * Same for Stocks
     */
    downturn.services.stockService = function () {
        this.stocks = [];
    };

    /**
     * Message service will contain messages to display to the user, similar to Grails's message scope and Django's
     * Message middleware (?).
     *
     * On call of retrieve(), the caller will receive a list of strings to be displayed to the user, which have been
     * added via the 'add' function. All messages, once retrieved, will be deleted.
     */
    downturn.services.messageService = function () {
        this.messages = [];

        this.add = function(msg) {
            if(typeof msg === "string") {
                this.messages.push(msg);
            }
        };

        /**
         * Returns the messages. Will delete them once retrieved
         */
        this.retrieve = function () {
            var max = this.messages.length,
                i = 0,
                holder = [];
            for (i; i < max; i++) {
                holder.push(this.messages.pop());
            }
            return holder;
        };

        this.fadeFirst = function (time) {
            var self = this;
            if (time === undefined || time === 0) {
                time = 3000;
            }
            setTimeout(function () {

                self.retrieve();
                console.log(self.messages);
            }, time);
        };
    };



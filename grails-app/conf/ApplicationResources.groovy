modules = {
    angular {
        resource url:'https://ajax.googleapis.com/ajax/libs/angularjs/1.1.3/angular.min.js', disposition: 'head'
        resource url:'https://ajax.googleapis.com/ajax/libs/angularjs/1.1.3/angular-resource.min.js', disposition: 'head'
    }

    baseCSS {
        resource "css/reset.css"
        resource "css/bootstrap.css"
        resource "css/downturn-base.css"
        resource "css/respond.css"
    }

    external {
        dependsOn "baseCSS"
    }

    downturn {
        dependsOn "angular"
        dependsOn "baseCSS"
        resource "css/downturn.css"

        resource "js/app/init.js"
        [
                "utils.js",
                "services.js",
                "controllers/stock.js",
                "controllers/quote.js",
                "directives/misc.js",
                "directives/messages.js",
                "templates/misc.js",
                "templates/stocks.js",

                // main is last
                "main.js"

        ].each {file ->
            resource "js/app/" + file
        }



    }
}
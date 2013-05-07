<!DOCTYPE html>
<!--[if lt IE 7 ]> <html ng-app lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html ng-app lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html ng-app lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html ng-app lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html ng-app="downturn" lang="en" class="no-js"><!--<![endif]-->
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Downturn"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link href='http://fonts.googleapis.com/css?family=Ubuntu:400,400italic|Sancreek' rel='stylesheet' type='text/css'>
    <r:require module="downturn"/>
    <g:layoutHead/>
    <r:layoutResources />
</head>
<body>
<header class="internal">
    <div class="container header-container btcf">
        <h3 class="logo"><a href = "#/home">Downturn</a></h3>
            <div class="btn-row primary">
                <a href="#/quote/search" class="btn btn-info">Search for Stocks</a>
                <a href="#/stock/list" class="btn btn-info">Watch list</a>
                <g:link controller="logout" action="index" class="btn btn-primary">Logout</g:link>
            </div>
    </div>
</header>
<g:layoutBody/>
<r:layoutResources />
</body>
</html>
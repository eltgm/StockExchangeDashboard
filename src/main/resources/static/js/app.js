'use strict'

let appModule = angular.module('appModule', ['ui.router', 'apexcharts']);
appModule.config(function ($stateProvider, $urlRouterProvider) {
    $stateProvider
        .state('news', {
            url: '/news',
            templateUrl: 'views/news_main.html',
            controller: 'newsController'
        })
        .state('stock', {
            url: '/stock/:stockId',
            templateUrl: 'views/stock_main.html',
            controller: 'stockController'
        })
    $urlRouterProvider.otherwise('/news')
});
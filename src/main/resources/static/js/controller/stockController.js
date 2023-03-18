'use strict'

appModule.controller('stockController', function ($scope, $rootScope, stockService, $stateParams) {
    $scope.init = function () {
        stockService.loadStockById($stateParams.stockId)
            .then(function success(stock) {
                let data = []
                let candles = stock.data.candles

                candles.month.forEach(function callback(currentValue) {
                    data.push({
                        x: new Date(currentValue.date),
                        y: [currentValue.open, currentValue.high, currentValue.low, currentValue.close]
                    })
                })

                let chart = new ApexCharts(document.querySelector("#chart"), {
                    series: [{
                        data: data
                    }],
                    chart: {
                        type: 'candlestick',
                        height: 350
                    },
                    title: {
                        text: '3 month candlestick chart',
                        align: 'left'
                    },
                    xaxis: {
                        type: 'datetime'
                    },
                    yaxis: {
                        tooltip: {
                            enabled: true
                        }
                    },
                    tooltip: {
                        theme: 'dark'
                    }
                })
                chart.render()
                $scope.stock = stock
            }, function error(response) {
                alert("Error from service! " + response.data.error)
            })
    }

    $scope.changeStockStatus = function (user, stock) {
        let existsStock = user.stocks.filter(stockItem => stockItem.ticker === stock.ticker)[0]
        let stocks = $scope.user.stocks;
        if (existsStock != null) {
            stockService.removeStockFromFavorites(user.id, existsStock.id)
                .then(function () {
                    $scope.user.stocks = stocks.filter(stockItem => stockItem.id !== existsStock.id)
                })
        } else {
            stockService.addStockToFavorites(user.id, stock)
                .then(function (returnedStock) {
                    stocks.push(returnedStock.data)
                })
        }
    }
});
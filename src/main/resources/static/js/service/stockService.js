'use strict'

let stockServiceFn = function ($http, $rootScope) {
    this.loadStockById = function (stockId) {
        let url = $rootScope.basePath + '/stocks/' + stockId
        return $http.get(url)
    }

    this.removeStockFromFavorites = function (userId, stockId) {
        return $http.delete($rootScope.basePath + '/user/' + userId + '/stock/' + stockId + '/remove')
    }

    this.addStockToFavorites = function (userId, stock) {
        return $http.post($rootScope.basePath + '/user/' + userId + '/stock/add', stock)
    }
}

appModule.service('stockService', stockServiceFn)
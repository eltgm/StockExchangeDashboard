'use strict'

let newsServiceFn = function ($http, $rootScope) {
    this.loadAllNews = function (page, pageSize) {
        let param = {
            page: page,
            pageSize: pageSize
        }
        return $http.get($rootScope.basePath + '/news', {
            params: param
        })
    }
}

appModule.service('newsService', newsServiceFn)
'use strict'

let userServiceFn = function ($http, $rootScope) {
    this.loadUserInfo = function () {
        return $http.get($rootScope.basePath + '/user')
    }

    this.logout = function () {
        return $http.get('http://localhost:8080/logout')
    }
}

appModule.service('userService', userServiceFn)
'use strict'

appModule.controller('mainController', function ($scope, $state, $rootScope, userService, $window) {
    $rootScope.basePath = 'http://localhost:8080/api/v1'

    $scope.init = function () {
        userService.loadUserInfo()
            .then(function (user) {
                $scope.user = user.data
            })
    }

    $scope.openNews = function () {
        $state.go('news', null, {'reload': true})
    }

    $scope.openStock = function (stockSymbol) {
        $state.go('stock', {stockId: stockSymbol}, {'reload': true})
    }

    $scope.logout = function () {
        userService.logout()
            .then(function () {
                $window.location.href = '/';
            })
    }
})
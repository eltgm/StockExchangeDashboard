'use strict'

appModule.controller('newsController', function ($scope, $rootScope, newsService) {
    const PAGE_SIZE = 6

    $scope.init = function () {
        newsService.loadAllNews(0, PAGE_SIZE)
            .then(function (news) {
                $scope.news = news
                $scope.currentPage = 0
                $scope.totalPages = news.data.totalPages
                $scope.currentDate = new Date()
            })
    }

    $scope.loadNews = function (page) {
        if (page < 0) {
            page = $scope.totalPages - 1
        } else if (page >= $scope.totalPages) {
            page = 0
        }

        newsService.loadAllNews(page, PAGE_SIZE)
            .then(function (news) {
                $scope.news = news
                $scope.currentPage = page
            })
    }
});
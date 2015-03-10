'use strict';

var app = angular.module('feeder', []);

var apiUrl = "http://45.56.76.113:8080/api/";
//apiUrl = "http://localhost:8080/api/";

app.controller('FeedsController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {

    $scope.feeds = [];
    $scope.feed = {};

    $scope.loadFeeds = function () {
        $http.get(apiUrl + 'feeds')
            .success(function (data) {
                $scope.feeds = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.addFeed = function () {
        console.log('Adding Feed: ' + $scope.feed);
        $http.post(apiUrl + 'feeds', $scope.feed)
            .success(function (data) {
                $scope.feed = {};
                $scope.loadFeeds();
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.deleteFeed = function (feedId) {
        console.log('Deleting Feed: ' + feedId);
        if (confirm('¿Eliminar Feed?')) {
            $http.delete(apiUrl + 'feeds/' + feedId)
                .success(function (data) {
                    $scope.loadFeeds();
                })
                .error(function (data, status) {
                    alert('Error: ' + data + ' :' + status);
                });
        }
    };

}]);

app.controller('FeedEntriesController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {

    $scope.feedEntries = [];
    $scope.states = [];

    $scope.loadStates = function() {
        $http.get(apiUrl + 'states')
            .success(function (data) {
                $scope.states = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.deleteFeedEntry = function (feedId, feedEntryId) {
        console.log('deleteFeedEntry');
        if (confirm('¿Eliminar entrada?')) {
            $http.delete(apiUrl + 'feeds/' + feedId + '/entries/' + feedEntryId)
                .success(function (data) {
                    $scope.loadAllFeedEntries();
                })
                .error(function (data, status) {
                    alert('Error: ' + data + ' :' + status);
                });
        }
    };

    $scope.loadAllFeedEntries = function () {
        $http.get(apiUrl + 'feeds/all-entries')
            .success(function (data) {
                $scope.feedEntries = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.loadStates();

    $scope.processFeedEntry = function(index) {
        var feedEntry = $scope.feedEntries[index];
        var feedEntryProcess = {};
        feedEntryProcess.words = feedEntry.matched_words_as_string;
        feedEntryProcess.stateCode = feedEntry.state;
        $http.post(apiUrl + 'feeds/' + feedEntry.feedId + '/entries/' + feedEntry.id, feedEntryProcess)
            .success(function (data) {
                $scope.loadAllFeedEntries();
            })
            .error(function (data, status) {
                alert('Error: ' + status + ' :' + data._errorMessages[0]);
            });
    }

}]);

app.controller('CopisController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {

    $scope.states = [];
    $scope.copi = {};

    $scope.copi.texts = [];
    var copiText = {};
    copiText.text = "";
    $scope.copi.texts.push(copiText);

    $scope.loadStates = function() {
        $http.get(apiUrl + 'states')
            .success(function (data) {
                $scope.states = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.loadOngs = function() {
        $http.get(apiUrl + 'ongs')
            .success(function (data) {
                $scope.ongs = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.saveCopis = function() {
        console.log($scope.copi);
        if(confirm('¿Es correcta la informacion de los Copis? Checa bien los datos :-)')) {
            $http.post(apiUrl + 'copis/learn', $scope.copi)
                .success(function (data) {
                    $scope.copi = {};
                    $scope.copi.texts = [];
                    var copiText = {};
                    copiText.text = "";
                    $scope.copi.texts.push(copiText);
                    alert('Copis guardados correctamente');
                })
                .error(function (data, status) {
                    alert('Error: ' + data + ' :' + status);
                });
        }
    };

    $scope.addCopi = function() {
        var copiText = {};
        copiText.text = "";
        $scope.copi.texts.push(copiText);
    };


}]);

app.controller('ChangeCopisController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {

    $scope.states = [];
    $scope.ongs = [];
    $scope.copi = {};

    $scope.loadCombos = function() {
        $scope.loadStates();
        $scope.loadOngs();
    };

    $scope.loadStates = function() {
        $http.get(apiUrl + 'states')
            .success(function (data) {
                $scope.states = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.loadOngs = function() {
        $http.get(apiUrl + 'ongs')
            .success(function (data) {
                $scope.ongs = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.saveChangeCopi = function() {
        console.log($scope.copi);
        if(confirm('¿Es correcta la informacion del Copi?')) {
            $http.post(apiUrl + 'copis/change', $scope.copi)
                .success(function (data) {
                    $scope.copi = {};
                    alert('Copi guardado correctamente');
                })
                .error(function (data, status) {
                    alert('Error: ' + data + ' :' + status);
                });
        }
    };

}]);

app.controller('OngsController', ['$scope', '$rootScope', '$http', function ($scope, $rootScope, $http) {

    $scope.ongs = [];
    $scope.ong = {};

    $scope.loadOngs = function() {
        $http.get(apiUrl + 'ongs')
            .success(function (data) {
                $scope.ongs = data._data;
            })
            .error(function (data, status) {
                alert('Error: ' + data + ' :' + status);
            });
    };

    $scope.saveOng = function() {
        console.log($scope.ong);
        if(confirm('¿Guardar ONG?')) {
            $http.post(apiUrl + 'ongs', $scope.ong)
                .success(function (data) {
                    $scope.ong = {};
                    alert('ONG guardada correctamente');
                    window.location = "/ongs";
                })
                .error(function (data, status) {
                    alert('Error: ' + data + ' :' + status);
                });
        }
    };

}]);
'use strict';

/**
 * @ngdoc function
 * @name oauthClientAngularApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the oauthClientAngularApp
 */
angular.module('oauthClientAngularApp')
  .controller('AboutCtrl', function ($scope, $http, $sessionStorage) {

    $http({
      method: 'GET',
      url: 'http://localhost:8080/api/user',
      headers: {
        'Authorization': 'Bearer ' + $sessionStorage.token.access_token
      }
    }).then(
      function (response) {
        $scope.userDetails = response.data;
      }
    );

  });

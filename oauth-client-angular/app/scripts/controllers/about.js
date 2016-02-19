'use strict';

/**
 * @ngdoc function
 * @name oauthClientAngularApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the oauthClientAngularApp
 */
angular.module('oauthClientAngularApp')
  .controller('AboutCtrl', function ($scope, $rootScope, $http) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    $http({
      method: 'GET',
      url: 'http://localhost:8080/api/user',
      headers: {
        'Authorization': 'Bearer ' + $rootScope.accessToken
      }
    }).then(
      function (response) {
        console.log(response);
        $scope.userDetails = response.data;
      }
    );

  });

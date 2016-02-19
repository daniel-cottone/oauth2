'use strict';

/**
 * @ngdoc function
 * @name oauthClientAngularApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the oauthClientAngularApp
 */
angular.module('oauthClientAngularApp')
  .controller('MainCtrl', function ($scope, $rootScope, $window, $state, $stateParams) {
    this.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];

    if ($stateParams.access_token) {
      var parameters = $stateParams.access_token.split('&');
      var accessToken = parameters[0].split('=');
      $rootScope.accessToken = accessToken[1];
      $state.go('app.about', { access_token: undefined });
    }

    $scope.login = function () {
      $window.location.href = 'http://localhost:9999/uaa/oauth/authorize?response_type=token&client_id=acme&redirect_uri=http://localhost:9000/#/';
    };

  });

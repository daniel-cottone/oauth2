'use strict';

/**
 * @ngdoc function
 * @name oauthClientAngularApp.controller:HeaderCtrl
 * @description
 * # HeaderCtrl
 * Controller of the oauthClientAngularApp
 */
angular.module('oauthClientAngularApp')
  .controller('HeaderCtrl', function ($timeout, $scope, AccessToken) {

    $timeout(function() {
      $scope.logged = !!AccessToken.get();
    }, 0);

  });

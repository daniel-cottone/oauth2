'use strict';

/**
 * @ngdoc function
 * @name oauthClientAngularApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the oauthClientAngularApp
 */
angular.module('oauthClientAngularApp')
  .controller('LoginCtrl', function ($location, AccessToken) {

    var hash = $location.path().substr(1);
    AccessToken.setTokenFromString(hash);
    $location.path('/');
    $location.replace();

  });

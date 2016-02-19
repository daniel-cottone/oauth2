'use strict';

/**
 * @ngdoc overview
 * @name oauthClientAngularApp
 * @description
 * # oauthClientAngularApp
 *
 * Main module of the application.
 */
angular
  .module('oauthClientAngularApp', [
    'ngAnimate',
    'ngResource',
    'ngStorage',
    'ui.router'
  ])
  .config(function ($urlRouterProvider, $stateProvider) {

    // Default route
    $urlRouterProvider.otherwise('/');

    // Routes configuration
    $stateProvider

    // Application root
    .state('app', {
      url: '/:access_token',
      views: {
        'header': {
          templateUrl: 'views/header.html',
          controller: 'HeaderCtrl'
        },
        'content': {
          templateUrl: 'views/main.html',
          controller: 'MainCtrl'
        },
        'footer': {
          templateUrl: 'views/footer.html',
          controller: 'FooterCtrl'
        }
      }
    })

    // Account
    .state('app.about', {
      url: 'about/',
      views: {
        'content@': {
          templateUrl: 'views/about.html',
          controller: 'AboutCtrl'
        }
      }
    });

  });

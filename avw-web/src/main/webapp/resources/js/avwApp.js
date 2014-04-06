/**
 * 
 */

var avwApp = angular.module('avwApp', [ 'ngRoute', 'avwControllers' ]);

avwApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/', {
		controller : 'HomeController'
	}).otherwise({
		redirectTo : '/'
	});
} ]);
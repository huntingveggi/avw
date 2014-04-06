/**
 * 
 */

var avwControllers = angular.module('avwControllers', []);

avwControllers.controller('HomeController', function($scope, $http) {
	$scope.header = "resources/partials/header.html";
	$scope.content = "resources/partials/home.html";
	$scope.footer = "resources/partials/footer.html";
});

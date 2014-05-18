/**
 * 
 */

function LOG(object) {
	console.log(object);
}

var baseUrl = "/avw-web";

var app = angular.module('orders', [])

.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-with'];
} ])

.controller('OrdersController', [ '$scope', '$http', function($scope, $http) {

	// find documents by id
	$scope.findAll = function() {
		$http.get(baseUrl + "/orders/.json").success(function(result) {
			$scope.orders = result.response.model;
			LOG($scope.documents);
		}).error(function(result) {
			LOG(result);
		});
	};

	// find documents by id
	$scope.create = function() {
		$http.get(baseUrl + "/orders/create.json").success(function(result) {
			$scope.currentOrder = result.response.model;
			LOG($scope.currentOrder);
		}).error(function(result) {
			LOG(result);
		});
	};
	
	// find documents by id
	$scope.update = function(order) {
		LOG($scope.currentOrder);
		$http.post(baseUrl + "/orders/update.json", order).success(function(result) {
			$scope.currentOrder = result.response.model;
			LOG($scope.currentOrder);
		}).error(function(result) {
			LOG($scope.currentOrder);
			LOG(result);
		});
	};

	$scope.showOrderDetails = function(id) {
		var url = 'http://localhost:8080/avw-web/orders/' + id;
		window.location.href = url;
	};

} ]);

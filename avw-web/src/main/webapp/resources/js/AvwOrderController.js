/**
 * 
 */

var baseUrl = "/avw-web";

var avwControllers = angular.module('avwControllers', []);

avwControllers.controller('HomeController', function($scope, $http) {

});

avwControllers.controller('OrderController', function($scope, $http) {

	$scope.findById = function(id) {
		$http.get(baseUrl + "/orders/" + id + ".json").success(
				function(result) {
					$scope.currentOrder = result.order;
					LOG(result);
				}).error(function(result) {
			LOG(result);
		});
	};

	$scope.findAll = function() {
		$http.get(baseUrl + "/orders/.json").success(function(result) {
			$scope.orders = result.order.objects;
		}).error(function(result) {
			console.log(result);
		});
	};

	$scope.create = function() {
		$http.get(baseUrl + "/orders/create.json").success(function(result) {
			$scope.currentOrder = result.order;
			$scope.save();
			$scope.orders.push($scope.currentOrder);
		}).error(function(result) {
			console.log(result);
		});
	};

	$scope.save = function() {
		$http.get(baseUrl + "/orders/save.json").error(function(result) {
			console.log(result);
		});
	};

	$scope.deleteOrder = function(order) {
		$http.get(baseUrl + "/orders/" + order.id + "/delete.json").success(
				function(result) {
					$scope.findAll();
				}).error(function(result) {
			console.log(result);
		});
	};

	$scope.updateOrder = function(order) {
		$http.post(baseUrl + "/orders/update.json", order).success(
				function(result) {
					$scope.findAll();
				}).error(function(result) {
			console.log(result);
		});
	};

	$scope.findAll();

});

function LOG(object) {
	Console.log(object);
}

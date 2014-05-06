/**
 * 
 */

var baseUrl = "/avw-web";

var avwControllers = angular.module('avwControllers', []);

avwControllers.controller('HomeController', function($scope, $http) {

});

avwControllers.controller('OrderController', function($scope, $http) {


	$scope.currentOrder = function() {
		$http.get(baseUrl + "/orders/current.json").success(function(result) {
			$scope.currentOrder = result.order;
		}).error(function(result) {
			console.log(result);
		});
	};
	
	$scope.createOrder = function() {
		$http.get(baseUrl + "/orders/create.json").success(function(result) {
			$scope.currentOrder = result.order;
		}).error(function(result) {
			console.log(result);
		});
	};

	$scope.updateOrder = function(order) {
		order.id="1111111111";
		$http.post(baseUrl + "/orders/add.json", order).success(function(result) {
			$scope.currentOrder = result.order;
		}).error(function(result) {
			console.log(result);
		});
	};
	
	
	$scope.currentOrder();
	
});

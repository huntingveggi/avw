/**
 * 
 */

var avwApp = angular.module('order', [ 'ngResource' ]);

avwApp.config(['$httpProvider',function($httpProvider) {
	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-with'];
}]);

avwApp
		.controller('OrderController',
				[ '$scope', 'service', function($scope, service) {

					$scope.currentOrders = [];
					$scope.order;

					$scope.newInstance = function() {
						$scope.order = service.newInstance();
					};

					$scope.persist = function(order) {
						service.persist(order);
					};

				} ])
		.factory(
				'service',
				[
						'$http',
						'$resource',
						function($http, $resource) {

							return {
								newInstance : function() {
									return {
										id : '',
										comment : ''
									};
								},
								persist : function(order) {
									var url = 'http://127.0.0.1:5984/baseball/6e1295ed6c29495e54cc05947f18c8af';
									$http.defaults.useXDomain = true;
//									var res = 	$resource(url);
//									res.query(function(){
//										res.$save();
//									});
									
									$http.put(url, order).success(
											function(result) {
												console.log(result);
											}).error(function(result) {
										console.log(result);
									});
								}
							};
						} ]);
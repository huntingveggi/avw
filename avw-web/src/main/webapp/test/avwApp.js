/**
 * 
 */

var avwApp = angular.module('order', [ 'ngResource' ]);

avwApp.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-with'];
} ]);

avwApp.controller('OrderController',
		[ '$scope', 'service', function($scope, service) {

			$scope.currentOrders = [];
			$scope.order;

			$scope.newInstance = function() {
				$scope.order = service.newInstance();
			};

			$scope.persist = function(order) {
				service.persist(order);
			};

			$scope.newInstance();

		} ]).factory(
		'service',
		[
				'$http',
				'$resource',
				function($http, $resource) {

					return {

						urlId : '',

						newInstance : function() {
							return {
								id : 'Id',
								comment : 'Comment'
							};
						},
						persist : function(order) {

							url = 'http://127.0.0.1:5984/';

							$http.get('http://127.0.0.1:5984/_uuids').success(
									function(data) {
										id = "" + data.uuids;
										console.log("id url " + id);
										url = url + "baseball/" + id;
										console.log("afer url " + url);
										$http.put(url, order).error(
												function(d2) {
													console.log(d2);
												});
									}).error(function(data) {
								console.log("error " + data);
							});

						}
					};
				} ]);
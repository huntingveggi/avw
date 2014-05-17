/**
 * 
 */

var avwApp = angular.module('avwApp', [ 'ngResource' ]);

avwApp.config([ '$httpProvider', function($httpProvider) {
	$httpProvider.defaults.useXDomain = true;
	delete $httpProvider.defaults.headers.common['X-Requested-with'];
} ]);

avwApp
		.controller(
				'DocumentsController',
				[
						'$scope',
						'$http',
						'service',
						function($scope,$http, service) {

							$scope.currentDocuments = [];
							$scope.currentDocument;

							$scope.newInstance = function() {
								$http
										.get(
												'http://localhost:8080/avw-web/documents/create/pdf/testContainer.json')
										.success(
												function(data) {
													console.log(data);
													$scope.currentDocument = data.model;
												}).error(function(data) {
											console.log(data);
										});
							};

							$scope.persist = function(document) {
								service.persist(document);
							};

							$scope.newInstance();

						} ]).factory('service',
				[ '$http', '$resource', function($http, $resource) {

					return {

						urlId : '',

						newInstance : function() {
							return {
								id : 'Id',
								comment : 'Comment'
							};
						},
						persist : function(document) {

						}
					};
				} ]);
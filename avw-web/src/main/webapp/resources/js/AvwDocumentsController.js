/**
 * 
 */

function LOG(object) {
	console.log(object);
}

var baseUrl = "/avw-web";

var app = angular
		.module('documents', [])

		.config([ '$httpProvider', function($httpProvider) {
			$httpProvider.defaults.useXDomain = true;
			delete $httpProvider.defaults.headers.common['X-Requested-with'];
		} ])

		.controller(
				'DocumentsController',
				[
						'$scope',
						'$http',
						function($scope, $http) {

							// find documents by id
							$scope.findAll = function() {
								$http
										.get(baseUrl + "/documents/.json")
										.success(
												function(result) {
													$scope.documents = result.responseMessage.model;
													LOG($scope.documents);
												}).error(function(result) {
											LOG(result);
										});
							};

							// find available mimetyps by id
							$scope.findAvailableMimeTypes = function() {
								$http
										.get(
												baseUrl
														+ "/documents/mimetypes.json")
										.success(
												function(result) {
													$scope.mimetypes = result.responseMessage.model.objects;
													LOG($scope.mimetypes);
												}).error(function(result) {
											LOG("ERROR: " + result);
										});
							};

							$scope.showDocumentDetails = function(id) {
								var url = 'http://localhost:8080/avw-web/documents/'
										+ id + '/details';
								window.location.href = url;
							};

							$scope.showContainerDetails = function(id) {
								var url = 'http://localhost:8080/avw-web/containers/'
										+ id + '/details';
								window.location.href = url;
							};

							// find available mimetyps by id
							$scope.download = function(id) {

								var url = 'http://localhost:8080/avw-web/documents/'
										+ id;

								window.location.href = url;
							};

							// find available containers
							$scope.findAvailableContainers = function() {
								$http
										.get(baseUrl + "/containers/.json")
										.success(
												function(result) {
													$scope.containers = result.responseMessage.model;
													LOG($scope.containers);
												}).error(function(result) {
											LOG("ERROR: " + result);
										});
							};

						} ]);

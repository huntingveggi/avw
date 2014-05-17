<!doctype html>
<html ng-app="avwApp">
<head>
<script src="/avw-web/resources/js/angular.js"></script>
<script src="/avw-web/resources/js/angular-resource.js"></script>
<script src="/avw-web/resources/js/avwApp2.js"></script>
</head>
<body>
	<h1>Documents</h1>
	<ul>
		<li>http://localhost:8080/avw-web/documents/create/pdf/Testme.json <button ng-click="newInsstance()">new instance</button></li>
	</ul>
	<div ng-controller="DocumentsController">
		{{currentDocument.id}} | {{currentDocument.container.id}}
	</div>
	<hr>

	<h1>Orders</h1>
</body>
</html>


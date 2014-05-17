<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="documents">
<head>
<%@include file="../default.jsp"%>
<script src="/avw-web/resources/js/AvwDocumentsController.js"></script>

<title>Document containers overview</title>

</head>
<body>
<body ng-controller="DocumentsController" ng-init="findAvailableContainers()">
	<ul>
		<li ng-repeat="container in containers | orderBy:'id'">
			{{container.id}} - {{container.name}}
		</li>
	</ul>
</body>
</html>

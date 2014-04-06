<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="avwApp">
<head>

<title>Home</title>
<script src="resources/js/angular.js"></script>
<script src="resources/js/angular-route.js"></script>
<script src="resources/js/avwApp.js"></script>
<script src="resources/js/avwControllers.js"></script>
<link href="resources/avw.css" rel="stylesheet" type="text/css">
</head>
<body>

	<div ng-controller="HomeController">

		<div id="header" ng-include="header"></div>

		<div id="content" ng-include="content"></div>

		<div id="footer" ng-include="footer"></div>


	</div>
</body>

</html>

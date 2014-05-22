<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="orders">
<head>
<title>Orders overview</title>
<%@include file="../default.jsp"%>
<script src="/avw-web/resources/js/AvwOrdersController.js"></script>

</head>
<body>
<body ng-controller="OrdersController" ng-init="create()">
	<h1>Create order</h1>
	<hr>
		id: {{currentOrder.id}}<br> 
		state: {{currentOrder.state.name}}<br>
		comment: <input
			ng-model="currentOrder.comment"><br>
			<input type="submit" name="submit" value="submit" ng-click="update(currentOrder)">
</body>
</html>

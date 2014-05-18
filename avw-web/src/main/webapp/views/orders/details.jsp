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
<body ng-controller="OrdersController" ng-init="findAll()">
	<h1>Orders Overview</h1>
	<hr>
	<ul>
		<li ng-repeat="order in orders | orderBy:'id'">
			<table>
				<tr>
					<td><a href="/avw-web/orders/{{order.id}}">{{order.id}}</a>
					<br>
					Comment: {{order.comment}}<br>
					State: {{order.state.name}}
					</td>
				<tr>
			</table>
		</li>
	</ul>
</body>
</html>

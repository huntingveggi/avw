<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="avwApp">
<head>
<%@include file="../default.jsp"%>

<title>Documents</title>

</head>
<body>
<body ng-controller="DocumentsController">
	<ul>
		<li ng-repeat="document in documents | orderBy:'id'">
			<table>
				<tr>
					<td><a href="/avw-web/orders/{{order.id}}">{{order.id}}</a></td>
					<td><br>Comment: <input ng-model="order.comment">

					</td>
					<td><button ng-click="deleteOrder(order)">delete</button></td>
					<td><button ng-click="updateOrder(order)">update</button></td>
				<tr>
			</table>
		</li>
	</ul>
</body>
</html>

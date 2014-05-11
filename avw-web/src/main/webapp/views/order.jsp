<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="avwApp">
<head>
<%@include file="default.jsp"%>

<title>Order</title>

</head>
<body>
<body ng-controller="OrderController">

	<table>
		<tr>
			<td>ID</td>
			<td>:</td>
			<td>{{currentOrder.id}}</td>
		</tr>
		<tr>
			<td>Comment</td>
			<td>:</td>
			<td>{{currentOrder.comment}}</td>
		</tr>
	</table>
</html>

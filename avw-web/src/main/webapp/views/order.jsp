<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="avwApp">
<head>
<%@include file="default.jsp"%>

<title>Order </title>

</head>
<body>
<body ng-controller="OrderController">
	{{currentOrder.id}}
	<button ng-click="updateOrder(currentOrder)">update</button>
</html>

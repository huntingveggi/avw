<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="avwApp">
<head>

<title>Order</title>
<jsp:include page="structure/head.jsp" />
</head>
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
<jsp:include page="structure/foot.jsp" />	
</body>
</html>

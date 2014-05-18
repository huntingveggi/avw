<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="documents">
<head>
<%@include file="../default.jsp"%>
<script src="/avw-web/resources/js/AvwDocumentsController.js"></script>

<title>Documents</title>

</head>
<body>
<body ng-controller="DocumentsController" ng-init="findAll()">
	<h1>Documents overview</h1>
	<hr>
	<ul>
		<li ng-repeat="document in documents | orderBy:'id'">
			<table>
				<tr>
					<td><a href="/avw-web/documents/{{document.id}}/details">{{document.id}} - {{document.container.name}}</a></td>
				<tr>
			</table>
		</li>
	</ul>
</body>
</html>

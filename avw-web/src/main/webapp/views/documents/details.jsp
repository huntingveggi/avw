<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="documents">
<head>
<%@include file="../default.jsp"%>
<script src="/avw-web/resources/js/AvwDocumentsController.js"></script>

<title>Document details</title>

</head>
<body>

	<body ng-controller="DocumentsController">
<h1>Document details</h1>
	<hr>
	 Document id : ${response.model.id}
	<br> Container :
	<a
		href="http://localhost:8080/avw-web/containers/${response.model.container.name}/details">${response.model.container.name}</a>
	<br>
	<br> Mimetype name : ${response.model.mimeType.description}
	- ${response.model.mimeType.mimeType}
	<br> Version : ${response.model.version}
	<br>
	<br>
	<a href="/avw-web/documents/${response.model.id}">Download</a>
</body></html>

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
	Details:<br>
	Document id : ${responseMessage.model.id}<br>
	Container : <a href="http://localhost:8080/avw-web/containers/${responseMessage.model.container.name}/details">${responseMessage.model.container.name}</a><br><br>
	
	Mimetype name : ${responseMessage.model.mimeType.description} - ${responseMessage.model.mimeType.mimeType}<br>
	Version : ${responseMessage.model.version}<br>
	<br>
	<a href="/avw-web/documents/${responseMessage.model.id}">Download</a>
</body>
</html>

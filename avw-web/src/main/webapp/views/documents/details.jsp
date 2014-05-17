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
	Details:<br>
	Document id : ${responseMessage.model.id}<br>
	Container name : ${responseMessage.model.container.name}<br>
	Mimetype name : ${responseMessage.model.mimeType.description} - ${responseMessage.model.mimeType.mimeType}<br>
	<br>
	<a href="/avw-web/documents/${responseMessage.model.id}">Download</a>
</body>
</html>

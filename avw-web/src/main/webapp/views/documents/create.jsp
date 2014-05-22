<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="documents">
<head>
<%@include file="../default.jsp"%>
<script src="/avw-web/resources/js/AvwDocumentsController.js"></script>

<title>Create document</title>

</head>
<body>
<body ng-controller="DocumentsController" ng-init="create()">
	<h1>Create document</h1>
	<hr>
	<br>

	<form action="/avw-web/documents/upload" method="POST"
		enctype="multipart/form-data" accept="*/*">
		id: {{currentDocument.id}}<br>
		ContainerName: <input type="text" name="containerName"><br>
		MimeType: <select name="mimetype" ng-init="findAvailableMimeTypes()">
			<option ng-repeat="type in mimetypes" value="{{type.extension}}">{{type.extension}}</option>
		</select><br> File: <input type="file" name="file"><br> <br><input
			type="submit" name="submit" value="submit"><br>
	</form>
</body>
</html>

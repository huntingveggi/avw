<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="documents">
<head>

<title>API</title>
<%@include file="default.jsp"%>
<script src="/avw-web/resources/js/AvwDocumentsController.js"></script>

<style type="text/css">
* {
	font-family: Arial;
}

.div-head {
	background-color: #efefef;
}

.div-body {
	margin-bottom: 10px;
}

.div-head,.div-body {
	width: 800px;
	padding: 3px;
}

hr {
	border: solid red 1px;
}
</style>

</head>
<body ng-controller="DocumentsController">
	<h1>Documents</h1>
	<hr>

	<div class="div-head">Create documents</div>
	<div class="div-body">
		<a href="http://localhost:8080/avw-web/documents/create">http://localhost:8080/avw-web/documents/create</a>
	</div>

	<div class="div-head">Show document details</div>
	<div class="div-body">

		http://localhost:8080/avw-web/documents/ <input ng-model="documentId"
			ng-init="documentId='type document id here'" ng-click="documentId=''"
			value="type document id" ng-minlength="1">/details
		<button ng-click="showDocumentDetails(documentId)">OPEN</button>
	</div>
	
	<div class="div-head">Document mimetypes</div>
	<div class="div-body">
	<select name="mimetype" ng-init="findAvailableMimeTypes()">
		<option ng-repeat="type in mimetypes">{{type.extension}} - {{type.mimeType}}</option>
	</select>
	</div>
	
	<div class="div-head">Document upload</div>
	<div class="div-body">
	http://localhost:8080/avw-web/documents/upload<br> 
	 --> This is convinience api which is called from the form of <a href="http://localhost:8080/avw-web/documents/create">http://localhost:8080/avw-web/documents/create</a>
	</div>
	
	<div class="div-head">Download existing documents</div>
	<div class="div-body">

		http://localhost:8080/avw-web/documents/ <input ng-model="documentId2"
			ng-init="documentId2='type document id here'" ng-click="documentId2=''"
			value="type document id">
		<button ng-click="download(documentId2)">DOWNLOAD</button>
	</div>

</body>

</html>

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
	background-color: #fcfcfc;
	padding: 3px;
	padding-bottom: 3px;
	margin-bottom: 10px;
	border-bottom: solid #efefef 1px;
	border-top: solid #efefef 1px;
}

.div-body {
	padding: 3px;
	margin-bottom: 10px;
	padding-bottom: 20px;
	margin-left: 50px;
}

.div-head,.div-body {
	width: 800px;
}

hr {
	border: solid red 1px;
}
</style>

</head>
<body ng-controller="DocumentsController">
	<h1>Documents</h1>
	<hr>

	<div class="div-head">Show existing documents</div>
	<div class="div-body">
		<a href="http://localhost:8080/avw-web/documents/">http://localhost:8080/avw-web/documents/</a>
	</div>

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
			<option ng-repeat="type in mimetypes">{{type.extension}} -
				{{type.mimeType}}</option>
		</select>
	</div>

	<div class="div-head">Document upload</div>
	<div class="div-body">
		http://localhost:8080/avw-web/documents/upload<br> --> This is
		convinience api which is called from the form of <a
			href="http://localhost:8080/avw-web/documents/create">http://localhost:8080/avw-web/documents/create</a>
	</div>

	<div class="div-head">Download existing documents</div>
	<div class="div-body">

		http://localhost:8080/avw-web/documents/ <input ng-model="documentId2"
			ng-init="documentId2='type document id here'"
			ng-click="documentId2=''" value="type document id">
		<button ng-click="download(documentId2)">DOWNLOAD</button>
	</div>
	
	<hr>
	<h1>Containers</h1>
	<hr>
	
	<div class="div-head">Containers overview</div>
	<div class="div-body">
		<a href="http://localhost:8080/avw-web/containers/ ">http://localhost:8080/avw-web/containers/</a>				
	</div>
	
	<div class="div-head">Show container details</div>
	<div class="div-body">

		http://localhost:8080/avw-web/containers/ <input ng-model="containerId"
			ng-init="containerId='type container id'" ng-click="containerId=''"
			value="type containerId id" ng-minlength="1">/details
		<button ng-click="showContainerDetails(containerId)">OPEN</button>
	</div>

</body>

</html>

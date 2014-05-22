<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="documents">
<head>
<%@include file="../default.jsp"%>
<script src="/avw-web/resources/js/AvwDocumentsController.js"></script>

<title>Container details</title>

</head>
<body>
<body ng-controller="DocumentsController">

	<h1>Container details</h1>
	<hr>
	Details:
	<br> Container id : ${container.model.id}
	<br>
	<br>Contained documents:
	<br>
	<c:forEach var="doc" items="${documents.model}">
	<a href="http://localhost:8080/avw-web/documents/${doc.id}/details">${doc.id} - Version ${doc.version}</a><br>
	</c:forEach>
	<br>
	<br>
</body>
</html>

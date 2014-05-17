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
	Details:
	<br> Container id : ${container.model.id}
	<br>
	Documents:
	<br>
	<c:forEach var="doc" items="${documents.model}">
	${doc.id}<br>
	</c:forEach>
	<br>
	<br>
</body>
</html>

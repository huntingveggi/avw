<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!doctype html>
<html ng-app="avwApp">
<head>

<title>Home</title>
<jsp:include page="structure/head.jsp" />
</head>
<body>
	<!-- Fixed navbar -->
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="#" onClick="click('HOME')">Auftragsverwaltung</a>
			</div>
			<div class="navbar-collapse collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="#" onClick="click('ORDER')">Auftr&auml;ge</a></li>
					<li><a href="#" onClick="click('DOC')">Dokumente</a></li>
					<li><a href="#" onClick="click('DOC')">Contact</a></li>
				</ul>
			</div>
			<!--/.nav-collapse -->
		</div>
	</div>
	<div ng-controller="HomeController">HOME</div>


	<jsp:include page="structure/foot.jsp" />
</body>

</html>

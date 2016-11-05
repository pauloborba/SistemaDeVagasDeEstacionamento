
<%@ page import="sistemadevagasdeestacionamento.ReservedParkingSpace" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservedParkingSpace.label', default: 'ReservedParkingSpace')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<g:each in="${reservedParkingsSpace}" var="person" status="i">
			<br/>
			<h3>${i+1}. ${person.description}, ${person.information}</h3>
			<p>
				Age: ${person.firstDate} , Total Time: ${person.totalTime}
			</p>

			<br/>
		</g:each>
	</body>
</html>

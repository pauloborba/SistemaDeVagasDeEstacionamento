
<%@ page import="sistemadevagasdeestacionamento.ReservedParkingSpace" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'reservedParkingSpace.label', default: 'ReservedParkingSpace')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-reservedParkingSpace" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-reservedParkingSpace" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:each in="${reservedInstance}" status="i" var="reservedParkingSpaceInstance">
				<h3>${i+1}. ${reservedParkingSpaceInstance.user.username}</h3>
				<p>
					Data e Horário de Entrada: ${reservedParkingSpaceInstance.date}
				</p>
					Setor: ${reservedParkingSpaceInstance.parkingSpace.sector}
				</p>
				<p>
					Preferencial: ${reservedParkingSpaceInstance.parkingSpace.preferential}
				</p>
				<p>
					Descrição: ${reservedParkingSpaceInstance.parkingSpace.description}
				</p>
				<br/>
			</g:each>

			<div class="pagination">
				<g:paginate total="${reservedParkingSpaceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

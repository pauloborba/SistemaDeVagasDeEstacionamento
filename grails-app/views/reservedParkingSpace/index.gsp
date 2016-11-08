
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
			<g:each in="${reservedParkingSpaceInstanceList}" status="i" var="reservedParkingSpaceInstance">
				<h3>${i+1}. ${reservedParkingSpaceInstance.reserved.owner.username}</h3>
				<p>
					Horário de Entrada: ${reservedParkingSpaceInstance.firstDate}
				</p>
				<p>
					Horário de Saída: ${reservedParkingSpaceInstanceList.lastDate}
				</p>
				<p>
					Tempo Total da Reserva: ${reservedParkingSpaceInstanceList.totalTime}
				</p>
				<p>
					Setor: ${reservedParkingSpaceInstance.reserved.sector}
				</p>
				<p>
					Preferencial: ${reservedParkingSpaceInstance.reserved.preferential}
				</p>
				<br/>
			</g:each>

			<div class="pagination">
				<g:paginate total="${reservedParkingSpaceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

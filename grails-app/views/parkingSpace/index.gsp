
<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'parkingSpace.label', default: 'ParkingSpace')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-parkingSpace" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-parkingSpace" class="content scaffold-list" role="main">


			<h1><g:message code="default.list.label" args="[entityName]" /></h1>

				<g:form controller="ParkingSpace" action="pref">
				Preferencial	<g:checkBox name="preferential" value="${false}" />
				Setor	<g:checkBox name="sector" value="${false}" />
					<g:submitButton name="Submit" value="Update" />

				</g:form>


			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>

			<table>
			<thead>
					<tr>
						<g:sortableColumn property="owner" title="${message(code: 'parkingSpace.owner.label', default: 'Owner')}" />

						<g:sortableColumn property="description" title="${message(code: 'parkingSpace.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="sector" title="${message(code: 'parkingSpace.sector.label', default: 'Sector')}" />
					
						<g:sortableColumn property="available" title="${message(code: 'parkingSpace.available.label', default: 'Available')}" />
					
						<g:sortableColumn property="preferential" title="${message(code: 'parkingSpace.preferential.label', default: 'Preferential')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${parkingSpaceInstanceList}" status="i" var="parkingSpaceInstance">
					<tr class="parking-space ${(i % 2) == 0 ? 'even' : 'odd'}" data-preferential="${parkingSpaceInstance.getPreferential()}" data-sector="${parkingSpaceInstance.getSector()}" data-id="${parkingSpaceInstance.getId()}">

						<td>
							<g:if test="${parkingSpaceInstance.owner}">
								<g:fieldValue bean="${parkingSpaceInstance}" field="owner.firstName" />
							</g:if>
							<g:else>
								<g:link action="book" params="[parkingSpaceId: parkingSpaceInstance.getId()]">Reservar</g:link>
							</g:else>
							
						</td>
						
						<td>${fieldValue(bean: parkingSpaceInstance, field: "description")}</td>
					
						<td>${fieldValue(bean: parkingSpaceInstance, field: "sector")}</td>
					
						<td><g:formatBoolean boolean="${parkingSpaceInstance.available}" /></td>
					
						<td><g:formatBoolean boolean="${parkingSpaceInstance.preferential}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${parkingSpaceInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>

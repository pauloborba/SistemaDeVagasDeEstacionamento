
<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'parkingSpace.label', default: 'ParkingSpace')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script type="text/javascript">
			function filter() {
				var sector = $("input[name='sector']").prop('checked')
				var preferential = $("input[name='preferential']").prop('checked')

				$(location).attr("href", "${createLink(action: 'suggestion')}" + "?sector=" + sector + "&preferential=" + preferential)
			}
		</script>
	</head>
	<body>
		<a href="#list-parkingSpace" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>

		<br>Somente vagas preferenciais: <g:checkBox name="preferential" checked="${params.preferential}" />
	    <br>Somente do meu setor de preferência: <g:checkBox name="sector" checked="${params.sector}" />
		<br><a href="#" onclick="filter()">Filtrar</a>

		<div id="list-parkingSpace" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="parkingSpace.owner.label" default="Owner" /></th>
					
						<g:sortableColumn property="description" title="${message(code: 'parkingSpace.description.label', default: 'Description')}" />
					
						<g:sortableColumn property="sector" title="${message(code: 'parkingSpace.sector.label', default: 'Sector')}" />
					
						<g:sortableColumn property="available" title="${message(code: 'parkingSpace.available.label', default: 'Available')}" />
					
						<g:sortableColumn property="preferential" title="${message(code: 'parkingSpace.preferential.label', default: 'Preferential')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${parkingSpaceInstanceList}" status="i" var="parkingSpaceInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}" data-id="${parkingSpaceInstance.id}">
					
						<td><g:link action="show" id="${parkingSpaceInstance.id}">${fieldValue(bean: parkingSpaceInstance, field: "owner")}</g:link></td>
					
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

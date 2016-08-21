
<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'parkingSpace.label', default: 'ParkingSpace')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-parkingSpace" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-parkingSpace" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list parkingSpace">
			
				<g:if test="${parkingSpaceInstance?.owner}">
				<li class="fieldcontain">
					<span id="owner-label" class="property-label"><g:message code="parkingSpace.owner.label" default="Owner" /></span>
					
						<span class="property-value" aria-labelledby="owner-label"><g:link controller="user" action="show" id="${parkingSpaceInstance?.owner?.id}">${parkingSpaceInstance?.owner?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${parkingSpaceInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="parkingSpace.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${parkingSpaceInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${parkingSpaceInstance?.sector}">
				<li class="fieldcontain">
					<span id="sector-label" class="property-label"><g:message code="parkingSpace.sector.label" default="Sector" /></span>
					
						<span class="property-value" aria-labelledby="sector-label"><g:fieldValue bean="${parkingSpaceInstance}" field="sector"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${parkingSpaceInstance?.preferential}">
				<li class="fieldcontain">
					<span id="preferential-label" class="property-label"><g:message code="parkingSpace.preferential.label" default="Preferential" /></span>
					
						<span class="property-value" aria-labelledby="preferential-label"><g:formatBoolean boolean="${parkingSpaceInstance?.preferential}" /></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:parkingSpaceInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${parkingSpaceInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

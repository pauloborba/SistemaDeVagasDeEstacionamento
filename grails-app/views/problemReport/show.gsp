
<%@ page import="sistemadevagasdeestacionamento.ProblemReport" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'problemReport.label', default: 'ProblemReport')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-problemReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-problemReport" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list problemReport">
			
				<g:if test="${problemReportInstance?.user}">
				<li class="fieldcontain">
					<span id="user-label" class="property-label"><g:message code="problemReport.user.label" default="User" /></span>
					
						<span class="property-value" aria-labelledby="user-label"><g:link controller="user" action="show" id="${problemReportInstance?.user?.id}">${problemReportInstance?.user?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${problemReportInstance?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="problemReport.title.label" default="Title" /></span>
					
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${problemReportInstance}" field="title"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${problemReportInstance?.sector}">
				<li class="fieldcontain">
					<span id="sector-label" class="property-label"><g:message code="problemReport.sector.label" default="Sector" /></span>
					
						<span class="property-value" aria-labelledby="sector-label"><g:fieldValue bean="${problemReportInstance}" field="sector"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${problemReportInstance?.description}">
				<li class="fieldcontain">
					<span id="description-label" class="property-label"><g:message code="problemReport.description.label" default="Description" /></span>
					
						<span class="property-value" aria-labelledby="description-label"><g:fieldValue bean="${problemReportInstance}" field="description"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:problemReportInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${problemReportInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

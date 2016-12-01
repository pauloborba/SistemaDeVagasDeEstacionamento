
<%@ page import="sistemadevagasdeestacionamento.Book" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'book.label', default: 'Book')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-book" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-book" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list book">
			
				<g:if test="${bookInstance?.parkingSpace}">
				<li class="fieldcontain">
					<span id="parkingSpace-label" class="property-label"><g:message code="book.parkingSpace.label" default="Parking Space" /></span>
					
						<span class="property-value" aria-labelledby="parkingSpace-label"><g:link controller="parkingSpace" action="show" id="${bookInstance?.parkingSpace?.id}">${bookInstance?.parkingSpace?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.inHour}">
				<li class="fieldcontain">
					<span id="inHour-label" class="property-label"><g:message code="book.inHour.label" default="In Hour" /></span>
					
						<span class="property-value" aria-labelledby="inHour-label"><g:fieldValue bean="${bookInstance}" field="inHour"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${bookInstance?.outHour}">
				<li class="fieldcontain">
					<span id="outHour-label" class="property-label"><g:message code="book.outHour.label" default="Out Hour" /></span>
					
						<span class="property-value" aria-labelledby="outHour-label"><g:fieldValue bean="${bookInstance}" field="outHour"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:bookInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${bookInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

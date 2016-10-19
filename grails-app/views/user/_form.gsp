<%@ page import="sistemadevagasdeestacionamento.User" %>



<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="user.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${userInstance?.username}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'firstName', 'error')} required">
	<label for="firstName">
		<g:message code="user.firstName.label" default="First Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="firstName" required="" value="${userInstance?.firstName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'lastName', 'error')} required">
	<label for="lastName">
		<g:message code="user.lastName.label" default="Last Name" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="lastName" required="" value="${userInstance?.lastName}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'preferredSector', 'error')} required">
	<label for="preferredSector">
		<g:message code="user.preferredSector.label" default="Preferred Sector" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="preferredSector" from="${userInstance.constraints.preferredSector.inList}" required="" value="${userInstance?.preferredSector}" valueMessagePrefix="user.preferredSector"/>

</div>

<div class="fieldcontain ${hasErrors(bean: userInstance, field: 'preferential', 'error')} ">
	<label for="preferential">
		<g:message code="user.preferential.label" default="Preferential" />
		
	</label>
	<g:checkBox name="preferential" value="${userInstance?.preferential}" />

</div>


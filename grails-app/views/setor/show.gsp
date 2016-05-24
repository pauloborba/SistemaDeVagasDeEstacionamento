

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'setor.label', default: 'Setor')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-setor" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-setor" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list setor">
			
				<g:if test="${setorInstance?.estacionamento}">
				<li class="fieldcontain">
					<span id="estacionamento-label" class="property-label"><g:message code="setor.estacionamento.label" default="Estacionamento" /></span>
					
						<span class="property-value" aria-labelledby="estacionamento-label"><g:link controller="estacionamento" action="show" id="${setorInstance?.estacionamento?.id}">${setorInstance?.estacionamento?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${setorInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="setor.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${setorInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${setorInstance?.vagas}">
				<li class="fieldcontain">
					<span id="vagas-label" class="property-label"><g:message code="setor.vagas.label" default="Vagas" /></span>
					
						<g:each in="${setorInstance.vagas}" var="v">
						<span class="property-value" aria-labelledby="vagas-label"><g:link controller="vaga" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:setorInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${setorInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>

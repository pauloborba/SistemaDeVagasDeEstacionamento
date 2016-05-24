

<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'estacionamento.label', default: 'Estacionamento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-estacionamento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-estacionamento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="codigo" title="${message(code: 'estacionamento.codigo.label', default: 'Codigo')}" />
					
						<g:sortableColumn property="nome" title="${message(code: 'estacionamento.nome.label', default: 'Nome')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${estacionamentoInstanceList}" status="i" var="estacionamentoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${estacionamentoInstance.id}">${fieldValue(bean: estacionamentoInstance, field: "codigo")}</g:link></td>
					
						<td>${fieldValue(bean: estacionamentoInstance, field: "nome")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${estacionamentoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>



<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'vaga.label', default: 'Vaga')}" />
    <title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-vaga" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
    </ul>
</div>
<div id="list-vaga" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]" /></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <tr>

            <g:sortableColumn property="descricao" title="${message(code: 'vaga.descricao.label', default: 'Descricao')}" />

            <th><g:message code="vaga.usuario.label" default="Usuario" /></th>

            <g:sortableColumn property="setor" title="${message(code: 'vaga.setor.label', default: 'Setor')}" />

            <g:sortableColumn property="ocupada" title="${message(code: 'vaga.ocupada.label', default: 'Ocupada')}" />

            <g:sortableColumn property="preferencial" title="${message(code: 'vaga.preferencial.label', default: 'Preferencial')}" />

        </tr>
        </thead>
        <tbody>
        <g:each in="${vagaInstanceList}" status="i" var="vagaInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">

                <td><g:link action="show" id="${vagaInstance.id}">${fieldValue(bean: vagaInstance, field: "descricao")}</g:link></td>

                <td>${fieldValue(bean: vagaInstance, field: "usuario")}</td>

                <td>${fieldValue(bean: vagaInstance, field: "setor")}</td>

                <td><g:formatBoolean boolean="${vagaInstance.ocupada}" /></td>

                <td><g:formatBoolean boolean="${vagaInstance.preferencial}" /></td>

            </tr>
        </g:each>
        </tbody>
    </table>
    <div class="pagination">
        <g:paginate total="${vagaInstanceCount ?: 0}" />
    </div>
</div>
</body>
</html>
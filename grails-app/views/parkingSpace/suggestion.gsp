<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'parkingSpace.label', default: 'ParkingSpace')}"/>
        <title><g:message code="default.list.label" args="[entityName]"/></title>
        <script>
            $(document).ready(function () {
                $("a[name='filter']").click(function () {
                    var sector = $("input[name='sector']").prop('checked')
                    var preferential = $("input[name='preferential']").prop('checked')
                    $.ajax({
                        url: "${createLink(action: 'suggestion')}" + "?sector=" + sector + "&preferential=" + preferential,
                        type: 'POST',
                        success: function (data) {
                            $("h1[id='lettering']").text("Filtradas")
                            $("div[id='list-parkingSpace']").replaceWith($(data).filter("div[id='list-parkingSpace']"))
                        }
                    });
                });
            });
        </script>
    </head>

    <body>
        <a href="#list-parkingSpace" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
            </ul>
        </div>

        <br>
        Somente do meu setor de preferência: <g:checkBox name="sector" checked="${params.sector}"/>
        <br>
        Somente vagas preferenciais: <g:checkBox name="preferential" checked="${params.preferential}"/>
        <br>
        <a href="#" name="filter">Filtrar</a>

        <h1 id="lettering"><g:message code="default.list.label" args="[entityName]"/></h1>

        <div id="list-parkingSpace" class="content scaffold-list" role="main">
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                    <tr>
                        <g:sortableColumn property="description" title="${message(code: 'parkingSpace.description.label', default: 'Description')}"/>
                        <g:sortableColumn property="sector" title="${message(code: 'parkingSpace.sector.label', default: 'Sector')}"/>
                        <g:sortableColumn property="available" title="${message(code: 'parkingSpace.available.label', default: 'Available')}"/>
                        <g:sortableColumn property="preferential" title="${message(code: 'parkingSpace.preferential.label', default: 'Preferential')}"/>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${parkingSpaceInstanceList}" status="i" var="parkingSpaceInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}" data-id="${parkingSpaceInstance.id}">
                            <td><g:link action="show" id="${parkingSpaceInstance.id}">${fieldValue(bean: parkingSpaceInstance, field: "description")}</g:link></td>
                            <td>${fieldValue(bean: parkingSpaceInstance, field: "sector")}</td>
                            <td><g:formatBoolean boolean="${parkingSpaceInstance.available}"/></td>
                            <td><g:formatBoolean boolean="${parkingSpaceInstance.preferential}"/></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>

            <div class="pagination">
                <g:paginate total="${parkingSpaceInstanceCount ?: 0}"/>
            </div>
        </div>
    </body>
</html>
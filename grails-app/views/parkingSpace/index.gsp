<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'parkingSpace.label', default: 'ParkingSpace')}"/>
        <title><g:message code="default.list.label" args="[entityName]"/></title>
    </head>

    <body>
        <a href="#list-parkingSpace" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></li>
            </ul>
        </div>

        <div id="list-parkingSpace" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
                %{--#if($ParkingSpaceBooking)--}%
            <table>
                <thead>
                    <tr>
                        <th><g:message code="parkingSpace.owner.label" default="Owner"/></th>

                        <g:sortableColumn property="description" title="${message(code: 'parkingSpace.description.label', default: 'Description')}"/>
                        <g:sortableColumn property="sector" title="${message(code: 'parkingSpace.sector.label', default: 'Sector')}"/>
                        <g:sortableColumn property="preferential" title="${message(code: 'parkingSpace.preferential.label', default: 'Preferential')}"/>
                    </tr>
                </thead>
                <tbody>
                    <g:each in="${parkingSpaceInstanceList}" status="i" var="parkingSpaceInstance">
                        <tr id="${parkingSpaceInstance.id}" class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td>
                                <g:if test="${parkingSpaceInstance.owner}">
                                    <h4 id="${parkingSpaceInstance.id}"><g:fieldValue bean="${parkingSpaceInstance}" field="owner.username" /></h4>
                                </g:if>
                                <g:else>
                                    <g:link id="${parkingSpaceInstance.id}" action="book"><h4 id="${parkingSpaceInstance.id}">Reservar</h4></g:link>
                                </g:else>
                            </td>
                            <td>${fieldValue(bean: parkingSpaceInstance, field: "description")}</td>
                            <td>${fieldValue(bean: parkingSpaceInstance, field: "sector")}</td>
                            <td><g:formatBoolean boolean="${parkingSpaceInstance.preferential}"/></td>
                        </tr>
                    </g:each>
                </tbody>
            </table>
            %{--#end--}%
            <div class="pagination">
                <g:paginate total="${parkingSpaceInstanceCount ?: 0}"/>
            </div>
        </div>
    </body>
</html>

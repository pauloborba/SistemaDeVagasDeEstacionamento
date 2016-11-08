<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>
<%@ page import="sistemadevagasdeestacionamento.User" %>
<%@ page import="sistemadevagasdeestacionamento.AuthHelper" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'user.historicoReservas.label', default: 'User.historicoReservas')}" />
        <title><g:message code="default.history.label" args="[entityName]" /></title>
    </head>

    <body>
        <g:set var="currUser" value="${message(default: 'User')}"/>

        <a href="#list-user" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/home/index')}"><g:message code="default.home.label"/></a></li>
            </ul>
        </div>
        <div id="list-user" class="content scaffold-list" role="main">
            <h1><g:message code="default.history.label" args="[currUser]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
        <table>
            <thead>
            <tr>
                <th></th>

                <g:sortableColumn property="description" title="${message(code: 'parkingSpace.description.label', default: 'Description')}"/>
                <g:sortableColumn property="sector" title="${message(code: 'parkingSpace.sector.label', default: 'Sector')}"/>
                <g:sortableColumn property="preferential" title="${message(code: 'parkingSpace.preferential.label', default: 'Preferential')}"/>
            </tr>
            </thead>
            <tbody>
            <g:each in="${User.findByUsername(AuthHelper.instance.currentUsername).historicoReservas}" status="i" var="historicoReserva">
                <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                    <td>
                        <g:if test="${historicoReserva.vaga.owner}">

                        </g:if>
                        <g:else>
                            <g:link action="book" id="${historicoReserva.vaga.id}">Reservar</g:link>
                        </g:else>
                    </td>
                    <td>${fieldValue(bean: historicoReserva.vaga, field: "description")}</td>
                    <td>${fieldValue(bean: historicoReserva.vaga, field: "sector")}</td>
                    <td><g:formatBoolean boolean="${historicoReserva.vaga.preferential}"/></td>
                </tr>
            </g:each>
            </tbody>
        </table>
    </body>
</html>
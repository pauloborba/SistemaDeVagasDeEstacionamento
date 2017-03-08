<%@ page import="sistemadevagasdeestacionamento.User" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title>Home</title>
    </head>
    <body>
        Olá, ${userInstance.firstName}
        <br>
        <br> Escolha uma das opções:
        %{--#if($ParkingSpaceBooking)--}%
        <br> <g:link controller="parkingSpace" action="index" name="spotlist"><g:message code="default.menu.list"/></g:link>
        %{--#end--}%
        <br> <g:link controller="parkingSpace" action="suggestion" name="suggestions"><g:message code="default.menu.suggestion"/></g:link>
        <br> <g:link controller="user" action="index" name="profile"><g:message code="default.menu.profile"/></g:link>
        <br> <g:link class="reminder-link" controller="user" name = "reminder" action="lembrete" id="${userInstance.id}"><g:message code="default.menu.reminder"/></g:link>
        %{--#if($ReportParkingSpaceProblem)--}%
        <br> <g:link controller="problemReport" action="create" name="report">Reportar problemas com vaga</g:link>
        %{--#end--}%
        <br> <g:link controller="problemReport" action="index" name="problemReport" >%{--${message(code: 'problemReport.label')}--}% Problemas Reportados</g:link>
        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
    </body>
</html>

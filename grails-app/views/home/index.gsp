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
        <br> <g:link controller="parkingSpace" action="index" name="spotlist">Parking spot list</g:link>
        <br>
        <br> <g:link controller="parkingSpace" action="suggestion" name="suggestions">Parking spot suggestions</g:link>
        <br>
        <br> <g:link controller="user" action="index" name="profile">My profile</g:link>
        <br>
        <br> <g:link class="reminder-link" controller="user" name = "reminder" action="lembrete" id="${userInstance.id}">Parking spot reminder</g:link>

        <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
        </g:if>
    </body>
</html>

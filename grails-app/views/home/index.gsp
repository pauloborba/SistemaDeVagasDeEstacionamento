
<%@ page import="sistemadevagasdeestacionamento.User" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>

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
	<br> <g:link controller="parkingSpace" action="index" >Parking spot list</g:link>
	<br> <g:link controller="parkingSpace" action="suggestion" >Parking spot suggestions</g:link>
	<br> <g:link controller="user" action="index" >My profile</g:link>
	<br><g:link controller="user" action="lembrete" id="${userInstance.id}">Lembrete de vaga</g:link>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
</body>
</html>
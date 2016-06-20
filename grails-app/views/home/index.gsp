
<%@ page import="sistemadevagasdeestacionamento.User" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>

<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Sistema de Gerenciamento de Vaga - Home</title>
</head>

<body>

	Olá, ${nome}
	<br>
	<br> Escolha uma das opções:
	<br> <g:link controller="parkingSpace" action="index" >Parking spot list</g:link>
	<br> <g:link controller="parkingSpace" action="suggestion" >Parking spot suggestions</g:link>
	<br> <g:link controller="user" action="index" >My profile</g:link>

</body>
</html>
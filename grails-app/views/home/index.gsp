<%@ page import="sistemadevagasdeestacionamento.User" %>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<title>Sistema de Gerenciamento de Vaga - Home</title>
</head>
	Olá, ${nome}
	<br>
	<br> Escolha uma das opções:
	<br> <g:link controller="parkingSpace" action="index" >Lista de vagas</g:link>
	<br> <g:link controller="user" action="index" >Meu perfil</g:link>
</body>
</html>
<%@ page import="org.apache.shiro.SecurityUtils" %>
<!DOCTYPE html>
<html>
<head>
    <g:if test="${SecurityUtils.subject.isAuthenticated()}">
        <meta http-equiv="refresh" content="0;URL=/SistemaDeVagasDeEstacionamento/home">
    </g:if>
    <title>Sistema de Gerenciamento de Vaga</title>
</head>
<body>
    Página inicial
</body>
</html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Sign up</title>
    <meta name="layout" content="main"/>
</head>
<body>
    <h1>Sign up</h1>

    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>

    <g:form action="register">
        <table>
            <tbody>
            <tr>
                <td>Username:</td>
                <td><input type="text" name="username"/></td>
            </tr>
            <tr>
                <td>First name:</td>
                <td><input type="text" name="firstName"/></td>
            </tr>
            <tr>
                <td>Last name:</td>
                <td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td>Preferred sector:</td>
                <td><input type="text" name="preferredSector"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type="password" name="password"/></td>
            </tr>
            <tr>
                <td>Confirm Password:</td>
                <td><input type="password" name="password2"/></td>
            </tr>
            <tr>
                <td/>
                <td><input type="submit" name="signUp" value="Sign up"/></td>
            </tr>
            </tbody>
        </table>
    </g:form>
</body>




<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'login', 'error')} required">
    <label for="login">
        <g:message code="usuario.login.label" default="Login" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="login" required="" value="${usuarioInstance?.login}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'nome', 'error')} required">
    <label for="nome">
        <g:message code="usuario.nome.label" default="Nome" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="nome" required="" value="${usuarioInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} required">
    <label for="password">
        <g:message code="usuario.password.label" default="Password" />
        <span class="required-indicator">*</span>
    </label>
    <g:textField name="password" required="" value="${usuarioInstance?.password}"/>

</div>

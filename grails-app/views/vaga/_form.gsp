<<<<<<< HEAD
<%@ page import="Vaga" %>
=======



>>>>>>> 4481a3f6481da1345e121bc0fbe2e88d11d9e9c7

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'descricao', 'error')} required">
	<label for="descricao">
		<g:message code="vaga.descricao.label" default="Descricao" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descricao" required="" value="${vagaInstance?.descricao}"/>

</div>

<<<<<<< HEAD
=======
<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'usuario', 'error')} ">
	<label for="usuario">
		<g:message code="vaga.usuario.label" default="Usuario" />
		
	</label>
	<g:select id="usuario" name="usuario.id" from="${Usuario.list()}" optionKey="id" value="${vagaInstance?.usuario?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

>>>>>>> 4481a3f6481da1345e121bc0fbe2e88d11d9e9c7
<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'setor', 'error')} required">
	<label for="setor">
		<g:message code="vaga.setor.label" default="Setor" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="setor" from="${vagaInstance.constraints.setor.inList}" required="" value="${vagaInstance?.setor}" valueMessagePrefix="vaga.setor"/>

</div>

<<<<<<< HEAD
=======
<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'ocupada', 'error')} ">
	<label for="ocupada">
		<g:message code="vaga.ocupada.label" default="Ocupada" />
		
	</label>
	<g:checkBox name="ocupada" value="${vagaInstance?.ocupada}" />

</div>

>>>>>>> 4481a3f6481da1345e121bc0fbe2e88d11d9e9c7
<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'preferencial', 'error')} ">
	<label for="preferencial">
		<g:message code="vaga.preferencial.label" default="Preferencial" />
		
	</label>
	<g:checkBox name="preferencial" value="${vagaInstance?.preferencial}" />

</div>


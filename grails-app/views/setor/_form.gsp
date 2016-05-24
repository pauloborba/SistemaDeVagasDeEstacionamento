



<div class="fieldcontain ${hasErrors(bean: setorInstance, field: 'estacionamento', 'error')} required">
	<label for="estacionamento">
		<g:message code="setor.estacionamento.label" default="Estacionamento" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estacionamento" name="estacionamento.id" from="${Estacionamento.list()}" optionKey="id" required="" value="${setorInstance?.estacionamento?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain ${hasErrors(bean: setorInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="setor.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${setorInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: setorInstance, field: 'vagas', 'error')} ">
	<label for="vagas">
		<g:message code="setor.vagas.label" default="Vagas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${setorInstance?.vagas?}" var="v">
    <li><g:link controller="vaga" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="vaga" action="create" params="['setor.id': setorInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'vaga.label', default: 'Vaga')])}</g:link>
</li>
</ul>


</div>


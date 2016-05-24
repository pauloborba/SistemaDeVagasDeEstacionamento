



<div class="fieldcontain ${hasErrors(bean: estacionamentoInstance, field: 'codigo', 'error')} required">
	<label for="codigo">
		<g:message code="estacionamento.codigo.label" default="Codigo" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="codigo" required="" value="${estacionamentoInstance?.codigo}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: estacionamentoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="estacionamento.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${estacionamentoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: estacionamentoInstance, field: 'setores', 'error')} ">
	<label for="setores">
		<g:message code="estacionamento.setores.label" default="Setores" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${estacionamentoInstance?.setores?}" var="s">
    <li><g:link controller="setor" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="setor" action="create" params="['estacionamento.id': estacionamentoInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'setor.label', default: 'Setor')])}</g:link>
</li>
</ul>


</div>


<%@ page import="sistemadevagasdeestacionamento.Book" %>


<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'inHour', 'error')} required">
	<label for="inHour">
		<g:message code="book.inHour.label" default="In Hour" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="inHour" from="${0..23}" class="range" required="" value="${fieldValue(bean: bookInstance, field: 'inHour')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'outHour', 'error')} required">
	<label for="outHour">
		<g:message code="book.outHour.label" default="Out Hour" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="outHour" from="${0..23}" class="range" required="" value="${fieldValue(bean: bookInstance, field: 'outHour')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: bookInstance, field: 'parkingSpace', 'error')} required">
    <g:select id="parkingSpace" name="parkingSpace.id" from="${sistemadevagasdeestacionamento.ParkingSpace.list()}"
              style="visibility: hidden; height: 0px" optionKey="id" required="" value="${fieldValue(bean: bookInstance, field: 'parkingSpace.id')}" class="many-to-one"/>

</div>


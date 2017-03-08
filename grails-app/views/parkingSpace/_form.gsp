<%@ page import="sistemadevagasdeestacionamento.ParkingSpace" %>

%{--#if($ParkingSpaceBooking)--}%
<div class="fieldcontain ${hasErrors(bean: parkingSpaceInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="parkingSpace.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${parkingSpaceInstance?.description}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: parkingSpaceInstance, field: 'sector', 'error')} required">
	<label for="sector">
		<g:message code="parkingSpace.sector.label" default="Sector" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sector" from="${parkingSpaceInstance.constraints.sector.inList}" required="" value="${parkingSpaceInstance?.sector}" valueMessagePrefix="parkingSpace.sector"/>

</div>

<div class="fieldcontain ${hasErrors(bean: parkingSpaceInstance, field: 'preferential', 'error')} ">
	<label for="preferential">
		<g:message code="parkingSpace.preferential.label" default="Preferential" />
		
	</label>
	<g:checkBox name="preferential" value="${parkingSpaceInstance?.preferential}" />

</div>
%{--#end--}%


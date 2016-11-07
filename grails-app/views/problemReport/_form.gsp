%{--#if($ReportParkingSpaceProblem)--}%
<%@ page import="sistemadevagasdeestacionamento.ProblemReport" %>




<div class="fieldcontain ${hasErrors(bean: problemReportInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="problemReport.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${problemReportInstance?.title}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: problemReportInstance, field: 'sector', 'error')} required">
	<label for="sector">
		<g:message code="problemReport.sector.label" default="Sector" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="sector" from="${problemReportInstance.constraints.sector.inList}" required="" value="${problemReportInstance?.sector}" valueMessagePrefix="problemReport.sector"/>

</div>

<div class="fieldcontain ${hasErrors(bean: problemReportInstance, field: 'description', 'error')} required">
	<label for="description">
		<g:message code="problemReport.description.label" default="Description" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="description" required="" value="${problemReportInstance?.description}"/>

</div>

%{--#end--}%
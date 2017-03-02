<%@ page import="sistemadevagasdeestacionamento.ProblemReport" %>
<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main">
	<g:set var="entityName" value="${message(code: 'problemReport.label', default: 'ProblemReport')}" />
	<title><g:message code="default.list.label" args="[entityName]" /></title>
</head>
<body>
<a href="#list-problemReport" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<div class="nav" role="navigation">
	<ul>
		<li>
			<a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
		</li>
	</ul>
</div>
<div id="list-problemReport" class="content scaffold-list" role="main">
	<h1><g:message code="default.list.label" args="[entityName]" /></h1>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<table>
		<thead>
		<tr>

			<th>
				<g:message code="problemReport.user.label" default="User" />
			</th>
			<g:sortableColumn property="title" title="${message(code: 'problemReport.title.label', default: 'Title')}" />
			<g:sortableColumn property="sector" title="${message(code: 'problemReport.sector.label', default: 'Sector')}" />
			<g:sortableColumn property="description" title="${message(code: 'problemReport.description.label', default: 'Description')}" />

		</tr>
		</thead>
		<tbody>
		<g:each in="${problemReportInstanceList}" status="i" var="problemReportInstance">
			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}" data-id="${problemReportInstance.title}">

				<td>
					<g:link controller="user" action="index" id="${problemReportInstance.id}">
						${fieldValue(bean: problemReportInstance, field: "user.username")}
					</g:link>
				</td>
				<td>${fieldValue(bean: problemReportInstance, field: "title")}</td>
				<td>${fieldValue(bean: problemReportInstance, field: "sector")}</td>
				<td>${fieldValue(bean: problemReportInstance, field: "description")}</td>
				<td>
					<g:form url="[resource:problemReportInstance, action:'resolve']" method="DELETE">
						<input type="submit"
							   name="${problemReportInstance.id}"
							   value="${message(code: 'problemReport.resolve.button')}"/>
					</g:form>
				</td>

			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${problemReportInstanceCount ?: 0}" />
	</div>
</div>
</body>
</html>
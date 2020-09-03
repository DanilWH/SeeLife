<#import "parts/base.ftlh" as base />

<@base.content title="Notes | See Life}">

	<h3>${day.getFormattedLocalDate()}: ${day.title}</h3>
	
	<#if isRelevant == true>
		<a href="">Edit title &#8595</a>
		<form action="/edit_day/id/${day.id}" method="post">
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
			<input type="text" name="dayTitle" placeholder="Change the day title" required />
			<button name="submit">Change</button>
			<div>${message?ifExists}</div>
		</form>
	</#if>

	<ul>
		<#list notes as note>
			<li>
				<p>
					<#assign lines = note.text?split('\n') />
					<#list lines as line>
						${line}<#sep><br />
					</#list>
					<hr />
					<div align="right">at ${note.getFormattedLocalTime()}</div>
					<#if isRelevant == true>
						<div><a href="/edit_note/id/${note.id}">Edit note</a></div>
					</#if>
				</p>
			</li>
		</#list>
	</ul>

</@base.content>
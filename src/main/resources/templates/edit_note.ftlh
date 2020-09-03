<#import "parts/base.ftlh" as base />

<@base.content title="Edit note | See Life">

	<form action="/edit_note/id/${note.id}" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div><textarea name="text" required>${note.text}</textarea></div>
		<div>${message?ifExists}</div>
		<div><button name="submit">Save changes</button></div>
	</form>

</@base.content>
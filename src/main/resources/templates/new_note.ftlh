<#import "parts/base.ftlh" as base />

<@base.content title="New Note | See Life">

	<form action="/new_note" method="post" enctype="multipart/form-data">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div><textarea name="text" placeholder="What's going on now?"></textarea></div>
		<div>${message?ifExists}</div>
		<div><input type="file" name="files" multiple /></div>
		<div><button name="submit">Add note</button></div>
	</form>

</@base.content> 
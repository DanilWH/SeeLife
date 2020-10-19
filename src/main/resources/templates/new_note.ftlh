<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="New Note | See Life">

	<@common.header>New note</@common.header>
	
	<form action="/new_note" method="post" enctype="multipart/form-data">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div class="form-group">
			<@common.textarea message="${message?ifExists}" placeholder="What's going on now?"></@common.textarea>
		</div>
		
		<div class="form-group">
			<@common.file_browser />
		</div>
		
		<button class="btn btn-info" name="submit">Add note</button>
		
	</form>

</@base.content> 
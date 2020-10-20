<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="${user.username} deletion | Admin">

	<@common.header>Are you sure?</@common.header>
	<p>
		Are you sure you want to delete the user "${user.username}"?
		All related items will be deleted.
	</p>
	<form action="/admin/user/${user.id}/delete" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		
		<button class="btn btn-danger mr-2" name="submit">Yes, I'm sure</button>
		<!-- turn the link into a bootstrap button -->
		<a  class="btn btn-light" href="/admin/edit_user/${user.id}">No, take me back</a>
	</form>
	

</@base.content>
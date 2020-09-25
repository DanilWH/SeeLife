<#import "../parts/base.ftlh" as base />

<@base.content title="${user.username} deletion | Admin">

	<h1>Are you sure?</h1>
	<p>
		Are you sure you want to delete the user "${user.username}"?
		All related items will be deleted.
	</p>
	<form action="/admin/user/${user.id}/delete" method="post">
		<button name="submit">Yes, I'm sure</button>
		<!-- turn the link into a bootstrap button -->
		<a href="/admin/edit_user/${user.id}">No, take me back</a>
	</form>
	

</@base.content>
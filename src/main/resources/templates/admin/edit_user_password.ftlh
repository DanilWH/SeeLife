<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="Change ${username}'s password | Admin | See Life">

	<h3>Change password: ${username}</h3>
	<p>Enter a new password for the user <strong>${username}</strong>.</p>
	
	<form action="/admin/edit_user/${userId}/password" method="POST">
		<fieldset>
			<div>
				<!-- password form -->
				<@common.password_form message="${password_msg?ifExists}" />
			</div>
		</fieldset>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div>
			<button name="submit">CHANGE PASSWORD</button>
		</div>
	</form>

</@base.content>
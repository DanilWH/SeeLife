<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="Change ${username}'s password | Admin | See Life">

	<@common.header>Change password: ${username}</@common.header>
	
	<p>Enter a new password for the user <strong>${username}</strong>.</p>
	
	<form action="/admin/edit_user/${userId}/password" method="POST">
		<fieldset>
			<div>
				<!-- password form -->
				<@common.password_form message="${password_msg?ifExists}" />
			</div>
		</fieldset>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		
		<div class="submit-row">
			<p class="submit-box">
				<button class="btn btn-info" name="submit">change password</button>
			</p>
		</div>
	</form>

</@base.content>
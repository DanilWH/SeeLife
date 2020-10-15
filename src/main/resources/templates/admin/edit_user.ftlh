<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="Edit User | Admin | See Life">
	
	<form action="/admin/edit_user/${user.id}" method="post">
		<fieldset>
			<!-- user id -->
			<div>User ID: ${user.id}</div>
			
			<!-- username -->
			<@common.username_form message="${username_msg?if_exists}" username="${user.username}" />
			
			<!-- password -->
			<div>
				<label for="password_id">Password:</label>
				<small>
					Raw passwords are not stored, so there is no way to see this user's password,
					but you can change the password using
					<a href="/admin/edit_user/${user.id}/password">this form</a>.
				</small>
			</div>
		</fieldset>
		
		<!-- permissions -->
		<fieldset>
			<legend>Permissions</legend>
			<#list role_values as role_value>
				<div>
					<input type="checkbox" name="chosen_roles" value="${role_value}" id="${role_value}"
						${user.roles?seq_contains(role_value)?string("checked", "")} />
					<label for="${role_value}">${role_value}</label>
				</div>
			</#list>
		</fieldset>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		
		<!-- turn the link into a bootstrap button later TODO!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!-->
		<a href="/admin/user/${user.id}/delete">Delete</a>
		<button name="submit">Save</button>
	</form>

</@base.content>
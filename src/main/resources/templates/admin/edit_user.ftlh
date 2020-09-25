<#import "../parts/base.ftlh" as base />

<@base.content title="Edit User | Admin | See Life">
	
	<form action="/admin/edit_user/${user.id}" method="post">
		<fieldset>
			<div>User ID: ${user.id}</div>
			<div>
				<label for="username_id">Username:</label>
				<input type="text" name="username" value="${user.username}" id="username_id" />
			</div>
			<div>
				<label for="password_id">Password:</label>
				<input type="text" name="password" value="${user.password}" id="password_id" />
			</div>
		</fieldset>
		<fieldset>
			<h5>Permissions</h5>
			<#list role_values as role_value>
				<div>
					<input type="checkbox" name="chosen_roles" value="${role_value}" id="${role_value}"
						${user.roles?seq_contains(role_value)?string("checked", "")} />
					<label for="${role_value}">${role_value}</label>
				</div>
			</#list>
		</fieldset>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		
		<!-- turn the link into a bootstrap button -->
		<a href="/admin/user/${user.id}/delete">Delete</a>
		<button name="submit">Save</button>
	</form>

</@base.content>
<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="Change User | Admin | See Life">
	
	<@common.header>Change user</@common.header>
	
	<form action="/admin/edit_user/${user.id}" method="post">
		<fieldset class="form-group">
			<div class="form-group">
				<!-- user id -->
				<label for="user_id_id">User ID</label>
				<input id="user_id_id" class="form-control" type="text" value="${user.id}" readonly />
			</div>
			
			<div class="form-group">
				<!-- username -->
				<@common.username_form message="${username_msg?if_exists}" username="${user.username}" />
			</div>
			
			<!-- password -->
			<div class="form-group">
				<label for="password_id">Password</label>
				<input id="password_id" class="form-control" type="password" value="${user.password}" aria-describedby="passwordMsg" readonly />
				<small id="passwordMsg" class="form-text text-muted">
					Raw passwords are not stored, so there is no way to see this user's password,
					but you can change the password using
					<a href="/admin/edit_user/${user.id}/password">this form</a>.
				</small>
			</div>
		</fieldset>
		
		<!-- permissions -->
		<fieldset class="form-group">
			<#list role_values as role_value>
				<div class="form-check">
					<input class="form-check-input" type="checkbox" name="chosen_roles" value="${role_value}" id="${role_value}"
						${user.roles?seq_contains(role_value)?string("checked", "")} />
					<label class="form-check-label" for="${role_value}">${role_value}</label>
					
					<!--
						"Implement the description of each role later." TODO

						<div><small class="form-text text-muted">
							Designates that this user has all permissions without explicitly assigning them.
						</small></div>
					 -->
				</div>
				<hr />
			</#list>
		</fieldset>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		
		<div class="submit-row">
			<p class="submit-box">
				<button class="btn btn-info" name="submit">Save</button>
			</p>
			<p class="deletelink-box">
				<a class="btn btn-danger" href="/admin/user/${user.id}/delete">Delete</a>
			</p>
		</div>
	</form>

</@base.content>
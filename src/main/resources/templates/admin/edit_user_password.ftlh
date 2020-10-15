<#import "../parts/base.ftlh" as base />

<@base.content title="Change ${username}'s password | Admin | See Life">

	<h3>Change password: ${username}</h3>
	<p>Enter a new password for the user <strong>${username}</strong>.</p>
	
	<form action="/admin/edit_user/${userId}/password" method="POST">
		<fieldset>
			<div>
				<div>
					<label for="password_id">Password: </label>
					<input id="password_id" type="password" name="password" required />
				</div>
				<div>
					<small>
						<ul>
							<li>Your password can't be too similar to your other personal information.</li>
							<li>Your password must contain at least 8 characters.</li>
							<li>Your password can't be a commonly used password.</li>
							<li>Your password can't be entirely numeric.</li>
						</ul>
					</small>
				</div>
				<div>
					<label id="password_confirm_id">Password (again): </label>
					<input id="password_confirm_id" type="password" name="password_confirm" required />
				</div>
				<div><small>${password_msg?ifExists}</small></div>
			</div>
		</fieldset>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div>
			<button name="submit">CHANGE PASSWORD</button>
		</div>
	</form>

</@base.content>
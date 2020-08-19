<#import "parts/base.ftlh" as base />

<@base.content title="Registration | See Life">

	<h3>Registration</h3>
	
	<form action="/registration" method="post">
		<div><label>Username: <input type="text" name="username" required /></label></div>
		<div><small>${username_msg?ifExists}</small></div>
		<div><small>Required 150 characters or fewer. Letters, digits and @/./+/-/_ only.</small></div>
		
		<div><label>Password: <input type="password" name="password" required /></label></div>
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
			<label>Password Confirmation: <input type="password" name="password_confirm" required /></label>
		</div>
		<div><small>${password_msg?ifExists}</small></div>
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<button name="submit">Create account</button>
	</form>

</@base.content>
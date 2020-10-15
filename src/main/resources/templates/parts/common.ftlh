<#macro username_form message username="">

	<div>
		<label for="username_id">Username: </label>
		<input id="username_id" type="text" name="username" value="${username}" required />
	</div>
	<div><small>${message?ifExists}</small></div>
	<div><small>Required 150 characters or fewer. Letters, digits and @/./+/-/_ only.</small></div>

</#macro>

<#macro password_form message>

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
	<div><small>${message?ifExists}</small></div>

</#macro>
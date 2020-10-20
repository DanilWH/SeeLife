<#macro username_form message="" username="">

	<div class="form-group">
		<label for="username_id">Username</label>
		<input id="username_id" class="form-control ${(message?length != 0)?string("is-invalid", "")}" type="text" name="username" value="${username}" aria-describedby="usernameHelp" placeholder="Enter username" required />
		<#if (message?length != 0)><div class="invalid-feedback">${message}</div></#if>
		<small id="usernameHelp" class="form-text text-muted">Required 150 characters or fewer. Letters, digits and @/./+/-/_ only.</small>
	</div>

</#macro>

<#macro password_form message="">

	<div class="form-group">
		<label for="password_id">Password</label>
		<input id="password_id" type="password" name="password" class="form-control ${(message?length != 0)?string("is-invalid", "")}" aria-describedby="passwordHelp" required />
		
		<small id="passwordHelp" class="form-text text-muted">
			<ul>
				<li>Your password can't be too similar to your other personal information.</li>
				<li>Your password must contain at least 8 characters.</li>
				<li>Your password can't be a commonly used password.</li>
				<li>Your password can't be entirely numeric.</li>
			</ul>
		</small>
	</div>
	
	<div class="form-group">
		<label id="password_confirm_id">Password (again): </label>
		<input id="password_confirm_id" type="password" name="password_confirm" class="form-control ${(message?length != 0)?string("is-invalid", "")}" aria-describedby="passwordHelp" required />
		
		<#if (message?length != 0)><div class="invalid-feedback">${message}</div></#if>
	</div>

</#macro>

<#macro header>

	<h1 class="display-4"><#nested /></h1>
	<hr />
	
</#macro>

<#macro send_form_btn>

	<button class="btn btn-info my-2 my-sm-0" type="submit"><#nested /></button>

</#macro>

<#macro file_browser name="files" multiple="true">

	<div class="custom-file">
		<input type="file" name="${name}" ${multiple?boolean?string("multiple", "")} class="custom-file-input" id="customFile" />
		<label class="custom-file-label" for="customFile">Choose files</label>
	</div>

</#macro>

<#macro textarea message name="text" rows="5" required="true" placeholder="">

	<textarea
		name="${name}"
		rows="${rows}"
		${required?boolean?string("required", "")}
		class="form-control ${(message?length != 0)?string("is-invalid", "")}"
		placeholder="${placeholder}"
	><#nested /></textarea>
	
	<#if (message?length != 0)><div class="invalid-feedback">${message}</div></#if>

</#macro>
<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="Registration | See Life">

	<@common.header>Registration</@common.header>
	
	<form action="/registration" method="post">
		<!-- username -->
		<div class="form-group">
			<label for="username_id">Username</label>
			<input id="username_id"
				   type="text" name="username" value="${username?ifExists}"
				   aria-describedby="usernameHelp" placeholder="Enter username" required
				   class="form-control ${(usernameError??)?string("is-invalid", "")}" />

			<#if usernameError??>
				<div class="invalid-feedback">
					${usernameError}
				</div>
			</#if>

			<small id="usernameHelp" class="form-text text-muted">Required 20 characters or fewer. Letters, digits and @/./+/-/_ only.</small>
		</div>

		<!-- password -->
		<div class="form-group">
			<label for="password_id">Password</label>
			<input id="password_id" type="password" name="password"
				   aria-describedby="passwordHelp" required
				   class="form-control ${(passwordError??)?string("is-invalid", "")}" />

			<#if passwordError??>
				<div class="invalid-feedback">
					${passwordError?noEsc}
				</div>
			</#if>

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
			<label for="password_confirm_id">Password (again): </label>
			<input id="password_confirm_id" type="password" name="password_confirm"
				   aria-describedby="passwordHelp" required
				   class="form-control ${(password_confirmError??)?string("is-invalid", "")}" />

			<#if password_confirmError??>
				<div class="invalid-feedback">
					${password_confirmError}
				</div>
			</#if>
		</div>

		<!-- reCAPTCHA -->
		<div class="form-group">
		    <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>
		    </#if>

            <div class="g-recaptcha" data-sitekey="6Lc7HWkaAAAAAK7DwZD7yRmycdY_ZOPohkymQZlp"></div>
		</div>

		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<@common.send_form_btn>Create account</@common.send_form_btn>
	</form>

</@base.content>
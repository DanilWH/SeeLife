<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="Registration | See Life">

	<@common.header>Registration</@common.header>
	
	<form action="/registration" method="post">
		<!-- username -->
		<@common.username_form message="${username_msg?ifExists}" />
		
		<!-- password -->
		<@common.password_form message="${password_msg?ifExists}" />

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
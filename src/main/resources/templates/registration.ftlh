<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="Registration | See Life">

	<h3>Registration</h3>
	
	<form action="/registration" method="post">
		<!-- username -->
		<@common.username_form message="${username_msg?ifExists}" />
		
		<!-- password -->
		<@common.password_form message="${password_msg?ifExists}" />
		
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<button name="submit">Create account</button>
	</form>

</@base.content>
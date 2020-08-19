<#import "parts/base.ftlh" as base />

<@base.content title="Log In | See Life">

	<form action="/login" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
	    <div><label>User Name: <input type="text" name="username" required /> </label></div>
	    <div><label>Password: <input type="password" name="password" required /> </label></div>
	    <div><input type="submit" value="Sign In"/></div>
	</form>

</@base.content>
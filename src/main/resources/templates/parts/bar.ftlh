<#include "user_security.ftlh" />

<a href="/">SeeLife</a> -
<a href="/">Home</a> -
<#if user??>
	Hello, ${user.getUsername()}.
	<form action="/logout" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<button name="submit">Log Out</button>
	</form>
<#else>
	<a href="/login">Sign In</a> -
	<a href="/registration">Sign Up</a>
</#if>
<hr />
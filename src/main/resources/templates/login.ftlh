<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="Log In | See Life">

	<@common.header>Signing In</@common.header>

	<form action="/login" method="post">
		<div class="form-group">
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
			
		    <label for="username_id">User Name</label>
		    <input id="username_id" class="form-control" type="text" name="username" placeholder="User Name" required />
		</div>
		
		<div class="form-group">
		    <label for="password_id">Password</label>
		    <input id="password_id" class="form-control" type="password" name="password" placeholder="Password" required />
	    </div>
	    
	    <@common.send_form_btn>Sign In</@common.send_form_btn>
	</form>

</@base.content>
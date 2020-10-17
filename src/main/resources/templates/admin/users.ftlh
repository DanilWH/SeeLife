<#import "../parts/base.ftlh" as base />

<@base.content title="Admin Privileges | See Life">

	<h3>Select user to change</h3>
	
	<form action="/admin/users" method="get">
		<input type="search" name="user_filter" placeholder="Search users" />
		<button>Search</button>
		<#if result??>
			<div><small>${result} results (<a href="?">${total} total</a>)</small></div>
		</#if>
	</form>
	
	<#if users?size != 0>
		<table>
			<tr>
				<th>User ID</th>
				<th>Username</th>
				<th>User role</th>
				<th></th>
			</tr>
			<#list users as user>
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td><#list user.roles as role>${role}<#sep>,</#list></td>
				<td><a href="/admin/edit_user/${user.id}">Edit</a></td>
			</tr>
			</#list>
		</table>
	<#else>
		<p>...There's no users found...</p>
	</#if>

</@base.content>
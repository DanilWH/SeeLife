<#import "../parts/base.ftlh" as base />

<@base.content title="Admin Privileges | See Life">

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

</@base.content>
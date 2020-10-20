<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="Admin Privileges | See Life">

	<@common.header>Select user to change</@common.header>
	
	<form action="/admin/users" method="get" class="form-inline">
		<div class="form-group">
			<input class="form-control mr-2" type="search" name="user_filter" placeholder="Search users" aria-describedby="resultDescribedby" />
			<button class="btn btn-success">Search</button>
		</div>
	</form>
	
	<!-- results viewer -->
	<#if result??>
		<small id="resultDescribedby" class="text-muted ml-2">${result} results (<a href="?">${total} total</a>)</small>
	</#if>
	
	<#if users?size != 0>
		
		<table class="table mt-3">
			<thead class="thead-dark">
				<tr>
					<th scope="col">#</th>
					<th scope="col">First</th>
					<th scope="col">Last</th>
					<th scope="col">Handle</th>
				</tr>
			</thead>
			<tbody>
				<#list users as user>
					<tr>
						<th scope="row">${user.id}</th>
						<td>${user.username}</td>
						<td><#list user.roles as role>${role}<#sep>, </#list></td>
						<td><a href="/admin/edit_user/${user.id}">Change</a></td>
					</tr>
				</#list>
			</tbody>
		</table>
			<#else>
		<h1>...This is impossible to see this page if there is no user...</h1>

	</#if>

</@base.content>
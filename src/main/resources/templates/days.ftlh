<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="SeeLife">

	<@common.header>See Life</@common.header>
	
	<#if startDate??>
		<div class="alert alert-secondary" role="alert">
		  	${startDate} - ${endDate}
		</div>
	</#if>
	
	<#list days as day>
		<div class="card m-5">
			<div class="card-header navbar">
				<ul class="navbar-nav mr-auto">
				   	<li>${day.getFormattedLocalDate()}</li>
				</ul>
				<ul class="navbar-nav">
				   	<li><span class="badge badge-pill badge-info">${day.notesNumber} notes</span></li>
				</ul>
			</div>
			<div class="card-body">
			    <h5 class="card-title">${day.title}</h5>
			    <a href="/day/id/${day.id}" class="btn btn-info">See the day</a>
			</div>
		</div>
	<#else>
		<div><p>...There're no days found...</p></div>
	</#list>

</@base.content>
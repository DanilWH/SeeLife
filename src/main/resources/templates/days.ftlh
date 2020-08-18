<#import "parts/base.ftlh" as base />

<@base.content title="SeeLife">

	<h1>SeeLife</h1>
	<#list days as day>
		<div>${day.title}</div>
	</#list>

</@base.content>
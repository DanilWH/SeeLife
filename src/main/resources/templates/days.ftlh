<#import "parts/base.ftlh" as base />

<@base.content title="SeeLife">

	<h1>SeeLife</h1>
	<ul>
		<#list days as day>
			<li><a href="/day/id/${day.id}">${day.getFormattedLocalDate()}: ${day.title}</a></li>
		<#else>
			<div><p>...There're no days you write something...</p></div>
		</#list>
	</ul>

</@base.content>
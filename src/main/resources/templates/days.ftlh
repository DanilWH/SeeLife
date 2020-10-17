<#import "parts/base.ftlh" as base />

<@base.content title="SeeLife">

	<h1>SeeLife</h1>
	
	<div>
		<form method="get">
			<input type="date" name="startDate" /> -
			<input type="date" name="endDate" />
			<button>Search</button>
			<#if startDate??>
				<div><small>${startDate} - ${endDate}</small></div>
			</#if>
		</form>
	</div>
	<ul>
		<#list days as day>
			<li>
				<a href="/day/id/${day.id}">${day.getFormattedLocalDate()}: ${day.title}</a>
				<i>${day.notesNumber} notes</i>
			</li>
		<#else>
			<div><p>...There're no days found...</p></div>
		</#list>
	</ul>

</@base.content>
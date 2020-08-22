<#import "parts/base.ftlh" as base />

<@base.content title="Notes | See Life}">

	<ul>
		<#list notes as note>
			<li>
				<p>
					<#assign lines = note.text?split('\n') />
					<#list lines as line>
						${line}<#sep><br />
					</#list>
					<hr />
					<div align="right">at ${note.getFormattedLocalTime()}</div>
				</p>
			</li>
		</#list>
	</ul>

</@base.content>
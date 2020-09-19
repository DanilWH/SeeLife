<#import "parts/base.ftlh" as base />

<@base.content title="Notes | See Life">

	<h3>${day.getFormattedLocalDate()}: ${day.title}</h3>
	
	<#if isRelevant == true>
		<a href="">Edit title &#8595</a>
		<form action="/edit_day/id/${day.id}" method="post">
			<input type="hidden" name="_csrf" value="${_csrf.token}" />
			<input type="text" name="dayTitle" placeholder="Change the day title" required />
			<button name="submit">Change</button>
			<div>${message?ifExists}</div>
		</form>
	</#if>

	<ul>
		<#list notes as note>
			<li>
				<div>
					<p>
						<#assign lines = note.text?split('\n') />
						<#list lines as line>
							${line}<#sep><br />
						</#list>
					</p>
					
					<!-- show images -->
					<#if note.images?size != 0>
						<div>
							<#list note.images as image>
								<img src="/image/${image}" />
							</#list>
						</div>
					</#if>
				
					<!-- show videos -->
					<#if note.videos?size != 0>
						<div>
							<#list note.videos as video>
								<video src="/video/${video}" preload="metadata" controls></video>
							</#list>
						</div>
					</#if>
					
					<!-- show audio -->
					<#if note.audios?size != 0>
						<div>
							<#list note.audios as audio>
								<audio src="/audio/${audio}" preload="metadata" controls></audio>
							</#list>
						</div>
					</#if>
					
					<!-- show documents -->
					<#if note.documents?size != 0>
						<div>
							<#list note.documents as document>
								<#assign filename = document?split('.') />
								<div>
									<a href="/document/${document}">
										${filename[1]}<#if filename[2]??>.${filename[2]}</#if>
									</a>
								</div>
							</#list>
						</div>
					</#if>
					
					<hr />
					<div align="right">at ${note.getFormattedLocalTime()}</div>
					<#if isRelevant == true>
						<div><a href="/edit_note/id/${note.id}">Edit note</a></div>
					</#if>
				</div>
			</li>
		</#list>
		<#if isRelevant == false>
			<div align="middle"><h2>...ZZZ...</h2></div>
		</#if>
	</ul>

</@base.content>
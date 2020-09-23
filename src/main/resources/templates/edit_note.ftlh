<#import "parts/base.ftlh" as base />

<@base.content title="Edit note | See Life">

	<form action="/edit_note/id/${note.id}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		<div><textarea name="text" required>${note.text}</textarea></div>
		<div>${message?ifExists}</div>
		
		<div>
			Choose file to delete if you want any:<br />
			<!-- show images -->
			<#if note.images?size != 0>
				<ul>
					<#list note.images as img_name>
						<li>
							<img src="/image/${img_name}" style="max-width: 10%;" align="top"/>
							<div>
								<label>
									<input type="checkbox" name="deletingFiles" value="/image/${img_name}" />
									<#assign filename = img_name?split('.') />
									${filename[1]}.${filename[2]}
								</label>
							</div>
						</li>
					</#list>
				</ul>
			</#if>
		
			<!-- show videos -->
			<#if note.videos?size != 0>
				<ul>
					<#list note.videos as video_name>
						<li>
							<video src="/video/${video_name}" preload="metadata" controls></video>
							<div>
								<label>
									<input type="checkbox" name="deletingFiles" value="/video/${video_name}" />
									<#assign filename = video_name?split('.') />
									${filename[1]}.${filename[2]}
								</label>
							</div>
						</li>
					</#list>
				</ul>
			</#if>
			
			<!-- show audio -->
			<#if note.audios?size != 0>
				<ul>
					<#list note.audios as audio_name>
						<li>
							<audio src="/audio/${audio_name}" preload="metadata" controls>
								
							</audio>
							<div>
								<label>
									<input type="checkbox" name="deletingFiles" value="/audio/${audio_name}" />
									<#assign filename = audio_name?split('.') />
									${filename[1]}.${filename[2]}
								</label>
							</div>
						</li>
					</#list>
				</ul>
			</#if>
			
			<!-- show documents -->
			<#if note.documents?size != 0>
				<ul>
					<#list note.documents as doc_name>
						<li>
							<#assign filename = doc_name?split('.') />
							<div>
								<input type="checkbox" name="deletingFiles" value="/document/${doc_name}" />
								<a href="/document/${doc_name}">
									${filename[1]}<#if filename[2]??>.${filename[2]}</#if>
								</a>
							</div>
						</li>
					</#list>
				</ul>
			</#if>
		</div>
		
		<div><input type="file" name="files" multiple /></div>
		
		<div><button name="submit">Save changes</button></div>
	</form>
	
	

</@base.content>
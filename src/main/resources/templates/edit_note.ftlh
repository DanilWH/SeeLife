<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="Edit note | See Life">

	<@common.header>Edit note</@common.header>

	<form action="/edit_note/id/${note.id}" method="post" enctype="multipart/form-data">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
		
		<div class="form-group">
			<@common.textarea message="${message?ifExists}" placeholder="What's going on now?">${note.text}</@common.textarea>
		</div>
		
		<div>
			<!-- show images -->
			<#if note.images?size != 0>
			
				<strong>Choose images to delete:</strong>
				
				<#list note.images as img_name>
				
					<#assign filename_parts = img_name?split('.') />
			  		<#assign uuid = "uuid-" + filename_parts[0] />
			  		<#assign filename = filename_parts[1] + "." + filename_parts[2] />
			  		
			  		<!-- card -->
					<div class="card my-2" style="width: 25%; height: 25%;">
						<img class="card-img-top rounded" src="/image/${img_name}" style="width: 100%; height: 100%;" />
						
						<div class="card-footer">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="deletingFiles" value="/image/${img_name}" id="${uuid}" />
								<label class="form-check-label" for="${uuid}">${filename}</label>
							</div>
						</div>
					</div>
				</#list>
				<hr />
			</#if>
		
			<!-- show videos -->
			<#if note.videos?size != 0>
			
				<strong>Choose videos to delete:</strong>
				
				<#list note.videos as video_name>
					
					<#assign filename_parts = video_name?split('.') />
			  		<#assign uuid = "uuid-" + filename_parts[0] />
			  		<#assign filename = filename_parts[1] + "." + filename_parts[2] />
			  		
			  		<!-- card -->
					<div class="card my-2" style="width: 25%; height: 25%;">
						<video class="card-img-top rounded" src="/video/${video_name}" preload="metadata" controls style="width: 100%; height: 100%;"></video>
						<div class="card-footer">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="deletingFiles" value="/video/${video_name}" id="${uuid}" />
								<label class="form-check-label" for="${uuid}">${filename}</label>
							</div>
						</div>
					</div>
				</#list>
				<hr />
			</#if>
			
			<!-- show audio -->
			<#if note.audios?size != 0>
			
				<strong>Choose audios to delete:</strong>
				
				<#list note.audios as audio_name>
					
					<#assign filename_parts = audio_name?split('.') />
			  		<#assign uuid = "uuid-" + filename_parts[0] />
			  		<#assign filename = filename_parts[1] + "." + filename_parts[2] />
			  		
			  		<!-- card -->
					<div class="card my-2">
						<div class="card-body">
							<audio src="/audio/${audio_name}" preload="metadata" controls style="display: block; width: 100%; pointer-events: auto; position: relative;">
						</div>
						
						<div class="card-footer">
							<div class="form-check">
								<input class="form-check-input" type="checkbox" name="deletingFiles" value="/audio/${audio_name}" id="${uuid}" />
								<label class="form-check-label" for="${uuid}">${filename}</label>
							</div>
						</div>
					</div>
				</#list>
				<hr />
			</#if>
			
			<!-- show documents -->
			<#if note.documents?size != 0>
			
				<strong>Choose documents to delete:</strong>
				
				<ul>
					<#list note.documents as doc_name>
						<li>
							<#assign filename = doc_name?split('.') />
					  		
							<input type="checkbox" name="deletingFiles" value="/document/${doc_name}">
							<a href="/document/${doc_name}">
								${filename[1]}<#if filename[2]??>.${filename[2]}</#if>
							</a>
						</li>
					</#list>
				</ul>
				<hr />
			</#if>
		</div>
		
		<div class="form-group">
			<@common.file_browser />
		</div>
		
		<button name="submit" class="btn btn-info">Save changes</button>
	</form>
	
	

</@base.content>
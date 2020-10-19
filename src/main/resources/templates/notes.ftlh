<#import "parts/base.ftlh" as base />
<#import "parts/common.ftlh" as common />

<@base.content title="Notes | See Life">

	<@common.header>${day.getFormattedLocalDate()}</@common.header>
	
	<div class="navbar">
		<h3 class="navbar-nav mr-auto">${day.title}</h3>
		
		<#if isRelevant == true>
			<div class="navbar-nav">
				<a href="">Edit title &#8595</a>
				<form action="/edit_day/id/${day.id}" method="post">
					<input type="hidden" name="_csrf" value="${_csrf.token}" />
					<input type="text" name="dayTitle" placeholder="Change the day title" required />
					<button name="submit">Change</button>
					<div>${message?ifExists}</div>
				</form>
			</div>
		</#if>
	</div>

	<ul>
		<#list notes as note>
		
			
			
			<div class="card p-3 my-4">
				<div class="bd-example bd-example-tabs">
					<div class="row">
						<div class="col-3">
							<div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
								<a class="nav-link active" id="v-pills-writing-tab-${note.id}" data-toggle="pill" href="#v-pills-writing-${note.id}" role="tab" aria-controls="v-pills-writing-${note.id}" aria-selected="true">Writing</a>
								
								<#if note.images?size != 0>
									<a class="nav-link" id="v-pills-images-tab-${note.id}" data-toggle="pill" href="#v-pills-images-${note.id}" role="tab" aria-controls="v-pills-images-${note.id}" aria-selected="false">Images</a>
								</#if>
								
								<#if note.videos?size != 0>
									<a class="nav-link" id="v-pills-videos-tab-${note.id}" data-toggle="pill" href="#v-pills-videos-${note.id}" role="tab" aria-controls="v-pills-videos-${note.id}" aria-selected="false">Videos</a>
								</#if>
								
								<#if note.audios?size != 0>
									<a class="nav-link" id="v-pills-audios-tab-${note.id}" data-toggle="pill" href="#v-pills-audios-${note.id}" role="tab" aria-controls="v-pills-audios-${note.id}" aria-selected="false">Audios</a>
								</#if>
								
								<#if note.documents?size != 0>
									<a class="nav-link" id="v-pills-documents-tab-${note.id}" data-toggle="pill" href="#v-pills-documents-${note.id}" role="tab" aria-controls="v-pills-documents-${note.id}" aria-selected="false">Documents</a>
								</#if>
							</div>
						</div>
						<div class="col-9">
							<div class="tab-content" id="v-pills-tabContent">
								<div class="tab-pane fade show active" id="v-pills-writing-${note.id}" role="tabpanel" aria-labelledby="v-pills-writing-tab-${note.id}">
								  	<p>
										<#assign lines = note.text?split('\n') />
										<#list lines as line>
											${line}<#sep><br />
										</#list>
									</p>
								</div>
								
								<!-- show images -->
								<#if note.images?size != 0>
									<div class="tab-pane fade text-center" id="v-pills-images-${note.id}" role="tabpanel" aria-labelledby="v-pills-images-tab-${note.id}">
									  	<#list note.images as image>
									  		<#assign filename_parts = image?split('.') />
									  		<#assign uuid = "uuid" + filename_parts[0] />
									  		<#assign filename = filename_parts[1] + "." + filename_parts[2] />
									  		
											<img class="rounded mx-2 mb-3" src="/image/${image}" style="width: 30%; height: 30%;" data-toggle="modal" data-target="#${uuid}" />
											
											<!-- Modal -->
											<div class="modal fade bd-example-modal-lg" id="${uuid}" tabindex="-1" role="dialog" aria-labelledby="${uuid}Title" aria-hidden="true">
												<div class="modal-dialog modal-lg" role="document">
													<div class="modal-content">
														<div class="modal-header">
															<h5 class="modal-title" id="${uuid}Title">${filename}</h5>
															<button type="button" class="close" data-dismiss="modal" aria-label="Close">
															  	<span aria-hidden="true">&times;</span>
															</button>
														</div>
														<div class="modal-body">
														    <img src="/image/${image}" style="width: 100%; height: 100%;" alt="Couldn't load ${filename}"/>
														</div>
														<div class="modal-footer">
															<button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
														</div>
													</div>
												</div>
											</div>
										</#list>
									</div>
								</#if>
								
								<!-- show videos -->
								<#if note.videos?size != 0>
									<div class="tab-pane fade text-center" id="v-pills-videos-${note.id}" role="tabpanel" aria-labelledby="v-pills-videos-tab-${note.id}">
									  	<#list note.videos as video>
											<video class="rounded mx-2 mb-3" src="/video/${video}" preload="metadata" controls style="width: 30%; height: 30%;"></video>
										</#list>
									</div>
								</#if>
								
								<!-- show audio -->
								<#if note.audios?size != 0>
									<div class="tab-pane fade" id="v-pills-audios-${note.id}" role="tabpanel" aria-labelledby="v-pills-audios-tab-${note.id}">
									  	<#list note.audios as audio>
											<audio src="/audio/${audio}" preload="metadata" controls></audio>
										</#list>
									</div>
								</#if>
								
								<!-- show documents -->
								<#if note.documents?size != 0>
									<div class="tab-pane fade" id="v-pills-documents-${note.id}" role="tabpanel" aria-labelledby="v-pills-documents-tab-${note.id}">
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
								
								<div class="card-footer text-muted ${isRelevant?string("navbar", "")}">
									<#if isRelevant == true>
											<a href="/edit_note/id/${note.id}" class="btn btn-outline-info btn-sm">Edit note</a>
											at ${note.getFormattedLocalTime()}
									<#else>
								    	<div align="right">at ${note.getFormattedLocalTime()}</div>
									</#if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</#list>
		
		<#if isRelevant == false>
			<div align="middle"><h2>...ZZZ...</h2></div>
		</#if>
	</ul>

</@base.content>
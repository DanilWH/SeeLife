<#macro header>

	<h1 class="display-4"><#nested /></h1>
	<hr />
	
</#macro>

<#macro send_form_btn>

	<button class="btn btn-info my-2 my-sm-0" type="submit"><#nested /></button>

</#macro>

<#macro file_browser name="files" multiple="true">

	<div class="custom-file">
		<input type="file" name="${name}" ${multiple?boolean?string("multiple", "")} class="custom-file-input" id="customFile" />
		<label class="custom-file-label" for="customFile">Choose files</label>
	</div>

</#macro>

<#macro textarea message name="text" rows="5" required="true" placeholder="">

	<textarea
		name="${name}"
		rows="${rows}"
		${required?boolean?string("required", "")}
		class="form-control ${(message?length != 0)?string("is-invalid", "")}"
		placeholder="${placeholder}"
	><#nested /></textarea>
	
	<#if (message?length != 0)><div class="invalid-feedback">${message}</div></#if>

</#macro>
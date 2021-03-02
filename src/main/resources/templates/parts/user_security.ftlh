<#assign
	context = Session.SPRING_SECURITY_CONTEXT??
/>

<#if context>
	<#assign user = Session.SPRING_SECURITY_CONTEXT.authentication.principal />
</#if>
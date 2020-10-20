<#import "../parts/base.ftlh" as base />
<#import "../parts/common.ftlh" as common />

<@base.content title="404 Page Not Found | See Life">

	<@common.header>404 - Page Not Found</@common.header>
	<p>The requestd URL ${springMacroRequestContext.getRequestUri()} search was not found on this server.
    <img src="https://i.gifer.com/7VE.gif" class="fixed"/>
	
</@base.content>
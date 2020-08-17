<#macro content title>

<!DOCTYPE html>
<html lang="en">
	<head>
		<title>${title}</title>
	</head>
	<body>
		<#include "bar.ftlh" />
		
		<#nested />
	</body>
</html>

</#macro>
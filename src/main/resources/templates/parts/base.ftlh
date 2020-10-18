<#macro content title>

<!DOCTYPE html>
<html lang="en">
	<head>
		<!-- Required meta tags -->
	    <meta charset="utf-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	
	    <!-- Bootstrap CSS -->
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
	    
		<title>${title}</title>
	</head>
	<body>
		
		<#include "user_security.ftlh" />
		
		<nav class="navbar navbar-expand-lg navbar-dark sticky-top bg-dark">
			<a class="navbar-brand" href="/">See Life</a>
			<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
			    <span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
		    	<ul class="navbar-nav mr-auto">
		      		<li class="nav-item active">
		        		<a class="btn btn-outline-info my-2 my-sm-0" href="/new_note">What's happening? <span class="sr-only">(current)</span></a>
		      		</li>
		      	</ul>
		      	
		      	<!-- Date form -->
		      	
			    
			    <form action="/" method="get" class="form-inline my-2 my-lg-0 mr-auto">
					<input class="form-control mr-sm-2" type="date" name="startDate" /> -
					<input class="form-control mr-sm-2" type="date" name="endDate" />
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
				</form>
			    
			    <ul class="navbar-nav">
			    	<#if user??>
			    		<li class="nav-item">
							<a class="nav-link">Hello, ${user.getUsername()}.</a>
						</li>
						<li class="nav-item">
							<form action="/logout" method="post">
								<input type="hidden" name="_csrf" value="${_csrf.token}" />
								<button class="btn btn-outline-light my-2 my-sm-0" name="submit">Log Out</button>
							</form>
						</li>
					<#else>
						<li class="nav-item">
							<a class="nav-link" href="/login">Sign In</a>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="/registration">Sign Up</a>
						</li>
					</#if>
			    </ul>
		    </div>
		</nav>
		
		<div class="container-fluid">
		    <div class="row">
			    <nav class="col-md-2 d-none d-md-block bg-light sidebar">
				    <div class="sidebar-sticky">
					    <ul class="nav flex-column">
						    <li class="nav-item">
							    <a class="nav-link active" href="/">
							      <span data-feather="home"></span>
							      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-home">
							      	<path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path>
							      	<polyline points="9 22 9 12 15 12 15 22"></polyline>
							      </svg>
							      Home <span class="sr-only">(current)</span>
							    </a>
						     </li>
					    </ul>
					    
			            <#if user?? && user.isAdmin()>
						    <h6 class="sidebar-heading d-flex justify-content-between align-items-center px-3 mt-4 mb-1 text-muted">
				              	<span>Admin rights</span>
				              	<a class="d-flex align-items-center text-muted" href="#">
				                	<span data-feather="plus-circle"></span>
				              	</a>
				            </h6>
			            
				            <ul class="nav flex-column">
							    <li class="nav-item">
								    <a class="nav-link" href="/admin/users">
								      <span data-feather="home"></span>
								      <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file-text">
								      	<path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
								      	<polyline points="14 2 14 8 20 8"></polyline>
								      	<line x1="16" y1="13" x2="8" y2="13"></line>
								      	<line x1="16" y1="17" x2="8" y2="17"></line>
								      	<polyline points="10 9 9 9 8 9"></polyline>
								      </svg>
								      Admin operations
								    </a>
							    </li>
						    </ul>
					    </#if>
				    </div>
			    </nav>
			    
				<main role="main" class="col-md-9 ml-sm-auto col-lg-10 pt-3 px-4">
			            <div class="container my-5">
							<#nested />
						</div>
				</main>
		    </div>
		</div>
		
		<!-- Option 2: jQuery, Popper.js, and Bootstrap JS -->
	    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
	    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.min.js" integrity="sha384-w1Q4orYjBQndcko6MimVbzY0tgp4pWB4lZ7lr30WKz0vr/aWKhXdBNmNb5D92v7s" crossorigin="anonymous"></script>
	</body>
</html>

</#macro>
<%@ include file="init.jsp" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

 
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/social.css">
<title>FilmClub</title>
<link rel="shortcut icon" href="img/FCLogo.png" />

<script>
	window.onload = function () {
    document.getElementById("name").addEventListener("blur", function telfVal() {

        var regex = /^[a-z0-9_-]{8,15}$/;

        if (document.getElementById("name").value == " " || document.getElementById("name").value.length == 0 || regex.test(document.getElementById("name").value) == false) {
            document.getElementById("name").style = "background-color:red";
            var text = "Ejemplo nombre correcto: ''Daniel86''";  
        	document.getElementById("errorName").innerHTML=text;
        } else {

            document.getElementById("name").style = "background-color:green";
			document.getElementById("errorName").innerHTML=" ";
			
        }
    });

    document.getElementById("pass").addEventListener("blur", function telfVal2() {

        var regex = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[$@$!%*?&])([A-Za-z\d$@$!%*?&]|[^ ]){8,15}$/;

        if (document.getElementById("pass").value == " " || document.getElementById("pass").value.length == 0 || regex.test(document.getElementById("pass").value) == false) {
			document.getElementById("pass").style = "background-color:red";
			var text = "Ejemplo password correcto: ''Password8$''";  
        	document.getElementById("errorPass").innerHTML=text;

        } else {

            document.getElementById("pass").style = "background-color:green";
        }
    });

    document.getElementById("fullname").addEventListener("blur", function telfVal3() {

        var regex = /^([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\']+[\s])+([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])+[\s]?([A-Za-zÁÉÍÓÚñáéíóúÑ]{0}?[A-Za-zÁÉÍÓÚñáéíóúÑ\'])?$/;

        if (document.getElementById("fullname").value == " " || document.getElementById("fullname").value.length == 0 || regex.test(document.getElementById("fullname").value) == false) {
            document.getElementById("fullname").style = "background-color:red";
            var text = "Ejemplo Nombre completo correcto: ''Daniel Casanova Herrera''";  
        	document.getElementById("errorFullname").innerHTML=text;

        } else {

            document.getElementById("fullname").style = "background-color:green";
        }
    });

    document.getElementById("address").addEventListener("blur", function telfVal4() {

        var regex = /^[a-zA-Z0-9\s,'-]*$/;

        if (document.getElementById("address").value == " " || document.getElementById("address").value.length == 0 || regex.test(document.getElementById("address").value) == false) {
            document.getElementById("address").style = "background-color:red";
            var text = "Ejemplo direccion correcto: ''Calle Ejemplo 5''";  
        	document.getElementById("errorAddress").innerHTML= text;

        } else {

            document.getElementById("address").style = "background-color:green";
        }
    });

    document.getElementById("email").addEventListener("blur", function telfVal5() {

        var regex = /^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$/;

        if (document.getElementById("email").value == " " || document.getElementById("email").value.length == 0 || regex.test(document.getElementById("email").value) == false) {
            document.getElementById("email").style = "background-color:red";
            var text = "Ejemplo email correcto: ''ejemplo@email.com''"; 
        	document.getElementById("errorEmail").innerHTML= text;

        } else {

            document.getElementById("email").style = "background-color:green";
        }
    });

}
</script>
</head>
<body>
	<%
		String username = null;
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					username = cookie.getValue();
					request.setAttribute("username", username);
				}
			}
		}
	%>
	<%@ include file="social.jsp" %>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"
		style="margin: 24px 0;">
		<a class="navbar-brand" href="index.jsp"><img src="img/FCLogo.png"
			height="50px"> </a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navb">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navb">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link"
					href="index.jsp">Principal</a></li>
				<li class="nav-item"><a class="nav-link" href="films.jsp">Peliculas</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="tvSeries.jsp">Series</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="games.jsp">Juegos</a>
				</li>
				
				<c:choose>
					<c:when test="${role == 'admin'}">
					
					<li class="nav-item dropdown">
				        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				          Administracion
				        </a>
				        <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
				          <a class="dropdown-item" href="updateProduct.jsp">Crear y modificar Productos</a>
				          <a class="dropdown-item" href="administrator.jsp">Cambiar estado de los productos y eliminar</a>
				          
				        </div>
			      	</li>
						
					</c:when>
					<c:when test="${username != null}">
						<li class="nav-item"><a class="nav-link" href="settingsUser.jsp">Mysite</a></li>
					</c:when>
					
				</c:choose>
			</ul>

			<c:choose>

				<c:when test="${username == null}">
					<div class="form-inline my-2 my-lg-0">
						<div class="btn-group">
							<button style="margin-right:50px" type="button" class="btn btn-success dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false">Iniciar Sesion</button>
							<div class="dropdown-menu">
								<form action="LoginServlet" method="POST">
									<label for="validationTooltipUsername">Nombre</label>
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"
												id="validationTooltipUsernamePrepend"><i
												class="material-icons">account_box</i></span>
										</div>
										<input type="text" name="username" value="usuario"
											class="form-control" id="validationTooltipUsername"
											placeholder="Login"
											aria-describedby="validationTooltipUsernamePrepend" required>
										<div class="invalid-tooltip">Please choose a unique and
											valid username.</div>
									</div>
									<label for="validationTooltipPassword">Contraseña</label>
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text"
												id="validationTooltipPasswordPrepend"><i
												class="material-icons">https</i></span>
										</div>
										<input type="password" name="password" value="contraseña"
											class="form-control" id="validationTooltipPassworde"
											placeholder="Password"
											aria-describedby="validationTooltipUsernamePrepend" required>
										<div class="invalid-tooltip">Please choose a unique and
											valid password.</div>
									</div>
									<br>
									<button type="submit" class="btn btn-warning" value="Enviar">Enviar</button>
									<a class="btn btn-primary" href="register.jsp">Resgistrate</a>
								</form>
							</div>
						</div>
					</div>
				</c:when>
				<c:when test="${username != null}">
					<form class="form-inline my-2 my-lg-0">
						<a class="btn btn-danger" href="/FilmClub/LogoutServlet">Cerrar
							sesion</a>
					</form>
				</c:when>
			</c:choose>

		</div>
	</nav>

	<nav aria-label="breadcrumb">
		<ol class="breadcrumb">
			<li class="breadcrumb-item"><a href="index.jsp">Principal</a></li>
			<li class="breadcrumb-item active"><a href="register.jsp">Register</a></li>
		</ol>
	</nav>
<div class="container" >



	<%
		String err = "";
		if (request.getAttribute("err") != null) {
			err = (String) request.getAttribute("err");
		}
	%>

<h1>Registrate FilmClub</h1>
	<form action="ResgistrationServlet "  method="post">
		<fieldset>
			<c:choose>
				<c:when test="${not empty err}">
					<li style="color: red"><c:out value="${err}" /></li>
				</c:when>
			</c:choose>
			
			<div class="col-md-4 mb-3">
			<label for="name"><fmt:message key="register.companyname" />:</label> 
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text"
						id="validationTooltipUsernamePrepend"><i
						class="material-icons">account_box</i></span>
				</div>
				
			
				<input id="name" type="text" placeholder="Introduce el nombre" name="username">
				<div id="errorName" style="color: coral; font-size: large;"></div>
			</div>
			</div>
			
			<div class="col-md-4 mb-3">
			<label for="pass"><fmt:message key="register.password" />:</label> 
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text"
						id="validationTooltipPasswordPrepend"><i
						class="material-icons">https</i></span>
				</div>
				
				<input id="pass" type="password" placeholder="Contraseña" name="password">
			</div>
			<div id="errorPass" style="color: coral; font-size: large;"></div>
			</div>
			
			<div class="col-md-4 mb-3">
			<label for="fullname"><fmt:message key="register.companyfullname" />:</label> 
			<div class="input-group">
			<div class="input-group-prepend">
					<span class="input-group-text"
						id="validationTooltipUsernamePrepend"><i
						class="material-icons">account_box</i></span>
				</div>
				<input id="fullname" type="text" placeholder="Nombre completo" name="user_fullname">
				<div id="errorFullname" style="color: coral; font-size: large;"></div>
			</div>
			</div>
			<div class="col-md-4 mb-3">
			<label for="address"><fmt:message key="register.companyfulladdress" />:</label> 
			<div class="input-group"> 
				<div class="input-group-prepend">
                    <span class="input-group-text" id="validationTooltipUsernamePrepend"><i class="material-icons">list</i></span>
                </div>
				
				<input id="address" type="text" placeholder="Direccion" name="address"> 
				<div id="errorAddress" style="color: coral; font-size: large;"></div>
			</div>
			</div>
			<div class="col-md-4 mb-3">
			<label for="email"><fmt:message key="register.companyemail" />:</label> 
			<div class="input-group">
			
	            <div class="input-group-prepend">
	                <span class="input-group-text" id="validationTooltipUsernamePrepend"><i class="material-icons">email</i></span>
	            </div>
				<input id="email" type="text" placeholder="Email…" name="email"><br />
				<div id="errorEmail" style="color: coral; font-size: large;"></div>
			</div>
			</div>
			<div class="col-md-4 mb-3">
			<button type="submit" class="btn btn-primary"><fmt:message key="register.signup" /></button>
			</div>
		</fieldset>
	</form>
	
	
</div>

 <footer id="sticky-footer" class="py-4 bg-dark text-white">
    <div class="container text-center">
      <small>Copyright &copy; FilmClub</small>
    </div>
  </footer>
</body>
</html>
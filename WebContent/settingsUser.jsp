<%@page import="org.iesalixar.daw2.dao.UserDaoImpl"%>
<%@ include file="init.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/social.css">
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


<title>FilmClub</title>
<link rel="shortcut icon" href="img/film2.jpg" />
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
	<%@ include file="social.jsp"%>
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
				<li class="nav-item"><a class="nav-link active"
					href="index.jsp">Principal</a></li>
				<li class="nav-item"><a class="nav-link" href="films.jsp">Peliculas</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="tvSeries.jsp">Series</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="games.jsp">Juegos</a>
				</li>
				<c:choose>
					<c:when test="${role == 'admin'}">
						<li class="nav-item"><a class="nav-link"
							href="administrator.jsp">administrador</a></li>
					</c:when>
				</c:choose>
			</ul>

			<c:choose>

				<c:when test="${username == null}">
					<div class="form-inline my-2 my-lg-0">
						<div class="btn-group">
							<button style="margin-right: 50px" type="button"
								class="btn btn-success dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Iniciar
								Sesion</button>
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
			<li class="breadcrumb-item active"><a href="index.jsp">Principal</a></li>
		</ol>
	</nav>




	<%
		request.setAttribute("products", ProductDaoImpl.getProducts(-1, true));
		request.setAttribute("myRent", RentDaoImpl.rentUser(username));
		request.setAttribute("user", UserDaoImpl.getUserIDForUsername(username));

		if (username != null) {
	%>



	<div class="jumbotron alert-info">
		<div class="container">
			<h1>
				Bienvenido
				<%=username%></h1>
			<p>Esperamos que les guste y encuentren lo que buscan</p>
		</div>
	</div>
	<div class="container alert-info" id="my-content">

		<h1 class="">Datos Personales:</h1>
		<div class="col-lg-4 col-md-4 col-6 margin-left">
			<form class="" action="UpdateUser" method="POST">
				<label for="userName"><fmt:message key="register.companyname" />:</label> <br> 
				<input name="userName" value="${user.getUsername()}"><br>
				<br> <label for="pass"><fmt:message
						key="register.password" />:</label> <br> 
						<input name="password" value="${user.getPassword()}"><br>
				<br> <label for="fullname"><fmt:message
						key="register.companyfullname" />:</label> <br> <input
					name="fullname" value="${user.getUser_fullname()}"><br>
				<br> <label for="address"><fmt:message
						key="register.companyfulladdress" />:</label> <br> <input
					name="address" value="${user.getAddress()}"><br>
				<br> <label for="email"><fmt:message
						key="register.companyemail" />:</label> <br> <input name="email"
					value="${user.getEmail()}"><br>
				<br>

				<button style="margin-right: 10px"
					class="nav navbar-nav navbar-right btn btn-success text-center"
					type="submit" style="margin: auto">Actualizar</button>
			</form>
		</div>
	</div>

	<%
		}
	%>
	<br>
	<br>
	<footer id="sticky-footer" class="py-4 bg-dark text-white">
		<div class="container text-center">
			<small>Copyright &copy; FilmClub</small>
		</div>
	</footer>

</body>
</html>
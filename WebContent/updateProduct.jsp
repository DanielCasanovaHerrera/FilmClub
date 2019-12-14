<%@page import="org.iesalixar.daw2.dao.UserDaoImpl"%>
<%@ include file="init.jsp"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<link rel="stylesheet" type="text/css" href="css/social.css">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
	<fmt:setBundle basename="interface" />

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
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin: 24px 0;">
		<a class="navbar-brand" href="index.jsp"><img src="img/FCLogo.png" height="50px"> </a>
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navb">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link active" href="index.jsp">Principal</a></li>
				<li class="nav-item"><a class="nav-link" href="films.jsp">Peliculas</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="tvSeries.jsp">Series</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="games.jsp">Juegos</a>
				</li>
				<c:choose>
					<c:when test="${role == 'admin'}">
						<li class="nav-item"><a class="nav-link" href="administrator.jsp">administrador</a></li>
						<li class="nav-item"><a class="nav-link" href="updateProduct.jsp">Modificar Productos</a></li>
					</c:when>
				</c:choose>
			</ul>

			<c:choose>

				<c:when test="${username == null}">
					<div class="form-inline my-2 my-lg-0">
						<div class="btn-group">
							<button style="margin-right: 50px" type="button" class="btn btn-success dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Iniciar
								Sesion</button>
							<div class="dropdown-menu">
								<form action="LoginServlet" method="POST">
									<label for="validationTooltipUsername">Nombre</label>
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="validationTooltipUsernamePrepend"><i
													class="material-icons">account_box</i></span>
										</div>
										<input type="text" name="username" value="usuario" class="form-control"
											id="validationTooltipUsername" placeholder="Login"
											aria-describedby="validationTooltipUsernamePrepend" required>
										<div class="invalid-tooltip">Please choose a unique and
											valid username.</div>
									</div>
									<label for="validationTooltipPassword">Contrase�a</label>
									<div class="input-group">
										<div class="input-group-prepend">
											<span class="input-group-text" id="validationTooltipPasswordPrepend"><i
													class="material-icons">https</i></span>
										</div>
										<input type="password" name="password" value="contrase�a" class="form-control"
											id="validationTooltipPassworde" placeholder="Password"
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

	<div class="container mt-3">

		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link active" data-toggle="tab" href="#entrantes">Modificar Producto</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" data-toggle="tab" href="#primeros">Crear nuevo producto</a>
			</li>
		</ul>
		<div class="tab-content">
			<div id="entrantes" class="container tab-pane active"><br>

				<div class="row">


					<div class="container alert-info" id="my-content">

						<h1 class="">Actualizar Productos:</h1>
						<div class="container alert-danger" id="general-content">
							<div class="row align-items-center">

								<c:forEach var='item' items='${products}'>

									<div class="col-lg-4 col-md-4 col-6 margin-left" id="productoscss"
										data-toggle="modal" data-target="#modal${item.product_id}">
										<h3>${item.getShortname()}</h3>
										<a href="#" class="d-block mb-4 h-100"> <img class="img-fluid img-thumbnail"
												alt="${item.getShortname()}"
												src="ConvertBlobServlet?product_id=${item.product_id}">
										</a>
									</div>
									<div class="modal" id="modal${item.product_id}">
										<div class="modal-dialog">
											<div class="modal-content">

												<!-- Modal Header -->
												<div class="modal-header">
													<h4 class="modal-title">${item.getShortname()}</h4>
													<button type="button" class="close"
														data-dismiss="modal">&times;</button>
												</div>

												<!-- Modal body -->
												<div class="modal-body">

													<img alt="${item.getShortname()}"
														src="ConvertBlobServlet?product_id=${item.product_id}">
													<form class="" action="UpdateProduct" method="POST">
														<input type="hidden" name="id" value="${item.getProduct_id()}">

														<label for="fulldescription">Descripcion:</label> <br>
														<textarea class="fulldescription" rows="10"
															cols="40">${item.getFulldescription()}</textarea><br><br>

														<label for="company">Empresa creadora:</label> <br>
														<input name="company" value="${item.getCompany()}"><br><br>


														<label for="reposition">Precio Alquiler:</label> <br>
														<input type="number" name="reposition"
															value="${item.getReposition_value()}"><br><br>

														<button style="margin-right: 10px"
															class="nav navbar-nav navbar-right btn btn-success text-center"
															type="submit" style="margin: auto">Actualizar</button>
													</form>
												</div>

												<!-- Modal footer -->
												<div class="modal-footer">
													<button type="button" class="btn btn-success"
														data-dismiss="modal">CERRAR</button>

												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div id="primeros" class="container tab-pane fade"><br>
				<div class="row">
					<form action="CreateProduct" method="post">
						<label for="shortname">Nombre del producto</label><br>
						<input type="text" name="shortname" id="shortname" placeholder="nombre del producto"><br><br>

						<label for="type_id">Tipo de producto</label><br>
						<select name="type_id">
							<option value="1" selected>Pelicula</option> 
							<option value="2" >Serie</option>
							<option value="3">Juego</option>
						</select><br><br>

						<label for="fulldescription">Descripcion del producto</label><br>
						<input type="textarea" name="fulldescription" id="fulldescription" placeholder="descripcion del producto"><br><br>

						<label for="company">Compañia creadora del producto</label><br>
						<input type="text" name="company" id="company" placeholder="compañia del producto"><br><br>

						<label for="year">Año de lanzamiento</label><br>
						<input type="date" name="year" id="year" placeholder="año de lanzamiento"><br><br>

						<label for="reposition_value">Precio alquiler</label><br>
						<input type="text" name="reposition_value" id="reposition_value" placeholder="precio alquiler"><br><br>

						<label for="img">Selecciona una imagen</label><br>
						<input type="file" name="img"><br><br>

						<button style="margin-right: 10px" class="nav navbar-nav navbar-right btn btn-success text-center" type="submit" style="margin: auto">Crear producto</button>
					</form>
				</div>
			
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
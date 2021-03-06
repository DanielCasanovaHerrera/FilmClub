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
			<li class="breadcrumb-item active"><a href="index.jsp">Principal</a></li>
		</ol>
	</nav>




	<%
		request.setAttribute("products", ProductDaoImpl.getProducts(-1, true));
		request.setAttribute("myRent", RentDaoImpl.rentUser(username));

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
	<div class="container alert-info" id="my-content" >
		<c:choose>
			<c:when test="${role == 'user'}">
				<h1 class="text-center">
					<fmt:message key="products.yourlist" />
				</h1>
				<div class="row align-items-center">
				<c:forEach var='item' items='${myRent}'>
				<div class="col-lg-4 col-md-4 col-6 margin-left">
				${item.getProduct_id().getShortname()}
				<a href="#" class="d-block mb-4 h-100"> <img
						class="img-fluid img-thumbnail"
						alt="${item.getProduct_id().getShortname()}"
						src="ConvertBlobServlet?product_id=${item.getProduct_id().getProduct_id()}">
					</a>

					<form action="ChangeRentServlet" method="post">
						<input type="hidden" id="<c:out value="${item.getRent_id()}"/>"
							name="rent_id" value="<c:out value="${item.getRent_id()}"/>">
						<input type="hidden"
							id="<c:out value="${item.getProduct_id().getProduct_id()}"/>"
							name="product_id"
							value="<c:out value="${item.getProduct_id().getProduct_id()}"/>">
						<input type="hidden"
							id="<c:out value="${item.getUser_id().getUser_id()}"/>"
							name="user_id"
							value="<c:out value="${item.getUser_id().getUser_id()}"/>">
						<button style="margin-right: 10px"
							class="nav navbar-nav navbar-right btn btn-warning" type="submit"
							style="margin-rigth: 20px">Dejar de alquilar</button>
					</form>
					<form action="PdfServlet" method="post">
						<input type="hidden" name="rentId"
							value="Precio alquiler: <c:out value="${item.getRent_id()}"/> euros">
						<input type="hidden" name="username"
							value="Nombre del usuario: <c:out value="${item.getUser_id().getUsername()}"/>">
						<input type="hidden" name="productname"
							value="Nombre del producto: <c:out value="${item.getProduct_id().getShortname()}"/>">
						<input type="hidden" name="img"
							value="<img src="ConvertBlobServlet?product_id=${item.getProduct_id().getProduct_id()}">" />

						<input type="hidden" name="fulldescription"
							value="Descripcion completa: <c:out value="${item.getProduct_id().getFulldescription()}"/>">

						<button style="margin-right: 10px"
							class="nav navbar-nav navbar-right btn btn-success" type="submit"
							style="margin-rigth: 20px">PDF</button>
					</form>
					</div>
				</c:forEach>
				</div>
			</c:when>
		</c:choose>
	</div>
	<br>
	<h1 class="text-center" ><fmt:message key="products.generalist" /></h1>
	<div class="container alert-danger" id="general-content">
		<div class="row align-items-center">
			
			<c:forEach var='item' items='${products}'>

				<div class="col-lg-4 col-md-4 col-6 margin-left" id="productoscss"
					data-toggle="modal" data-target="#modal${item.product_id}">
					<h3>${item.getShortname()}</h3>
					<a href="#" class="d-block mb-4 h-100"> <img
						class="img-fluid img-thumbnail" alt="${item.getShortname()}"
						src="ConvertBlobServlet?product_id=${item.product_id}">
					</a>
				</div>
				<div class="modal" id="modal${item.product_id}">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">${item.getShortname()}</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">

								<img alt="${item.getShortname()}"
									src="ConvertBlobServlet?product_id=${item.product_id}">

								<h5>Tipo: ${item.getType_id().getTypename()}</h5>
								<h5>
									Descripcion:
									<h6>${item.getFulldescription()}</h6>
								</h5>
								<h5>Empresa creadora: ${item.getCompany()}</h5>
								<h5>Año de lanzamiento: ${item.getYear()}</h5>
								<h5>Precio Alquiler: ${item.getReposition_value()}</h5>

							</div>

							<!-- Modal footer -->
							<div class="modal-footer">
								<button type="button" class="btn btn-success"
									data-dismiss="modal">CERRAR</button>
								<c:choose>
									<c:when test="${role=='user'}">
										<form action="NewRentServlet" method="post">
											<input type="hidden"
												id="<c:out value="${item.getProduct_id()}"/>"
												name="product_id"
												value="<c:out value="${item.getProduct_id()}"/>"> <input
												type="hidden" id="username" name="username"
												value="<%=username%>">
											<button style="margin-right: 10px"
												class="nav navbar-nav navbar-right btn btn-danger"
												type="submit" style="margin-rigth: 20px">Alquilar</button>
										</form>
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
	</div>

	<%
		} else {
	%>
	<div class="container">
		<h1>
			Bienvenido, inicie sesion o registrese</h1>
		<p>Para tener acceso a todas las funciones</p>
	</div>

<br>
<h1 class="text-center" ><fmt:message key="products.generalist" /></h1>
	<div class="container alert-danger" id="general-content">
		<div class="row text-center center text-lg-left">



			<c:forEach var='item' items='${products}'>



				<div class="col-lg-4 col-md-4 col-6" data-toggle="modal"
					data-target="#modal${item.product_id}">
					<h3>${item.getShortname()}</h3>
					<a href="#" class="d-block mb-4 h-100"> <img
						class="img-fluid img-thumbnail" alt="${item.getShortname()}"
						src="ConvertBlobServlet?product_id=${item.product_id}">
					</a>
				</div>
				<div class="modal" id="modal${item.product_id}">
					<div class="modal-dialog">
						<div class="modal-content">

							<!-- Modal Header -->
							<div class="modal-header">
								<h4 class="modal-title">${item.getShortname()}</h4>
								<button type="button" class="close" data-dismiss="modal">&times;</button>
							</div>

							<!-- Modal body -->
							<div class="modal-body">
								<img alt="${item.getShortname()}"
									src="ConvertBlobServlet?product_id=${item.product_id}">
								<h5>Tipo: ${item.getType_id().getTypename()}</h5>
								<h5>
									Descripcion:
									<h6>${item.getFulldescription()}</h6>
								</h5>
								<h5>Empresa creadora: ${item.getCompany()}</h5>
								<h5>Año de lanzamiento: ${item.getYear()}</h5>
								<h5>Precio Alquiler: ${item.getReposition_value()}</h5>
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
	<%
		}
	%>
	
	 <footer id="sticky-footer" class="py-4 bg-dark text-white">
    <div class="container text-center">
      <small>Copyright &copy; FilmClub</small>
    </div>
  </footer>

</body>
</html>
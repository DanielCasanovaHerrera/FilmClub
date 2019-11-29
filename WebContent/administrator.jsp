<%@ include file="init.jsp" %>

<!DOCTYPE html>
<html>

<head>
	<meta charset="ISO-8859-1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"></script>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

	<link rel="stylesheet" type="text/css" href="css/style.css">
	<title>FilmClub</title>
	<link rel="shortcut icon" href="img/FCLogo.png" />
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

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="margin: 24px 0;">
		<a class="navbar-brand" href="index.jsp"><img src="img/FCLogo.png" height="50px"></a>
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navb">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navb">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">Principal</a></li>
				<li class="nav-item"><a class="nav-link" href="films.jsp">Peliculas</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="tvSeries.jsp">Series</a>
				</li>
				<li class="nav-item"><a class="nav-link" href="games.jsp">Juegos</a>
				</li>
				<c:choose>
					<c:when test="${role == 'admin'}">
						<li class="nav-item"><a class="nav-link active" href="administrator.jsp">administrador</a></li>
					</c:when>
				</c:choose>
			</ul>

			<c:choose>
				<c:when test="${username == null}">
					<div class="form-inline my-2 my-lg-0">
						<div class="btn-group">
							<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown"
								aria-haspopup="true" aria-expanded="false">Iniciar Sesion</button>
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
			<li class="breadcrumb-item "><a href="index.jsp">Principal</a></li>
			<li class="breadcrumb-item active"><a href="administrator.jsp">Zona de Administraci�n</a></li>
		</ol>
	</nav>




	<%
		request.setAttribute("products", ProductDaoImpl.getProducts(-1, true));
		request.setAttribute("productsNotActivated", ProductDaoImpl.getProducts(-1, false));

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

	<c:choose>
		<c:when test="${role == 'admin'}">
			<h1>
				<fmt:message key="products.fulllist" />
				:
			</h1>
			<h1>Products Activated</h1>
		</c:when>
	</c:choose>

	<div class="row text-center center text-lg-left">



		<c:forEach var='item' items='${products}'>
			<div class="col-lg-4 col-md-4 col-6" data-toggle="modal" data-target="#modal${item.product_id}">
				<h3>${item.getShortname()}</h3>
				<a href="#" class="d-block mb-4 h-100"> <img class="img-fluid img-thumbnail"
						alt="${item.getShortname()}" src="ConvertBlobServlet?product_id=${item.product_id}">
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
							<img alt="${item.getShortname()}" src="ConvertBlobServlet?product_id=${item.product_id}">
							<h5>Tipo: ${item.getType_id().getTypename()}</h5>
							<h5>
								Descripcion:
								<h6>${item.getFulldescription()}</h6>
							</h5>
							<h5>Empresa creadora: ${item.getCompany()}</h5>
							<h5>A�o de lanzamiento: ${item.getYear()}</h5>
							<h5>Precio Alquiler: ${item.getReposition_value()}</h5>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-success" data-dismiss="modal">CERRAR</button>
							<c:choose>
								<c:when test="${role=='admin'}">
									<form action="ChangeStatusServlet" method="post">
										<input type="hidden" id="<c:out value=" ${item.getProduct_id()}" />"
										name="product_id"
										value="
										<c:out value="${item.getProduct_id()}" />&unapprove">
										<button style="margin-right: 10px"
											class="nav navbar-nav navbar-right btn btn-warning" type="submit"
											style="margin-rigth: 20px">
											<i class="icon-thumbs-down"> </i>
											<fmt:message key="products.unapproveproduct" />
										</button>
									</form>
									<form action="RemoveServlet" method="post">
										<input type="hidden" id="<c:out value=" ${item.getProduct_id()}" />"
										name="product_id"
										value="
										<c:out value="${item.getProduct_id()}" />&unapprove">
										<button style="margin-right: 10px"
											class="nav navbar-nav navbar-right btn btn-danger" type="submit"
											style="margin-rigth: 20px">
											eliminar
										</button>
									</form>
								</c:when>

							</c:choose>
						</div>

					</div>
				</div>
			</div>
		</c:forEach>
		<br><br>
		<h1>Products Not Activated</h1>

		<c:forEach var='item' items='${productsNotActivated}'>
			<div class="col-lg-4 col-md-4 col-6" data-toggle="modal" data-target="#modal${item.product_id}">
				<h3>${item.getShortname()}</h3>
				<a href="#" class="d-block mb-4 h-100"> <img class="img-fluid img-thumbnail"
						alt="${item.getShortname()}" src="ConvertBlobServlet?product_id=${item.product_id}">
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
							<img alt="${item.getShortname()}" src="ConvertBlobServlet?product_id=${item.product_id}">
							<h5>Tipo: ${item.getType_id().getTypename()}</h5>
							<h5>
								Descripcion:
								<h6>${item.getFulldescription()}</h6>
							</h5>
							<h5>Empresa creadora: ${item.getCompany()}</h5>
							<h5>A�o de lanzamiento: ${item.getYear()}</h5>
							<h5>Precio Alquiler: ${item.getReposition_value()}</h5>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="button" class="btn btn-success" data-dismiss="modal">CERRAR</button>
							<c:choose>
								<c:when test="${role=='admin'}">
									<form action="ChangeStatusServlet" method="post">
										<input type="hidden" id="<c:out value=" ${item.getProduct_id()}" />"
										name="product_id" value="
										<c:out value="${item.getProduct_id()}" />&approve">
										<button style="margin-right: 10px"
											class="nav navbar-nav navbar-right btn btn-warning" type="submit"
											style="margin-rigth: 20px"> <i class="icon-thumbs-up"> </i>
											<fmt:message key="products.approveproduct" /></button>
									</form>
								</c:when>
								<c:when test="${role=='admin'}">
									<!-- RemoveServlet contains logic related with removing a product given its 'product_id' -->
									<form action="RemoveServlet" method="post">
										<input type="hidden" id="<c:out value=" ${item.getProduct_id()}" />"
										name="product_id"
										value="
										<c:out value="${item.getProduct_id()}" />">
										<button style="margin-right: 10px"
											class="nav navbar-nav navbar-right btn btn-danger" type="submit"
											style="margin-rigth: 20px">
											<i class="icon-trash"> </i>
											<fmt:message key="products.removeproduct" />
										</button>
									</form>
								</c:when>
								<c:when test="${role=='admin'}">
									<button style="margin-right: 10px"
										class="nav navbar-nav navbar-right btn btn-warning" type="submit"
										style="margin-rigth: 20px">
										<i class="icon-trash"> </i>
										<fmt:message key="products.updateproduct" />
									</button>
								</c:when>
							</c:choose>
						</div>

					</div>
				</div>
			</div>
		</c:forEach>
		<div class="modal" id="modal${item.product_id}">
			<div class="modal-dialog">
				<div class="modal-content">

					<!-- Modal Header -->
					<div class="modal-header">
						<h4 class="modal-title">
							<fmt:message key="products.updateproduct" />
						</h4>
						<button type="button" class="close" data-dismiss="modal">&times;</button>
					</div>

					<!-- Modal body -->
					<div class="modal-body">
						<input type="hidden" id="<c:out value=" ${item.getProduct_id()}" />"
						name="product_id"
						value="
						<c:out value="${item.getProduct_id()}" />">

						<label for="productName">Nombre:</label>
						<input id="productName" type="text" value="${item.getShortname()}">

						<label for="productDescription">Descripcion:</label>
						<input id="productDescription" type="text" value="${item.getFulldescription()}">

						<label for="productCompany">Compañia:</label>
						<input id="productCompany" type="text" value="${item.getCompany()}">

						<label for="productReposition_value">Precio alquiler:</label>
						<input id="productReposition_value" type="text"
							value="${item.getReposition_value()}">
					</div>

					<!-- Modal footer -->
					<div class="modal-footer">
						<button type="button" class="btn btn-success" data-dismiss="modal">CERRAR</button>
						<c:choose>

						</c:choose>
					</div>

				</div>
			</div>
		</div>

	</div>


	<%
		}
	%>

</body>

</html>
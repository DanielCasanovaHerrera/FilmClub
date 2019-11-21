<%@page import="org.iesalixar.daw2.dao.RentDaoImpl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="org.iesalixar.daw2.model.Product"%>
<%@ page import="org.iesalixar.daw2.dao.ProductDaoImpl"%>
<%@ page import="org.iesalixar.daw2.model.Rent"%>
<%@ page import="org.iesalixar.daw2.dao.RentDaoImpl"%>
<%@ page import="java.util.List"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="interface" />

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

	<nav class="navbar navbar-expand-lg navbar-dark bg-dark"
		style="margin: 24px 0;">
		<a class="navbar-brand" href="index.jsp"><img src="img/FCLogo.png"
			height="50px"></a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navb">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navb">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link " href="index.jsp">Principal</a>
				</li>
				<li class="nav-item"><a class="nav-link active"
					href="films.jsp">Peliculas</a></li>
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
				<c:when test="${role == 'user'}">
						<li class="nav-item"><a class="nav-link"
							href="shoppingCart.jsp"> <i
						class="material-icons"><span class="badge">10</span></i></a></li>
					</c:when>
				<c:when test="${username == null}">
					<div class="form-inline my-2 my-lg-0">
						<div class="btn-group">
							<button type="button" class="btn btn-success dropdown-toggle"
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
			<li class="breadcrumb-item active"><a href="shoppingCart.jsp">Shopping Cart</a></li>
		</ol>
	</nav>




	<%
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

	<c:choose>
		<c:when test="${role == 'user'}">
			<h1>
				<fmt:message key="products.yourlist" />
				:
			</h1>

			<c:forEach var='item' items='${myRent}'>
			<div class="row">
			<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
			${item.getProduct_id().getShortname()}
			<a href="#" class="d-block mb-4 h-100"> <img
					class="img-fluid img-thumbnail"
					alt="${item.getProduct_id().getShortname()}"
					src="ConvertBlobServlet?product_id=${item.getProduct_id().getProduct_id()}">
				</a>
				</div>
				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
				<form action="ChangeRentServlet" method="post">
					<input type="hidden" id="${item.getRent_id()}" name="rent_id" value="${item.getRent_id()}"/>
					<input type="hidden" id="${item.getProduct_id().getProduct_id()}" name="product_id" value="${item.getProduct_id().getProduct_id()}"/>
					<button style="margin-right: 10px" class="nav navbar-nav navbar-right btn btn-warning" type="submit" style="margin-rigth: 20px">
						Dejar de alquilar
					</button>
				</form>
				<!-- <form action="NewRentServlet" method="post">
					<input type="hidden" id="<c:out value="${item.getProduct_id()}"/>" name="product_id" value="<c:out value="${item.getProduct_id()}"/>"> 
					<input type="hidden" id="username" name="username" value="<%=//username%>">
					<button style="margin-right: 10px" class="nav navbar-nav navbar-right btn btn-danger" type="submit" style="margin-rigth: 20px">Alquilar</button>
				</form> -->
				
				</div>
				</div>
			</c:forEach>
		</c:when>
	</c:choose>
	
	<%
		}
	%>

</body>
</html>
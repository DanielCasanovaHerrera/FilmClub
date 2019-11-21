<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.iesalixar.daw2.model.Product"%>
<%@ page import="org.iesalixar.daw2.dao.ProductDaoImpl"%>
<%@ page import="java.util.List"%>
<%@ page import="org.iesalixar.daw2.model.Rent"%>
<%@ page import="org.iesalixar.daw2.dao.RentDaoImpl"%>
<%@ page import="org.iesalixar.daw2.model.Product_Category"%>
<%@ page import="org.iesalixar.daw2.dao.Product_CategoryDaoImpl"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="interface" />
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css">
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
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark"
		style="margin: 24px 0;">
		<a class="navbar-brand" href="index.jsp"><img src="img/FCLogo.png"
			height="50px">
			</a>
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navb">
			<span class="navbar-toggler-icon"></span>
		</button>
		</nav>

</body>
</html>
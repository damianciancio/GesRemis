<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="util.web.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.js"></script>
				<script type="text/javascript" src="js/jquery.validate.js"></script>
				<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
				<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap-theme.css">
				<link rel="stylesheet" type="text/css" href="bootstrap/js/bootstrap.js">
				<link rel="stylesheet" type="text/css" href="bootstrap/js/npm.js">
				<link rel="stylesheet" type="text/css" href="datepicker/css/datepicker.css">
				<script type="text/javascript" src="datepicker/js/bootstrap-datepicker.js"></script>
				<script type="text/javascript" src="js/scripts.js"></script>
				<link rel="stylesheet" type="text/css" href="css/styles.css">
	<title>Iniciar sesión</title>
</head>
<body>
<div class="main-wrapper-generic">
	<form action="Login" method="post">
		<h3>Login</h3>
		<fieldset>
			<div class="form-group">
				<label for="usuario">Usuario</label><input class="form-control" type="text" name="usuario"></input>
			</div>
			<div class="form-group">
				<label for="contrasenia">Contraseña</label><input class="form-control" type="password" name="contrasenia"></input>
			</div>
		</fieldset>
		<button type="submit">Ingresar</button>
	</form>
</div>
</body>
</html>
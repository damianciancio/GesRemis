<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="util.web.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<%= Html.getLinksStylesAndScripts() %>
	<title>Iniciar sesión</title>
</head>
<body>
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
</body>
</html>
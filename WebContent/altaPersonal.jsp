<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="business.entities.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta personal</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
	<body>
		<form action="ABMPersonal" method="post">
			<ul>
				<li><p>Nombre</p><input type="text" name="nombre"></input></li>
				<li><p>Apellido</p><input name="apellido"></input></li>
				<li><p>Dni</p><input name="dni"></input></li>
				<li><p>Dirección</p><input name="direccion"></input></li>
				<li><p>Teléfono</p><input name="telefono"></input></li>
				<li><p>Fecha de incorporación</p><input name="fechaIncorporacion" type="date" value="<%Date today = Calendar.getInstance().getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");%><%= sdf.format(today) %>"></input></li>
					<% List<TipoPersonal> tipos = Arrays.asList(TipoPersonal.values());
					String html = "";
					for (TipoPersonal tipo : tipos ) {
						html += "<option>"+ tipo.name() +"</option>";
					}%>
				<li><p>Tipo</p><select name="tipo"><option><%= html %></option></select></li>
				<li><p>Usuario</p><input name="usuario"></input></li>
				<li><p>Contraseña</p><input name="contrasenia" type="password"></input></li><input name="modo" display='none' value="<%= request.getAttribute("modo") %>"></input>
				<li><button type="submit">Guardar</button></li>
			</ul>
		</form>	
	</body>
</html>
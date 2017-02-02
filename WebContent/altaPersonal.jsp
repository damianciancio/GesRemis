<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="business.entities.*"%>
<%@page import="business.logic.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta personal</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
</head>
	<body>
		<form action="ABMPersonal" method="post">
			<ul>
				<% 
				boolean emptyFields = true;
				Personal personaActual = new Personal();
				if(request.getParameter("id") != null){
					emptyFields = false;
					int id = Integer.parseInt(request.getParameter("id"));
					personaActual.setLegajo(id);
					personaActual = (new PersonalLogic()).getOne(personaActual);
				} 
				String nombre = "";
				String apellido = "";
				String dni = "";
				String direccion = "";
				String telefono = "";
				String usuario = "";
				String legajo = "";
				if(!emptyFields) {
					nombre = personaActual.getNombre();
					apellido = personaActual.getApellido();
					dni = personaActual.getDni();
					direccion = personaActual.getDireccion();
					telefono = personaActual.getTelefono();
					usuario = personaActual.getUsuario();
				} %>
				<li style="display:none"><p>Legajo</p><input type="text" name="legajo" value="<%=legajo %>"></input></li>
				<li><p>Nombre</p><input type="text" name="nombre" value="<%=nombre %>"></input></li>
				<li><p>Apellido</p><input name="apellido" value="<%=apellido %>"></input></li>
				<li><p>Dni</p><input name="dni" value="<%=dni %>"></input></li>
				<li><p>Dirección</p><input name="direccion" value="<%=direccion %>"></input></li>
				<li><p>Teléfono</p><input name="telefono" value="<%=telefono %>"></input></li>
				<li><p>Fecha de incorporación</p><input name="fechaIncorporacion" type="date" value="<%	Date date = null;
					if(emptyFields){
						date = Calendar.getInstance().getTime();
					}
					else {
						date = personaActual.getFechaIncorporacion();
					}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");%><%= sdf.format(date) %>"></input></li>
					<% List<TipoPersonal> tipos = Arrays.asList(TipoPersonal.values());
					String html = "";
					for (TipoPersonal tipo : tipos ) {
						if(!emptyFields) {
							if(tipo.equals(personaActual.getTipo())) {
								html += "<option selected=\"selected\">"+ tipo.name() +"</option>";
							}
						}
					}%>
				<li><p>Tipo</p><select name="tipo"><option></option><%= html %></select></li>
				<li><p>Usuario</p><input name="usuario" value="<%=usuario %>"></input></li>
				<li><p>Contraseña</p><input name="contrasenia" type="password"></input></li><div style="display:none"><input name="modo" value="<%= request.getAttribute("modo") %>"></input></div>
				<li><button type="submit">Guardar</button></li>
			</ul>
		</form>	
	</body>
</html>
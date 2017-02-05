<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@page import="business.entities.*"%>
<%@page import="business.logic.*"%>
<%@page import="util.web.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta personal</title>

<%= Html.getLinksStylesAndScripts() %>
</head>
	<body>
		<form id="form-abm-personal" action="ABMPersonal" method="post">
			<fieldset>
				<% 
				Personal usuarioActual = (Personal)request.getSession().getAttribute("usuarioActual");
				if(usuarioActual == null){
					response.sendRedirect("notPermission.html");
				} else if(usuarioActual.getTipo().name() != "Administrativo") {
					response.sendRedirect("notPermission.html");
				}
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
					legajo = String.valueOf(personaActual.getLegajo());
					nombre = personaActual.getNombre();
					apellido = personaActual.getApellido();
					dni = personaActual.getDni();
					direccion = personaActual.getDireccion();
					telefono = personaActual.getTelefono();
					usuario = personaActual.getUsuario();
				} %>

				<% 
				String modo = "NEW";
				if(!emptyFields){
					modo = "MODIFIED";
				} %>


				<div class="form-group" style="display:none"><label for="legajo">Legajo</label><input class="form-control" type="text" name="legajo" value="<%= legajo %>"><%= legajo %></input></div>
				<div class="form-group"><label for="nombre">Nombre</label><input class="form-control" type="text" name="nombre" value="<%=nombre %>" required></input></div>
				<div class="form-group"><label for="apellido">Apellido</label><input class="form-control" type="text" name="apellido" value="<%=apellido %>" required></input></div>
				<div class="form-group"><label for="dni">Dni</label><input class="form-control" type="text" name="dni" value="<%=dni %>" required></input></div>
				<div class="form-group"><label for="direccion">Direcci�n</label><input class="form-control" type="text" name="direccion" value="<%=direccion %>" required></input></div>
				<div class="form-group"><label for="telefono">Tel�fono</label><input class="form-control" class="form-control" type="text" name="telefono" value="<%=telefono %>" required></input></div>
				<%	Date date = null;
					if(emptyFields){
						date = Calendar.getInstance().getTime();
					}
					else {
						date = personaActual.getFechaIncorporacion();
					}
						SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				%>
				<div class="form-group input-append date" data-date="<%= sdf.format(date) %>" data-date-format="dd-mm-yyyy">
					<label for="fechaIncorporacion">Fecha de incorporaci�n</label>
					<input class="form-control" name="fechaIncorporacion" value="<%= sdf.format(date) %>" required></input></p>
					<% List<TipoPersonal> tipos = Arrays.asList(TipoPersonal.values());
					String html = "";
					for (TipoPersonal tipo : tipos ) {
						if(!emptyFields) {
							if(tipo.equals(personaActual.getTipo())) {
								html += "<option selected=\"selected\">"+ tipo.name() +"</option>";
							} else {
								html += "<option>"+ tipo.name() +"</option>";
							}
						} else {
							html += "<option>"+ tipo.name() +"</option>";
						} 
					}%>
				<div class="form-group"><label for="tipo">Tipo</label><select class="form-control" name="tipo"><option></option><%= html %></select></div>
				<div class="form-group"><label for="usuario">Usuario</label><input class="form-control" type="text" name="usuario" value="<%=usuario %>" required></input></div>
				
				<% 
					String htmlHideOldPass = "";
					if(modo.equals("NEW")){
						htmlHideOldPass = "style=\"display:none\"";
					}; 

				%><div class="form-group" <%= htmlHideOldPass %>><label for="antiguaContrasenia">Antigua contrase�a</label><input id="antiguaContrasenia" class="form-control" name="antiguaContrasenia" type="password" required></input></div>
				<div class="form-group"><label for="contrasenia">Contrase�a</label><input class="form-control" name="contrasenia" type="password" required></input></div>
				<div class="form-group"><label for="confirmarContrasenia">Confirmar contrase�a</label><input id="confirmarContrasenia" class="form-control" name="confirmarContrasenia" type="password" required></input></div>
				<div class="form-group" style="display:none"><input class="form-control" type="text" name="modo" value="<%= modo %>" ></input></div>
				</fieldset>
				<div><button type="submit">Guardar</button></div>
		</form>	
	</body>
</html>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="business.entities.Marca"%>
<%@page import="business.logic.PersonalLogic"%>
<%@page import="business.logic.MarcasLogic"%>
<%@page import="java.util.*"%>
<%@page import="business.entities.Remis"%>
<%@page import="business.logic.RemisLogic"%>
<%@page import="business.entities.Personal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="../bootstrap/js/bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-theme.css">
	<script type="text/javascript" src="../js/bootbox.min.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.js"></script>
	<script type="text/javascript" src="../datepicker/js/bootstrap-datepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="../datepicker/css/datepicker.css">
	<script type="text/javascript" src="../js/scripts.js?v=1.0.1"></script>
	<link rel="stylesheet" type="text/css" href="../css/styles.css">
<title>Insert title here</title>
</head>
<body>
	<form id="form-abm-remis" action="ABMRemis" method="post">
			<fieldset>
				<% 
				Personal usuarioActual = (Personal)request.getSession().getAttribute("usuarioActual");
				if(usuarioActual == null){
					response.sendRedirect("../notPermission.html");
				} else if(usuarioActual.getTipo().name() != "Administrativo") {
					response.sendRedirect("../notPermission.html");
				}
				boolean emptyFields = true;
				Remis remisActual = new Remis();
				if(request.getParameter("id") != null){
					emptyFields = false;
					int id = Integer.parseInt(request.getParameter("id"));
					remisActual.setId(id);
					remisActual = (new RemisLogic()).getOne(remisActual);
				} 
				String id = "";
				String patente = "";
				int idMarca = 0;
				String descModelo = "";
				Date fechaIncorporacion = null;
				Date fechaBaja = null;
				String anioModelo = "";
				Personal choferActual = null;
				if(!emptyFields) {
					id = String.valueOf(remisActual.getId());
					patente = remisActual.getPatente();
					idMarca = remisActual.getIdMarca();
					descModelo = remisActual.getDescModelo();
					fechaIncorporacion = remisActual.getFechaIncorporacion();
					fechaBaja = remisActual.getFechaBaja();
					anioModelo = String.valueOf(remisActual.getAnioModelo());
					choferActual = remisActual.getChoferActual();
					
				} %>

				<% 
				String modo = "NEW";
				if(!emptyFields){
					modo = "MODIFIED";
				} %>


				<div class="form-group" style="display:none"><label for="id">ID</label><input class="form-control" type="text" name="id" value="<%= id %>"><%= id %></input></div>

				<div class="form-group"><label for="patente">Patente</label><input class="form-control" type="text" name="patente" value="<%= patente %>"></input></div>

				<div class="form-group"><label for="anioModelo">Año modelo</label><input class="form-control" type="text" name="anioModelo" value="<%= anioModelo %>"></input></div>

				<%	Date date = null;
					if(emptyFields){
						date = Calendar.getInstance().getTime();
					}
					else {
						date = remisActual.getFechaIncorporacion();
					}
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				%>
				<div class="form-group input-append date" data-date="<%= sdf.format(date) %>" data-date-format="dd-mm-yyyy">
					<label for="fechaIncorporacion">Fecha de incorporación</label>
					<input class="form-control" type="date" name="fechaIncorporacion" value="<%= sdf.format(date) %>"></input></div>
				<%	Date dateLow = null;
					String fechaFormateada = ""; 
					if(!emptyFields && remisActual.getFechaBaja() != null){
						dateLow = remisActual.getFechaBaja();
						fechaFormateada = sdf.format(dateLow);
					}
					
				%>

				<div class="form-group input-append date" data-date="<%= fechaFormateada %>" data-date-format="dd-mm-yyyy">
					<label for="fechaBaja">Fecha de baja</label>
					<input type="date" class="form-control" name="fechaBaja" value="<%= fechaFormateada %>" ></input></p>
					<% ArrayList<Marca> marcas = (new MarcasLogic()).getAll();
					String html = "";
					for (Marca marca : marcas ) {
						if(!emptyFields) {
							if(marca.getId() == remisActual.getIdMarca()) {
								html += "<option selected=\"selected\" value=\"" + marca.getId()+"\">"+ marca.getDescripcion() +"</option>";
							} else {
								html += "<option value=\"" + marca.getId()+"\">"+ marca.getDescripcion() +"</option>";
							}
						} else {
							html += "<option value=\"" + marca.getId()+"\">"+ marca.getDescripcion() +"</option>";
						} 
					}%>
				
				<div class="form-group"><label for="marca">Marca</label><select class="form-control" name="marca"><option value="0"></option><%= html %></select>
				</div>
				
					<% ArrayList<Personal> personal = (new PersonalLogic()).getAllChoferesWithoutRemis();
					html = "";
						personal.add(remisActual.getChoferActual());
						String originalValue = "0";
					for (Personal chofer : personal ) {
						if(!emptyFields) {
							if(chofer.equals(remisActual.getChoferActual())) {
								html += "<option selected=\"selected\" value=\"" + chofer.getLegajo()+"\">"+ chofer.getNombreApellido() +"</option>";
								originalValue = String.valueOf(chofer.getLegajo());
							} else {
								html += "<option value=\"" + chofer.getLegajo()+"\">"+ chofer.getNombreApellido() +"</option>";
							}
						} else {
							html += "<option value=\"" + chofer.getLegajo()+"\">"+ chofer.getNombreApellido() +"</option>";
						} 
					}%>
				<div class="form-group"><label for="choferActual">Chofer asignado</label><select class="form-control" id="choferActual" original-value="<%= originalValue %>" name="choferActual"><option value="0"></option><%= html %></select></div>

				
				<%	Date fechaDesdeChoferActual = null;
					if(!emptyFields && remisActual.getFechaDesdeChoferActual() != null){
						fechaDesdeChoferActual = remisActual.getFechaDesdeChoferActual();
					}
					else {
						fechaDesdeChoferActual = Calendar.getInstance().getTime();
					}
				%>
				<div class="form-group input-append date" id="fechaDesdeChoferActual" data-date="<%= sdf.format(fechaDesdeChoferActual) %>" data-date-format="dd-mm-yyyy">
					<label for="fechaDesdeChoferActual">Fecha de incorporación</label>
					<input class="form-control" type="date" name="fechaDesdeChoferActual" value="<%= sdf.format(fechaDesdeChoferActual) %>" ></input></div>

				
				<div class="form-group" style="display:none"><input class="form-control" type="text" name="modo" value="<%= modo %>" ></input></div>
				</fieldset>
				<div><button type="submit">Guardar</button></div>
		</form>
</body>
</html>
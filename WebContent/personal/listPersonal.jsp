<%@page import="util.exception.ConsultaSQLException"%>
<%@page import="util.exception.DataBaseConnectionException"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.logic.PersonalLogic"%>
<%@page import="business.entities.Personal"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.js"></script>
	<script type="text/javascript" src="../js/jquery.validate.js"></script>
	<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="../bootstrap/css/bootstrap-theme.css">
	<link rel="stylesheet" type="text/css" href="../bootstrap/js/bootstrap.js">
	<link rel="stylesheet" type="text/css" href="../bootstrap/js/npm.js">
	<link rel="stylesheet" type="text/css" href="../datepicker/css/datepicker.css">
	<script type="text/javascript" src="../datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="../jasny-bootstrap/js/jasny-bootstrap.js"></script>
	<link rel="stylesheet" type="text/css" href="../jasny-bootstrap/css/jasny-bootstrap.css">
	<script type="text/javascript" src="../js/scripts.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/styles.css">
<title>Listado de personal</title>
</head>
<body>
<div class="main-wrapper-generic">
	<% 
	Personal usuarioActual = (Personal)request.getSession().getAttribute("usuarioActual");
	if(usuarioActual == null){
		response.sendRedirect("../notPermission.html");
	} else if(usuarioActual.getTipo().name() != "Administrativo") {
		response.sendRedirect("../notPermission.html");
	}
	
	ArrayList<Personal> personal;
	String html = "";
	try {
		personal = (new PersonalLogic()).getAll();
		html = "<div class=\"responsive-table\">\n";
		html += "<table class=\"table table-hover\">\n<thead>\n";
		html += "<tr>\n";
		html += "<th>Legajo</th><th>DNI</th><th>Apellido y nombre</th><th>Tipo</th>\n</tr></thead><tbody data-link=\"row\" class=\"rowlink\">";
		for(Personal per : personal) {
			html+= "<tr>";
			html+= "<td><a href=\"altaPersonal.jsp?id="+ per.getLegajo()+"\">";
			html+= per.getLegajo() + "</td>";
			html+="<td>" + per.getDni() + "</td>";
			html+="<td>" + per.getNombreApellido() + "</td>";
			html+="<td>" + per.getTipo().name() + "</td>";
			html+="</tr>";
		}
		html+="</tbody>";
	} catch (Exception e) {
		html += e.getMessage();
	} %><%= html %>
	</div>
</body>
</html>
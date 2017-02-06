<%@page import="business.entities.Personal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="business.logic.RemisLogic"%>
<%@page import="business.entities.Remis"%>
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
<title>Listado de remises</title>
</head>
<body>
	<% 
	Personal usuarioActual = (Personal)request.getSession().getAttribute("usuarioActual");
	if(usuarioActual == null){
		response.sendRedirect("../notPermission.html");
	} else if(usuarioActual.getTipo().name() != "Administrativo") {
		response.sendRedirect("../notPermission.html");
	}
	
	ArrayList<Remis> flota;
	String html = "";
	try {
		flota = (new RemisLogic()).getAll();
		html = "<div class=\"responsive-table\">\n";
		html += "<table class=\"table table-hover\">\n<thead>\n";
		html += "<tr>\n";
		html += "<th>Patente</th><th>Marca</th><th>Modelo</th><th>Chofer Actual</th>\n</tr></thead><tbody data-link=\"row\" class=\"rowlink\">";
		for(Remis r : flota) {
			html += "<tr>\n";
			html += "<a href=\"altaRemis.jsp?id="+r.getId()+"\">";
			html += "<td>"+ r.getPatente() +"</td></a>";
			html += "<td>"+ r.getDescMarca() +"</td>";
			html += "<td>"+ r.getDescModelo() +"</td>";
			if(r.getChoferActual().getLegajo() != 0){
				html += "<td>"+ r.getChoferActual().getApellido()+", "+r.getChoferActual().getNombre() +"</td>";
			} else {
				html += "<td>No tiene asignado</td>";
			}
			html += "</tr>";
		}
		html += "</body>";
	} catch (Exception e) {
		html += "hola";
	} %><%= html %>
</body>
</html>
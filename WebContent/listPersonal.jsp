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
<script type="text/javascript" src="scripts.js"></script>
<title>Listado de personal</title>
</head>
<body>
	<% 
	Personal usuarioActual = (Personal)request.getSession().getAttribute("usuarioActual");
	if(usuarioActual == null){
		response.sendRedirect("notPermission.html");
	} else if(usuarioActual.getTipo().name() != "Administrativo") {
		response.sendRedirect("notPermission.html");
	}
	
	ArrayList<Personal> personal;
	String html = "";
	try {
		personal = (new PersonalLogic()).getAll();
		html = "<ul>";
		for(Personal per : personal) {
			html += "<li id="+ per.getLegajo() +"><a href=\"altaPersonal.jsp?id="+ per.getLegajo() +"\">"+ per.getApellido()+", "+per.getNombre()+"</a></li>"; 
		}
	} catch (Exception e) {
		html += e.getMessage();
	} %><%= html %>
</body>
</html>
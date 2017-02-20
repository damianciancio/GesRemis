<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.js"></script>
<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="bootstrap/js/bootstrap.js">
<link rel="stylesheet" type="text/css" href="bootstrap/js/npm.js">
<link rel="stylesheet" type="text/css" href="datepicker/css/datepicker.css">
<script type="text/javascript" src="datepicker/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="timepicker/js/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?key=AIzaSyC9DvGnnWKamblT3PSybZVKDcE9fwazmq4&libraries=places&sensor=false" ></script>
<link rel="stylesheet" type="text/css" href="timepicker/css/bootstrap-timepicker.css">
  
<script type="text/javascript" src="js/scripts.js?v=2"></script>
<link rel="stylesheet" type="text/css" href="css/styles.css?v=2">
<title>Asignación de viajes</title>
</head>
<body>
<section>
	<div id="map"></div>
</section>

    <div id="form-new-travel-wrapper">
    	<form id="form-new-travel" method="post" action="NewTravel">
    		<div id="new-travel-first-step">
	    		<h3>Direcci&oacute;n de Origen</h3>
	    		<div class="form-group">
	          		<input type="text" class="form-control address-search" name="direccionOrigen" id="direccionOrigen">
	    		</div>
	    		<h3>Direcci&oacute;n de Destino</h3>
	    		<div class="form-group">
	    			<input type="text" name="direccionDestino" id="direccionDestino" class="form-control address-search">
	    		</div>
	    		<h3>Fecha y hora</h3>
	    		<%	Date date = null;
					date = Calendar.getInstance().getTime();
					SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
				%>
				<div class="form-group input-append" >
					<label for="fechaViaje">Fecha</label>
					<input class="form-control datepicker form-control" type="text" name="fechaViaje" value="<%= sdf.format(date) %>" >
				</div>
				<div class="input-group bootstrap-timepicker timepicker">
					<label for="horaViaje">Hora</label>
		            	<input id="horaViaje" name="horaViaje" type="text" class="form-control input-small">
		            <span class="input-group-addon">
		            	<i class="glyphicon glyphicon-time"></i>
		            </span>
	    		</div>
	    		<div class="button-group">
    				<button type="button" id="btn-cancelar">Cancelar</button>
    				<button type="button" id="btn-siguiente">Siguiente</button>
    			</div>
    		</div>
    		<div style="display: none" id="new-travel-second-step">
    			<label id="lblOrigen">
    			</label>
    			<input type="text" name="latOrigen" >
    			<input type="text" name="lngOrigen" >
    			<input type="text" name="CPOrigen" >
    			<label id="lblDestino"></label>
    			<input type="text" name="latDestino" >
    			<input type="text" name="lngDestino" >
    			<input type="text" name="CPDestino" >
    		
	    		<div>
	    			<ul>
	    				
	    			</ul>
	    		</div>
    		</div>
    		<button type="submit">Listo</button>
    	</form>

	        </div>
  </body>
</html>

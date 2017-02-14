<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/styles.css?v=1"></link>
<title>Insert title here</title>
</head>
<body>
	<div id="main-wrapper">
		<div id="map">
		</div>
		<div id="travel-form-wrapper">
			<h3>
				<label>Origen</label>
			</h3>
				<div>
					<label>Localidad</label>
					<select></select>
				</div>
				<div>
					<label>Direccion</label>
					<input type="text"></input>
				</div>
				<h3>
					<label>Destino</label>
				</h3>
				<div>
					<label>Localidad</label>
					<select></select>
				</div>
				<div>
					<label>Direccion</label>
					<input type="text"></input>
				</div>
				<div class="float-right">
					<button type="submit" id="btnAceptar" >Aceptar</input>
					<button id="btnCancelar" >Cancelar</input>
				</div>
		</div>
	</div>
</body>
</html>
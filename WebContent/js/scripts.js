$(document).ready( function(){
	validarFormAbmPersonal();
	$('.datepicker').datepicker();
	altaRemisBehavior();
});
function validarFormAbmPersonal(){
	if ($('input[name="modo"]').val() == 'NEW') {
		validarFormAbmPersonalAlta();
	} else if ($('input[name="modo"]').val() == 'MODIFIED') {
		validarFormAbmPersonalModificacion();
	}
}
function validarFormAbmPersonalAlta(){
	$("#form-abm-personal").validate({
		rules: {
			nombre: "required",
			apellido: "required",
			direccion: "required",
			telefono: "required",
			confirmarContrasenia: "required",
			contrasenia: {
				required: true,
				minlength: 12,
				equalsTo: "#comprobarContrasenia"
			},
			usuario: {
				required: true,
				minlength: 6
			}
		},
		messages: {
			nombre: "Por favor, ingrese el nombre",
			apellido: "Por favor, ingrese el apellido",
			direccion: "Por favor, ingrese direccion",
			telefono: "Por favor, ingrese telefono",
			contrasenia: {
				required: "Por favor, ingrese contraseña",
				minlength: "Debe ser de al menos 12 caracteres",
				equalsTo: "Las contraseñas no coinciden"
			},
			confirmarContrasenia: {
				required: "Por favor, repita contraseña",
			},
			usuario: {
				required: "Por favor, ingrese nombre de usuario",
				minlength: "Debe ser de al menos 6 caracteres"
			}
		}
	});
}

function validarFormAbmPersonalModificacion(){
	$("#form-abm-personal").validate({
		rules: {
			legajo: "required",
			nombre: "required",
			apellido: "required",
			direccion: "required",
			telefono: "required",
			antiguaContrasenia: "required",
			confirmarContrasenia: "required",
			contrasenia: {
				required: true,
				minlength: 12,
				equalsTo: "#comprobarContrasenia"
			},
			usuario: {
				required: true,
				minlength: 6
			}
		},
		messages: {
			nombre: "Por favor, ingrese el nombre",
			apellido: "Por favor, ingrese el apellido",
			direccion: "Por favor, ingrese direccion",
			telefono: "Por favor, ingrese telefono",
			antiguaContrasenia: "Por favor, ingrese su contraseña anterior",
			contrasenia: {
				required: "Por favor, ingrese contraseña",
				minlength: "Debe ser de al menos 12 caracteres",
				equalsTo: "Las contraseñas no coinciden"
			},
			confirmarContrasenia: {
				required: "Por favor, repita contraseña",
			},
			usuario: {
				required: "Por favor, ingrese nombre de usuario",
				minlength: "Debe ser de al menos 6 caracteres"
			}
		}
	});
}

/*abmremis */
function altaRemisBehavior(){
	$("#fechaDesdeChoferActual").hide();
	var fechaIncorporacionOriginal = $('#fechaIncorporacion').val();
	$("#choferActual").on("change", function(){
		if($(this).val() != $(this).attr("original-value")){
			$("#fechaDesdeChoferActual").val(fechaIncorporacionOriginal);
			$("#fechaDesdeChoferActual").show();
		} else {
			$("#fechaDesdeChoferActual").val(new Date());
			$("#fechaDesdeChoferActual").hide();
		}
	});
	$("#form-abm-remis").submit(function(){
		return validarABMRemis();
	});

}

function validarABMRemis(){
	var mode = $('[name="modo"]').val();
	var patente = $('[name="patente"]').val();
	var anioModelo = $('[name="anioModelo"]').val();
	var fechaIncorporacion = $('[name="fechaIncorporacion"]').val();
	var marca = $('select [name="marca"]').val();
	var choferActual = $('select [name="choferActual"]').val();
	var choferActualOriginal = $("#choferActual").attr("original-value");
	var fechaDesdeChoferActual = $('[name="fechaDesdeChoferActual"]').val();
	var id = $('[name="id"]').val();

	if (!validatePatente(patente)) {
		bootbox.alert("Ingrese una patente válida");
		return false;
	}
	if (patente == '') {
		bootbox.alert("Ingrese una patente.");
		return false;
	}
	if (anioModelo == '') {
		bootbox.alert("Ingrese el año del modelo.");
		return false;
	}
	if (fechaIncorporacion == '') {
		bootbox.alert("Ingrese fecha de incorporación.");
		return false;
	}
	if(marca == '0'){
		bootbox.alert("Seleccione la marca.");
		return false;
	}
	if (choferActual != '0' && choferActual != choferActualOriginal && fechaDesdeChoferActual == '') {
		bootbox.alert("Ha cambiado de chofer, ingrese la fecha desde la cual lo conduce");
		return false;
	}
	if (modo == 'MODIFIED' && ID == '') {
		return false;
	}
	return true;
}

function validatePatente(value) { 
   	return /^[A-Z]{3}\d{3}$/.test(value); 
}
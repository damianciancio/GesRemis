var $j = jQuery.noConflict();

$j(document).ready( function(){
	validarFormAbmPersonal();
	$j(function(){
  		$j( '.datepicker' ).datepicker({
  			dateFormat: "mm-dd-yyy"
  		});
  	});
	altaRemisBehavior();
});

function validarFormAbmPersonal(){
	if ($j('input[name="modo"]').val() == 'NEW') {
		validarFormAbmPersonalAlta();
	} else if ($j('input[name="modo"]').val() == 'MODIFIED') {
		validarFormAbmPersonalModificacion();
	}
}
function validarFormAbmPersonalAlta(){
	$j("#form-abm-personal").validate({
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
	$j("#form-abm-personal").validate({
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
	$j("#fechaDesdeChoferActual").hide();
	var fechaIncorporacionOriginal = $j('#fechaIncorporacion').val();
	$j("#choferActual").on("change", function(){
		if($j(this).val() != $j(this).attr("original-value")){
			$j("#fechaDesdeChoferActual").val(fechaIncorporacionOriginal);
			$j("#fechaDesdeChoferActual").show();
		} else {
			$j("#fechaDesdeChoferActual").val(new Date());
			$j("#fechaDesdeChoferActual").hide();
		}
	});
	$j("#form-abm-remis").submit(function(){
		return validarABMRemis();
	});

}

function validarABMRemis(){
	var mode = $j('[name="modo"]').val();
	var patente = $j('[name="patente"]').val();
	var anioModelo = $j('[name="anioModelo"]').val();
	var fechaIncorporacion = $j('[name="fechaIncorporacion"]').val();
	var marca = $j('select [name="marca"]').val();
	var choferActual = $j('select [name="choferActual"]').val();
	var choferActualOriginal = $j("#choferActual").attr("original-value");
	var fechaDesdeChoferActual = $j('[name="fechaDesdeChoferActual"]').val();
	var id = $j('[name="id"]').val();

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
	if (mode == 'MODIFIED' && ID == '') {
		return false;
	}
	return true;
}

function validatePatente(value) { 
   	return /^[A-Z]{3}\d{3}$/.test(value); 
}
$(document).ready( function(){
	validarFormAbmPersonal();
	$('.datepicker').datepicker();
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


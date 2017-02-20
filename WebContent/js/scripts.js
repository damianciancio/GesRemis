var $j = jQuery.noConflict();

$j(document).ready( function(){
	validarFormAbmPersonal();
	$j(function(){
  		$j( '.datepicker' ).datepicker({
  			dateFormat: "mm-dd-yyy"
  		});
  		$j('#horaViaje').timepicker();
  	});
	altaRemisBehavior();
	initMap();
	/*$j('input').on('keyup', function(){
	var request = {
		query: $j(this).val()
	};

	service = new google.maps.places.PlacesService(map);
	service.textSearch(request,callback);
});*/

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
			var choferActual = $j('select [name="choferActual"]').val();
			var choferActualOriginal = $j("#choferActual").attr("original-value");

			if (choferActual != choferActualOriginal) {
				$j('input [name="cambiaChofer"]').val("1");
			} else {
				$j('input [name="cambiaChofer"]').val("0");
			}	
			return validarABMRemis();
		});

	}

	function validarABMRemis(){
		var mode = $j('[name="modo"]').val();
		var patente = $j('[name="patente"]').val();
		var anioModelo = $j('[name="anioModelo"]').val();
		var fechaIncorporacion = $j('[name="fechaIncorporacion"]').val();
		var marca = $j('select [name="marca"]').val();
		var descModelo = $j('select [name="descModelo"]').val();
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
		if (descModelo == '') {
			bootbox.alert("Ingrese el modelo.");
		}
		if (choferActual != '0' && choferActual != choferActualOriginal && fechaDesdeChoferActual == '') {
			bootbox.alert("Ha cambiado de chofer, ingrese la fecha desde la cual lo conduce");
			return false;
		}
		if (mode == 'MODIFIED' && id == '') {
			return false;
		}
		return true;
	}

	function validatePatente(value) { 
	   	return /^[A-Z]{3}\d{3}$j/.test(value); 
	}
	

var map;
var autocompleteOrigen;
var autcompleteDestino;
var markerOrigen;
var markerDestino;

function initMap() {
  map = new google.maps.Map(document.getElementById('map'), {
    center: {lat: -32.8, lng: -60.8},
    zoom: 11
  });

  var inputOrigen = document.getElementById('direccionOrigen');
  var inputDestino = document.getElementById('direccionDestino');
  var defaultBounds = new google.maps.LatLngBounds(
  	new google.maps.LatLng(-33.600082, -61.489761),
  	new google.maps.LatLng(-32.257430, -60.232593));
  var options = {
  	bounds: defaultBounds
  };

  autocompleteOrigen = new google.maps.places.Autocomplete(inputOrigen, options);
  autocompleteOrigen.addListener('place_changed', 
  	function(){
  		setPlace(autocompleteOrigen.getPlace(),'Origen');
  	}
  );
  autocompleteDestino = new google.maps.places.Autocomplete(inputDestino, options);
  autocompleteDestino.addListener('place_changed', 
  	function(){
  		setPlace(autocompleteDestino.getPlace(),'Destino');
  	}
  );

  $j('#btn-siguiente').on('click',function(){
  	if (validateFirstStepNewTravel()) {
	  	$j('#new-travel-first-step').css('display','none');
	  	$j('#new-travel-second-step').css('display','block');
  	}
  });
}



function setPlace(place, type){
	
	var ciudad, 
		calle, 
		numero, 
		latitud, 
		longitud, 
		codPost;

	for (var i = 0; i < place.address_components.length; i++) {
			if(place.address_components[i].types[0] == 'street_number')
				numero = place.address_components[i]['long_name'];
			if (place.address_components[i].types[0] == 'locality') {
				ciudad = place.address_components[i]['long_name'];
			}
			if (place.address_components[i].types[0] == 'route') {
				calle = place.address_components[i]['long_name'];
			}
			if (place.address_components[i].types[0] == 'postal_code') {
				codPost = place.address_components[i]['short_name'];
			}
	}

	latitud = place.geometry.location.lat();
	longitud = place.geometry.location.lng();

	if (type == 'Origen') {
		markerOrigen = new google.maps.Marker({
			position: place.geometry.location,
			title: 'Origen',
			map: map
		});
		$j('#lblOrigen').html(calle + ' ' + numero + ', ' +ciudad);
		$j('input[name="latOrigen"]').val(latitud);
		$j('input[name="lngOrigen"]').val(longitud);
		$j('input[name="CPOrigen"').val(codPost);
	} else if (type == 'Destino') {
		markerDestino = new google.maps.Marker({
			position: place.geometry.location,
			title: 'Destino',
			map: map
		});
		$j('#lblDestino').html(calle + ' ' + numero + ', ' +ciudad);
		$j('input[name="latDestino"]').val(latitud);
		$j('input[name="lngDestino"]').val(longitud);
		$j('input[name="CPDestino"').val(codPost);
	}

}

function validateFirstStepNewTravel(){
	valido = true;
	if($j('input[name="latOrigen"]').val() == ''){
		valido = false;
		return false;
	} else if ($j('input[name="lngOrigen"]') == '') {
		valido = false;
		return false;
	} else if($j('input[name="latDestino"]').val() == ''){
		valido = false;
		return false;
	} else if ($j('input[name="lngDestino"]') == '') {
		valido = false;
		return false;
	}
	return valido;
}
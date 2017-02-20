	var map ;

function initMap() {
        // Create a map object and specify the DOM element for display.
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -32.818831, lng: -60.720646},
          scrollwheel: false,
          zoom: 14
        });

      		var input = $j('.address-search');
      		var autocomplete = new google.maps.places.Autocomplete(input);
  			autocomplete.bindTo('bounds', map);

  			map.controls[google.maps.ControlPosition.TOP_LEFT].push(input[0]);

  			var infowindow = new google.maps.InfoWindow();
  			var marker = new google.maps.Marker({
    			map: map
  			});
  			google.maps.event.addListener(marker, 'click', function() {
    			infowindow.open(map, marker);
  			});

  // Get the full place details when the user selects a place from the
  // list of suggestions.
  			google.maps.event.addListener(autocomplete, 'place_changed', function() {
    			infowindow.close();
    			var placeAuto = autocomplete.getPlace();
    			if (!placeAuto.geometry) {
      				return;
    			}

	    		if (placeAuto.geometry.viewport) {
	      			map.fitBounds(placeAuto.geometry.viewport);
	    		} else {
	      			map.setCenter(placeAuto.geometry.location);
	      			map.setZoom(17);
	    		}

			    // Set the position of the marker using the place ID and location.
			    marker.setPlace(/** @type {!google.maps.Place} */ ({
			      placeId: placeAuto.place_id,
			      location: placeAuto.geometry.location
			    }));
			    marker.setVisible(true);

			    infowindow.setContent('<div><strong>' + placeAuto.name + '</strong><br>' +
			        'Place ID: ' + placeAuto.place_id + '<br>' +
			        placeAuto.formatted_address + '</div>');
			    infowindow.open(map, marker);
		  	});
      }

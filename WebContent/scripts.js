

$(document).ready( function() {
	editPersonal();
});

function editPersonal(){
	$(".edit").on("click", function(){
		var id = $(this).parent().attr("id");
		$(window).location.replace("altaPersonal.jsp");
	})
}
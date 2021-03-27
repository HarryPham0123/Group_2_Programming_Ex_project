$(function () {
	$(".hide-btn").click(function() {
		$(".table-container").slideUp()
	})
	$(".show-btn").click(function() {
		$(".table-container").slideDown()
	})
	$("body").one("click",".upload", function () {
        	getData();
    	});
})
function getData(){
    $.ajax({
		type: 'GET',
		url: 'http://localhost:8080/survey/api/general',
		success: function(data) {
		data.map(val=>{
		    $(`<tr>
			<td>${val.AYcode}</td>
			<td>${val.Ccode}</td>
			<td>${val.size}</td>
			<td>${val.Mname}</td>
			<td>${val.Pname}</td>
			<td>${val.Fname}</td>
		    <tr>`).appendTo(".table-list");
		    })
			},
	    		error:function () { 
			alert("Error loading order");
			}
		});
}

$(function () {
    $("body").one("click",".upload", function () {
        getData();
    });
});
$(function () {
    $("body").on("click",".hide-btn", function () {
        $(".container").css("display", "none");

    });
});
$(function () {
    $("body").on("click",".show-btn", function () {
        $(".container").css("display", "block");
    });
});
function getData(){
    $.ajax({
		type: 'GET',
		url: 'mockdata.txt',
		success: function(data) {
            	let myJson=JSON.parse(data)
		myJson.map(val=>{
		    $(`<tr>
			<td>${val.AYcode}</td>
			<td>${val.Ccode}</td>
			<td>${val.size}</td>
			<td>${val.Mname}</td>
			<td>${val.Pname}</td>
			<td>${val.Fname}</td>
		    <//tr>`).appendTo(".table-list");
		    })
			},
	    		error:function () { 
			alert("Error loading order");
			}
		});
}

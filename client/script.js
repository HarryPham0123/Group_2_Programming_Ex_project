var filterList = [];
$(function () {
	$(".hide-btn").click(function() {
		$(".table-container").slideUp()
	})
	$(".show-btn").click(function() {
		$(".table-container").slideDown()
	})
	$("body").one("click",".update", function () {
        	getData();
  	})
	$("body").on("input", "#search-bar", function() {
		var searchValue = $("#search-bar").val().toLowerCase();
		filterList.remove();
		Array.from(filterList).map((row, index) => {
			elText = filterList[index].innerText.toLowerCase();
			if(elText.length !== 0 && elText.includes(searchValue)) {
				$(".table-list").append(filterList[index])
			}
		})

	})
})


function getData(){
    $.ajax({
		type: 'GET',
		url: 'http://localhost:8080/survey/api/general',
		success: function(data) {
			dataRender(data);
		},
		error:function () {
			alert("Error loading order");
			},
		}).always(function() {
			//Get the filtered list
			filterList = $(".table-list tr").slice(1);
	});
}

function dataRender(data) {
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
}

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
            //let obj=JSON.stringify(data);
            let myJson=JSON.parse(data)
            //console.log(obj[0])
            console.log(myJson)
			myJson.map(val=>{
            // $(`<div class="col-sm-3">
            //     <h5>Academic Year Code:${val.AYcode}</h5>
            //     <h5>Faculty code:${val.Fcode}</h5>
            //     <h5>PM Code:${val.PMcode}</h5>
            //     <h5>Module code:${val.Mcode}</h5>
            //     <h5>Program Code:${val.Pcode}</h5>
            //     <h5>Class code:${val.Ccode}</h5>
            //     <h5>Size:${val.size}</h5>
            //     <h5>Semester code:${val.Scode}</h5>
            //     <h5>Module Name:${val.Mname}</h5>
            //     <h5>Program name:${val.Pname}</h5>
            //     <h5>Faculty name:${val.Fname}</h5>
            //     <h5>FAY code:${val.FAYcode}</h5>
            // </div>`).appendTo(".row");
            $(`<tr>
                <td>${val.AYcode}</td>
                <td>${val.Ccode}</td>
                <td>${val.size}</td>
                <td>${val.Mname}</td>
                <td>${val.Pname}</td>
                <td>${val.Fname}</td>
            <//tr>`).appendTo(".table-list");
            })
		}
	});
}

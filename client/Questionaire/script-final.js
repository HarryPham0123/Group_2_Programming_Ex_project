$(function () {
    $("input").empty();
    $("body").one("click",".sel-class", function () {
        getData();
    });
    $(".sel-class").change(function (e) {
         const newVal=getVal();
         addInput(newVal)
         changLec(newVal)
    }); 
    $('.submit').click(function() {
        const getQuestion=document.getElementsByClassName("question")
        if($(".sel-class option:selected").val()=="starter"){
            alert("Please select the class option")
        }
        else if($(".sel-lec option:selected").val()=="starter"){
            alert("Please select the lecturer option")
        }
        if(!$('input[name=attend]:checked').length > 0){
            alert("Please fill in your attendance")
        }
        if(!$('input[name=gender]:checked').length > 0){
            alert("Please fill in your gender")
        }
        for(let i =1;i<=getQuestion.length;i++){
            if (!$('input[name=question'+`${i}`+']:checked').length > 0) {
                alert("Please fill in the question"+`${i}`);
            }
        }
        for(let i =1;i<=getQuestion.length;i++){
            if (!$('input[name=question'+`${i}`+']:checked').length > 0) {
                alert("Please fill in the question"+`${i}`); 
            }
        }
     });
});
function getData(){
    $.ajax({
		type: 'GET',
		url: 'http://localhost:8080/survey/api/general',
		success: function(data) {
			data.map(val=>{
            $(`
                <option>${val.Ccode}</option>
            `).appendTo(".sel-class");
            })
		},
        error:function () {
            alert("Error loading order");
        }
	});
}
function getVal(){
	const selectedVal=$( ".sel-class option:selected" ).text();
    return selectedVal;
}
function addInput(input){
const api='http://localhost:8080/survey/api/general';
    $.get(api,function(data){
        $(input).empty();
        const obj=data
		const newData=obj.filter(val=>{
            return val.Ccode.includes(input)
        })
            $(".acad-year").val(newData[0].AYcode);
            $(".semester").val(newData[0].size);
            $(".faculty").val(newData[0].Fname);
            $(".module").val(newData[0].Mname);
            $(".program").val(newData[0].Pname);
        //});

    });
}
function changLec(input){
    const api='http://localhost:8080/survey/api/general';
        $.get(api,function(data){
            $(input).empty();
            const obj=data
            const newData=obj.filter(val=>{
                return val.Ccode.includes(input)
            })
            const newObj=newData.filter(vals=>{
                return vals.Pname
            })
            newObj.map(value=>{
                $(`
                <option>${value.Lname}</option>
            `).appendTo(".sel-lec");
            })
        });
    }

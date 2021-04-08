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
    $('.submit-btn').click(function() {
        submitQuestionnaire()
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
function submitQuestionnaire(){
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
	const attendance = document.querySelector('input[name=attend]:checked').value;
	const gender = document.querySelector('input[name=gender]:checked').value;
	const q1 =  document.querySelector('input[name=question1]:checked').value;
	const q2 =  document.querySelector('input[name=question2]:checked').value;
	const q3 =  document.querySelector('input[name=question3]:checked').value;
	const q4 =  document.querySelector('input[name=question4]:checked').value;
	const q5 =  document.querySelector('input[name=question5]:checked').value;
	const q6 =  document.querySelector('input[name=question6]:checked').value;
	const q7 =  document.querySelector('input[name=question7]:checked').value;
	const q8 =  document.querySelector('input[name=question8]:checked').value;
	const q9 =  document.querySelector('input[name=question9]:checked').value;
	const q10 =  document.querySelector('input[name=question10]:checked').value;
	const q11 =  document.querySelector('input[name=question11]:checked').value;
	const q12 =  document.querySelector('input[name=question12]:checked').value;
	const q13 =  document.querySelector('input[name=question13]:checked').value;
	const q14 =  document.querySelector('input[name=question14]:checked').value;
	const q15 =  document.querySelector('input[name=question15]:checked').value;
	const q16 =  document.querySelector('input[name=question16]:checked').value;
	const q17 =  document.querySelector('input[name=question17]:checked').value;
	const q18 = document.getElementById("comment").value;
	let PostJson = {
		"attendance" : attendance,
		"gender" : gender,
		"question_1" : q1,
		"question_2" : q2,
		"question_3" : q3,
		"question_4" : q4,
		"question_5" : q5,
		"question_6" : q6,
		"question_7" : q7,
		"question_8" : q8,
		"question_9" : q9,
		"question_10" : q10,
 		"question_11" : q11,
		"question_12" : q12,
		"question_13" : q13,
		"question_14" : q14,
		"question_15" : q15,
		"question_16" : q16,
		"question_17" : q17,
		"question_18" : q18,
	}
	$.ajax({
		type: 'POST',
		contentType: "application/json",
		url: "survey/api/questionnaire",
		data: JSON.stringify(PostJson),
		dataType: "text",
		error: function(e) {
		alert("Something wrong");
 		   console.log(e);
 		 },
		success : function(data, textStatus, jqXHR){
			alert("Submit successful");
			}
	})
}

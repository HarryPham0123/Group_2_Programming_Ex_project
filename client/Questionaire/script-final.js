/*Created by Ha Xuan Huy, Vu Viet Hoang, Nguyen Dang Khoa
* This script is the implementation of questionnaire page
* */
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
        var isNotValid = answerValidator();

        if (isNotValid) {
            alert(isNotValid);
            return;
        }

        submitQuestionnaire();
     });
});

function answerValidator() {
    const questions = $(".question");
    var errorMessage = "";

    if($(".sel-class option:selected").val() == "starter"){
        errorMessage += "Please select the class option \n";
    }

    if($(".sel-lec option:selected").val() == "starter"){
        errorMessage += "Please select the lecturer option \n";
    }

    if(!$('input[name=attend]:checked')){
        errorMessage += "Please fill in your attendance \n";
    }

    if(!$('input[name=gender]:checked')){
        errorMessage += "Please fill in your gender \n";
    }

    for(let i = 1 ; i <= questions.length; i++) {
        if ($(`input[name=question${i}]:checked`).length == 0) {
            errorMessage += `Please fill in the question ${i} \n`;
        }
    }

    return errorMessage;
}

function gatherQuestionAnswer() {
    const questions = $('.question');
    var questionList = [];

    //Attendance question
    questionList.push({
        "question": "attendance",
        "answer": String($("input[name=attendance]:checked").val())
    });

    //Gender question
    questionList.push({
        "question": "gender",
        "answer": String($("input[name=gender]:checked").val())
    });

    //Questions
    for(let i = 1; i <= questions.length ; i++){
        questionList.push({
            "question": String(i),
            "answer": parseInt($(`input[name=question${i}]:checked`).val())
        })
    }

    //Additional comment session
    questionList.push({
        "question": "18",
        "answer": String($("textarea[name=question18]").val())
    })

    return questionList;
}
function submitQuestionnaire() {
    var requestBody = {
        lcode: String($(".sel-class option:selected").val()),
        ccode: String($(".sel-lec option:selected").val()),
        question_list: gatherQuestionAnswer()
    };
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/survey/api/questionnaire",
        contentType: "application/json",
        data: JSON.stringify(requestBody),
        success: function(data) {
            alert(data.message.string);
        },
        error: function(data) {
            console.log(data.message.string)
        }
    })
    return requestBody;
}
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
            $(".semester").val(newData[0].Scode);
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
                <option value=${value.Lcode}>${value.Lname}</option>
            `).appendTo(".sel-lec");
            })
        });
    }

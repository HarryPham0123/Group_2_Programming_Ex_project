$(function () {

    $(".login-btn").click(function() {
        const usernamevalid=$('#username').val();
        const passwordvalid=$('#password').val();
        if(usernamevalid.length == 0 || passwordvalid.length == 0){
            alert("Please provide all the input fields before login");
        }
        else{
            let PostJson = {
                "username" : usernamevalid,
                "password" : passwordvalid,
            }
            $.ajax({
                type: 'POST',
                contentType: "application/json",
                url: "http://localhost:8080/survey/api/auth",
                data: JSON.stringify(PostJson),
                dataType: "text",
                error: function(e) {
                    alert("Something wrong");
                    console.log(e);
                },
                success : function(data){
                    alert("Login successfully");
                    window.location.replace("/survey/questionnaire");
                }
            })
        }
    })
})

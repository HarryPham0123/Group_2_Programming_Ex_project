//Created by Ha Xuan Huy, Vu Viet Hoang
$(function () {
  //Calling dropDown function for selection lists: 
  dropDown();
  //Hide tables function 
	$(".hide-btn1").click(function() {
		$(".table-container1").slideUp()
	})
  $(".hide-btn2").click(function() {
		$(".table-container2").slideUp()
	})
  $(".hide-btn3").click(function() {
		$(".table-container3").slideUp()
	})
  $(".hide-btn4").click(function() {
		$(".table-container4").slideUp()
	})
  $(".hide-btn5").click(function() {
		$(".table-container5").slideUp()
	})
  $(".hide-btn6").click(function() {
		$(".table-container6").slideUp()
	})
  $(".hide-btn7").click(function() {
		$(".table-container7").slideUp()
	})

  $(".hide-btn8").click(function() {
		$(".table-container8").slideUp()
	})

  $(".hide-btn9").click(function() {
		$(".table-container9").slideUp()
	})

  $(".hide-btn10").click(function() {
		$(".table-container10").slideUp()
	})

  $(".hide-btn11").click(function() {
		$(".table-container11").slideUp()
	})



	//Show tables functions
	$(".show-btn1").click(function() {
		$(".table-container1").slideDown()
	})
  $(".show-btn2").click(function() {
		$(".table-container2").slideDown()
	})
  $(".show-btn3").click(function() {
		$(".table-container3").slideDown()
	})
  $(".show-btn4").click(function() {
		$(".table-container4").slideDown()
	})
  $(".show-btn5").click(function() {
		$(".table-container5").slideDown()
	})
  $(".show-btn6").click(function() {
		$(".table-container6").slideDown()
	})
  $(".show-btn7").click(function() {
		$(".table-container7").slideDown()
	})

$(".show-btn8").click(function() {
		$(".table-container8").slideDown()
	})
  $(".show-btn9").click(function() {
    $(".table-container9").slideDown()
	})
  $(".show-btn10").click(function() {
		$(".table-container10").slideDown()
	})
  $(".show-btn11").click(function() {
		$(".table-container11").slideDown()
	})



  //Load data functions that call getData() and display the form of each selected table
  $(".update1").one("click", function() {
		getData1();
    var x = document.getElementById("table-list1");
    x.style.display = "block";
	})
  $(".update2").one("click", function() {
		getData2();
    var x = document.getElementById("table-list2");
    x.style.display = "block";
	})
  $(".update3").one("click", function() {
		getData3();
    var x = document.getElementById("table-list3");
    x.style.display = "block";
	})
  $(".update4").one("click", function() {
		getData4();
    var x = document.getElementById("table-list4");
    x.style.display = "block";
	})
  $(".update5").one("click", function() {
		getData5();
    var x = document.getElementById("table-list5");
    x.style.display = "block";
	})
  $(".update6").one("click", function() {
		getData6();
    var x = document.getElementById("table-list6");
    x.style.display = "block";
	})
  $(".update7").one("click", function() {
		getData7();
    var x = document.getElementById("table-list7");
    x.style.display = "block";
	})

  $(".update8").one("click", function() {
		getData8();
    var x = document.getElementById("table-list8");
    x.style.display = "block";
	})
  $(".update9").one("click", function() {
		getData9();
    var x = document.getElementById("table-list9");
    x.style.display = "block";
	})
  $(".update10").one("click", function() {
		getData10();
    var x = document.getElementById("table-list10");
    x.style.display = "block";
	})
  $(".update11").one("click", function() {
		getData11();
    var x = document.getElementById("table-list11");
    x.style.display = "block";
	})


  //Create functions when click New buttons
  $(".create1").click(function() {
    createAYear();
	})
  $(".create2").click(function() {
    createSemester();
	})
  $(".create3").click(function() {
		createFaculty();
	})
  $(".create4").click(function() {
    createProgram();
	})
  $(".create5").click(function() {
    createModule();
	})
  $(".create6").click(function() {
    createClass();
	})
  $(".create7").click(function() {
    createLecturer();
	})
  $(".create8").click(function() {
    createLecturerClass();
	})
  $(".create9").click(function() {
    createAcaFaculty();
	})
  $(".create10").click(function() {
    createAcaFacuProgram();
	})
  $(".create11").click(function() {
    createAcaFacuProModule();
	})
})



//Ajax getData() functions to get data from server, also call the dataRender() to render to tables
function getData1(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/academic_year',
  success: function(data) {
    dataRender1(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData2(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/semesters',
  success: function(data) {
    dataRender2(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}

function getData3(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/faculties',
  success: function(data) {
    dataRender3(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData4(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/programs',
  success: function(data) {
    dataRender4(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData5(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/module',
  success: function(data) {
    dataRender5(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData6(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/classes',
  success: function(data) {
    dataRender6(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData7(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/lecturers',
  success: function(data) {
    dataRender7(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}

function getData8(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/lecturer_class',
  success: function(data) {
    dataRender8(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData9(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/academic_year_faculty',
  success: function(data) {
    dataRender9(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData10(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/academic_year_faculty_program',
  success: function(data) {
    dataRender10(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}
function getData11(){
  $.ajax({
  type: 'GET',
  url: 'http://localhost:8080/survey/api/academic_year_faculty_program_module',
  success: function(data) {
    dataRender11(data);
  },
  error:function () {
    alert("Error loading order");
    },
  })
}


//dataRender functions that render the data from server to tables
function dataRender1(data) {
data.map(val=>{
  $(`<tr class=${val.code}>
    <td>${val.code}</td>
    <td><button id=${val.code} type="button" onClick="deleteAcademicYear(this.id)">Delete</button></td>
      <tr>`).appendTo(".table-list1");
})
}
function dataRender2(data) {
  data.map(val=>{
    $(`<tr class=${val.code}>
      <td>${val.code}</td>
      <td>${val.aycode}</td>
      <td><button id=${val.code} type="button" onClick="deleteSemester(this.id)">Delete</button></td>
      <tr>`)
        .appendTo(".table-list2");
})
 }
  function dataRender3(data) {
    data.map(val=>{
      $(`<tr class=${val.code}>
        <td>${val.code}</td>
        <td>${val.name}</td>
        <td><button id=${val.code} type="button" onClick="deleteFaculty(this.id)">Delete</button></td>
        <td><input type="text" id=${val.code}-modfname placeholder="Faculty name"></td>
        <td><button id=${val.code} type="button" onClick="modifyFaculty(this.id)">Modify</button></td>
          <tr>`).appendTo(".table-list3");
})
}
function dataRender4(data) {
  data.map(val=>{
    $(`<tr class=${val.code}>
      <td>${val.code}</td>
      <td>${val.name}</td>
      <td><button id=${val.code} type="button" onClick="deleteProgram(this.id)">Delete</button></td>
      <td><input type="text" id=${val.code}-modproname placeholder="Program name"></td>
      <td><button id=${val.code} type="button" onClick="modifyProgram(this.id)">Modify</button></td>
        <tr>`).appendTo(".table-list4");
})
}
function dataRender5(data) {
  data.map(val=>{
    $(`<tr class=${val.code}>
      <td>${val.code}</td>
      <td>${val.name}</td>
      <td><button id=${val.code} type="button" onClick="deleteModule(this.id)">Delete</button></td>
      <td><input type="text" id=${val.code}-modmodulename placeholder="Module name"></td>
      <td><button id=${val.code} type="button" onClick="modifyModule(this.id)">Modify</button></td>
        <tr>`).appendTo(".table-list5");
})
}
function dataRender6(data) {
  data.map(val=>{
    $(`<tr class=${val.code}>
      <td>${val.code}</td>
      <td>${val.size}</td>
      <td id=${val.code}-takescode>${val.scode}</td>
      <td id=${val.code}-takemcode>${val.mcode}</td>
      <td><button id=${val.code} type="button" onClick="deleteClass(this.id)">Delete</button></td>
      <td><input type="text" id=${val.code}-modclasssize placeholder="Class size"></td>
      <td><button id=${val.code} type="button" onClick="modifyClass(this.id)">Modify</button></td>
        <tr>`).appendTo(".table-list6");
})
}
function dataRender7(data) {
  data.map(val=>{
    $(`<tr class=${val.code}>
      <td>${val.code}</td>
      <td>${val.name}</td>
      <td><button id=${val.code} type="button" onClick="deleteLecturer(this.id)">Delete</button></td>
      <td><input type="text" id=${val.code}-modlecname placeholder="Lecturer name"></td>
      <td><button id=${val.code} type="button" onClick="modifyLecturer(this.id)">Modify</button></td>
        <tr>`).appendTo(".table-list7");
})
}
function dataRender8(data) {
  data.map(val=>{
    $(`<tr class=?class_code=${val.ccode}&lecturer_code=${val.lcode}>
      <td>${val.ccode}</td>
      <td>${val.lcode}</td>   
      <td><button id=?class_code=${val.ccode}&lecturer_code=${val.lcode} type="button" onClick="deleteLecturerClass(this.id)">Delete</button></td>
        <tr>`).appendTo(".table-list8");
})
}
function dataRender9(data) {
  data.map(val=>{
    $(`<tr class=?academic_year=${val.aycode}&faculty_code=${val.fcode}>
      <td>${val.aycode}</td>
      <td>${val.fcode}</td>  
      <td><button id=?academic_year=${val.aycode}&faculty_code=${val.fcode} type="button" onClick="deleteAcaFaculty(this.id)">Delete</button></td>
        <tr>`).appendTo(".table-list9");
})
}
function dataRender10(data) {
  data.map(val=>{
    $(`<tr class=?academic_year=${val.aycode}&faculty_code=${val.fcode}&program_code=${val.pcode}>
      <td>${val.aycode}</td>
      <td>${val.fcode}</td>
      <td>${val.pcode}</td>
      <td><button id=?academic_year=${val.aycode}&faculty_code=${val.fcode}&program_code=${val.pcode} type="button" onClick="deleteAcaFacuProgram(this.id)">Delete</button></td>
        <tr>`).appendTo(".table-list10");
})
}
function dataRender11(data) {
  data.map(val=>{
    $(`<tr class=?academic_year=${val.aycode}&faculty_code=${val.fcode}&program_code=${val.pcode}&module_code=${val.mcode}>
      <td>${val.mcode}</td>
      <td>${val.aycode}</td>
      <td>${val.fcode}</td>
      <td>${val.pcode}</td>
      <td><button id=?academic_year=${val.aycode}&faculty_code=${val.fcode}&program_code=${val.pcode}&module_code=${val.mcode} type="button" onClick="deleteAcaFacuProModule(this.id)">Delete</button></td>
        <tr>`).appendTo(".table-list11");
})
}

//Create functions that send POST request to server (if the input data is valid), whenever the user click the New buttons
function createAYear(){
  const code = $('#create-AYCode').val();
  if(code.length==9){
  let PostJson = {
    "code" : code,
  }
  $.ajax({
    type: 'POST',
    contentType: "application/json",
    url: "http://localhost:8080/survey/api/academic_year",
    data: JSON.stringify(PostJson),
    dataType: "text",
    error: function(e) {
      alert("Something wrong");
        },
    success : function(data, textStatus, jqXHR){
      //Load data dynamically after press New
      $(`<tr  class=${code}>
  <td>${code}</td>
  <td><button id=${code} type="button" onClick="deleteAcademicYear(this.id)">Delete</button></td>
    <tr>`).appendTo(".table-list1");
    $(".create-relaAcaCode").append(`<option value=${code}>${code}</option>`);
    alert("Create new academic year successfully");
      }
  })
}
else{
  alert("Wrong input, please provide the correct input");
}
  //Auto erase the New fields
  $("#create-AYCode").val('');
}

function createSemester(){
    const code = $('#create-semCode').val();
    const aycode = $('#create-semAyCode').val();
    if(code.length == 5 & aycode.length == 9){
    let PostJson = {
      "code" : code,
      "aycode" : aycode,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/semesters",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(e) {
      alert("Something wrong");
          console.log(e);
        },
      success : function(data, textStatus, jqXHR){
         //Load data dynamically right after click New button
    $(`<tr  class=${code}>
    <td>${code}</td>
    <td>${aycode}</td>
    <td><button id=${code} type="button" onClick="deleteSemester(this.id)">Delete</button></td>
      <tr>`).appendTo(".table-list2");
      $(".create-relaSemesterCode").append(`<option value=${code}>${code}</option>`);
      alert("Create new semester successfully");
        }
    })
  }
    else{
      alert("Wrong input, please provide the input in correct form");
    }
    $('#create-semCode').val('');
    $('#create-semAyCode').val('');
  }


function createFaculty(){
    const fcode = $('#create-fcode').val();
    const fname = $('#create-faculty').val();
    if(fcode.length == 5 && fname.length > 0){
      let PostJson = {
        "code" : fcode,
        "name" : fname,
      }
      $.ajax({
        type: 'POST',
        contentType: "application/json",
        url: "http://localhost:8080/survey/api/faculties",
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          
        //Load data dynamically right after click New button
      $(`<tr class=${fcode}>
      <td>${fcode}</td>
      <td>${fname}</td>
      <td><button id=${fcode} type="button" onClick="deleteFaculty(this.id)">Delete</button></td>
      <td><input type="text" id=${fcode}-modfname placeholder="Faculty name"></td>
      <td><button id=${fcode} type="button" onClick="modifyFaculty(this.id)">Modify</button></td>
        <tr>`).appendTo(".table-list3");
        $(".create-relaFacultyCode").append(`<option value=${fcode}>${fcode}</option>`);
        alert("Create new faculty successfully");
          }
      })
    }
    else{
      alert("Wrong input, please provide the input in correct form");
    }
    //Auto erase the New input fields
    $("#create-fcode").val('');
    $("#create-faculty").val('');
  }


  function createProgram(){
    const programcode = $('#create-programCode').val();
    const programname = $('#create-programName').val();
    if(programcode.length == 5 && programname.length >0){
    let PostJson = {
      "code" : programcode,
      "name" : programname,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/programs",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(e) {
      alert("Something wrong");
          console.log(e);
        },
      success : function(data, textStatus, jqXHR){
        	      //Load data dynamically right after click New button
    $(`<tr  class=${programcode}>
    <td>${programcode}</td>
    <td>${programname}</td>
    <td><button id=${programcode} type="button" onClick="deleteProgram(this.id)">Delete</button></td>
    <td><input type="text" id=${programcode}-modproname placeholder="Program name"></td>
    <td><button id=${programcode} type="button" onClick="modifyProgram(this.id)">Modify</button></td>  
      <tr>`).appendTo(".table-list4");
      $(".create-relaProgramCode").append(`<option value=${programcode}>${programcode}</option>`);
        alert("Create new program successfully");
        }
    })
  }
     else{
       alert("Wrong input, please provide the input in correct form");
     }
     //Auto erase the New input fields
     $("#create-programCode").val('');
     $("#create-programName").val('');
  }


  function createModule(){
    const code = $('#create-moduleCode').val();
    const name = $('#create-moduleName').val();
    if(code.length == 5 && name.length >0){
    let PostJson = {
      "code" : code,
      "name" : name,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/module",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(e) {
      alert("Something wrong");
          console.log(e);
        },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically
        $(`<tr  class=${code}>
        <td>${code}</td>
        <td>${name}</td>
        <td><button id=${code} type="button" onClick="deleteModule(this.id)">Delete</button></td>
        <td><input type="text" id=${code}-modmodulename placeholder="Module name"></td>
        <td><button id=${code} type="button" onClick="modifyModule(this.id)">Modify</button></td>
          <tr>`).appendTo(".table-list5");
          $(".create-relaModuleCode").append(`<option value=${code}>${code}</option>`);
        alert("Create new module successfully");
        }
    })
  } 
     else{
       alert("Wrong input, please provide the input in correct form");
     }
     //Auto erase the New input fields
     $("#create-moduleCode").val('');
     $("#create-moduleName").val('');
  }


  function createClass(){
    const code = $('#create-classCode').val();
    const size = $('#create-size').val();
    const scode =$('#create-relaSemesterCode').val();
    const mcode = $('#create-relaModuleCode1').val();
    let PostJson = {
      "code" : code,
      "size" : size,
      "scode" : scode,
      "mcode" : mcode,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/classes",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(e) {
      alert("Something wrong");
          console.log(e);
        },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically right after click New button
        $(`<tr  class=${code}>
        <td>${code}</td>
        <td>${size}</td>
        <td id=${code}-takescode >${scode}</td>
        <td id=${code}-takemcode >${mcode}</td>
        <td><button id=${code} type="button" onClick="deleteClass(this.id)">Delete</button></td>
        <td><input type="text" id=${code}-modclasssize placeholder="Class size"></td>
        <td><button id=${code} type="button" onClick="modifyClass(this.id)">Modify</button></td>  
      <tr>`).appendTo(".table-list6");
      $(".create-relaclassCode").append(`<option value=${code}>${code}</option>`);
        alert("Create new class successfully");
        }
    })
     //Auto erase the New input fields
     $("#create-classCode").val('');
     $("#create-size").val('');
     $("#create-relaSemesterCode").val('');
     $("#create-relaModuleCode1").val('');
  }


  function createLecturer(){
    const code = $('#create-lecturerCode').val();
    const name = $('#create-lecturerName').val();
    if(code.length == 5 && name.length > 0){
    let PostJson = {
      "code" : code,
      "name" : name,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/lecturers",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(e) {
      alert("Something wrong");
          console.log(e);
        },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically 
        $(`<tr class=${code}>
        <td>${code}</td>
        <td>${name}</td> 
        <td><button id=${code} type="button" onClick="deleteLecturer(this.id)">Delete</button></td>
        <td><input type="text" id=${code}-modlecname placeholder="Lecturer name"></td>
        <td><button id=${code} type="button" onClick="modifyLecturer(this.id)">Modify</button></td>
          <tr>`).appendTo(".table-list7");
          $(".create-relalecturerCode").append(`<option value=${code}>${code}</option>`);
        alert("Create new lecturer successfully");
        }
    })
  } 
     else{
       alert("Wrong input, please provide input in the correct form");
     }
     //Auto erase the New input fields
     $("#create-lecturerCode").val('');
     $("#create-lecturerName").val('');
  }

  function createLecturerClass(){
    const ccode = $('#create-relaclassCode').val();
    const lcode = $('#create-relalecturerCode').val();
    let PostJson = {
      "ccode" : ccode,
      "lcode" : lcode,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/lecturer_class",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(e) {
      alert("Something wrong");
          console.log(e);
        },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically 
        $(`<tr class=?class_code=${ccode}&lecturer_code=${lcode}>
        <td>${ccode}</td>
        <td>${lcode}</td>
        <td><button id=?class_code=${ccode}&lecturer_code=${lcode} type="button" onClick="deleteLecturerClass(this.id)">Delete</button></td>
          <tr>`).appendTo(".table-list8");
        alert("Create new class and lecturer successfully");
        }
    })
     //Auto erase the New input fields
     $("#create-relaclassCode").val('');
     $("#create-relalecturerCode").val('');
  }

  function createAcaFaculty(){
    const aycode = $('#create-relaAcaCode1').val();
    const fcode = $('#create-relaFacultyCode1').val();
  
    let PostJson = {
      "aycode" : aycode,
      "fcode" : fcode,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/academic_year_faculty",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(xhr, status, error) {
      alert(xhr.responseText);
        },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically 
        $(`<tr class=?academic_year=${aycode}&faculty_code=${fcode}>
        <td>${aycode}</td>
        <td>${fcode}</td>
        <td><button id=?academic_year=${aycode}&faculty_code=${fcode} type="button" onClick="deleteAcaFaculty(this.id)">Delete</button></td>
          <tr>`).appendTo(".table-list9");
        alert("Create new academic year and faculty successfully");
        }
    })
     //Auto erase the New input fields
     $("#create-relaAcaCode1").val('');
     $("#create-relaFacultyCode1").val('');
  }

  function createAcaFacuProgram(){
    const aycode = $('#create-relaAcaCode2').val();
    const fcode = $('#create-relaFacultyCode2').val();
    const pcode = $('#create-relaProgramCode2').val();
    let PostJson = {
      "aycode" : aycode,
      "fcode" : fcode,
      "pcode" : pcode,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/academic_year_faculty_program",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(xhr, status, error) {
      alert(xhr.responseText);
        },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically 
        $(`<tr class=?academic_year=${aycode}&faculty_code=${fcode}&program_code=${pcode}>
        <td>${aycode}</td>
        <td>${fcode}</td>
        <td>${pcode}</td>
        <td><button id=?academic_year=${aycode}&faculty_code=${fcode}&program_code=${pcode} type="button" onClick="deleteAcaFacuProgram(this.id)">Delete</button></td>
          <tr>`).appendTo(".table-list10");
        alert("Create new academic year, faculty and program successfully");
        }
    })
     //Auto erase the New input fields
     $("#create-relaAcaCode2").val('');
     $("#create-relaFacultyCode2").val('');
     $("#create-relaProgramCode2").val('');
  }

  function createAcaFacuProModule(){
    const mcode = $('#create-relaModuleCode3').val();
    const aycode = $('#create-relaAcaCode3').val();
    const fcode = $('#create-relaFacultyCode3').val();
    const pcode=$('#create-relaProgramCode3').val();
    let PostJson = {
      "mcode"  : mcode,
      "aycode" : aycode,
      "fcode" : fcode,
      "pcode" : pcode,
    }
    $.ajax({
      type: 'POST',
      contentType: "application/json",
      url: "http://localhost:8080/survey/api/academic_year_faculty_program_module",
      data: JSON.stringify(PostJson),
      dataType: "text",
      error: function(xhr, status, error) {
        alert(xhr.responseText);
       },
      success : function(data, textStatus, jqXHR){
        //Load data dynamically 
        $(`<tr class=?academic_year=${aycode}&faculty_code=${fcode}&program_code=${pcode}&module_code=${mcode}>
        <td>${mcode}</td>
        <td>${aycode}</td>
        <td>${fcode}</td>
        <td>${pcode}</td>
        <td><button id=?academic_year=${aycode}&faculty_code=${fcode}&program_code=${pcode}&module_code=${mcode} type="button" onClick="deleteAcaFacuProModule(this.id)">Delete</button></td>
          <tr>`).appendTo(".table-list11");
        alert("Create new academic year, faculty, program and module successfully");
        }
    })
     //Auto erase the New input fields
     $("#create-relaModuleCode3").val('');
     $("#create-relaAcaCode3").val('');
     $("#create-relaFacultyCode3").val('');
     $("#create-relaProgramCode3").val('');
  }
  //Delete functions that send Delete request of selected row to server 
  function deleteAcademicYear(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/academic_year/'+`${ID}`,
      success: function() {
         //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relaAcaCode option[value=' + ID + ']').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteSemester(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/semesters/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relaSemesterCode option[value=' + ID + ']').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteFaculty(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/faculties/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relaFacultyCode option[value=' + ID + ']').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteProgram(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/programs/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relaProgramCode option[value=' + ID + ']').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteModule(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/module/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relaModuleCode option[value=' + ID + ']').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteClass(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/classes/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relaclassCode option[value=' + ID + ']').remove(); 
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteLecturer(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/lecturers/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        $('.create-relalecturerCode option[value=' + ID + ']').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteLecturerClass(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/lecturer_class/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteAcaFaculty(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/academic_year_faculty/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteAcaFacuProgram(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/academic_year_faculty_program/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteAcaFacuProModule(clicked_id)
  {
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/academic_year_faculty_program_module/'+`${ID}`,
      success: function() {
        	  //Delete the append row automatically
        $('tr[class*="' + clicked_id + '"]').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }

//Modify functions when click Modify, send PUT request (if data is valid) to server to modify the data
  function modifyFaculty(clicked_id){
    var ID=clicked_id;
    var alo="fuck";
    const name=document.getElementById(clicked_id +'-modfname').value;
    if(name.length >0){
    let PostJson = {
      "code": clicked_id,
      "name" : name,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/faculties/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new faculty name successful");
          }
      })
    }
    else{
      alert("Wrong input, please provide the correct input");
    }
    //Erase the modify input field
    document.getElementById(clicked_id +'-modfname').value="";
  }

  function modifyProgram(clicked_id){
    var ID=clicked_id;
    const name=document.getElementById(clicked_id +'-modproname').value;
    if(name.length >0){
    let PostJson = {
      "code" : clicked_id,
      "name" : name,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/programs/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new program name successful");
          }
      })
    }
    else{
      alert("Wrong input, please provide the correct input");
    }
    //Erase the modify input field
    document.getElementById(clicked_id +'-modproname').value="";
  }

  function modifyModule(clicked_id){
    var ID=clicked_id;
    const name=document.getElementById(clicked_id +'-modmodulename').value;
    if (name.length >0){
    let PostJson = {
      "code" : clicked_id,
      "name" : name,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/module/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new module name successful");
          }
      })
    }
    else{
      alert("Wrong input, please provide the correct input");
    }
        //Erase the modify input field
        document.getElementById(clicked_id +'-modmodulename').value="";
  }

  function modifyClass(clicked_id){
    var ID=clicked_id;
    const size=document.getElementById(clicked_id +'-modclasssize').value;
    const scode=document.getElementById(clicked_id + '-takescode').innerText;
    const mcode=document.getElementById(clicked_id + '-takemcode' ).innerText;
    if(size >0){
    let PostJson = {
      "code" : clicked_id,
      "size" : size,
      "scode": scode,
      "mcode": mcode,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/classes/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new class size successful");
          }
      })
    }
    else{
      alert("Wrong input, please provide the correct input");
    }
       //Erase the modify input field
       document.getElementById(clicked_id +'-modclasssize').value="";
  }

  function modifyLecturer(clicked_id){
    var ID=clicked_id;
    const name=document.getElementById(clicked_id +'-modlecname').value;
    if(name.length >0){
    let PostJson = {
      "code" : clicked_id,
      "name" : name,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/lecturers/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new Lecturer name successful");
          }
      })
    }
    else{
      alert("Wrong input, please provide the correct input");
    }
      //Erase the modify input field
      document.getElementById(clicked_id +'-modlecname').value="";
  }

  //Dropdown list function for relational tables
  function dropDown(){
    const api1='http://localhost:8080/survey/api/academic_year'
    $.get(api1,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relaAcaCode");
      })
    })
    const api2='http://localhost:8080/survey/api/faculties'
    $.get(api2,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relaFacultyCode");
      })
    })

    const api3='http://localhost:8080/survey/api/programs'
    $.get(api3,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relaProgramCode");
      })
    })
    const api4='http://localhost:8080/survey/api/module'
    $.get(api4,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relaModuleCode");
      })
    })
    const api5='http://localhost:8080/survey/api/classes'
    $.get(api5,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relaclassCode");
      })
    })
    const api6='http://localhost:8080/survey/api/lecturers'
    $.get(api6,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relalecturerCode");
      })
    })
    const api7='http://localhost:8080/survey/api/semesters'
    $.get(api7,function(data){
      data.map(value=>{
         $(`
           <option value=${value.code}>${value.code}</option>
         `).appendTo(".create-relaSemesterCode");
      })
    })
  }

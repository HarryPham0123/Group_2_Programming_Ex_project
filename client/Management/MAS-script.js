$(function () {


	//Khi bam hide btn thi goi ham slideUp()
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


	//Khi bam show btn thi goi ham slideDown()
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



  //Load data buttons
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


  //Create (New) buttons
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
})

//Delete buttons


//Ajax GET de keo data ve display khi bam nut
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


//Ham DataRender de keo data ve display, nhet vao bang
function dataRender1(data) {
data.map(val=>{
  $(`<tr class=${val.code}>
    <td>${val.code}</td>
    <td><button id=${val.code} type="button" onClick="deleteAcademicYear(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modacode placeholder="code"></td>
    <td><button id=${val.code} type="button" onClick="modifyAcademic(this.id)">Modify</button></td>
      <tr>`).appendTo(".table-list1");
})
}
function dataRender2(data) {
  data.map(val=>{
    $(`<tr class=${val.code}>
      <td>${val.code}</td>
      <td>${val.aycode}</td>
      <td><button id=${val.code} type="button" onClick="deleteSemester(this.id)">Delete</button></td>
      <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modsemcode placeholder="code"></td>
      <td><input type="text" id=${val.code}-modsemAYcode placeholder="Academic Year code"></td>
      <td><button id=${val.code} type="button" onClick="modifySemester(this.id)">Modify</button></td>
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
        <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modfcode placeholder="code"></td>
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
      <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modprocode placeholder="code"></td>
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
      <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modmodulecode placeholder="code"></td>
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
      <td>${val.scode}</td>
      <td>${val.mcode}</td>
      <td><button id=${val.code} type="button" onClick="deleteClass(this.id)">Delete</button></td>
      <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modclasscode placeholder="code"></td>
      <td><input type="text" id=${val.code}-modclasssize placeholder="Class size"></td>
      <td><input type="text" id=${val.code}-modclassscode placeholder="Class scode"></td>
      <td><input type="text" id=${val.code}-modclassmcode placeholder="Class mcode"></td>
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
      <td style="padding:0px 0px 0px 120px;"><input type="text" id=${val.code}-modleccode placeholder="code"></td>
      <td><input type="text" id=${val.code}-modlecname placeholder="Lecturer name"></td>
      <td><button id=${val.code} type="button" onClick="modifyLecturer(this.id)">Modify</button></td>
        <tr>`).appendTo(".table-list7");
})
}


//POST request when pressing New buttons:
function createAYear(){
  const code = $('#create-AYCode').val();
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
        console.log(e);
      },
    success : function(data, textStatus, jqXHR){
      alert("Create new academic year successfully");
      }
  })
	 //Load data dynamically right after click New button
  $(`<tr  class=${code}>
  <td>${code}</td>
  <td><button id=${code} type="button" onClick="deleteAcademicYear(this.id)">Delete</button></td>
  <td style="padding:0px 0px 0px 120px;"><input type="text" id=${code}-modacode placeholder="code"></td>
  <td><button id=${code} type="button" onClick="modifyAcademic(this.id)">Modify</button></td>
    <tr>`).appendTo(".table-list1");
}

function createSemester(){
    const code = $('#create-semCode').val();
    const aycode = $('#create-semAyCode').val();
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
        alert("Create new semester successfully");
        }
    })
	    //Load data dynamically right after click New button
    $(`<tr  class=${code}>
    <td>${code}</td>
    <td>${aycode}</td>
    <td><button id=${code} type="button" onClick="deleteSemester(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${code}-modsemcode placeholder="code"></td>
    <td><input type="text" id=${code}-modsemAYcode placeholder="Academic Year code"></td>
    <td><button id=${code} type="button" onClick="modifySemester(this.id)">Modify</button></td>
      <tr>`).appendTo(".table-list2");
  }


function createFaculty(){
    const fcode = $('#create-fcode').val();
    const fname = $('#create-faculty').val();
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
        alert("Create new faculty successfully");
        }
    })
	    //Load data dynamically right after click New button
    $(`<tr class=${fcode}>
    <td>${fcode}</td>
    <td>${fname}</td>
    <td><button id=${fcode} type="button" onClick="deleteFaculty(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${fcode}-modfcode placeholder="code"></td>
    <td><input type="text" id=${fcode}-modfname placeholder="name"></td>
    <td><button id=${fcode} type="button" onClick="modifyFaculty(this.id)">Modify</button></td>
      <tr>`).appendTo(".table-list3");
  }


  function createProgram(){
    const programcode = $('#create-programCode').val();
    const programname = $('#create-programName').val();
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
        alert("Create new program successfully");
        }
    })
	      //Load data dynamically right after click New button
    $(`<tr  class=${programcode}>
    <td>${programcode}</td>
    <td>${programname}</td>
    <td><button id=${programcode} type="button" onClick="deleteProgram(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${programcode}-modprocode placeholder="code"></td>
    <td><input type="text" id=${programcode}-modproname placeholder="Program name"></td>
    <td><button id=${programcode} type="button" onClick="modifyProgram(this.id)">Modify</button></td> 
      <tr>`).appendTo(".table-list4");
  }


  function createModule(){
    const code = $('#create-moduleCode').val();
    const name = $('#create-moduleName').val();
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
        alert("Create new module successfully");
        }
    })
	     $(`<tr  class=${code}>
    <td>${code}</td>
    <td>${name}</td>
    <td><button id=${code} type="button" onClick="deleteModule(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${code}-modmodulecode placeholder="code"></td>
    <td><input type="text" id=${code}-modmodulename placeholder="Module name"></td>
    <td><button id=${code} type="button" onClick="modifyModule(this.id)">Modify</button></td>
      <tr>`).appendTo(".table-list5");
  }


  function createClass(){
    const code = $('#create-classCode').val();
    const size = $('#create-size').val();
    const scode = $('#create-semesterCode').val();
    const mcode = $('#create-modCode').val();
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
        alert("Create new class successfully");
        }
    })
	      //Load data dynamically right after click New button
    $(`<tr  class=${code}>
    <td>${code}</td>
    <td>${size}</td>
    <td>${scode}</td>
    <td>${mcode}</td>
    <td><button id=${code} type="button" onClick="deleteClass(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${code}-modclasscode placeholder="code"></td>
    <td><input type="text" id=${code}-modclasssize placeholder="Class size"></td>
   <td><input type="text" id=${code}-modclassscode placeholder="Semester code"></td>
   <td><input type="text" id=${code}-modclassmcode placeholder="Module code"></td>
   <td><button id=${code} type="button" onClick="modifyClass(this.id)">Modify</button></td>  
      <tr>`).appendTo(".table-list6");
 
  }


  function createLecturer(){
    const code = $('#create-lecturerCode').val();
    const name = $('#create-lecturerName').val();
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
        alert("Create new lecturer successfully");
        }
    })
	      $(`<tr class=${code}>
    <td>${code}</td>
    <td>${name}</td>
    <td><button id=${code} type="button" onClick="deleteLecturer(this.id)">Delete</button></td>
    <td style="padding:0px 0px 0px 120px;"><input type="text" id=${code}-modleccode placeholder="code"></td>
    <td><input type="text" id=${code}-modlecname placeholder="Lecturer name"></td>
    <td><button id=${code} type="button" onClick="modifyLecturer(this.id)">Modify</button></td>
      <tr>`).appendTo(".table-list7");
  }


  //Delete functions
  function deleteAcademicYear(clicked_id)
  {
	  //Delete the append row automatically
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/academic_year/'+`${ID}`,
      success: function() {
        $('tr[class*="' + clicked_id + '"]').remove();
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteSemester(clicked_id)
  {
	  //Delete the append row automatically
    $('tr[class*="' + clicked_id + '"]').remove();
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/semesters/'+`${ID}`,
      success: function() {
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteFaculty(clicked_id)
  {
	  //Delete the append row automatically
    $('tr[class*="' + clicked_id + '"]').remove();
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/faculties/'+`${ID}`,
      success: function() {
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteProgram(clicked_id)
  {
	  //Delete the append row automatically
    $('tr[class*="' + clicked_id + '"]').remove();
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/programs/'+`${ID}`,
      success: function() {
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteModule(clicked_id)
  {
	  //Delete the append row automatically
    $('tr[class*="' + clicked_id + '"]').remove();
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/module/'+`${ID}`,
      success: function() {
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteClass(clicked_id)
  {
	  //Delete the append row automatically
    $('tr[class*="' + clicked_id + '"]').remove();
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/classes/'+`${ID}`,
      success: function() {
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }
  function deleteLecturer(clicked_id)
  {
	  //Delete the append row automatically
    $('tr[class*="' + clicked_id + '"]').remove();
    var ID=clicked_id;
    $.ajax({
      type: 'DELETE',
      url: 'http://localhost:8080/survey/api/lecturers/'+`${ID}`,
      success: function() {
        alert("Delete successful");
      },
      error:function () {
        alert("Error");
        },
      })
  }


  // Modify buttons onclick function
  function modifyAcademic(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modacode').value;
    let PostJson = {
      "code" : code,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/academic_year/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new academic year successful");
          }
      })
  }

  function modifySemester(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modsemcode').value;
    const aycode=document.getElementById(clicked_id +'-modsemAYcode').value;
    let PostJson = {
      "code" : code,
      "aycode" : aycode,
      }
      $.ajax({
        type: 'PUT',
        contentType: "application/json",
        url: 'http://localhost:8080/survey/api/semesters/' + `${ID}`,
        data: JSON.stringify(PostJson),
        dataType: "text",
        error: function(e) {
        alert("Something wrong");
            console.log(e);
          },
        success : function(data, textStatus, jqXHR){
          alert("Update new semester successful");
          }
      })
  }

  function modifyFaculty(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modfcode').value;
    const name=document.getElementById(clicked_id +'-modfname').value;
    let PostJson = {
      "code" : code,
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
          alert("Update new faculty successful");
          }
      })
  }

  function modifyProgram(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modprocode').value;
    const name=document.getElementById(clicked_id +'-modproname').value;
    let PostJson = {
      "code" : code,
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
          alert("Update new program successful");
          }
      })
  }

  function modifyModule(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modmodulecode').value;
    const name=document.getElementById(clicked_id +'-modmodulename').value;
    let PostJson = {
      "code" : code,
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
          alert("Update new module successful");
          }
      })
  }

  function modifyClass(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modclasscode').value;
    const size=document.getElementById(clicked_id +'-modclasssize').value;
    const scode=document.getElementById(clicked_id +'-modclassscode').value;
    const mcode=document.getElementById(clicked_id +'-modclassmcode').value;
    let PostJson = {
      "code" : code,
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
          alert("Update new class successful");
          }
      })
  }

  function modifyLecturer(clicked_id){
    var ID=clicked_id;
    const code=document.getElementById(clicked_id +'-modleccode').value;
    const name=document.getElementById(clicked_id +'-modlecname').value;
    let PostJson = {
      "code" : code,
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
          alert("Update new Lecturer successful");
          }
      })
  }

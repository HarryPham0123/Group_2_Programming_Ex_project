# Programming Exercise - Group 2
## 3. Maven Tomcat deploy
1. Go to server's folder: `cd server`.
2. Compile the project: `mvn package`.
3. Deloy with cargo plugin by: `mvn cargo:deploy`.
4. Run Tomcat's server by running `startup.bat` from your Tomcat directory. Example: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat`.
5. __Further information:__ [click here](https://www.baeldung.com/tomcat-deploy-war)


## Server API

### Questionnaire route

`GET:` /survey/api/questionnaire
+ Get all questionnaire with corresponding parameter filters. Setting to a parameter to null, meaning that get all questionnaires relating to that parameter. For instance, setting `academic_year=null` to fetch questionnaires in all academic year. 
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`

`POST:` /survey/api/questionnaire
+ Create a new survey.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`
+ Request form: `{"lcode": "L001M", "ccode": "C005I", "question_list": [{"question": "attendance", "answer": "often"}, {"question": "gender", "answer": "female"}, {"question": "1", "answer": 5}, {...}, {...}]}`

`GET:` /survey/api/questionnaire/attendance_question
+ Get attendance questionnaire statistic.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`
+ Example response: `{"never": 0, "rarely": 0, "sometimes": 5, "often": 11, "always": 20}`

`GET:` /survey/api/questionnaire/gender_question
+ Get gender questionnaire statistic.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`
+ Example response: `{"male": 20, "female": 18, "other": 0}`


`GET:` /survey/api/questionnaire/question_1
+ Get question 1 questionnaire statistic.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`
+ Example response: `{"1": 20, "2": 18, "3": 10, "4": 15, "5": 10}`

`GET:` /survey/api/questionnaire/question_2
+ Get question 2 questionnaire statistic.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`
+ Example response: `{"1": 21, "2": 17, "3": 11, "4": 12, "5": 9}`

...Same as `GET` request question_3, question_4,... etc

`GET:` /survey/api/questionnaire/question_18
+ Get optional comment question 18 with required class and lecturer filters.
+ Params: 
    + `class=[String]`
    + `lecturer=[String]`
+ Example response: `[{"question_18": "This course is awesome!"}, {"question_18": "This is not worth your money!"}, {...}, ...]`

### General route

`GET:` /survey/api/general
+ Get all available codes and names with the corresponding query parameter filters.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`
+ Example response: `{"AYcode": "2020-2021","Scode": "S002q","Fcode": "F001I","Pcode": "P001C","Mcode": "M007A","Ccode": "C007q","Lcode": "L001v","size": 35}, {...}, ...`

### Academic year with Faculty route

`GET:` /survey/api/academic_year_faculty
+ Get all available academic year and faculty codes relationship.
+ Params: NONE
+ Body: NONE
+ Example response: `[{"AYcode": "2020-2021", "Fcode": "F001I"}, {"AYcode": "2019-2020", "Fcode": "F001I"}, {...}, ...]`

`GET:` /survey/api/academic_year_faculty/{code}
+ Get all available academic year and faculty codes according to the given {code}
+ Params: NONE
+ Example body: NONE
+ Example response: `[{"AYcode": "2020-2021", "Fcode": "F001I"}, {"AYcode": "2019-2020", "Fcode": "F001I"}, {...}, ...]`

`POST:` /survey/api/academic_year_faculty
+ Assign new relationship between academic year and faculty.
+ Params: NONE
+ Example body: `{"AYcode": "2020-2021", "Fcode": "F001I"}`
+ Example response: `insert successfully`, `cannot insert`

`DELETE:` /survey/api/academic_year_faculty
+ Delete a relationship between academic year and faculty by given query parameters.
+ Params: 
    + `academic_year=[String or null]`
    + `faculty_code=[String or null]`
+ Body: NONE
+ Example response: `delete successfully`, `cannot delete`

### Academic year with Faculty, Program route

`GET:` /survey/api/academic_year_faculty_program
+ Get all available codes academic year, faculty and program relationship.
+ Params: NONE
+ Body NONE
+ Example response: `[{"AYcode": "2020-2021", "Fcode": "F001I", "Pcode": "P001C"}, {"AYcode": "2019-2020", "Fcode": "F001I", "Pcode": "P002K"}, {...}, ...]`

`GET:` /survey/api/academic_year_faculty_program/{code}
+ Get all available codes academic year, faculty and program relationship with corresponding {code}
+ Params: NONE
+ Body: NONE
+ Example response: `[{"AYcode": "2020-2021", "Fcode": "F001I", "Pcode": "P001C"}, {"AYcode": "2019-2020", "Fcode": "F001I", "Pcode": "P002K"}, {...}, ...]`

`POST:` /survey/api/academic_year_faculty_program
+ Assign new relationship between an academic year, faculty and program.
+ Params: NONE
+ Example body: `{"AYcode": "2020-2021", "Fcode": "F001I", "Pcode": "P001C"}`

`DELETE:` /survey/api/academic_year_faculty_program_module/{code}
+ Delete the relationship between an academic year, faculty and program according the given {code}
+ Params: NONE
+ Body: NONE

### Academic year with Faculty, Program and Module route

`GET:` /survey/api/academic_year_faculty_program_module
+ Get all available codes academic year, faculty, program and module relationship.
+ Params: NONE
+ Body NONE
+ Example response: `[{"AYcode": "2020-2021", "Fcode": "F001I", "Pcode": "P001C", "Mcode": "M005T"}, {"AYcode": "2019-2020", "Fcode": "F001I", "Pcode": "P002K", "Mcode": "M006V"}, {...}, ...]`

`GET:` /survey/api/academic_year_faculty_program_module/{code}
+ Get all available codes academic year, faculty, program and module relationship with corresponding {code}
+ Params: NONE
+ Body: NONE
+ Example response: `[{"AYcode": "2020-2021", "Fcode": "F001I", "Pcode": "P001C", "Mcode": "M005T"}, {"AYcode": "2019-2020", "Fcode": "F001I", "Pcode": "P002K", "Mcode": "M006V"}, {...}, ...]`

`POST:` /survey/api/academic_year_faculty_program_module
+ Assign new relationship between an academic year, faculty, program and module.
+ Params: NONE
+ Example body: `{"AYcode": "2020-2021", "Fcode": "F001I", "Pcode": "P001C", "Mcode": "M001T"}`

`DELETE:` /survey/api/academic_year_faculty_program/{code}
+ Delete the relationship between an academic year, faculty, program and module according the given {code}
+ Params: NONE
+ Body: NONE

### Class with Lecturers route

`GET:` /survey/api/lecturer_class
+ Get all available lecturer and class codes relationship.
+ Params: NONE
+ Body NONE
+ Example response: `[{"Lcode": "L001v", "Ccode": "L007T"}, {...}, ...]`

`GET:` /survey/api/lecturer_class/{code}
+ Get all available lecturer and class codes relationship according to the given {code}
+ Params: NONE
+ Body: NONE
+ Example response: `[{"Lcode": "L001v", "Ccode": "L007T"}, {...}, ...]`

`POST:` /survey/api/lecturer_class
+ Assign new relationship between a lecturer and class.
+ Params: NONE
+ Example body: `{"Lcode": "L001v", "Ccode": "L007T"}`

`DELETE:` /survey/api/lecturer_class/{code}
+ Delete the relationship between an lectuer and class according the given {code}
+ Params: NONE
+ Body: NONE

### Authorisation route
`POST:` /survey/api/auth
+ Get JWT token attached to cookie, utilize to the access resource control later on.
+ Params: NONE
+ Body: `{username=[String or null], password=[String or null]`
+ Example request: `{username=bob123, password=aliceinwonderworld}`


### Academic year route
`GET:` /survey/api/academic_year
+ Get all available academic year code
+ __Request body:__ NONE

`GET:` /survey/api/academic_year/{code}
+ Get academic year with the corresponding code.
+ __Request body:__ NONE

`POST:` /survey/api/academic_year
+ Submit a new academic year code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "A001M"}

`DELETE:` /survey/api/academic_year/{code}
+ Delete the available academic year code
+ __Request body:__ NONE

### Semester route
`GET:` /survey/api/semesters
+ Get all available semester code and name
+ __Request body:__ NONE

`GET:` /survey/api/semesters/{code}
+ Get the semester code and name with the corresponding code.
+ __Request body:__ NONE

`POST:` /survey/api/semesters
+ Submit a new semester code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "S004M", AYcode: "A001M"}

`PUT:` /survey/api/semesters/{code}
+ Update the semester information with the corresponding code
+ __Request body:__ NONE

`DELETE:` /survey/api/semesters/{code}
+ Delete the available semester code
+ __Request body:__ NONE

### Faculty route
`GET:` /survey/api/faculties
+ Get all available faculty code and name
+ __Request body:__ NONE

`GET:` /survey/api/faculties/{code}
+ Get the faculty code and name with the corresponding code.
+ __Request body:__ NONE

`POST:` /survey/api/faculties
+ Submit a new faculty code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "S004M", AYcode: "A001M"}

`PUT:` /survey/api/faculties/{code}
+ Update the faculty information with the corresponding code
+ __Request body:__ NONE

`DELETE:` /survey/api/faculties/{code}
+ Delete the available faculty code
+ __Request body:__ NONE

### Program route
`GET:` /survey/api/programs
+ Get all available program code and name
+ __Request body:__ NONE

`GET:` /survey/api/programs/{code}
+ Get the program code and name with the corresponding code.
+ __Request body:__ NONE

`POST:` /survey/api/programs
+ Submit a new program code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "S004M", AYcode: "A001M"}

`PUT:` /survey/api/programs/{code}
+ Update the program information with the corresponding code
+ __Request body:__ NONE

`DELETE:` /survey/api/programs/{code}
+ Delete the available program code
+ __Request body:__ NONE

### Module route
`GET:` /survey/api/modules
+ Get all available module code and name
+ __Request body:__ NONE

`GET:` /survey/api/modules/{code}
+ Get the module code and name with the corresponding code.
+ __Request body:__ NONE

`POST:` /survey/api/modules
+ Submit a new module code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "S004M", AYcode: "A001M"}

`PUT:` /survey/api/modules/{code}
+ Update the module information with the corresponding code
+ __Request body:__ NONE

`DELETE:` /survey/api/modules/{code}
+ Delete the available module code
+ __Request body:__ NONE

### Class route
`GET:` /survey/api/classes
+ Get all available class code and name
+ __Request body:__ NONE

`GET:` /survey/api/classes/{code}
+ Get the class code and name with the corresponding code.
+ __Request body:__ NONE

`GET:` /survey/api/classes/size
+ Get class size according the given query parameter filters.
+ __Request body:__ NONE
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`

`POST:` /survey/api/classes
+ Submit a new class code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "S004M", AYcode: "A001M"}

`PUT:` /survey/api/classes/{code}
+ Update the class information with the corresponding code
+ __Request body:__ NONE

`DELETE:` /survey/api/classes/{code}
+ Delete the available class code
+ __Request body:__ NONE

### Lecturer route
`GET:` /survey/api/lecturers
+ Get all available lecturers code and name
+ __Request body:__ NONE

`GET:` /survey/api/lecturers/{code}
+ Get the lecturers code and name with the corresponding code.
+ __Request body:__ NONE

`POST:` /survey/api/lecturers
+ Submit a new lecturers code in request body with JSON format to create new academic year code
+ __Example body:__ {code: "S004M", AYcode: "A001M"}

`PUT:` /survey/api/lecturers/{code}
+ Update the semester information with the corresponding code
+ __Request body:__ NONE

`DELETE:` /survey/api/lecturers/{code}
+ Delete the available lecturer code
+ __Request body:__ NONE

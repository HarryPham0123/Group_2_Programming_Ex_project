# Programming Exercise - Group 2
## 3. Maven Tomcat deploy
1. Go to server's folder: `cd server`.
2. Compile the project: `mvn package`.
3. Deloy with cargo plugin by: `mvn cargo:deploy`.
4. Run Tomcat's server by running `startup.bat` from your Tomcat directory. Example: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat`.
5. __Further information:__ [click here](https://www.baeldung.com/tomcat-deploy-war)


## Server API
`GET:` /survey/api/questionnaire
+ Get all questionnaire with corresponding filters.
+ Params: 
    + `academic_year=[String or null]`
    + `semester=[String or null]`
    + `faculty=[String or null]`
    + `program=[String or null]`
    + `module=[String or null]`
    + `class=[String or null]`
    + `lecturer=[String or null]`

`GET:` /survey/api/questionnaire/attendance_question
+ Get attendance questionnaire statistic.
+ Params: same as router
+ Example response: `{"never": 0, "rarely": 0, "sometimes": 5, "often": 11, "always": 20}`

`GET:` /survey/api/questionnaire/gender_question
+ Get gender questionnaire statistic.
+ Params: same as router.
+ Example response: `{"male": 20, "female": 18, "other": 0}`


`GET:` /survey/api/questionnaire/question_1
+ Get question 1 questionnaire statistic.
+ Params: same as router.
+ Example response: `{"1": 20, "2": 18, "3": 10, "4": 15, "5": 10}`

`GET:` /survey/api/questionnaire/question_2
+ Get question 2 questionnaire statistic.
+ Params: same as router.
+ Example response: `{"1": 21, "2": 17, "3": 11, "4": 12, "5": 9}`

...Same as `GET` request question_3, question_4,... etc

`POST:` /survey/api/questionnaire
+ Create a new survey.
+ Params: 
+ Request form: `{"lcode": "L001M", "ccode": "C005I", [{"question": "attendance", "answer": "often"}, {"question": "gender", "answer": "female"}, {"question": "1", "answer": 5}, {...}, {...}]}`

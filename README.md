# Programming Exercise - Group 2
## 3. Maven Tomcat deploy
1. Go to server's folder: `cd server`.
2. Compile the project: `mvn package`.
3. Deloy with cargo plugin by: `mvn cargo:deploy`.
4. Run Tomcat's server by running `startup.bat` from your Tomcat directory. Example: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat`.
5. __Further information:__ [click here](https://www.baeldung.com/tomcat-deploy-war)


## Server API
`GET:` /survey/api/questionnaire
+ Get all survey information with corresponding filters.
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

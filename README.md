# Programming Exercise - Group 2
This project is made to accomplish the module "Programming Exercise" in Vietnamese-German University lecturing by Professor Manuel Cravel. All of the information reserve to the members of the group 2 including
 + Nguyen Dang Khoa, 14322 - Project Manager 
 + Vu Viet Hoang, 15041 - Front-end Lead
 + Ha Xuan Huy, 15268 - Front-end
 + Nguyen Phan Yen Ngan, 14597 - Front-end
 + Pham Viet Hoang, 14708 - Database lead
 + Vuong Chi Hieu, 14923 - Database
 + Phan Cong Huy, 14337 - Back-end lead
 + Tran Van Hung, 14347 - Back-end
 + Pham Minh Huy, 14450 - Back-end, Tester

## Dependencies

### 1. Front-end
 + HTML, CSS and Javascript
 + Jstat [link](https://github.com/jstat/jstat)
 + ChartJS [link](https://github.com/chartjs/Chart.js)
 + ChartJS-chart-error-bar [link](https://github.com/sgratzl/chartjs-chart-error-bars)
 + Bootstrap 5 [link](https://github.com/twbs/bootstrap)
 + 
### 2. Back-end
 + Apache Maven 3.8.1 [link](https://maven.apache.org/)
 + Maven Cargo Deploy [link](https://github.com/codehaus-cargo/cargo)
 + Maven Compiler plugin [link](https://github.com/apache/maven-compiler-plugin)
 + Jersey 2.29.1 [link](https://github.com/eclipse-ee4j/jersey)
 + Java JWT 0.11.2 [link](https://github.com/jwtk/jjwt)
 + Java MySQL connector 8.0.22 [link](https://dev.mysql.com/downloads/connector/j/)

### 3. Database
 + MySQL Server 8.0.23
## 3. Maven Tomcat deploy
1. Go to server's folder: `cd server`.
2. Compile the project: `mvn package`.
3. Deloy with cargo plugin by: `mvn cargo:deploy`.
4. Run Tomcat's server by running `startup.bat` from your Tomcat directory. Example: `C:\Program Files\Apache Software Foundation\Tomcat 9.0\bin\startup.bat`.
5. __Further information:__ [click here](https://www.baeldung.com/tomcat-deploy-war)

#Project Employee Registry

This is a springboot project that uses H2 Database for in memory retrieval and storage of employees.

The project uses gradle for build.

## The project contains
1) Authentication - Encrypted password stored in DB
2) Authorization 
    ( username: foo, password: foo, Role: Admin) <- Can delete employee
    ( username: bar, password: bar, Role: User) <- Cannot delete employee
3) The app loads with set of data at startup
4) Custom Exception Handling
5) Validation of Requests
6) Unit Testing using Mockito
7) The project has added support for Internalization i18
8) The project uses JPA repository and service layer

#### The following endpoints are available after the start up:

___Please Note: You need to select basic auth in headers and supply the username and password supplied for differnt roles.___

1) To get all employees use shows only ACTIVE employees
    _GET : "http://localhost:8080/employees"_

2) To get employee by Id shows only ACTIVE employees
    _GET: "http://localhost:8080/employee/{id}"_
    
3) To add an employee
    _POST : "http://localhost:8080/employee"_

4) To update an employee (Cannot update status to INACTIVE)
    _PUT: "http://localhost:8080/employee/{id}"_

5) To delete an employee (Need to be an ADMIN(foo))
    _DELETE: "http://localhost:8080/employee/{id}"_
    
   If the employee is not found Employee Not Found exception is thrown.

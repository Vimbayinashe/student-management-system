## Student Management System

A student management system built with Java EE.

### API Documentation

 1. **GET** api/v1/students
    - Description - gets all students
    - Parameters - none
    - Request body - none
    - Responses
      - **200 OK**   
      _examples_:
        ```
        [
          {
            "email": "lewisedwins@email.com",
            "firstName": "Lewis",
            "id": 1,
            "lastName": "Edwins"
          },
          {
            "email": "mike@thomasandsons.com",
            "firstName": "Mike",
            "id": 2,
            "lastName": "Thomas"
          }
        ]
        ``` 
        ,
    
        ``
        []
        ``  
       
 2. **GET**  api/v1/students?lastName=query
     - Description - get all students with a requested surname 
     - Query Parameters 
        - **lastName** - a String query parameter which represents the surname to search for
     - Request body - none
     - Responses
         - **200 OK**   
           _examples_:
           ```
           [
             {
               "email": "mike@thomasandsons.com",
               "firstName": "Lewis",
               "id": 1,
               "lastName": "Thomas"
             },
             {
               "email": "mike@thomasandsons.com",
               "firstName": "Mike",
               "id": 2,
               "lastName": "Thomas"
             }
           ]
           ``` 
           ,

           ``
           []
           ``
       
 3. **GET**  api/v1/students/{id}
     - Description - get a student with the specified id
     - Path Parameters
         - **id** - a number that represents the student's ID number in the system
     - Request body - none
     - Responses
         - **200 OK**   
           _example:_
           ```
           {
              "email": "e.stewart@mymail.com",
              "firstName": "Elizabeth",
              "id": 2,
              "lastName": "Stewart"
           }
           ``` 
         - **404 Not Found**   
           _example:_
           ```
           {
              "errorCode": 404,
              "message": "Student with ID 8 not found",
              "timestamp": "2022-02-26T21:34:47.780382166",
              "url": "/api/v1/students/8"
           }
           ```
           
 4. **POST**  api/v1/students/
     - Description - add a new student
     - Parameters - none
     - Request body
       1. Required keys - firstName (String), lastName (String) & email (String)
       2. Optional key - phoneNumber (String)    
          _example:_
          ``` 
           {
             "firstName": "Marie",
             "lastName": "Jean-Pierre",
             "email": "marie.jp@lalouvre.fr",
             "phoneNumber": "0732345678"
           }
          ````
     - Responses
         - **200 OK**   
           _example:_
           ```
           {
              "email": "marie.jp@lalouvre.fr",
              "firstName": "Marie",
              "id": 2,
              "lastName": "Jean-Pierre",
              "phoneNumber": "0732345678"
           }
           ``` 
         - **400 Bad Request**    
           _example:_
           ```
           {
              "errorCode": 404,
              "errorMessages": [
                "Email address is a required field",
                "Firstname is a required field",
                "Lastname is a required field"
              ],
              "timestamp": "2022-02-26T21:34:47.780382166",
              "url": "/api/v1/students/"
           }
           ```

 5. **PUT**  api/v1/students/
     - Description - entirely replace an existing student's details
     - Parameters - none
     - Request body
         1. Required keys - id (int), firstName (String), lastName (String) & email (String)
         2. Optional key - phoneNumber (String)   
            _example:_
            ``` 
             {
               "id": 24,
               "firstName": "Per",
               "lastName": "Larsson",
               "email": "plarsson@melodi.se",
               "phoneNumber": "0732345678"
             }
            ````
     - Responses
         - **200 OK**   
           _example:_
           ```
           {
              "email": "plarsson@melodi.se",
              "firstName": "Per",
              "id": 24,
              "lastName": "Larsson",
              "phoneNumber": "0732345678"
           }
           ``` 
         - **400 Bad Request**   
           _example:_
           ```
           {
              "errorCode": 404,
              "errorMessages": [
                "Email address is a required field"
              ],
              "timestamp": "2022-02-26T21:34:47.780382166",
              "url": "/api/v1/students/"
           }
           ```           
       
 6. **PATCH**  api/v1/students/{id}
     - Description - partially update an existing student's details. The provided property must be a string and it 
       must not be empty or null.
     - Path Parameters
         - **id** - a number that represents the student's ID number in the system
     - Request body
         1. Optional keys - firstName (String), lastName (String), email (String) or phoneNumber (String)   
            _examples:_
            ``` 
             {
               "firstName": "Petros"
             }
            ```
            ,
            ``` 
             {
               "lastName": "Larsson",
               "phoneNumber": "0732345678"
             }
            ```
     - Responses
         - **200 OK**   
           _example:_
           ```
           {
              "email": "plarsson@melodi.se",
              "firstName": "Petros",
              "id": 24,
              "lastName": "Larsson",
              "phoneNumber": "0732345678"
           }
           ``` 
         - **404 Not Found**   
           _example:_
           ```
           {
              "errorCode": 404,
              "message": "Student with ID 24 not found",
              "timestamp": "2022-02-26T21:34:47.780382166",
              "url": "/api/v1/students/24"
           }
           ```        
       
 7. **DELETE**  api/v1/students/{id}
     - Description - delete a student with the given ID from the system
     - Path Parameters
         - **id** - a number that represents the student's ID number in the system
     - Request body - none
     - Responses
         - **204 No Content**    
           
         - **404 Not Found**   
           _example:_
           ```
           {
              "errorCode": 404,
              "message": "Student with ID 64 not found",
              "timestamp": "2022-02-26T21:34:47.780382166",
              "url": "/api/v1/students/64"
           }
           ```    

    
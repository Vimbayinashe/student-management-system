## Student Management System

A student management system built with Java EE.

### API Documentation

#### Entities: students or teachers

 1. **GET** /api/v1/{entities}
    - Description - gets all entities
    - Path Parameters
        - **entities** - students or teachers
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
       
 2. **GET**  /api/v1/{entities}?lastName=query
     - Description - get all entities with a requested surname 
     - Path Parameters
        - **entities** - students or teachers
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
       
 3. **GET**  /api/v1/{entities}/{id}
     - Description - get an entity with the specified id
     - Path Parameters
         - **entities** - students or teachers
         - **id** - an integer that represents the entity's id number in the system
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
           },
           {
             "errorCode": 404,
             "message": "Teacher with ID 8 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/teachers/8"
           }
           ```
           
 4. **POST**  /api/v1/{entities}
     - Description - add a new entity
    - Path Parameters
        - **entities** - students or teachers
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

 5. **PUT**  /api/v1/{entities}
     - Description - entirely replace an existing entity's details
    - Path Parameters
        - **entities** - students or teachers
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
              "url": "/api/v1/teachers/"
           }
           ```           
       
 6. **PATCH**  /api/v1/{entities}/{id}
     - Description - partially update an existing entity's details. The provided property must be a string and it 
       must not be empty or null.
     - Path Parameters
         - **entities** - students or teachers
         - **id** - an integer that represents the entity's ID number in the system
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
              "message": "Teacher with ID 24 not found",
              "timestamp": "2022-02-26T21:34:47.780382166",
              "url": "/api/v1/teachers/24"
           }
           ```        
       
 7. **DELETE**  /api/v1/{entities}/{id}
     - Description - delete an entity with the given ID from the system
     - Path Parameters
         - **entities** - students or teachers
         - **id** - an integer that represents the entity's ID number in the system
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
     **NB:** A teacher or student entity must **first** be detached from all subject before it can be deleted.
           
 #### Entity: subjects

1. **GET** /api/v1/subjects
    - Description - gets all subjects
    - Path Parameters - none
    - Request body - none
    - Responses
        - **200 OK**   
          _examples_:
          ```
          [
            {
                "id": 4,
                "name": "art",
                "students": []
            },
            {
                "id": 5,
                "name": "english",
                "students": [
                  {
                      "email": "henry_b@mymail.com",
                      "firstName": "Henry",
                       "id": 2,
                       "lastName": "Thorbjörn"
                  },
                  {
                       "email": "e.stewart@mymail.com",
                       "firstName": "Elizabeth",
                       "id": 8,
                       "lastName": "Stewart"
                  }
                ],
                "teacher": {
                   "email": "alice@thomasandsons.com",
                   "firstName": "Alice",
                   "id": 11,
                   "lastName": "Thomas"
                }
            }
          ]
          ``` 
          ,

          ``
          []
          ``  
2. **GET**  /api/v1/subjects/{id}
    - Description - get a subject with the specified id
    - Path Parameters
        - **id** - an integer that represents the subject's ID number in the system
    - Request body - none
    - Responses
        - **200 OK**   
          _example:_
          ```
          {
             "id": 3,
             "name": "geography",
             "students": []
             "teacher": {
                   "email": "alice@thomasandsons.com",
                   "firstName": "Alice",
                   "id": 11,
                   "lastName": "Thomas"
             }
          }
          ``` 
        - **404 Not Found**   
          _example:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 38 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/38"
          }
          ```

3. **POST**  /api/v1/subjects
    - Description - add a new subject
    - Path Parameters - none
    - Request body
        1. Required key - name (String)   
           _example:_
           ``` 
            {
              "name": "physics"
            }
           ````
    - Responses
        - **200 OK**   
          _example:_
          ```
          {
             "id": 5,
             "name": "physics",
             "students": []
          }
          ``` 
        - **400 Bad Request**    
          _example:_
          ```
          {
             "errorCode": 404,
             "errorMessages": [
               "Name is a required field"
             ],
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/"
          }
          ```

4. **PUT**  /api/v1/subjects
    - Description - update a subject
    - Path Parameters - none
    - Request body
        1. Required keys - id (int), name (String)
           _example:_
           ``` 
            {
              "id": 24,
              "name": "swedish"
            }
           ````
    - Responses
        - **200 OK**   
          _example:_
          ```
          {
             "id": "24",
             "name": "swedish",
             "students": []
          }
          ``` 
        - **400 Bad Request**   
          _example:_
          ```
          {
             "errorCode": 404,
             "errorMessages": [
               "Name is a required field"
             ],
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/"
          }
          ```     
        - **404 Not Found**   
          _example:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```      

5. **DELETE**  /api/v1/subjects/{id}
    - Description - delete a subject with the given ID 
    - Path Parameters
        - **id** - an integer that represents the subject's ID number in the system
    - Request body - none
    - Responses
        - **204 No Content**

        - **404 Not Found**   
          _example:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```

6. **GET**  /api/v1/subjects/{id}/students
    - Description - get all students for a subject with the specified ID
    - Path Parameters
        - **id** - an integer that represents the subject's ID number in the system
    - Request body - none
    - Responses
        - **200 OK**   
          _example:_
          ```
          [
            {
                "email": "henry_b@mymail.com",
                "firstName": "Henry",
                "id": 2,
                "lastName": "Thorbjörn",
                "phoneNumber": "0732345678"
            },
            {
                "email": "e.stewart@mymail.com",
                "firstName": "Elizabeth",
                "id": 8,
                "lastName": "Stewart"
            }
          ]
          ```
          ,

          ``
          []
          ``

          - **404 Not Found**   
          _example:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```

7. **GET**  /api/v1/subjects/{subjectId}/students/{studentId}
    - Description - get a student with ID (studentId) if the student is enrolled in a subject with ID (subjectId).
    - Path Parameters
        - **subjectId** - an integer that represents the subject's ID number in the system
        - **studentId** - an integer that represents the student's ID number in the system
    - Request body - none
    - Responses
        - **200 OK**   
          _example:_
          ```
            {
                "email": "henry_b@mymail.com",
                "firstName": "Henry",
                "id": 2,
                "lastName": "Thorbjörn",
                "phoneNumber": "0732345678"
            }
          ```

        - **404 Not Found**   
          _examples:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```
          ,
          ```
          {
             "errorCode": 404,
             "message": "student with ID 2 not found in this subject",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/3/students/2"
          }
          ```

8. **PUT**  /api/v1/subjects/{subjectId}/students/{studentId}
    - Description - add a student with ID (studentId) to a subject with ID (subjectId).
    - Path Parameters
        - **subjectId** - an integer that represents the subject's ID number in the system
        - **studentId** - an integer that represents the student's ID number in the system
    - Request body - none
    - Responses
        - **204 No Content**   
         
        - **404 Not Found**   
          _examples:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```
          ,
          ```
          {
             "errorCode": 404,
             "message": "Student with ID 2 not foundt",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/students/2"
          }
          ```

9. **DELETE**  /api/v1/subjects/{subjectId}/students/{studentId}
    - Description - remove a student with ID (studentId) from a subject with ID (subjectId).
    - Path Parameters
        - **subjectId** - an integer that represents the subject's ID number in the system
        - **studentId** - an integer that represents the student's ID number in the system
    - Request body - none
    - Responses
        - **204 No Content**

        - **404 Not Found**   
          _examples:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```
          ,
          ```
          {
             "errorCode": 404,
             "message": "student with ID 2 not found in this subject",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/3/students/2"
          }
          ```

10. **GET**  /api/v1/subjects/{subjectId}/teachers
    - Description - get the teacher for a subject with the specified ID (subjectId)
    - Path Parameters
        - **subjectId** - an integer that represents the subject's ID number in the system
    - Request body - none
    - Responses
        - **200 OK**   
          _example:_
          ```
            {
                "email": "miley_jonsson@mymail.com",
                "firstName": "Miley",
                "id": 28,
                "lastName": "Jonsson",
                "phoneNumber": "0732345678"
            }
          ```
          ,     
          ``
          { }
          ``

        - **404 Not Found**   
          _example:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```

11. **PUT**  /api/v1/subjects/{subjectId}/teachers/{teacherId}
    - Description - add a teacher with ID (teacherId) to a subject with ID (subjectId).
    - Path Parameters
        - **subjectId** - an integer that represents the subject's ID number in the system
        - **teacherId** - an integer that represents the teacher's ID number in the system
    - Request body - none
    - Responses
        - **204 No Content**

        - **404 Not Found**   
          _examples:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```
          ,
          ```
          {
             "errorCode": 404,
             "message": "Teacher with ID 24 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/teachers/24"
          }
          ```

9. **DELETE**  /api/v1/subjects/{subjectId}/teachers/{teacherId}
    - Description - remove a teacher with ID (teacherId) from a subject with ID (subjectId).
    - Path Parameters
        - **subjectId** - an integer that represents the subject's ID number in the system
        - **teacherId** - an integer that represents the teacher's ID number in the system
    - Request body - none
    - Responses
        - **204 No Content**

        - **404 Not Found**   
          _examples:_
          ```
          {
             "errorCode": 404,
             "message": "Subject with ID 64 not found",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/64"
          }
          ```
          ,
          ```
          {
             "errorCode": 404,
             "message": "Teacher with ID 12 not found in this subject",
             "timestamp": "2022-02-26T21:34:47.780382166",
             "url": "/api/v1/subjects/3/teachers/12"
          }
          ```
# Requirement
1. JDK11 or above, set system environment variable JAVA_HOME=${JDK11_PATH}
2. MAVEN3 or above, set system environment variable MAVEN_HOME=${MAVEN3_PATH}

# Getting Started
1. Start App with command: mvnw spring-boot:run, it will start up a local rest api service
2. call API with single policy JSON data as request body, it returns the missed fields as JSON string.
  E.g:  
   URL: "http://localhost:8080/api/findMissingFields/colonel"  
   Request Type: PUT  
   Request Body:  
   
   ```
   {
    "policyId": "9afe73e5-77c9-4677-ba5f-1b9e807396a0",
    "issuer": "nationwide",
    "issueDate": "2021-01-21",
    "renewalDate": "2021-07-22",
    "policyTermMonths": 6,
    "premiumCents": 49234,
    "policyHolder": {
      "address": {
        "number": "292",
        "street": "Hillside Way Apt 398",
        "suffix": "S",
        "city": "Pierce",
        "state": "IA",
        "zip": "30328"
      },
      "email": "Caroline.White897@me.com"
    },
    "operators": [
      {
        "isPrimary": true,
        "name": {
          "firstName": "Caroline",
          "middleName": "",
          "lastName": "White"
        },
        "birthdayRange": {
          "start": "1988-05-22",
          "end": "1989-05-21"
        },
        "gender": "female",
        "driversLicenseState": "IA",
        "driversLicenseStatus": "ValidUSLicense",
        "driversLicenseNumber": "11212211633",
        "relationship": "Named Insured"
      }
    ]
  }
  ```
  
  The policy above missed policy holder's name, it will return:
  ```
   {"policyHolder":{"name":{"firstName":null,"lastName":null}}}, 
  ```
# Test Cases
1. Run test with command: mvnw test

# TODO
1. For time limited, it only implemented API for "Colonel", the API for "Ranchers" was created: "http://localhost:8080/api/findMissingFields/ranchers", but it will always return "{}";
2. For "Colonel", if policy holder's driver license is empty or if the license number contains "XXXX", then it thinks the driver license number is valid, but there should be a fully driver license expression to validate it. 


### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.5/maven-plugin/reference/html/#build-image)


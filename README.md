# Pension-Management-System
The project automate pensioner detail provision, calculate provision, initiate pension disbursement.

# Microservices End Points
### POST : /auth/authenticate
       {"username":"Jhon","password":"Jhon@1234"}
       For more Credential Refer data.sql in Authorization-Microservice 
### POST: /process//PensionDetail
      { "name" : "padmini", "dateOfBirth" : "2000-08-30", "pan" : "PCASD1234Q", "aadharNumber" : 102233445566, "pensionType" : "Family" }
      For more PensionerDetails visit pensionersDetails.cvs file in pensionerDetailsMicroservice
### POST: /process/ProcessPension
      { "aadharNumber" : 102233445566, "pensionAmount": 24500.0}
      For valid aadharNumber or pensionAmount Return {"pensionStatusCode": 10}
      For Invalid aadharNumber or pensionAmount Return {"pensionStatusCode": 21}
## Base Url
      Microservice Gateway: http://localhost:8090/
      Angular App: http://localhost:4200/
      


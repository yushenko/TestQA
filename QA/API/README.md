# Slotegrator API test

Developed in Java with Rest Assured 

### Execution
`mvn clean test` - to run from command line. 

#### /src/test/java/com/slotegrator/api/ folder:

 `ConfProperties.java` - configuration class
 
 `Player.java` - Player object class;
 
 `ApiTests.java` - Api Test file;
 
 `RequestHelper.java` - helper for authentication.
 
 #### /src/test/resources folder:
 
`conf.properties` - configuration constants file
 
 Schemas of JSON response for comparing during tests:
 * `createNewUserResponseJSONSchema.json` - create new player, `POST /players/` request 
 * `getUserResponseJSONSchema.json` - get info about player, `GET /players/{userId}` request 
 

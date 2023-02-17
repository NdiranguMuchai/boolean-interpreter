# Boolean Interpreter :floppy_disk:

This project is a simple boolean logic interpreter that can evaluate simple expressions.
### Prerequisites
The following  should be installed in your system:
* [Java 17 or newer](https://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [Git command line tool](https://help.github.com/articles/set-up-git)
* Your preferred IDE
    * Eclipse with the m2e plugin. Note: when m2e is available, there is an m2 icon in `Help -> About` dialog. If m2e is
      not there, just follow the install process here: https://www.eclipse.org/m2e/
    * [Spring Tools Suite](https://spring.io/tools) (STS)
    * IntelliJ IDEA
    * [VS Code](https://code.visualstudio.com)


## Running Boolean Interpreter locally
This is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line:


```
git clone https://github.com/NdiranguMuchai/boolean-interpreter.git
cd boolean-interpreter
./mvnw package
java -jar target/*.jar
```
You can then access boolean interpreter here: http://localhost:8080/

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```

### Postman
A Postman collection has been provided to assist with endpoint request.

* Open Postman.
* Select the `Import` button.
* Import the file found in this repository under `boolean-interpreter.postman_collection.json`
* Expand the boolean interpreter folder in postman.

Each entry in this collection contains information in its `Body` tab if necessary.

### Querying
* The expressions are sent for interpretation through `HTTP` and the response is provided back in a similar fashion.
* The response body has two fields `code` and `response`
* If successful the code will be 200 and the appropriate answer computed from the expression provided
* Here is a sample success response object
  ```
  {
    "code": "200",
    "response": "T"
  }
  ```
  
* Errors have been handled in a simple manner using error codes
* Below is an example of an error response object when the program doesn't recognise the operator used.

 ```
  {
    "code": "ER-02",
    "response": "Operator(s) not defined in scope"
  }
  ```

Here are some expressions you can play around with:
```
T ∨ F
T ∧ F
(T ∧ F) = F
¬T
  ```

### Limitations
* The project as of now cannot assign new variables as booleans or operators programmatically.
* The only recognized booleans are `true`, `t`, `false`, `f` all case insensitive.
* And the only recognized operators are `and` , `∧`,  `or`, `∨`, `not`,  `equals`,  `is`,  `=` all case insensitive.
* If you wish to include more custom variables, you need to add them manually to the `BooleanConverter.kt` or `OperatorConverter.kt` classes 
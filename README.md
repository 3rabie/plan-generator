# Plan Generator
Installment/Repayment Plan Generator with Java 8 and Spring Boot &amp; MVC

## Running the project locally

### Installation

Clone this repository:

```sh
$ git clone https://github.com/3rabie/plan-generator.git
```

Build with Maven

```sh
$ cd <your_git_home>/plan-generator
$ mvn install
```

### Running the application

Start the Spring Boot application

```sh
$ cd <your_git_home>/plan-generator
$ java -jar target/plan-generator-0.0.1-SNAPSHOT
```

### Making a HTTP POST request

Using your favourite HTTP client eg (Postman, Advanced REST client, Insomnia), run a POST request to the URL:

```
http://localhost:8080/generate-plan
```

As for the JSON payload, start with the example below:

```
{
  "loanAmount": "5000",
  "nominalRate": "5.0",
  "duration": 24,
  "startDate": "2018-01-01T00:00:01Z"
}
```

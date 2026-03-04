# Task Management API

A task management REST API built using the [Spring Boot](https://spring.io/projects/spring-boot) framework in Java.

## Installation & Setup

Please ensure that the following prerequisites are met:

- [Java Development Kit (JDK)](https://en.wikipedia.org/wiki/Java_Development_Kit) and [Maven](https://maven.apache.org/) installed on your system
- An empty [PostgreSQL](https://www.postgresql.org/) database set up and running

```shell
  # Clone the repository
  git clone https://github.com/xinweny/task-management-api.git
  cd task-management-api

  # Create .env file from example and enter values for the environmental variables accordingly
  cp .env.example .env

  # Build and run the application
  mvn spring-boot:run
```

For example tests and use-cases of this API, please download the JSON files in the `.postman` folder and import them into [Postman](https://www.postman.com/).

## Features

- [x] Authentication via JSON Web Token (JWT)
- [x] CRUD functionality for task management, with role-based authorisation
  - `ROLE_ADMIN` can view all, create, assign and delete tasks
  - `ROLE_USER` can view and update (mark as complete/incomplete) assigned tasks
- [x] Data persistence with PostgreSQL database, with indexing for efficient querying
- [x] Error handling with `@ControllerAdvice`
- [x] User input validation with [Spring Validation](https://docs.spring.io/spring-framework/reference/web/webmvc/mvc-controller/ann-validation.html)

## Database Schema

![Database Schema](./db_schema.png 'Database Schema')

## Future Considerations

- Containerisation with [Docker](https://www.docker.com/) and [Docker Compose](https://docs.docker.com/compose/) for easier setup and consistency between systems
- Implementing lazy-loading wherever possible to reduce database load
- Switching to feature-based and/or hexagonal architecture folder structure as application grows
- Implementing refresh token mechanism with shorter-lived access token to improve security
- Increasing granular control of permissions (authorisation) via Privileges (e.g. `READ_TASK_PRIVILEGE`, `CREATE_TASK_PRIVILEGE`) associated to Roles

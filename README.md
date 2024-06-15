# Poster

**Poster** is a web application where people connect with others and share their thoughts.

## Features
- Creation of blog posts.
- Authorization and authentication.
- Searching posts.
- Liking posts.
- Saving data on a database.
- Logging.
- Basic messaging system.

## Running

### Prerequisites

- JDK 21 (JAVA_HOME should point to this version)
- Maven
- Docker + docker-compose

### Steps

- Init Postgres database:

    ```shell
  doocker-compose up
    ``` 
- Before running app for the first time set property `spring.jpa.hibernate.ddl-auto = create`
- If you don't want init data inside you application use docker container terminal and create it manually.

```postgresql
CREATE DATABASE poster;
```

- Run with maven:
    ```bash
  mvnw spring-boot:run
    ```

- To check data inside your database alongside to PostreSQL container there is PgAdmin4 provided. You can use it under:
`localhost:5051/` with email: `admin@admin.com` and password `1234` 

## Technologies

### Spring Framework

**Poster** was natively build with spring-boot framework:

Modules that were used:
- **Spring Data JPA** - works as ORM to communicate with database.
- **Spring Web** - provides framework to build MVC application that run on Tomcat.
- **Spring Thymeleaf** - template engine to create html pages.
- **Spring Security** - provides security configuration with many adhoc solutions to common security vulnerabilities.

Minor helpers:
- **Lombok** - simplifies creation of POJOs
- **Apache-commons-io**, **Apache-commons-lang3** - libraries providing useful utilities

### PostgreSQL
-   **PostgreSQL** is an object-relational database management system that offers more features than MySQL. It gives you more 
    flexibility in data types, scalability, concurrency, and data integrity.

### Docker
- **Docker** - containerization make our applications portable to other OS. Users don't have to bother 
    installing databases and tools to manage them. Additionally, if traffic would be high, application designed from the 
    beginning to run on container, could be easily deployed on CMS like K8S

## Data Entities

- User
    - UserId
    - Username
    - Password (BCrypt encoded)
    - Email
    - First Name
    - Last Name
    - List of Posts
    - List of liked Posts
    - List of Comments
    - ProfilePictureURL
    - List of Friends
    - Role
- Post
    - PostId
    - Content
    - Author (User)
    - Created timestamp
    - Set of users likes
    - List of Comments
- Comment
  - CommentId
  - Post it belongs to
  - Author (User)
  - Comment
  - Created timestamp
- Role
  - User
  - Admin (TBD)

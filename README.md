# Jooby RESTful API

This is a RESTful API built with the Jooby micro-framework and uses the embedded mem database.

### Version
1.0.0

### Usage
These fields are available for query

* id
* title
* isbn
* author

The database is empty, you can populate it using Postman, RestEasy or any other suitable tool


### Installation

Clone the Repo

In the project directory run `mvn jooby:run`

### API Endpints
```sh
$ GET /api/books
$ GET /api/books/:id
$ POST /api/books
$ PUT /api/books
$ DELETE /api/books/:id

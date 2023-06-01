# ToDo Service
The ToDo Service is a simple RESTful Web Service for managing Agile Software Projects. 

## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Setup](#setup)
- [Usage](#usage)
- [Future Improvements](#future-improvements)

## Introduction

It allows you to create boards, tasks, and users to organize and track your projects.

## Features

The ToDo Service provides the following endpoints:

The ToDo Service provides the following endpoints:

- `GET /boards`: Returns a list of all boards.
- `POST /boards`: Creates a new board.
- `GET /boards/{id}`: Returns a specific board with all its tasks.
- `DELETE /boards/{id}`: Deletes a board.
- `POST /boards/{id}/tasks`: Creates a new task in a board.
- `PUT /tasks/{id}`: Updates an existing task.
- `PATCH /tasks/{id}`: Updates specific attributes of a task.
- `DELETE /tasks/{id}`: Deletes a task.

The service also integrates with a Centralised User Service to retrieve user information associated with tasks. User data is fetched using a Random Data Service, such as [randomuser.me](https://randomuser.me/api/).

Additionally, the ToDo Service listens for a webhook `/webhooks/user-deleted` to handle user deletions from the Centralised User Service. When a user is deleted, the service receives a `POST` request to the webhook with the following payload:

```json
{
  "time": "2021-07-06T14:48:00.000Z",
  "data": {
    "user": "bec855a4-8db7-4392-bdb2-9cc594a7658d"
  }
}
```
## Technologies Used

The Todo service is built using the following technologies:

- Kotlin: The programming language used for the backend development.
- Spring Boot: The framework used to create the RESTful API and manage application components.
- No database integration is used, because of the simplicity of the challenge in memory solution is used.
- JUnit: The testing framework used for unit testing.
- Mockito: The mocking framework used for creating test doubles in unit tests.

## Setup

To run the ToDo Service locally, follow these steps:

- Navigate to the project directory: cd todo-service
- Install the dependencies: gradle build
- Start the service: gradle bootRun
- The service will be accessible at http://localhost:8080.

## Usage

**Creating a Board**
To create a new board, make a POST request to /boards with the board attributes (without the id). The newly created board will be returned in the response.

**Getting a Board**
To retrieve a specific board with its tasks, make a GET request to /boards/{id} where {id} is the ID of the board.

**Deleting a Board**
To delete a board, make a DELETE request to /boards/{id} where {id} is the ID of the board to be deleted.

**Creating a Task**
To create a new task in a board, make a POST request to /boards/{id}/tasks with the task attributes (without the id and status). The newly created task with the status CREATED will be returned in the response.

**Updating a Task**
To update an existing task, make a PUT request to /tasks/{id} where {id} is the ID of the task. Provide the updated task attributes in the request body.

**Updating Task Attributes**
To update specific attributes of a task, make a PATCH request to /tasks/{id} where {id} is the ID of the task. Provide the updated attributes in the request body.

**Deleting a Task**
To delete a task, make a DELETE request to /tasks/{id} where {id} is the ID of the task to be deleted.

## Future Improvements

While the current version of the Todo service provides basic functionality, there are several areas that could be improved:

- Database integration
- Authentication and Authorization: Implement a secure authentication mechanism to protect the API endpoints and restrict access based on user roles and permissions.
- Validation and Error Handling: Enhance the input validation and error handling mechanisms to provide more detailed error messages and improve the overall user experience.
- Implement caching: Consider implementing caching in the future to improve performance and reduce database load.
- Logging and Monitoring: Implement logging and monitoring features to track system events and performance metrics, enabling better system management and issue troubleshooting.
- Frontend Development: Develop a user-friendly web interface or a dedicated client application to facilitate easier interaction with the system.
- Performance Optimization: Analyze and optimize the application's performance by identifying bottlenecks

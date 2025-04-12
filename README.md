# Asset Management System

This is a comprehensive asset management system backend built with Spring Boot. The application allows organizations to track and manage their assets, handle asset requests, classify assets, and manage user access.

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [API Documentation](#api-documentation)
- [Authentication](#authentication)
- [Database Schema](#database-schema)
- [Contributing](#contributing)

## Features

- **Asset Request Management**: Create, view, update, and track asset requests
- **Employee Management**: Manage employee data including roles and permissions
- **Asset Classification**: Categorize assets by type, location, and classification
- **Approval Workflow**: Multi-level approval workflow for asset requests
- **JWT Authentication**: Secure API access with JWT token-based authentication
- **Role-based Access Control**: Different access levels for employees and managers

## Technology Stack

- **Java 21**
- **Spring Boot 3.2.2**
- **Spring Security** with JWT for authentication
- **Spring Data JPA** for data persistence
- **PostgreSQL** for database
- **Maven** for dependency management
- **Lombok** for reducing boilerplate code
- **Spring Validation** for input validation

## Project Structure

The project follows a modular architecture, organized by domain:

- **AssetRequest**: Handles asset request creation, management, and workflows
- **Category**: Manages asset categories
- **Classification**: Handles asset classification systems
- **Employee**: Manages employee data, authentication, and authorization
- **Type**: Manages asset types
- **Location**: Manages physical locations of assets
- **Visitor Management**: Handles visitor management system (appears to be in progress)

## Setup and Installation

### Prerequisites

- Java 21
- PostgreSQL 
- Maven

### Database Setup

1. Create a PostgreSQL database:
```sql
CREATE DATABASE asset_mgt;
```

2. Update database configuration in `src/main/resources/application.yml` if needed:
```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/asset_mgt
    username: your-username
    password: your-password
    driver-class-name: org.postgresql.Driver
```

### Build and Run

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
```bash
./mvnw clean install
```

4. Run the application:
```bash
./mvnw spring-boot:run
```

The application will start on http://localhost:8080 by default.

## API Documentation

### Asset Request Endpoints

- `GET /api/v1/AssetRequest/getAll/`: Get all asset requests
- `POST /api/v1/AssetRequest/createAssetRequest/`: Create a new asset request
- `PUT /api/v1/AssetRequest/{requestId}/approve`: Approve an asset request
- `PUT /api/v1/AssetRequest/{requestId}/pullback`: Pull back an asset request
- `DELETE /api/v1/AssetRequest/{assetreqID}`: Delete an asset request
- `DELETE /api/v1/AssetRequest/deleteSelected`: Delete multiple asset requests
- `PUT /api/v1/AssetRequest/updateAssetRequest`: Update an asset request
- `PUT /api/v1/AssetRequest/AssetRequestemployee/{AssetRequestsID}`: Update asset request to pull back status
- `PUT /api/v1/AssetRequest/AssetRequestManager/{AssetRequestsID}`: Update asset request to approved status

### Employee Endpoints

- `POST /api/v1/employee/login`: Authenticate an employee
- `POST /api/v1/employee/CreateEmployee/`: Create a new employee
- `GET /api/v1/employee/`: Get all employees
- `GET /api/v1/employee/{id}`: Get an employee by ID
- `DELETE /api/v1/employee/{id}`: Delete an employee
- `DELETE /api/v1/employee/deleteSelected`: Delete multiple employees
- `PUT /api/v1/employee/updateEmployee`: Update an employee
- `GET /api/v1/employee/registerEmployee/{employeeId}`: Register an employee

### Category Endpoints

- `POST /api/v1/category/CreateCategory/`: Create a new category
- `POST /api/v1/category/updateStatus`: Update category status
- `GET /api/v1/category/`: Get all categories
- `GET /api/v1/category/{id}`: Get a category by ID
- `DELETE /api/v1/category/{id}`: Delete a category
- `DELETE /api/v1/category/deleteSelected`: Delete multiple categories
- `PUT /api/v1/category/updateCategory`: Update a category

### Classification Endpoints

- `POST /api/v1/classification/CreateClassification`: Create a new classification
- `GET /api/v1/classification/`: Get all classifications
- `GET /api/v1/classification/{id}`: Get a classification by ID
- `DELETE /api/v1/classification/{id}`: Delete a classification
- `DELETE /api/v1/classification/deleteSelected`: Delete multiple classifications
- `PUT /api/v1/classification/updateClassification`: Update a classification
- `GET /api/v1/classification/findByLocation/{locationId}`: Find classifications by location

### Type Endpoints

- `POST /api/v1/type/Createtype`: Create a new type
- `GET /api/v1/type/`: Get all types
- `GET /api/v1/type/{id}`: Get a type by ID
- `DELETE /api/v1/type/{id}`: Delete a type

### Location Endpoints

- `POST /api/v1/location/CreateLocation/`: Create a new location
- `GET /api/v1/location/`: Get all locations
- `GET /api/v1/location/{id}`: Get a location by ID
- `DELETE /api/v1/location/{id}`: Delete a location
- `DELETE /api/v1/location/deleteSelected`: Delete multiple locations
- `PUT /api/v1/location/updateLocation`: Update a location

## Authentication

The system uses JWT (JSON Web Token) for authentication. To access protected endpoints:

1. Authenticate using the `/api/v1/employee/login` endpoint with valid credentials
2. Include the returned JWT token in the Authorization header of subsequent requests:
   ```
   Authorization: Bearer <your-jwt-token>
   ```

## Database Schema

The system uses several interconnected entities:

- **Asset_Requests**: Main entity for asset requests
- **StatusModel**: Tracks status of requests (pending, approved, rejected, etc.)
- **ColorModel**: Provides colors for status visualization
- **asset_category**: Categorizes assets
- **asset_classification**: Classifies assets by purpose or department
- **asset_type**: Defines types of assets
- **employees**: Stores employee information
- **locations**: Tracks physical locations

The system uses JPA relationships to maintain referential integrity between entities.

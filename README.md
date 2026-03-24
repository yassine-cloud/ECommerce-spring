# ECommerce Spring Boot Backend

This project is a Spring Boot-based backend for an E-Commerce application. It provides RESTful APIs for managing users, products, categories, and shopping carts (paniers). The backend is designed to be secure, scalable, and easy to integrate with frontend applications.

## Features
- User authentication and authorization (JWT-based)
- Product and category management
- Shopping cart (panier) management
- RESTful API endpoints
- Swagger/OpenAPI documentation
- Secure configuration with Spring Security

## Project Structure
```
ECommerce-spring/
├── src/
│   ├── main/
│   │   ├── java/com/iset/ECommerce/
│   │   │   ├── config/           # Security and Swagger configuration
│   │   │   ├── controller/       # REST controllers
│   │   │   ├── dao/              # Data access interfaces
│   │   │   ├── dto/              # Data transfer objects
│   │   │   ├── entity/           # JPA entities
│   │   │   ├── filter/           # JWT filter
│   │   │   └── metier/           # Service layer
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
│       └── java/com/iset/ECommerce/
├── pom.xml
└── ...
```

## Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run
1. Clone the repository:
   ```sh
   git clone https://github.com/yassine-cloud/ECommerce-spring.git
   cd ECommerce-spring
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```

The backend will start on [http://localhost:8080](http://localhost:8080).

### API Documentation
- Swagger UI is available at: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Configuration
- Edit `src/main/resources/application.properties` to configure database and other settings.

## License
This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.

## Author
- Yassine SAADAOUI
- saadaouiy51@gmail.com

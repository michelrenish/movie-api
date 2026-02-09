# Movie API - RESTful Web Service

A simple RESTful API for managing a movie catalog, built with Java and Spring Boot. This application demonstrates basic CRUD operations with in-memory data storage.

## 🎯 Features

- **Add Movies**: Create new movie entries with validation
- **Retrieve Movies**: Get movies by ID or retrieve all movies
- **Input Validation**: Comprehensive validation for all required fields
- **In-Memory Storage**: Uses ArrayList for data persistence during runtime
- **RESTful Design**: Follows REST API best practices

## 🛠️ Technologies Used

- **Java 17**: Programming language
- **Spring Boot 3.2.0**: Application framework
- **Spring Web**: For building REST APIs
- **Spring Validation**: For input validation
- **Lombok**: To reduce boilerplate code
- **Maven**: Build and dependency management

## 📋 Prerequisites

- Java 17 or higher
- Maven 3.6 or higher

## 🚀 How to Build and Run

### 1. Clone or Download the Project

Navigate to the project directory:
```bash
cd movie-api
```

### 2. Build the Application

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8000`

## 📚 Swagger API Documentation

The application includes **Swagger UI** for interactive API documentation and testing.

Once the application is running, access:
- **Swagger UI**: [http://localhost:8000/swagger-ui.html](http://localhost:8000/swagger-ui.html)
- **OpenAPI Spec**: [http://localhost:8000/api-docs](http://localhost:8000/api-docs)

Swagger UI provides:
- Interactive API testing interface
- Automatic API documentation
- Request/response schemas
- Try-it-out functionality for all endpoints

## 📡 API Endpoints

### 1. Add a New Movie

**Endpoint**: `POST /api/movies`

**Request Body**:
```json
{
  "title": "The Matrix",
  "description": "A computer hacker learns about the true nature of reality and his role in the war against its controllers.",
  "releaseYear": 1999,
  "genre": "Sci-Fi",
  "rating": 8.7
}
```

**Response** (201 Created):
```json
{
  "id": 4,
  "title": "The Matrix",
  "description": "A computer hacker learns about the true nature of reality and his role in the war against its controllers.",
  "releaseYear": 1999,
  "genre": "Sci-Fi",
  "rating": 8.7
}
```

**cURL Example**:
```bash
curl -X POST http://localhost:8000/api/movies \
  -H "Content-Type: application/json" \
  -d "{\"title\":\"The Matrix\",\"description\":\"A computer hacker learns about the true nature of reality and his role in the war against its controllers.\",\"releaseYear\":1999,\"genre\":\"Sci-Fi\",\"rating\":8.7}"
```

### 2. Get Movie by ID

**Endpoint**: `GET /api/movies/{id}`

**Response** (200 OK):
```json
{
  "id": 1,
  "title": "The Shawshank Redemption",
  "description": "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
  "releaseYear": 1994,
  "genre": "Drama",
  "rating": 9.3
}
```

**Response** (404 Not Found) - If movie doesn't exist

**cURL Example**:
```bash
curl http://localhost:8000/api/movies/1
```

### 3. Get All Movies (Bonus Endpoint)

**Endpoint**: `GET /api/movies`

**Response** (200 OK):
```json
[
  {
    "id": 1,
    "title": "The Shawshank Redemption",
    "description": "Two imprisoned men bond over a number of years...",
    "releaseYear": 1994,
    "genre": "Drama",
    "rating": 9.3
  },
  {
    "id": 2,
    "title": "Inception",
    "description": "A thief who steals corporate secrets...",
    "releaseYear": 2010,
    "genre": "Sci-Fi",
    "rating": 8.8
  }
]
```

**cURL Example**:
```bash
curl http://localhost:8000/api/movies
```

## ✅ Input Validation

The API validates all input fields when adding a movie:

| Field | Validation Rules |
|-------|-----------------|
| `title` | Required, cannot be blank |
| `description` | Required, cannot be blank |
| `releaseYear` | Required, must be between 1888 and 2031 |
| `genre` | Required, cannot be blank |
| `rating` | Optional, must be between 0 and 10 if provided |

### Validation Error Example

**Request with missing fields**:
```json
{
  "title": "",
  "releaseYear": 2050,
  "rating": 15
}
```

**Response** (400 Bad Request):
```json
{
  "title": "Title is required and cannot be blank",
  "description": "Description is required and cannot be blank",
  "genre": "Genre is required and cannot be blank",
  "releaseYear": "Release year cannot be more than 5 years in the future",
  "rating": "Rating must be at most 10"
}
```

## 📁 Project Structure

```
movie-api/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── movieapi/
│       │           ├── MovieApiApplication.java      # Main application class
│       │           ├── model/
│       │           │   └── Movie.java                # Movie entity with validation
│       │           ├── service/
│       │           │   └── MovieService.java         # Business logic layer
│       │           └── controller/
│       │               └── MovieController.java      # REST endpoints
│       └── resources/
│           └── application.properties                # Configuration
├── pom.xml                                          # Maven dependencies
└── README.md                                        # This file
```

## 🔧 Implementation Details

### In-Memory Data Storage

The application uses an `ArrayList<Movie>` to store movies in memory. This means:
- Data persists only during the application runtime
- All data is lost when the application stops
- Pre-populated with 3 sample movies for testing

### ID Generation

IDs are auto-generated using `AtomicLong` starting from 1. Each new movie receives a unique sequential ID.

### Error Handling

- **400 Bad Request**: Returned when validation fails with detailed error messages
- **404 Not Found**: Returned when a movie with the specified ID doesn't exist
- **201 Created**: Returned when a movie is successfully added

## 🧪 Testing the API

You can test the API using:
- **cURL**: Command-line examples provided above
- **Postman**: Import the endpoints and test interactively
- **Swagger UI**: Interactive documentation at `http://localhost:8000/swagger-ui.html`
- **Browser**: Use for GET requests (e.g., `http://localhost:8000/api/movies`)

### Quick Test Sequence

1. Start the application
2. Get all movies to see the pre-populated data:
   ```bash
   curl http://localhost:8000/api/movies
   ```
3. Add a new movie:
   ```bash
   curl -X POST http://localhost:8000/api/movies \
     -H "Content-Type: application/json" \
     -d "{\"title\":\"Interstellar\",\"description\":\"A team of explorers travel through a wormhole in space.\",\"releaseYear\":2014,\"genre\":\"Sci-Fi\",\"rating\":8.6}"
   ```
4. Get the newly added movie by ID (should be ID 4):
   ```bash
   curl http://localhost:8000/api/movies/4
   ```
5. Open Swagger UI for interactive testing:
   - Navigate to `http://localhost:8000/swagger-ui.html`

## 🚀 Deployment

This application is ready to deploy to **Render.com** with their **completely free tier**.

### Quick Deploy to Render (100% Free!)

1. **Push to GitHub**:
   ```bash
   git init
   git add .
   git commit -m "Initial commit"
   git remote add origin https://github.com/YOUR_USERNAME/movie-api.git
   git push -u origin main
   ```

2. **Deploy on Render**:
   - Sign up at [render.com](https://render.com) (no credit card required)
   - Click "New +" → "Web Service"
   - Connect your GitHub repository
   - Render auto-detects the `render.yaml` configuration
   - Click "Create Web Service"

3. **Access your deployed API**:
   - API: `https://your-app.onrender.com/api/movies`
   - Swagger UI: `https://your-app.onrender.com/swagger-ui.html`
   - Health Check: `https://your-app.onrender.com/actuator/health`

**Note**: Free tier apps sleep after 15 minutes of inactivity. First request after sleep takes ~30 seconds to wake up.

### Deployment Configuration

The following files are included for Render deployment:
- `render.yaml` - Render service configuration (free tier)
- `application.properties` - Dynamic port binding
- `.gitignore` - Git exclusions

## 📝 Notes

- This application uses in-memory storage (data is lost on restart)
- For production use, consider integrating a database (PostgreSQL, MongoDB)
- The application is **deployment-ready** with Railway.app configuration
- Server port is configurable via `PORT` environment variable
- Health check endpoint available at `/actuator/health`
- Pre-populated with 3 sample movies for immediate testing

## 👨‍💻 Author

Created as a demonstration of RESTful API development with Spring Boot.

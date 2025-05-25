# aBay-RESTFul-API
A RESTFul API for an online market platform


## Running with Docker Compose

This project uses Docker Compose to simplify the setup and execution of the service and its dependencies (like a database).

### Prerequisites

*   [Docker](https://docs.docker.com/get-docker/) installed on your system.
*   [Docker Compose](https://docs.docker.com/compose/install/) installed (Docker Desktop for Windows and Mac includes Docker Compose).
*   A `Dockerfile` in the root of this project that defines how to build and run the `aBay-RESTFul-API` service.

### `docker-compose.yml` File Structure

The `docker-compose.yml` file at the root of this project orchestrates the different components of our application. It defines the following:

*   **Services**:
    *   `abay-api`:
        *   **Description**: This is the main application service for the aBay RESTFul API.
        *   **Build**: It's built using the `Dockerfile` located in the project's root directory.
        *   **Ports**: Exposes port `8080` on your host machine, mapped to port `8080` in the container (e.g., accessible via `http://localhost:8080`).
        *   **Environment**: Configured with environment variables for database connection (e.g., `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD`) and potentially other application settings. **You must review and adjust these variables in `docker-compose.yml` to match your application's specific requirements.**
        *   **Dependencies**: Depends on the `db` service, ensuring the database is ready before the API starts.
        *   **Network**: Connected to the `abay-network`.
    *   `db`:
        *   **Description**: This service runs a PostgreSQL database instance.
        *   **Image**: Uses the official `postgres:15` image from Docker Hub.
        *   **Ports**: Exposes port `5433` on your host machine, mapped to the default PostgreSQL port `5432` in the container. This allows direct database access from your host (e.g., using a SQL client) without conflicting with a local PostgreSQL instance.
        *   **Environment**: Sets up the database with a default user (`abayuser`), password (`abaypassword`), and database name (`abaydb`). These credentials are used by the `abay-api` service.
        *   **Volumes**: Uses a named volume `abay_db_data` to persist database data, so your data isn't lost when containers are stopped or removed.
        *   **Network**: Connected to the `abay-network`.
        *   **Healthcheck**: Includes a healthcheck to ensure the database is ready to accept connections.

*   **Volumes**:
    *   `abay_db_data`: A named Docker volume used by the `db` service to persist PostgreSQL data across container restarts.

*   **Networks**:
    *   `abay-network`: A custom bridge network that allows the `abay-api` and `db` services to discover and communicate with each other using their service names (e.g., `abay-api` can connect to `db` at `db:5432`).

### Instructions to Run the Service

1.  **Clone the Repository** (if you haven't already):
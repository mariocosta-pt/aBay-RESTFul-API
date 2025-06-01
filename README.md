# aBay-RESTFul-API

A RESTful API for an online market platform, developed com Spring Boot e PostgreSQL.

![Builder](https://github.com/mariocosta-pt/aBay-RESTFul-API/actions/workflows/builder.yml/badge.svg)
![Deployer](https://github.com/mariocosta-pt/aBay-RESTFul-API/actions/workflows/deploy.yml/badge.svg)

---

## 🎯 Project Objective

This project implements a CI/CD (Continuous Integration and Continuous Delivery) pipeline using **GitHub Actions** to automatically build, test, package, publish, and deploy a RESTful service hosted in a public Docker Registry.

Whenever code is pushed to the `main` branch, the following steps are executed automatically:

1. 🔨 Build the project
2. ✅ Run unit and integration tests
3. 🐳 Build the Docker image
4. 🚀 Push the Docker image to [Docker Hub](https://hub.docker.com/r/marioatccosta/abay-restful-api)
5. 📦 Deploy the image via SSH to `HOST_MACHINE` using Docker Compose

---

## 🔧 CI/CD Pipeline Implementation

The CI/CD pipeline is split into two workflows:

### ✅ [`builder.yml`](.github/workflows/builder.yml)

Triggered on every push to `main`. It performs:

- Project checkout
- Java 17 setup
- Maven build (`mvn package`)
- Test execution (`mvn test`)
- Docker image build (`docker build`)
- Docker image push to Docker Hub

Secrets used:
- `DOCKER_USERNAME`
- `DOCKER_PASSWORD`

Docker image: [`marioatccosta/abay-restful-api:latest`](https://hub.docker.com/r/marioatccosta/abay-restful-api)

---

### 🚀 [`deployer.yml`](.github/workflows/deploy.yml)

Triggered automatically when the `builder` workflow completes successfully. It performs:

- Copies `docker-compose.yml` to the remote server via `scp`
- Connects to `HOST_MACHINE` via `ssh`
- Pulls the latest Docker image
- Executes `docker compose up -d` on the server

Secrets used:
- `SSH_HOST`
- `SSH_PORT`
- `SSH_USER_DEV1` / `SSH_KEY_DEV1` (and optionally for other users)

The SSH credentials are selected dynamically based on the commit author.

---

## 📦 Docker & Docker Compose

### 🗂 `Dockerfile`

- Based on `eclipse-temurin:17-jdk-alpine`
- Builds the `.jar` with Maven
- Runs the Spring Boot app with `prod` profile
- Exposes port `8080`

### 🧱 `docker-compose.yml`

Includes two services:

- **app**: the Spring Boot REST API (`marioatccosta/abay-restful-api:latest`)
- **postgres**: PostgreSQL 15 with persistent volume

### ▶️ How to Run Locally

1. Clone the repository:

```bash
git clone https://github.com/mariocosta-pt/aBay-RESTFul-API.git
cd aBay-RESTFul-API
```

2. Build the application

```bash
mvn clean package -DskipTests
```

3. Start the app and DB using Docker 
```bash
docker compose up --build 
```

4. Access the API at http://localhost:8080

### 🧪 How to Run Tests

```
mvn test
```

### ⚙️ Configuration & Parametrization

Critical settings are fully parameterized:
- All database credentials are configured via environment variables.

- Docker image and tag are defined in the builder.yml via ${{ secrets.DOCKER_USERNAME }}/image:latest

- SSH credentials and targets are fully externalized using GitHub secrets

- The deployer.yml dynamically selects the SSH user and password based on the push author.

### 🌐 Links

- 🧪 GitHub Repo: https://github.com/mariocosta-pt/aBay-RESTFul-API
- 🐳 Docker Hub: https://hub.docker.com/r/marioatccosta/abay-restful-api

### 📌 Decisions Made

CI and CD were split into separate workflows (builder and deployer) for modularity and visibility
Used appleboy/ssh-action and scp-action to deploy and transfer files securely
Chose Docker Compose for orchestration on the server to simplify future service expansion (e.g., Redis, Nginx, etc.)
Secrets management entirely handled via GitHub Actions Secrets interface.

### 🙋 Author

- Mário Costa - m61727 - github.com/mariocosta-pt
- Gonçalo Pires - m60716 - github.com/GoncaloPires19 
- Miguel M - 

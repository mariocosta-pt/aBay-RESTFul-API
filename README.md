# aBay-RESTFul-API

A RESTful API for an online market platform.

---

## 🐳 Running with Docker Compose

This project uses Docker Compose to simplify the setup and execution of the API and its PostgreSQL database.

---

## 📦 Prerequisites

- [Docker](https://docs.docker.com/get-docker/) installed.
- [Docker Compose](https://docs.docker.com/compose/install/) (included in Docker Desktop).

---

## 🧱 Project Structure

### 🔧 Dockerfile

- Uses `eclipse-temurin:17-jdk-alpine` as the base image.
- Builds and runs a Spring Boot `.jar` with the `prod` profile.
- Exposes port `8080`.

### 🗂 docker-compose.yml

Defines the following services:

#### ✅ app (aBay RESTFul API)

- **Build**: From the `Dockerfile` in the project root.
- **Image**: `marioatccosta/abay-restful-api:latest`.
- **Ports**: Maps `8080:8080` (accessible at `http://localhost:8080`).
- **Environment**:
    - `DB_USERNAME`: database user.
    - `DB_PASSWORD`: database password.
- **Depends on**: `postgres` service.

#### 🐘 postgres (database)

- **Image**: `postgres:15`.
- **Ports**: `5432:5432`.
- **Environment**:
    - `POSTGRES_DB=ES`
    - `POSTGRES_USER=postgres`
    - `POSTGRES_PASSWORD=pires`
- **Volume**: Persists data in `pgdata`.

#### 💾 Volumes

```yaml
volumes:
  pgdata:
```

---

## ▶️ How to Run

1. **Clone the repository**:

```bash
git clone https://github.com/your-username/aBay-RESTFul-API.git
cd aBay-RESTFul-API
```

2. **Build the .jar** (outside the container):

```bash
./mvnw clean package -DskipTests
```

3. **Start the application with Docker Compose**:

```bash
docker-compose up --build
```

4. **Access the API at**: [http://localhost:8080](http://localhost:8080)

---

## 🧪 Testing

Run tests with:

```bash
./mvnw test
```

---

## 🔐 Security Notes

- Environment variables like `DB_USERNAME` and `DB_PASSWORD` can be stored in a `.env` file (excluded from version control).
- Avoid hardcoding sensitive credentials in `docker-compose.yml` for production.

---

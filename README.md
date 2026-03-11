# Event-Driven Architecture Project

This project demonstrates an event-driven architecture using Spring Boot, Kafka, and H2 database.

## Prerequisites
- Java 21
- Maven
- Docker and Docker Compose

## How to Run

1. Build the project:
   ```bash
   mvn clean install
   ```
2. Start the infrastructure and applications:
   ```bash
   docker-compose up --build
   ```

## Services and Ports
- **Training Management Service**: http://localhost:8081
- **Review Service**: http://localhost:8082
- **Training Catalogue Service**: http://localhost:8083
- **Notification Service**: http://localhost:8084

## Scenarios

### Sunny Day Scenario (Approval)
1. **Submit a training draft**:
   ```bash
   curl -X POST http://localhost:8081/training-drafts \
   -H "Content-Type: application/json" \
   -d '{"title": "Spring Boot Expert", "description": "Advanced Spring Boot course"}'
   ```
   *Note: This will return a UUID (e.g., `550e8400-e29b-41d4-a716-446655440000`). Use it in the following steps.*

2. **Verify statuses**:
   - Training Management: `GET http://localhost:8081/training-drafts/{id}` -> Should be `APPROVED`
   - Review Service: `GET http://localhost:8082/reviews/{id}` -> Should be `APPROVED`
   - Training Catalogue: `GET http://localhost:8083/trainings/{id}` -> Should be `PUBLISHED`
   - Notification Service: `GET http://localhost:8084/notifications/{id}` -> Should show 4 emails (Submitted, Approved, Published)

### Rainy Day Scenario (Rejection)
1. **Submit a training draft with "REJECT" in the title**:
   ```bash
   curl -X POST http://localhost:8081/training-drafts \
   -H "Content-Type: application/json" \
   -d '{"title": "REJECT this course", "description": "This should fail"}'
   ```
   *Note the returned UUID.*

2. **Verify statuses**:
   - Training Management: `GET http://localhost:8081/training-drafts/{id}` -> Should be `REJECTED: Title contains REJECT`
   - Review Service: `GET http://localhost:8082/reviews/{id}` -> Should be `REJECTED`
   - Training Catalogue: `GET http://localhost:8083/trainings/{id}` -> Should be `REJECTED`
   - Notification Service: `GET http://localhost:8084/notifications/{id}` -> Should show 2 emails (Submitted, Rejected)

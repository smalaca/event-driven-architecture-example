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
- **Application Management Service**: http://localhost:8081
- **Review Service**: http://localhost:8082
- **Application Catalogue Service**: http://localhost:8083
- **Notification Service**: http://localhost:8084

## Scenarios

### Sunny Day Scenario (Approval)
1. **Submit an application draft**:
   ```bash
   curl -X POST http://localhost:8081/application-drafts \
   -H "Content-Type: application/json" \
   -d '{"title": "Spring Boot Expert", "description": "Advanced Spring Boot course"}'
   ```
   *Note: This will return a UUID (e.g., `550e8400-e29b-41d4-a716-446655440000`). Use it in the following steps.*

2. **Verify statuses**:
   - Application Management: `GET http://localhost:8081/application-drafts/{id}` -> Should be `APPROVED`
   - Review Service: `GET http://localhost:8082/reviews/{id}` -> Should be `APPROVED`
   - Application Catalogue: `GET http://localhost:8083/applications/{id}` -> Should be `PUBLISHED`
   - Notification Service: `GET http://localhost:8084/notifications/{id}` -> Should show 4 emails (Submitted, Approved, Published)

### Rainy Day Scenario (Rejection)
1. **Submit an application draft with "REJECT" in the title**:
   ```bash
   curl -X POST http://localhost:8081/application-drafts \
   -H "Content-Type: application/json" \
   -d '{"title": "REJECT this course", "description": "This should fail"}'
   ```
   *Note the returned UUID.*

2.A. **Verify statuses of single draft**:
   - Application Management: `GET http://localhost:8081/application-drafts/{id}` -> Should be `REJECTED: Title contains REJECT`
   - Review Service: `GET http://localhost:8082/reviews/{id}` -> Should be `REJECTED`
   - Application Catalogue: `GET http://localhost:8083/applications/{id}` -> Should be `REJECTED`
   - Notification Service: `GET http://localhost:8084/notifications/{id}` -> Should show 2 emails (Submitted, Rejected)

2.B. **Verify statuses of all drafts**:
  - Application Management: `GET http://localhost:8081/application-drafts`
  - Review Service: `GET http://localhost:8082/reviews`
  - Application Catalogue: `GET http://localhost:8083/applications`
  - Notification Service: `GET http://localhost:8084/notifications`

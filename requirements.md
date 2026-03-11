## Main Goal
This project should present event-driven architecture.

## High-Level Architecture
- Training Management Service
- Review Service
- Training Catalogue Service
- Notification Service

## Supported Feature
### Adding training draft
1. Invoke rest endpoint on Training Management Service
2. Training Management Service stores training draft in DB
3. Training Management Service publishes event to Kafka TrainingDraftSubmitted
4. Review Service subscribes to Kafka and either approves (TrainingDraftApproved) or rejects (TrainingDraftRejected) training draft
5. Review Service publishes events to Kafka (TrainingDraftApproved, TrainingDraftRejected)
6. Training Catalogue Service subscribes to Kafka (TrainingDraftApproved, TrainingDraftRejected) and stores training 
   draft review status in DB
7. If Training was approved Training Catalogue Service publishes event to Kafka (TrainingPublished)
8. Training Catalogue Service subscribes to Kafka (TrainingPublished) and stores training in DB 
9. Notification Service subscribes to Kafka (TrainingDraftSubmitted, TrainingDraftApproved, TrainingDraftRejected,  
   TrainingPublished) and sends email to user

### Implementation 
1. Processing of the events does not have to require any logic - use some dummy logic.
2. Sending emails should be faked somehow.
3. Each service have to expose REST API to validate what happened:
   - Training Management Service
     - what is the status of the training draft?
   - Review Service
     - what is the status of the review for the training draft?
   - Training Catalogue Service
     - what is the status of the training?
   - Notification Service
     - what mails were sent?

## Technical Structure
### Docker
Need a docker compose file that will:
- create kafka broker
- create all required topics
- run application: Training Management Service
- run application: Review Service
- run application: Training Catalogue Service
- run application: Notification Service

### Services
- multimodule project
- every module is maven project
- Spring Boot
- if DB needed - use H2

### Instructions
- explain how to trigger sunny day scenario
- explain how to trigger rainy day scenarios
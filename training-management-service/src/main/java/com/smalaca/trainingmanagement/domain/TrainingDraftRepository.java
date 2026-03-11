package com.smalaca.trainingmanagement.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface TrainingDraftRepository extends CrudRepository<TrainingDraft, UUID> {
}

package com.smalaca.applicationmanagement.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ApplicationDraftRepository extends CrudRepository<ApplicationDraft, UUID> {
}

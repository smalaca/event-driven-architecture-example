package com.smalaca.notification.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.UUID;

public interface SentEmailRepository extends CrudRepository<SentEmail, Long> {
    List<SentEmail> findByApplicationDraftId(UUID applicationDraftId);
}

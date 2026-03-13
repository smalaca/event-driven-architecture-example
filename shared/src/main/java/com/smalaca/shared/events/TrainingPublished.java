package com.smalaca.shared.events;

import java.math.BigDecimal;
import java.util.UUID;

public record TrainingPublished(UUID trainingDraftId, String title, String description, BigDecimal price) {
}

package com.smalaca.shared.events;

import java.util.UUID;

public record TrainingPublished(UUID trainingDraftId, String title) {
}

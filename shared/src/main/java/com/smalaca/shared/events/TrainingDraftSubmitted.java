package com.smalaca.shared.events;

import java.util.UUID;

public record TrainingDraftSubmitted(UUID trainingDraftId, String title, String description) {
}

package com.smalaca.shared.events;

import java.util.UUID;

public record TrainingDraftRejected(UUID trainingDraftId, String reason) {
}

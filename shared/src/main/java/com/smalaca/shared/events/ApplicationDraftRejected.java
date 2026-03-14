package com.smalaca.shared.events;

import java.util.UUID;

public record ApplicationDraftRejected(UUID applicationDraftId, String reason) {
}

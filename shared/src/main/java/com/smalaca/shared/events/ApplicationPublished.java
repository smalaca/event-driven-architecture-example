package com.smalaca.shared.events;

import java.math.BigDecimal;
import java.util.UUID;

public record ApplicationPublished(UUID applicationDraftId, String title, String description, BigDecimal price) {
}

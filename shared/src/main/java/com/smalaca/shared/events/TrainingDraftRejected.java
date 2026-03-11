package com.smalaca.shared.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDraftRejected {
    private UUID trainingDraftId;
    private String reason;
}

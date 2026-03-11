package com.smalaca.shared.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingDraftSubmitted {
    private UUID trainingDraftId;
    private String title;
    private String description;
}

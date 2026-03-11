package com.smalaca.review.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingReview {
    @Id
    private UUID trainingDraftId;
    private String status;
    private String reason;
}

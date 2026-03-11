package com.smalaca.trainingmanagement.domain;

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
public class TrainingDraft {
    @Id
    private UUID id;
    private String title;
    private String description;
    private String status;
}

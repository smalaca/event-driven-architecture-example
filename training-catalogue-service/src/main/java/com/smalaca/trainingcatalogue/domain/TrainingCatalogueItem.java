package com.smalaca.trainingcatalogue.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainingCatalogueItem {
    @Id
    private UUID trainingDraftId;
    private String title;
    private String description;
    private BigDecimal price;
}

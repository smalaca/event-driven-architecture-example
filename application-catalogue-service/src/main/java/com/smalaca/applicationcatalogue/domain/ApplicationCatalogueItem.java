package com.smalaca.applicationcatalogue.domain;

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
public class ApplicationCatalogueItem {
    @Id
    private UUID applicationDraftId;
    private String title;
    private String description;
    private BigDecimal price;
}

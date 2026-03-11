package com.smalaca.trainingcatalogue.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface TrainingCatalogueRepository extends CrudRepository<TrainingCatalogueItem, UUID> {
}

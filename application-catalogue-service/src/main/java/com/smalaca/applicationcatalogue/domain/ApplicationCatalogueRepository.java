package com.smalaca.applicationcatalogue.domain;

import org.springframework.data.repository.CrudRepository;
import java.util.UUID;

public interface ApplicationCatalogueRepository extends CrudRepository<ApplicationCatalogueItem, UUID> {
}

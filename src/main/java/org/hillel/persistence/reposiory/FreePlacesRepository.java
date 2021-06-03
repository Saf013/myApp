package org.hillel.persistence.reposiory;

import org.hillel.persistence.entity.FreePlacesEntity;
import org.springframework.stereotype.Repository;

@Repository
public class FreePlacesRepository extends CommonRepository<FreePlacesEntity, Long> {
    protected FreePlacesRepository() {
        super(FreePlacesEntity.class);
    }
}

package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.FreePlacesEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FreePlacesJpaRepository extends CommonJpaRepository<FreePlacesEntity, Long>,
        JpaSpecificationExecutor<FreePlacesEntity> {

}

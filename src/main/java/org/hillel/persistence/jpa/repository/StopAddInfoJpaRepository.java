package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.StopAddInfoEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StopAddInfoJpaRepository extends CommonJpaRepository<StopAddInfoEntity, Long>,
        JpaSpecificationExecutor<StopAddInfoEntity> {

}

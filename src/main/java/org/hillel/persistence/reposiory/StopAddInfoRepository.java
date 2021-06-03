package org.hillel.persistence.reposiory;

import org.hillel.persistence.entity.StopAddInfoEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StopAddInfoRepository extends CommonRepository<StopAddInfoEntity, Long> {
    protected StopAddInfoRepository() {
        super(StopAddInfoEntity.class);
    }
}

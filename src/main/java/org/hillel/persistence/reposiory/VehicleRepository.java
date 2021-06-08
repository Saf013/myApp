package org.hillel.persistence.reposiory;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.stereotype.Repository;

@Repository
public class VehicleRepository extends CommonRepository<VehicleEntity, Long> {

    protected VehicleRepository() {
        super(VehicleEntity.class);
    }

    @Override
    public void removeById(Long id) {
        final VehicleEntity vehicleEntity = new VehicleEntity();
        vehicleEntity.setId(id);
        remove(vehicleEntity);
    }

    @Override
    public void remove(VehicleEntity entity) {
        entity = findById(entity.getId()).get();
        entity.removeAllJourneys();
        super.remove(entity);
    }
}

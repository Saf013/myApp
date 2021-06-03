package org.hillel.persistence.jpa.repository;

import org.hillel.persistence.entity.VehicleEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface VehicleJpaRepository extends CommonJpaRepository<VehicleEntity, Long>, JpaSpecificationExecutor<VehicleEntity> {

    @Query(value = "select * from vehicle vh inner join free_places fp " +
            "on vh.id = fp.vehicle_id group by fp.id, vh.id " +
            "order by min(fp.free_places), fp.id", nativeQuery = true)
    Collection<VehicleEntity> findAllVehiclesMinPlaces();


    @Query(value = "select * from vehicle vh inner join free_places fp " +
            "on vh.id = fp.vehicle_id group by fp.id, vh.id " +
            "order by max(fp.free_places) DESC, fp.id", nativeQuery = true)
    Collection<VehicleEntity> findAllVehiclesMaxPlaces();
}

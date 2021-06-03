package org.hillel.service;

import org.hillel.persistence.entity.VehicleEntity;
import org.hillel.persistence.jpa.repository.VehicleJpaRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionalVehicleService {

    private final VehicleJpaRepository vehicleRepository;

    public TransactionalVehicleService(VehicleJpaRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Transactional
    public VehicleEntity createOrUpdate(VehicleEntity vehicleEntity) {
        return vehicleRepository.save(vehicleEntity);
    }

    @Transactional
    public void removeById(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Transactional
    public void remove(VehicleEntity entity) {
        vehicleRepository.delete(entity);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAll(){
        return (Collection<VehicleEntity>) vehicleRepository.findAll();
    }

    @Transactional
    public void deleteById(long id) {
        vehicleRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<VehicleEntity> pageSort(int page, int pageSize, String sortParam, Sort.Direction direction) {
        return vehicleRepository.pageSort(PageRequest.of(page, pageSize, Sort.by(direction, sortParam)));
    }

    @Transactional(readOnly = true)
    public Optional<VehicleEntity> findById(long id){
        return vehicleRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehiclesMinPlaces() {
        return vehicleRepository.findAllVehiclesMinPlaces();
    }

    @Transactional(readOnly = true)
    public Collection<VehicleEntity> findAllVehiclesMaxPlaces() {
        return vehicleRepository.findAllVehiclesMaxPlaces();
    }
}

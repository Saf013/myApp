package org.hillel.service;

import org.hillel.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class TicketClient {

    @Autowired
    private TransactionalJourneyService transactionalJourneyService;

    @Autowired
    private TransactionalVehicleService vehicleService;

    @Autowired
    private TransactionalFreePlaces freePlaces;

    @Autowired
    private TransactionalStopService stopService;

    @Autowired
    private TransactionalStopAddService stopAddService;

    public JourneyEntity createOrUpdate(final JourneyEntity journeyEntity) {
        return transactionalJourneyService.createOrUpdate(journeyEntity);
    }

    public Collection<JourneyEntity> findAllJourneys(){
        return transactionalJourneyService.findAll();
    }

    public Collection<JourneyEntity> onlyActive() {
        return transactionalJourneyService.onlyActive();
    }

    public List<JourneyEntity> pageSortJourney(int page, int pageSize, String sortParam, Sort.Direction direction) {
        return transactionalJourneyService.pageSort(page, pageSize, sortParam,  direction);
    }

    public Optional<JourneyEntity> findByIdJourney(long id) {
        return transactionalJourneyService.findById(id);
    }

    public Collection<VehicleEntity> findAllVehicles(){
        return vehicleService.findAll();
    }

    public List<VehicleEntity> pageSortVehicle(int page, int pageSize, String sortParam, Sort.Direction direction) {
        return vehicleService.pageSort(page, pageSize, sortParam,  direction);
    }

    public Optional<VehicleEntity> findByIdVehicle(long id){
        return vehicleService.findById(id);
    }

    public Collection<VehicleEntity> findAllVehiclesMinPlaces() {
        return vehicleService.findAllVehiclesMinPlaces();
    }

    public Collection<VehicleEntity> findAllVehiclesMaxPlaces() {
        return vehicleService.findAllVehiclesMaxPlaces();
    }

    public void removeVehicle(VehicleEntity entity) {
        vehicleService.remove(entity);
    }

    public void removeByIdVehicle(long id) {
        vehicleService.removeById(id);
    }

    public FreePlacesEntity createOrUpdate(FreePlacesEntity freePlacesEntity) {
        return freePlaces.createOrUpdate(freePlacesEntity);
    }

    public Collection<FreePlacesEntity> findAllPlaces(){
        return freePlaces.findAll();
    }

    public StopEntity createOrUpdate(StopEntity stopEntity) {
        return stopService.createOrUpdate(stopEntity);
    }

    public void removeStop(StopEntity stopEntity) {
        stopService.remove(stopEntity);
    }

    public List<StopEntity> pageSortStops(int page, int pageSize, String param, Sort.Direction direction) {
        return stopService.pageSort(page, pageSize, param, direction);
    }

    public Optional<StopEntity> findByIdStop(long id) {
        return stopService.findById(id);
    }

    public void removeStopById(long id) {
        stopService.removeById(id);
    }

    public void removeStopByIdWithDependency(long id) {
        stopService.removeByIdWithDependency(id);
    }

    public Collection<StopAddInfoEntity> findAllStopsInfo(){
        return stopAddService.findAll();
    }
}

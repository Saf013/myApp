package org.hillel.service;

import org.hillel.persistence.entity.FreePlacesEntity;
import org.hillel.persistence.jpa.repository.FreePlacesJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TransactionalFreePlaces {

    @Autowired
    private FreePlacesJpaRepository freePlacesRepository;

    @Transactional
    public FreePlacesEntity createOrUpdate(FreePlacesEntity freePlacesEntity) {
        return freePlacesRepository.save(freePlacesEntity);
    }

    @Transactional(readOnly = true)
    public Collection<FreePlacesEntity> findAll(){
        return freePlacesRepository.findAll();
    }
}

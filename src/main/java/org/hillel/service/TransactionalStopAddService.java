package org.hillel.service;

import org.hillel.persistence.entity.StopAddInfoEntity;
import org.hillel.persistence.jpa.repository.StopAddInfoJpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service("transactionalStopAddService")
public class TransactionalStopAddService {
    private final StopAddInfoJpaRepository infoRepository;

    public TransactionalStopAddService(StopAddInfoJpaRepository infoRepository) {
        this.infoRepository = infoRepository;
    }

    @Transactional(readOnly = true)
    public Collection<StopAddInfoEntity> findAll(){
        return infoRepository.findAll();
    }

/*    @Transactional(readOnly = true)
    public Collection<StopAddInfoEntity> findAllAsNative(){
        return infoRepository.findAllAsNative();
    }*/

    /*@Transactional(readOnly = true)
    public Collection<StopAddInfoEntity> findAllAsCriteria(){
        return infoRepository.findAllAsCriteria();
    }

    @Transactional(readOnly = true)
    public Collection<StopAddInfoEntity> findAllAsNamedQuery(String name) {
        return infoRepository.findAllAsNamedQuery(name);
    }

    @Transactional(readOnly = true)
    public Collection<StopAddInfoEntity> findAllAsStoredProcedure() {
        return infoRepository.findAllAsStoredProcedure();
    }*/
}

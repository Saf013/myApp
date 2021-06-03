package org.hillel.service;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.jpa.repository.JourneyJpaRepository;
import org.hillel.persistence.jpa.repository.specification.JourneySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TransactionalJourneyService {

    @Autowired
    private JourneyJpaRepository journeyRepository;

    @Transactional
    public JourneyEntity createOrUpdate(final JourneyEntity journeyEntity) {
        Objects.requireNonNull(journeyEntity);
        return journeyRepository.save(journeyEntity);
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> findAll(){
        return journeyRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<JourneyEntity> onlyActive() {
        return journeyRepository.findAll(JourneySpecification.onlyActive());
    }

    @Transactional(readOnly = true)
    public List<JourneyEntity> pageSort(int page, int pageSize, String sortParam, Sort.Direction direction) {
        return journeyRepository.pageSort(PageRequest.of(page, pageSize, Sort.by(direction, sortParam)));
    }

    @Transactional(readOnly = true)
    public Optional<JourneyEntity> findById(long id) {
        return journeyRepository.findById(id);
    }
}

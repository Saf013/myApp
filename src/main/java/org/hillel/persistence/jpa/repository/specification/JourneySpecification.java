package org.hillel.persistence.jpa.repository.specification;

import org.hillel.persistence.entity.JourneyEntity;
import org.hillel.persistence.entity.JourneyEntity_;
import org.springframework.data.jpa.domain.Specification;

public interface JourneySpecification {

    static Specification<JourneyEntity> onlyActive(){
        return (root, query, criteriaBuilder)->
                criteriaBuilder.isTrue(root.get(JourneyEntity_.ACTIVE));

    }
}

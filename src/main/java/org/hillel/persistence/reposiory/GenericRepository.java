package org.hillel.persistence.reposiory;

import java.util.Collection;
import java.util.Optional;

public interface GenericRepository<E, ID> {

    E createOrUpdate(E entity);

    Optional<E> findById(ID id);

    void removeById(ID id);

    void remove(E entity);

    Collection<E> findAll();

    Collection<E> findAllAsNative();

    Collection<E> findAllAsCriteria();

    Collection<E> findAllAsNamedQuery(String name);

    Collection<E> findAllAsStoredProcedure();
}

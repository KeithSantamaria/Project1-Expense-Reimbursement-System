package com.ex.persistence;

import java.util.Collection;

public interface Repository<E, ID> {
    E findById(ID id);
    Collection<E> findAll();
    // this would contain other general CRUD related methods
}

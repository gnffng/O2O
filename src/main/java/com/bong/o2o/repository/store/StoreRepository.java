package com.bong.o2o.repository.store;

import com.bong.o2o.dao.store.Store;

import java.util.List;
import java.util.Optional;

public interface StoreRepository {
    Store save(Store store);
    List<Store> findAll();
}

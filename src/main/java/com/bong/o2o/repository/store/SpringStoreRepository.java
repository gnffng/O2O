package com.bong.o2o.repository.store;

import com.bong.o2o.dao.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringStoreRepository extends JpaRepository<Store,Long>, StoreRepository {
}

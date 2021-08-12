package com.bong.o2o.service;

import com.bong.o2o.dao.product.MainMenu;
import com.bong.o2o.dao.store.Store;
import com.bong.o2o.repository.store.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Transactional
public class StoreService {
    StoreRepository storeRepository;

    @Autowired
    public StoreService(StoreRepository storeRepository){
        this.storeRepository = storeRepository;
    }

    public void initStore() {
        Store store;

        List<Store> stores = storeRepository.findAll();
        if(stores.size()==0){
            store = new Store();
            store.setId(0);
        }
        else{
            store = storeRepository.findAll().get(0);
            store.setUpdatedTimeAt(LocalDateTime.now());
        }

        store.setName("default");
        store.setLogoFileName(store.getUpdatedTimeAt().toString());
        store = storeRepository.save(store);
    }

    public Store updateStore(Store newStore) {
        Store store = storeRepository.findAll().get(0);

        store.setName(newStore.getName());
        store.setUpdatedTimeAt(newStore.getUpdatedTimeAt());
        store.setLogoFileName(newStore.getUpdatedTimeAt().toString().replace(".", "-").replace(":", "-")+".png");

        return storeRepository.save(store);
    }

    public Store readStore() {
        List<Store> stores = storeRepository.findAll();
        if(stores.size() == 0){
            Store defualtStore = new Store();
            defualtStore.setName("default store");
            defualtStore.setLogoFileName("default.png");
            return defualtStore;
        }
        else{
            return stores.get(0);
        }
    }
}

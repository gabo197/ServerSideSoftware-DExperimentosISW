package com.appdhome.services;

import com.appdhome.entities.City;
import java.util.Optional;


public interface ICityService extends CrudService<City>{
    Optional<City> findByName(String name) throws Exception;
}

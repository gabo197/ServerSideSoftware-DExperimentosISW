package com.appdhome.services;


import com.appdhome.entities.Specialty;
import java.util.Optional;

public interface ISpecialtyService extends CrudService<Specialty>{
    Optional<Specialty> findByName(String name) throws Exception;
}

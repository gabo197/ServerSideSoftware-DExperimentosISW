package com.appdhome.services;


import com.appdhome.entities.Specialty;

public interface ISpecialtyService extends CrudService<Specialty>{
    public Specialty findByName(String name) throws Exception;
}

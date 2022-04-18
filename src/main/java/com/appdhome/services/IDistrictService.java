package com.appdhome.services;

import com.appdhome.entities.District;

import java.util.List;

public interface IDistrictService extends CrudService<District>{
    public District findByName(String name) throws Exception;
    public List<District> findByIDCity(Long id) throws Exception;
}

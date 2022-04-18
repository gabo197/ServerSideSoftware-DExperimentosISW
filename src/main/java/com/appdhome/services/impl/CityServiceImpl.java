package com.appdhome.services.impl;

import com.appdhome.entities.City;
import com.appdhome.repository.ICityRepository;
import com.appdhome.services.ICityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class CityServiceImpl implements ICityService {

    @Autowired
    private ICityRepository cityRepository;

    @Override
    @Transactional
    public City save(City city) throws Exception {
        return cityRepository.save(city);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        cityRepository.deleteById(id);
    }

    @Override
    public List<City> getAll() throws Exception {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> getById(Long id) throws Exception {
        return cityRepository.findById(id);
    }

    @Override
    public City findByName(String name) throws Exception {
        return cityRepository.findByName(name);
    }
}

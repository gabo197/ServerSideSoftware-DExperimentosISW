package com.appdhome.services.impl;

import com.appdhome.entities.District;
import com.appdhome.repository.IDistrictRepository;
import com.appdhome.services.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class DistrictServiceImpl implements IDistrictService {

    @Autowired
    private IDistrictRepository districtRepository;

    @Override
    @Transactional
    public District save(District district) throws Exception {
        return districtRepository.save(district);
    }

    @Override
    @Transactional
    public void delete(Long id) throws Exception {
        districtRepository.deleteById(id);
    }

    @Override
    public List<District> getAll() throws Exception {
        return districtRepository.findAll();
    }

    @Override
    public Optional<District> getById(Long id) throws Exception {
        return districtRepository.findById(id);
    }

    @Override
    public District findByName(String name) throws Exception {
        return districtRepository.findByName(name);
    }

    @Override
    public List<District> findByIDCity(Long id) throws Exception {
        return districtRepository.findByCity_Id(id);
    }
}

package com.appdhome.repository;

import com.appdhome.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
    public Optional<City> findByName(String name);
}

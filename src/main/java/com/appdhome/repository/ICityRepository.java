package com.appdhome.repository;

import com.appdhome.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {
    public City findByName(String name);
}

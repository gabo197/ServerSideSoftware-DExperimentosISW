package com.appdhome.repository;


import com.appdhome.entities.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISpecialtyRepository extends JpaRepository<Specialty, Long> {
    public Specialty findByName (String name);
}

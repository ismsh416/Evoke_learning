package com.Covid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Covid.model.Covid;

@Repository
public interface CovidRepository extends JpaRepository<Covid, Long>{

}

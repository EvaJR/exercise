package com.XYZAirline.exercise.repository;

import com.XYZAirline.exercise.model.Airport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportRepository extends CrudRepository<Airport, Long> {


}
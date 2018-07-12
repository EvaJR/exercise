package com.XYZAirline.exercise.repository;

import com.XYZAirline.exercise.model.Airplane;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirplaneRepository extends CrudRepository<Airplane, Long> {


}
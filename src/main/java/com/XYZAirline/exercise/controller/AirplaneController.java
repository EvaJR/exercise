package com.XYZAirline.exercise.controller;

import com.XYZAirline.exercise.model.Airplane;
import com.XYZAirline.exercise.repository.AirplaneRepository;
import com.XYZAirline.exercise.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Autowired
    private AirportRepository airportRepository;


    @PostMapping
    public ResponseEntity<Airplane> create(@RequestBody Airplane newAirplane) {

        this.airplaneRepository.save(newAirplane);

        return new ResponseEntity<Airplane>(newAirplane, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Airplane>> list() {
        return new ResponseEntity<Iterable<Airplane>>( this.airplaneRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Airplane> findById(@PathVariable long id) {

        Optional<Airplane> possibleResult = this.airplaneRepository.findById(id);

        if(possibleResult.isPresent()) {
            Airplane result = possibleResult.get();
            return new ResponseEntity<Airplane>(result,HttpStatus.OK);
        } else { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        this.airplaneRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Airplane> update(@PathVariable long id, @RequestBody Airplane input) {

        Optional<Airplane> possibleOutput = this.airplaneRepository.findById(id);

        if(possibleOutput.isPresent()) {
            Airplane output = possibleOutput.get();

            output.setTonsOfFuel(input.getTonsOfFuel());

            output = this.airplaneRepository.save(output);

            return new ResponseEntity<Airplane>(this.airplaneRepository.save(output), HttpStatus.OK);

        } else { return new ResponseEntity<Airplane>(HttpStatus.NOT_FOUND); }


    }

}

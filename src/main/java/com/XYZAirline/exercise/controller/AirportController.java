package com.XYZAirline.exercise.controller;

import com.XYZAirline.exercise.model.Airport;
import com.XYZAirline.exercise.repository.AirportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("api/airports")
public class AirportController {

    @Autowired
    private AirportRepository airportRepository;

    @PostMapping
    public ResponseEntity<Airport> create(@RequestBody Airport newAirport) {

        this.airportRepository.save(newAirport);

        return new ResponseEntity<Airport>(newAirport, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Iterable<Airport>> list() {
        return new ResponseEntity<Iterable<Airport>>( this.airportRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Airport> findById(@PathVariable long id) {

        Optional<Airport> possibleResult = this.airportRepository.findById(id);

        if(possibleResult.isPresent()) {
            Airport result = possibleResult.get();
            return new ResponseEntity<Airport>(result,HttpStatus.OK);
        } else { return new ResponseEntity<>(HttpStatus.NOT_FOUND);}
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable long id) {
        this.airportRepository.deleteById(id);
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<Airport> update(@PathVariable long id, @RequestBody Airport input) {

        Optional<Airport> possibleOutput = this.airportRepository.findById(id);

        if(possibleOutput.isPresent()) {
            Airport output = possibleOutput.get();

            output.setName(input.getName());

            output = this.airportRepository.save(output);

            return new ResponseEntity<Airport>(this.airportRepository.save(output), HttpStatus.OK);

        } else { return new ResponseEntity<Airport>(HttpStatus.NOT_FOUND); }


    }

}

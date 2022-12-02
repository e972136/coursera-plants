package com.codecademy.plants.controllers;

import com.codecademy.plants.entities.Adventure;
import com.codecademy.plants.repositories.AdventureRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Optional;


@RestController
@RequestMapping("/traveladventures")
public class TravelAdventuresController {

    private final AdventureRepository adventureRepository;

    public TravelAdventuresController(AdventureRepository adventureRepo) {
        this.adventureRepository = adventureRepo;
    }


    @GetMapping
    public Iterable<Adventure> findAll(){
        return adventureRepository.findAll();
    }

    @GetMapping("/bycountry/{country}")
    public Iterable<Adventure> findByCountry(@PathVariable String country){
        return adventureRepository.findByCountry(country);
    }

    @GetMapping("/bystate")
    public Iterable<Adventure> findByState(@RequestParam String state){
        return adventureRepository.findByState(state);
    }

    @PostMapping
    public Adventure saveCountry(@RequestBody Adventure adventure){
        return adventureRepository.save(adventure);
    }

    @PutMapping("/{id}")
    public Adventure updateCountry(@PathVariable Integer id, @RequestBody Adventure adventure){
        Optional<Adventure> byId = adventureRepository.findById(id);
        if(!byId.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data aviable.");
        }
        Adventure adventure1 = byId.get();
        adventure1.setBlogCompleted(true);
        return adventureRepository.save(adventure1);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Integer id){
        Optional<Adventure> byId = adventureRepository.findById(id);
        if(byId.isPresent()){
            adventureRepository.deleteById(id);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No data aviable.");
        }
    }


}

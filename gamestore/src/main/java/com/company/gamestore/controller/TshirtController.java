package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TshirtController {

    @Autowired
    ServiceLayer serviceLayer;

    @GetMapping("/tshirts")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getAllTshirts() {
        return serviceLayer.findAllShirts();
    }

    @GetMapping("/tshirts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Tshirt getTshirtById(@PathVariable Integer id) {
        Optional<Tshirt> returnVal = serviceLayer.findShirtById(id);
        return returnVal.isPresent() ? returnVal.get() : null;
    }

    @GetMapping("/tshirts/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsByColor(@PathVariable String color) {
        return serviceLayer.findShirtByColor(color);
    }

    @GetMapping("/tshirts/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<Tshirt> getTshirtsBySize(@PathVariable String size) {
        return serviceLayer.findShirtBySize(size);
    }

    @PostMapping("/tshirt")
    @ResponseStatus(HttpStatus.CREATED)
    public Tshirt addTshirt(@RequestBody Tshirt tshirt) {
        return serviceLayer.saveTshirt(tshirt);
    }

    @PutMapping("/tshirt")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateTshirt(@RequestBody Tshirt tshirt) {
        serviceLayer.saveTshirt(tshirt);
    }

    @DeleteMapping("/tshirt/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTshirtById(@PathVariable Integer id) {
        serviceLayer.deleteShirt(id);
    }
}

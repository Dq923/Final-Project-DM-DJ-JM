package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createCustomer(@RequestBody Console console) {
        return serviceLayer.saveConsole(console);
    }

    @GetMapping("/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole() {
        return serviceLayer.findAllConsoles();
    }

    @GetMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Console> getConsoleById(@PathVariable int id) {
        return serviceLayer.findConsoleById(id);
    }

    @PutMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Console updateConsole(@PathVariable Integer id, @RequestBody Console updatedConsole) {


        return serviceLayer.findConsoleById(id)
                .map(existingConsole -> {
                    existingConsole.setModel(updatedConsole.getModel());
                    existingConsole.setManufacturer(updatedConsole.getManufacturer());
                    existingConsole.setMemoryAmount(updatedConsole.getMemoryAmount());
                    existingConsole.setProcessor(updatedConsole.getProcessor());
                    existingConsole.setPrice(updatedConsole.getPrice());
                    existingConsole.setQuantity(updatedConsole.getQuantity());
                    return serviceLayer.saveConsole(existingConsole);
                })
                .orElse(null);
    }

    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        serviceLayer.deleteConsole(id);
    }

    @GetMapping("/consoles/manufacturers/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return serviceLayer.findConsolesByManufacturers(manufacturer);
    }



// METHOD ONLY FOR CLEARING DATABASE WHEN TESTING W/ INSOMNIA
//    @DeleteMapping("/consoles")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteConsoles() {
//        serviceLayer.deleteAllConsoles();
//    }

}

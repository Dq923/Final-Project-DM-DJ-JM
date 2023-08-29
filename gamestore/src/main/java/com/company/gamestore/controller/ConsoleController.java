package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ConsoleController {

    @Autowired
    ConsoleRepository consoleRepository;

    @PostMapping("/consoles")
    @ResponseStatus(HttpStatus.CREATED)
    public Console createCustomer(@RequestBody Console console) {
        return consoleRepository.save(console);
    }

    @GetMapping("/consoles")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole() {
        return consoleRepository.findAll();
    }

    @GetMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsoleById(@PathVariable int id) {
        return consoleRepository.findConsoleById(id);
    }

    @PutMapping("/console/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Console updateConsole(@PathVariable Integer id, @RequestBody Console updatedConsole) {


        return consoleRepository.findById(id)
                .map(existingConsole -> {
                    existingConsole.setModel(updatedConsole.getModel());
                    existingConsole.setManufacturer(updatedConsole.getManufacturer());
                    existingConsole.setMemoryAmount(updatedConsole.getMemoryAmount());
                    existingConsole.setProcessor(updatedConsole.getProcessor());
                    existingConsole.setPrice(updatedConsole.getPrice());
                    existingConsole.setQuantity(updatedConsole.getQuantity());
                    return consoleRepository.save(existingConsole);
                })
                .orElse(null);
    }

    @DeleteMapping("/consoles/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteConsole(@PathVariable int id) {
        consoleRepository.deleteConsoleById(id);
    }

    @GetMapping("/consoles/manufacturers/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getConsoleByManufacturer(@PathVariable String manufacturer) {
        return consoleRepository.findAllConsoleByManufacturer(manufacturer);
    }

}

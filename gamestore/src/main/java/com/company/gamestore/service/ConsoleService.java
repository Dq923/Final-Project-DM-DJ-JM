package com.company.gamestore.service;

import com.company.gamestore.model.Console;
import com.company.gamestore.repository.ConsoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    public ConsoleService(ConsoleRepository consoleRepo) {
        this.consoleRepository = consoleRepo;
    }

    // SAVE A CONSOLE
    public Console saveConsole(Console console) {
        if (console.getModel().isEmpty()) // check if fields are valid
            throw new IllegalArgumentException("Model not provided.");
        else if (console.getManufacturer().isEmpty())
            throw new IllegalArgumentException("Manufacturer not provided.");
        else if (console.getMemoryAmount().isEmpty())
            throw new IllegalArgumentException("Memory amount information not provided. ");
        else if (console.getProcessor().isEmpty())
            throw new IllegalArgumentException("Processor info not provided.");
        else if (console.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (console.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        console = consoleRepository.save(console); // save to database

        return console;
    }

    // READ BY ID
    public Console findConsoleById(int id) {
        Optional<Console> console = consoleRepository.findById(id);

        return console.orElse(null);
    }

    // READ ALL
    public List<Console> findAllConsoles() {
        return consoleRepository.findAll();
    }

    // UPDATE CONSOLE
    public void updateConsole(Console console) {
        if (console.getModel().isEmpty()) // check if fields are valid
            throw new IllegalArgumentException("Model not provided.");
        else if (console.getManufacturer().isEmpty())
            throw new IllegalArgumentException("Manufacturer not provided.");
        else if (console.getMemoryAmount().isEmpty())
            throw new IllegalArgumentException("Memory amount information not provided. ");
        else if (console.getProcessor().isEmpty())
            throw new IllegalArgumentException("Processor info not provided.");
        else if (console.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (console.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        console = consoleRepository.save(console); // save to database
    }

    // DELETE BY ID
    public void deleteConsole(int id) {
        Optional<Console> console = consoleRepository.findById(id); // check if the shirt exists

        if (console.isEmpty())
            throw new IllegalArgumentException("Console cannot be found by the ID provided.");
    }

    // FIND CONSOLE BY MANUFACTURER
    public List<Console> findConsolesByManufacturers(String manufacturer) {
        return consoleRepository.findAllConsoleByManufacturer(manufacturer);
    }
}

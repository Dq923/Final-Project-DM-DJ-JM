package com.company.gamestore.service;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.repository.TshirtRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class TshirtService {
    @Autowired
    private TshirtRepository tshirtRepository;

    @Autowired
    public TshirtService(TshirtRepository tshirtRepo) {
        this.tshirtRepository = tshirtRepo;
    }

    // SAVE A TSHIRT (POST)
    public Tshirt saveTshirt(Tshirt tshirt) {
        if (tshirt.getColor().isEmpty())
            throw new IllegalArgumentException("Shirt color not provided.");
        else if (tshirt.getSize().isEmpty())
            throw new IllegalArgumentException("Shirt size not provided.");
        else if (tshirt.getDescription().isEmpty())
            throw new IllegalArgumentException("Shirt description not provided.");
        else if (tshirt.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (tshirt.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        tshirt = tshirtRepository.save(tshirt);

        return tshirt;
    }

    // READ BY ID
    public Tshirt findShirtById(int id) {
        Optional<Tshirt> shirt = tshirtRepository.findById(id);

        return shirt.orElse(null);
    }

    // READ ALL
    public List<Tshirt> findAllShirts() {
        return tshirtRepository.findAll();
    }

    // UPDATE TSHIRT
    public void updateShirt(Tshirt tshirt) {
        if (tshirt.getColor().isEmpty()) // Ensure all necessary fields are provided
            throw new IllegalArgumentException("Shirt color not provided.");
        else if (tshirt.getSize().isEmpty())
            throw new IllegalArgumentException("Shirt size not provided.");
        else if (tshirt.getDescription().isEmpty())
            throw new IllegalArgumentException("Shirt description not provided.");
        else if (tshirt.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (tshirt.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        tshirt = tshirtRepository.save(tshirt); // save to tshirtRepository

    }

    // DELETE A SHIRT
    public void deleteShirt(int id) {
        Optional<Tshirt> shirt = tshirtRepository.findById(id); // check if the shirt exists

        if (shirt.isEmpty())
            throw new IllegalArgumentException("Shirt cannot be found by the ID provided.");
    }

    // FIND SHIRT BY COLOR
    public List<Tshirt> findShirtByColor(String color) { // Might consider returning a Tshirt instead of List<Tshirt>
        return tshirtRepository.findTshirtByColor(color);
    }

    // FIND SHIRT BY SIZE

    public List<Tshirt> findShirtBySize(String size) {
        return tshirtRepository.findTshirtBySize(size);
    }

}

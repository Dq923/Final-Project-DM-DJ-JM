package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ConsoleRepositoryTest {
    @Autowired
    ConsoleRepository consoleRepository;

    private Console console;

    // Create a dummy console to test routes to run before each test
    @BeforeEach
    public void setUp() {
        consoleRepository.deleteAll();

        // Fill with dummy data
        console = new Console();
        console.setModel("Model X");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("512GB");
        console.setProcessor("AMD Zen 2 CPU");
        BigDecimal price = new BigDecimal("499.99");
        console.setPrice(price);
        console.setQuantity(20);

        // Save to repository
        console = consoleRepository.save(console);
    }

    @Test
    public void shouldAddConsole() {
        Optional<Console> addConsole = consoleRepository.findById(console.getId());
        assertEquals(addConsole.get(), console);
    }

    @Test
    public void shouldGetConsoleById() {
        Console getConsoleById = consoleRepository.findById(console.getId()).orElse(null);
        assertEquals(console, getConsoleById);
    }

    @Test
    public void shouldUpdateConsole() {
        console.setMemoryAmount("1TB");
        consoleRepository.save(console);

        Optional<Console> updatedConsole = consoleRepository.findById(console.getId());
        assertEquals(updatedConsole.get(), console);
    }

    @Test
    public void shouldDeleteConsole() {
        consoleRepository.deleteById(console.getId());
        Optional<Console> deletedConsole = consoleRepository.findById(console.getId());
        assertFalse(deletedConsole.isPresent());
    }

    @Test
    public void shouldFindAllConsolesByManufacturer() {
        // Create more dummy consoles with different manufacturers
        Console console1 = new Console();
        console1.setModel("Model S");
        console1.setManufacturer("Microsoft");
        console1.setMemoryAmount("512GB");
        console1.setProcessor("AMD Zen 2 CPU");
        BigDecimal price1 = new BigDecimal("299.99");
        console1.setPrice(price1);
        console1.setQuantity(15);
        consoleRepository.save(console1);

        Console console2 = new Console();
        console2.setModel("Model W");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("1TB");
        console2.setProcessor("AMD Zen 10 CPU");
        BigDecimal price2 = new BigDecimal("599.99");
        console2.setPrice(price2);
        console2.setQuantity(10);
        consoleRepository.save(console2);

        // Call the method to get consoles by manufacturer
        List<Console> microsoftConsoles = consoleRepository.findAllConsoleByManufacturer("Microsoft");

        // Assert the size and contents of the returned list
        assertEquals(3, microsoftConsoles.size());
        assertTrue(microsoftConsoles.contains(console));
        assertTrue(microsoftConsoles.contains(console1));
        assertTrue(microsoftConsoles.contains(console2));
    }

}

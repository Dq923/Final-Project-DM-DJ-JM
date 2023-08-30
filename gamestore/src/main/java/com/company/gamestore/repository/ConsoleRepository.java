package com.company.gamestore.repository;

import com.company.gamestore.model.Console;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ConsoleRepository extends JpaRepository<Console, Integer> {
    Console save(Console console);

    Optional<Console> findById(Integer id);

    Console findConsoleById(int id);

    //    List<Console> findAllConsoles();
    void deleteConsoleById(int id);

    List<Console> findAllConsoleByManufacturer(String manufacturer);
}

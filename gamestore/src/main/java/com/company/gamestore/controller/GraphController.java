package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
//import com.company.gamestore.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

@Controller
public class GraphController {

    @Autowired
    ConsoleRepository consoleRepository;

    @Autowired
    GameRepository gameRepository;

    @QueryMapping
    public List<Game> findAllGames() { return gameRepository.findAll(); }

    @QueryMapping
    public Optional<Game> findGameById(@Argument Integer id) { return gameRepository.findById(id); }

    @QueryMapping
    public List<Game> findGamesByTitle(@Argument String title) {
        return gameRepository.findGamesByTitle(title);
    }

    @QueryMapping
    public List<Game> findAllByEsrbRating(@Argument String esrb) {
        return gameRepository.findAllByEsrbRating(esrb);
    }

    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameRepository.findAllByStudio(studio);
    }

    @QueryMapping
    public List<Console> findAllConsoles() {
        return consoleRepository.findAll();
    }

    @QueryMapping
    public Console findConsoleById(@Argument Integer id) {
        Optional<Console> console = consoleRepository.findById(id);
        return console.orElse(null);
    }

    @QueryMapping
    public List<Console> findAllConsoleByManufacturer(@Argument String manufacturer) {
        return consoleRepository.findAllConsoleByManufacturer(manufacturer);
    }

}

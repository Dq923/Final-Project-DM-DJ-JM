package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.model.Game;
import com.company.gamestore.repository.ConsoleRepository;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.service.GameService;
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

    @Autowired
    GameService gameService;

    @QueryMapping
    public List<Game> findAllGames() { return gameService.findAllGames(); }

    @QueryMapping
    public Game findGameById(@Argument Integer id) { return gameService.findGameById(id); }

    @QueryMapping
    public Game findAGameByTitle(@Argument String title) {
        return gameService.findAGameByTitle(title);
    }

    @QueryMapping
    public List<Game> findAllByEsrbRating(@Argument String esrb) {
        return gameService.findGamesByEsrbRating(esrb);
    }

    @QueryMapping
    public List<Game> findGamesByStudio(@Argument String studio) {
        return gameService.findGamesByStudio(studio);
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

package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.company.gamestore.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

// import static org.graalvm.compiler.phases.common.DeadCodeEliminationPhase.Optionality.Optional;
@RestController
public class GameController {

    @Autowired
    ServiceLayer serviceLayer;


    // Create
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGameRecord(@RequestBody Game game) {
        return serviceLayer.saveGame(game);
    }

    // Read all
    @GetMapping("/games")
    public List<Game> getAllGames() {
        return serviceLayer.findAllGames();
    }

    // Read by id
    @GetMapping("/games/{id}") // need validation for id
    public Game getGameById(@PathVariable int id) {
        return serviceLayer.findGameById(id);
    }

    // Update
    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGameRecord(@RequestBody Game game) {
        serviceLayer.saveGame(game);
    }

    // Delete
    @DeleteMapping("/games/{id}") // need validation for id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameRecord(@PathVariable int id) {
        serviceLayer.deleteGame(id);
    }

    // Find By Studio
    @GetMapping("/games/studios/{studio}") // need validation for studio name
    public List<Game> findGamesByStudio(@PathVariable String studio) {
        return serviceLayer.findGamesByStudio(studio);
    }

    // Find By Rating
    @GetMapping("/games/ratings/{rating}") // need validation for studio name
    public List<Game> findGamesByRating(@PathVariable String rating) {
        return serviceLayer.findGamesByEsrbRating(rating);
    }

    // Find by title
    @GetMapping("/games/titles/{title}") // need validation for title
    public List<Game> findGamesByTitle(@PathVariable String title) {
        return serviceLayer.findGamesByTitle(title);
    }


}

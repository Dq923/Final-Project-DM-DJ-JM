package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.security.Provider;
import java.util.List;
import java.util.Optional;

// import static org.graalvm.compiler.phases.common.DeadCodeEliminationPhase.Optionality.Optional;
@RestController
public class GameController {

    @Autowired
    GameRepository gameRepo;


    // Create
    @PostMapping("/games")
    @ResponseStatus(HttpStatus.CREATED)
    public Game addGameRecord(@RequestBody Game game){return gameRepo.save(game);}

    // Read all
    @GetMapping("/games")
    public List<Game> getAllGames(){return gameRepo.findAll();}
    //return gameRepo.findAll()

    // Read by id
    @GetMapping("/games/{id}") // need validation for id
    public Game getGameById(@PathVariable int id){
        Optional <Game> returnValue = gameRepo.findById(id);

        if(returnValue.isPresent())
            return returnValue.get();
        else{
            return null;
        }
    }
    // Update
    @PutMapping("/games")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateGameRecord(@RequestBody Game game){ gameRepo.save(game);}
    // Delete
    @DeleteMapping("/games/{id}") // need validation for id
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteGameRecord(@PathVariable int id){gameRepo.deleteById(id);}

    // Find By Studio
    @GetMapping("/games/studios/{studio}") // need validation for studio name
    public List<Game> findGamesByStudio(@PathVariable String studio){ return gameRepo.findAllByStudio(studio);}
    // Find By Rating
    @GetMapping("/games/ratings/{rating}") // need validation for studio name
    public List<Game> findGamesByRating(@PathVariable String rating){ return gameRepo.findAllByEsrbRating(rating);}

    // Find by title
    @GetMapping("/games/titles/{title}") // need validation for title
    public Game findGamesByTitle(@PathVariable String title) {
        Optional<Game> game = gameRepo.findGameByTitle(title);

        if (game.isPresent())
            return game.get();
        else {
            return null;
        }
    }



}

package com.company.gamestore.service;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepo){
        this.gameRepository = gameRepo;
    }

    public Game  saveGame(Game game){
        // Validation for Game fields
        if(game.getTitle().isEmpty())
            throw new IllegalArgumentException("Game title was not provided.");
        else if(game.getDescription().isEmpty())
            throw new IllegalArgumentException("Game description was not provided");
        else if(game.getStudio().isEmpty())
            throw new IllegalArgumentException("Game studio name was not provided.");
        else if(game.getEsrbRating().isEmpty())
            throw new IllegalArgumentException("ESRB Rating was not provided");
        else if (game.getPrice().compareTo(new BigDecimal("0.00")) < 0) // if the price is a negative value
            throw new IllegalArgumentException("Not a valid price. Must be >= 0.00");
        else if(game.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");

        // If there are no issues with validation, save the game
        game = gameRepository.save(game);

        return game;
    }


    // Find(Read) Game By ID
    // GET BY ID
    public Game findGameById(int id){
        Optional<Game> game = gameRepository.findById(id);

        // if the game is present, return the game, otherwise return null
        return game.orElse(null);
    }


    // GET ALL Games

    public List<Game> findAllGames(){
        return gameRepository.findAll();
    }

    // PUT - Update a Game record
    public void updateGame(Game game){
        // Validation for Game fields
        if(game.getTitle().isEmpty())
            throw new IllegalArgumentException("Game title was not provided.");
        else if(game.getDescription().isEmpty())
            throw new IllegalArgumentException("Game description was not provided");
        else if(game.getStudio().isEmpty())
            throw new IllegalArgumentException("Game studio name was not provided.");
        else if(game.getEsrbRating().isEmpty())
            throw new IllegalArgumentException("ESRB Rating was not provided");
        else if (game.getPrice().compareTo(new BigDecimal("0.00")) < 0) // if the price is a negative value
            throw new IllegalArgumentException("Not a valid price. Must be >= 0");
        else if(game.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");

        gameRepository.save(game); // save the game's updated info
    }

    // DELETE a game
    public void removeGame(int id){
        Optional<Game> game = gameRepository.findById(id); // search by ID

        if(game.isEmpty()){ // if not found throw exception
            throw new IllegalArgumentException("Game cannot be found by the ID provided.");
        }

        gameRepository.deleteById(id); // delete by id if the game was found
    }

    // FIND GAMES BY STUDIO
    public List<Game> findGamesByStudio(String studio){
        List<Game> gameList = gameRepository.findAllByStudio(studio);

        // Do we throw an exception if empty or just return the empty list?

        return gameList;
    }

    // FIND GAMES BY RATING

    public List<Game> findGamesByEsrbRating(String rating){
        // Do we need to validate for all possible ratings? (E, E10, T, M, etc) ?

        List<Game> gameList = gameRepository.findAllByEsrbRating(rating);

        return gameList;
    }

    public Game findAGameByTitle(String title){
        Optional<Game> game = gameRepository.findGameByTitle(title);

        return game.orElse(null); // return the game if found, otherwise null
    }
}

package com.company.gamestore.repository;


import com.company.gamestore.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
    // CRUD already exists

    // Custom Queries
    public List<Game> findAllByStudio(String studio);

    public List<Game> findAllByEsrbRating(String esrbRating);

    public List<Game> findGamesByTitle(String title);

}
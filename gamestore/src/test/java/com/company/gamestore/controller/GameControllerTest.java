package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.repository.GameRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GameController.class)
class GameControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameRepository gameRepository;

    private ObjectMapper mapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        gameRepository.deleteAll();
    }

    @Test
    public void shouldCreateGame() throws Exception{
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));

        String inputJson = mapper.writeValueAsString(game);

        mockMvc.perform(post("/games")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReturnAllGamesOnGetRequest() throws Exception {
        // Arrange - Add 2 games
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
//        serviceLayer.saveGame(serviceLayer.buildGameViewModel(game));
        gameRepository.save(game);

        Game game2 = new Game();
        game2.setGameId(2);
        game2.setTitle("GTA VI");
        game2.setDescription("The newest addition to the GTA series brings you to the streets of Miami");
        game2.setEsrbRating("M");
        game2.setStudio("Rockstar");
        game2.setQuantity(5);
        game2.setPrice(new BigDecimal("59.99"));
//        serviceLayer.saveGame(serviceLayer.buildGameViewModel(game2));
        gameRepository.save(game2);


        mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAGameById() throws Exception {
        // Arrange
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
        gameRepository.save(game);

        // Act and Assert
        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateGameRecordOnPutRequest() throws Exception {
        // Arrange
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
        gameRepository.save(game);

        // Act then Assert - update and perform put request + status check
        game.setPrice(new BigDecimal("17.99"));
        game.setQuantity(3);

        String inputJson = mapper.writeValueAsString(game); //convert game record to json

        mockMvc.perform(put("/games") // put request
                        .content(inputJson) // request body
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteGameRecord() throws Exception{
        // Arrange
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
        gameRepository.save(game);

        // Act and Assert
        mockMvc.perform(delete("/games/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReturnAllGamesByStudio() throws Exception {
        // Arrange
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
        gameRepository.save(game);

        // Act and Assert
        mockMvc.perform(get("/games/studios/Mojang"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnAllGamesByRating() throws Exception {
        // Arrange
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang Studios");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
        gameRepository.save(game);

        // Act and Assert
        mockMvc.perform(get("/games/ratings/E"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnGameByTitle() throws Exception {
        // Arrange
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("A puzzle-adventure game in which players mine pixelated landscapes to harvest stylized cube-like materials");
        game.setEsrbRating("E");
        game.setStudio("Mojang");
        game.setQuantity(5);
        game.setPrice(new BigDecimal("19.99"));
        gameRepository.save(game);

        // Act and Assert
        mockMvc.perform(get("/games/titles/Minecraft"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
package com.company.gamestore.controller;

import com.company.gamestore.model.Console;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    // Create a testing console before testing
    Console console = new Console();

    @BeforeEach
    public void setUp() {
        console.setModel("Model X");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("400GB");
        console.setProcessor("AMD Zen 2 CPU");
        BigDecimal price = new BigDecimal("499.99");
        console.setPrice(price);
        console.setQuantity(20);
    }

    // Testing posting a new console
    @Test
    public void shouldAddConsole() throws Exception {
        Console console = new Console();
        String inputJson = mapper.writeValueAsString(console);
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/consoles")
                        .content(mapper.writeValueAsString(console))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    // Testing getting all consoles
    @Test
    public void getAllConsoles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consoles"))
                .andExpect(status().isOk());
    }

    // Testing getting a console by Id
    @Test
    public void shouldGetConsoleById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consoles/{id}", 1))
                .andExpect(status().isOk());
    }

    // Testing updating a console
    @Test
    public void shouldUpdateConsole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/console/{id}", 1)
                        .content(mapper.writeValueAsString(console))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    // Testing deleting a console
    @Test
    public void shouldDeleteConsole() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/consoles/{id}", 1))
                .andExpect(status().isNoContent());
    }

    // Testing custom query view all consoles by manufacturer
    @Test
    public void shouldGetConsolesByManufacturer() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/consoles/manufacturers/Microsoft"))
                .andExpect(status().isOk());
    }

}

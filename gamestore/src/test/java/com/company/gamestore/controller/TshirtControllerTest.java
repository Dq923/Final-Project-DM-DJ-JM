package com.company.gamestore.controller;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.ServiceLayer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TshirtController.class)
class TshirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
    }

    @Test
    public void shouldGetAllTshirtsAndReturnStatusOK() throws Exception {
        mockMvc.perform(
                        get("/tshirts"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtByIdAndReturnStatusOk() throws Exception {
        mockMvc.perform(
                        get("/tshirts/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtsByColorAndReturnStatusOk() throws Exception {
        mockMvc.perform(
                        get("/tshirts/color/black"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldGetTshirtsBySizeAndReturnStatusOk() throws Exception {
        mockMvc.perform(
                        get("/tshirts/size/small"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddTshirtAndReturnStatusCreated() throws Exception {
        Tshirt tshirt = new Tshirt();
        String inputJson = objectMapper.writeValueAsString(tshirt);

        mockMvc.perform(
                        post("/tshirt")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldUpdateTshirtAndReturnStatusNoContent() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("Blue");

        String inputJson = objectMapper.writeValueAsString(tshirt);

        mockMvc.perform(
                        put("/tshirt")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void deleteTshirtById() throws Exception {
        Tshirt tshirt = new Tshirt();

        mockMvc.perform(
                        delete("/tshirt/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}
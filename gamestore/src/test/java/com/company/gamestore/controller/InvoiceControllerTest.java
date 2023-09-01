package com.company.gamestore.controller;

import com.company.gamestore.model.Game;
import com.company.gamestore.model.Invoice;
import com.company.gamestore.service.ServiceLayer;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.coyote.http11.upgrade.UpgradeServletOutputStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        // Arrange - Create a game to save in each test
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
    }

    @Test
    public void shouldCreateInvoice() throws Exception{
        Invoice invoice = new Invoice();

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(post("/invoices")
                        .content(inputJson)
                        .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void shouldReadAllInvoices() throws Exception{
        mockMvc.perform(get("/invoices"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReadInvoiceById() throws Exception{

        mockMvc.perform(get("/invoices/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldDeleteInvoiceById() throws Exception {
        mockMvc.perform(delete("/invoices/1"))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldReadByCustomerName() throws Exception{
        mockMvc.perform(get("/invoices/name/johnny"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateInvoice() throws Exception{
        Invoice invoice = new Invoice();
        invoice.setName("Johnny");

        String inputJson = mapper.writeValueAsString(invoice);

        mockMvc.perform(
                        put("/invoices")
                                .content(inputJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }


}
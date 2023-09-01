//package com.company.gamestore.repository;
//
//import com.company.gamestore.model.Game;
//import com.company.gamestore.model.Invoice;
//import com.company.gamestore.service.ServiceLayer;
//import com.company.gamestore.viewmodel.InvoiceViewModel;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class InvoiceRepositoryTest {
//    @Autowired
//    ServiceLayer serviceLayer;
//
//    @BeforeEach
//    public void setUp() throws Exception{
//        serviceLayer.deleteAllInvoices();
//        serviceLayer.deleteAllGames();
//    }
//
//    @Test
//    public void shouldSaveInvoice() throws Exception{
//    // Arrange add a game, + add invoice
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
//        serviceLayer.saveGame(game);
//
//    // add invoice
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceId(1);
//        invoice.setName("Johnny Bravo");
//        invoice.setStreet("Random Street Name");
//        invoice.setCity("Los Angeles");
//        invoice.setState("CA");
//        invoice.setZipcode("12345");
//        invoice.setItemType("Game");
//        invoice.setItemId(game.getGameId());
//        invoice.setQuantity(1);
//        invoice.setUnitPrice(new BigDecimal("19.99"));
//        invoice.setSubtotal(new BigDecimal("19.99"));
//        invoice.setTax(new BigDecimal("1.49"));
//        invoice.setFee(new BigDecimal("1.99"));
//        invoice.setTotal(new BigDecimal("22.68"));
//
//       InvoiceViewModel viewModel =  serviceLayer.saveInvoice(invoice);
//
//
//       assertEquals(viewModel, serviceLayer.buildInvoiceViewModel(invoice));
//
//    }
//
//    @Test
//    public void getInvoiceById() throws Exception{
//        // Arrange - Add a game and invoice
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
//        serviceLayer.saveGame(game); // save the game
//
//        // add invoice
//        Invoice invoice = new Invoice();
//        invoice.setName("Johnny Bravo");
//        invoice.setStreet("Random Street Name");
//        invoice.setCity("Los Angeles");
//        invoice.setState("CA");
//        invoice.setZipcode("12345");
//        invoice.setItemType("Game");
//        invoice.setItemId(game.getGameId());
//        invoice.setQuantity(1);
//        invoice.setUnitPrice(new BigDecimal("19.99"));
//        invoice.setSubtotal(new BigDecimal("19.99"));
//        invoice.setTax(new BigDecimal("1.49"));
//        invoice.setFee(new BigDecimal("1.99"));
//        invoice.setTotal(new BigDecimal("22.68"));
//        serviceLayer.saveInvoice(invoice); // save the invoice
//
//        // Act and Assert
//        assertEquals(invoice, serviceLayer.findInvoiceById(invoice.getInvoiceId()));
//
//    }
//
//    @Test
//    public void shouldGetInvoicesByCustomerName() throws Exception{
//        // Arrange - Add a game and invoice
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
//        serviceLayer.saveGame(game); // save the game
//
//        // add invoice
//        Invoice invoice = new Invoice();
//        invoice.setName("Johnny Bravo");
//        invoice.setStreet("Random Street Name");
//        invoice.setCity("Los Angeles");
//        invoice.setState("CA");
//        invoice.setZipcode("12345");
//        invoice.setItemType("Game");
//        invoice.setItemId(game.getGameId());
//        invoice.setQuantity(1);
//        invoice.setUnitPrice(new BigDecimal("19.99"));
//        invoice.setSubtotal(new BigDecimal("19.99"));
//        invoice.setTax(new BigDecimal("1.49"));
//        invoice.setFee(new BigDecimal("1.99"));
//        invoice.setTotal(new BigDecimal("22.68"));
//        serviceLayer.saveInvoice(invoice); // save the invoice
//
//        // Act
//        List<Invoice> invoiceList = serviceLayer.findInvoicesByCustomerName("Johnny Bravo");
//        // Assert
//        assertEquals(invoiceList.size(), 1);
//    }
//
//    @Test
//    public void getAllInvoices() throws Exception {
//        // Arrange
//
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
//        serviceLayer.saveGame(game);
//
//        Invoice invoice = new Invoice();
//        invoice.setName("Johnny Bravo");
//        invoice.setStreet("Random Street Name");
//        invoice.setCity("Los Angeles");
//        invoice.setState("CA");
//        invoice.setZipcode("12345");
//        invoice.setItemType("Game");
//        invoice.setItemId(game.getGameId());
//        invoice.setQuantity(1);
//        invoice.setUnitPrice(new BigDecimal("19.99"));
//        invoice.setSubtotal(new BigDecimal("19.99"));
//        invoice.setTax(new BigDecimal("1.49"));
//        invoice.setFee(new BigDecimal("1.99"));
//        invoice.setTotal(new BigDecimal("22.68"));
//
//        // Act
//        serviceLayer.saveInvoice(invoice);
//        List<Invoice> invoiceList = serviceLayer.findAllInvoices();
//
//        // Assert
//        assertEquals(invoiceList.size(), 1);
//    }
//
//    @Test
//    public void shouldUpdateInvoice() throws Exception{
//        // Arrange - Add a game and invoice
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
//        serviceLayer.saveGame(game); // save the game
//
//        // add invoice
//        Invoice invoice = new Invoice();
//        invoice.setName("Johnny Bravo");
//        invoice.setStreet("Random Street Name");
//        invoice.setCity("Los Angeles");
//        invoice.setState("CA");
//        invoice.setZipcode("12345");
//        invoice.setItemType("Game");
//        invoice.setItemId(game.getGameId());
//        invoice.setQuantity(1);
//        invoice.setUnitPrice(new BigDecimal("19.99"));
//        invoice.setSubtotal(new BigDecimal("19.99"));
//        invoice.setTax(new BigDecimal("1.49"));
//        invoice.setFee(new BigDecimal("1.99"));
//        invoice.setTotal(new BigDecimal("22.68"));
//        serviceLayer.saveInvoice(invoice); // save the invoice
//
//        // Act - updating field
//        invoice.setName("Jack");
//        serviceLayer.saveInvoice(invoice);
//
//        Invoice inv = serviceLayer.findInvoiceById(invoice.getInvoiceId());
//
//        // Assert
//        assertEquals(invoice,inv);
//    }
//
//    @Test
//    public void shouldDeleteInvoice() throws Exception{
//        // Arrange - Add a game and invoice
//        Game game = new Game();
//        game.setTitle("Minecraft");
//        game.setDescription(" ");
//        game.setPrice(new BigDecimal("19.99"));
//        game.setStudio("Mojang");
//        game.setQuantity(5);
//        game.setEsrbRating("E");
//        serviceLayer.saveGame(game); // save the game
//
//        // add invoice
//        Invoice invoice = new Invoice();
//        invoice.setName("Johnny Bravo");
//        invoice.setStreet("Random Street Name");
//        invoice.setCity("Los Angeles");
//        invoice.setState("CA");
//        invoice.setZipcode("12345");
//        invoice.setItemType("Game");
//        invoice.setItemId(game.getGameId());
//        invoice.setQuantity(1);
//        invoice.setUnitPrice(new BigDecimal("19.99"));
//        invoice.setSubtotal(new BigDecimal("19.99"));
//        invoice.setTax(new BigDecimal("1.49"));
//        invoice.setFee(new BigDecimal("1.99"));
//        invoice.setTotal(new BigDecimal("22.68"));
//        serviceLayer.saveInvoice(invoice); // save the invoice
//
//        // Act - check if the invoice exists in DB, delete if so
//        assertEquals(invoice, serviceLayer.findInvoiceById(invoice.getInvoiceId()));
//
//        serviceLayer.deleteInvoiceById(invoice.getInvoiceId()); // delete
//
//        Invoice inv = serviceLayer.findInvoiceById(invoice.getInvoiceId());
//
//        assertNull(inv); // should be null if successfully deleted
//    }
//
// }
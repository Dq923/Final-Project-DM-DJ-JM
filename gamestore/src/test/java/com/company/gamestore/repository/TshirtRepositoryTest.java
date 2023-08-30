package com.company.gamestore.repository;

import com.company.gamestore.model.Tshirt;
import com.company.gamestore.service.ServiceLayer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TshirtRepositoryTest {

    @Autowired
    ServiceLayer serviceLayer;

    @BeforeEach
    void setUp() throws Exception {
        serviceLayer.deleteAllShirts();

        Tshirt tshirt1 = new Tshirt();
        tshirt1.setId(1);
        tshirt1.setColor("Black");
        tshirt1.setSize("Small");
        tshirt1.setDescription("Graphic-Tee");
        tshirt1.setPrice(BigDecimal.valueOf(19.99));
        tshirt1.setQuantity(20);

        serviceLayer.saveTshirt(tshirt1);

        Tshirt tshirt2 = new Tshirt();
        tshirt2.setId(2);
        tshirt2.setColor("Black");
        tshirt2.setSize("Medium");
        tshirt2.setDescription("Baseball Tee");
        tshirt2.setPrice(BigDecimal.valueOf(29.99));
        tshirt2.setQuantity(20);

        serviceLayer.saveTshirt(tshirt2);


        Tshirt tshirt3 = new Tshirt();
        tshirt3.setId(3);
        tshirt3.setColor("Yellow");
        tshirt3.setSize("small");
        tshirt3.setDescription(" Bright yellow t-shirt");
        tshirt3.setPrice(BigDecimal.valueOf(9.99));
        tshirt3.setQuantity(5);

        serviceLayer.saveTshirt(tshirt3);
    }

    // GET all T-Shirts
    @Test
    public void getAllTshirts() throws Exception {
        List<Tshirt> tshirts = serviceLayer.findAllShirts();
        assertEquals(tshirts.size(), 3);
    }

    // GET T-Shirt By ID
    @Test
    public void getTshirtById() throws Exception {

        Tshirt tshirtA = new Tshirt();
        tshirtA.setColor("blue");
        // HAD to add these in order for test to pass, Exception thrown when fields are null
        tshirtA.setDescription("description");
        tshirtA.setSize("large");
        tshirtA.setPrice(new BigDecimal("5.00"));
        tshirtA.setQuantity(2);

        serviceLayer.saveTshirt(tshirtA);

        Optional<Tshirt> tshirtB = serviceLayer.findShirtById(tshirtA.getId());

        assertEquals(tshirtB.get(), tshirtA);

    }

    // GET all T-Shirts By Color
    @Test
    public void getTshirtsByColor() throws Exception {
        List<Tshirt> blackTshirts = serviceLayer.findShirtByColor("black");
        List<Tshirt> tshirts = serviceLayer.findShirtByColor("Black");

        assertEquals(tshirts, blackTshirts);
    }

    // Get all T-Shirts By Size
    @Test
    public void getTshirtsBySize() throws Exception {
        List<Tshirt> smallTshirts = serviceLayer.findShirtBySize("small");
        List<Tshirt> tshirts = serviceLayer.findShirtBySize("Small");

        assertEquals(smallTshirts, tshirts);
    }

    // POST a T-Shirt
    @Test
    public void addTshirt() throws Exception {

        Tshirt tshirt = new Tshirt();
        // HAD to add these in order for test to pass, Exception thrown when fields are null
        tshirt.setColor("purple");
        tshirt.setDescription("description");
        tshirt.setSize("large");
        tshirt.setPrice(new BigDecimal("5.00"));
        tshirt.setQuantity(2);

        serviceLayer.saveTshirt(tshirt);

        Optional<Tshirt> tshirtA = serviceLayer.findShirtById(tshirt.getId());

        assertEquals(tshirtA.get(), tshirt);
    }

    //UPDATE a T-Shirt
    @Test
    public void updateTshirt() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("purple");
        tshirt.setDescription("description");
        tshirt.setSize("large");
        tshirt.setPrice(new BigDecimal("5.00"));
        tshirt.setQuantity(2);
        serviceLayer.saveTshirt(tshirt);

        tshirt.setColor("blue");

        serviceLayer.saveTshirt(tshirt);

        Optional<Tshirt> tshirtA = serviceLayer.findShirtById(tshirt.getId());

        assertEquals(tshirtA.get(), tshirt);
    }


    // DELETE a T-Shirt
    @Test
    public void deleteTshirt() throws Exception {
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("purple");
        tshirt.setDescription("description");
        tshirt.setSize("large");
        tshirt.setPrice(new BigDecimal("5.00"));
        tshirt.setQuantity(2);
        serviceLayer.saveTshirt(tshirt);

        serviceLayer.deleteShirt(tshirt.getId());

        assertEquals(serviceLayer.findShirtById(tshirt.getId()), Optional.empty());
    }
}
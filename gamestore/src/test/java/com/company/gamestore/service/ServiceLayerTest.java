package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServiceLayerTest {

    ServiceLayer serviceLayer;
    GameRepository gameRepository;
    TshirtRepository tshirtRepository;
    ConsoleRepository consoleRepository;
    FeeRepository feeRepository;
    TaxRepository taxRepository;
    InvoiceRepository invoiceRepository;

    @BeforeEach
    public void setUp() throws Exception{
        setUpGameRepositoryMock();
        setUpTshirtRepositoryMock();
        setUpConsoleRepositoryMock();
        setUpInvoiceRepositoryMock();
        setUpTaxRepositoryMock();
        setUpFeeRepositoryMock();

        serviceLayer = new ServiceLayer(invoiceRepository, gameRepository, tshirtRepository,
                consoleRepository, feeRepository, taxRepository);
    }



//
// GAME SERVICE TESTING
//
    @Test
    public void shouldSaveGame(){
        // Arrange
        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("description");
        expectedResult.setStudio("studio");
        expectedResult.setEsrbRating("M");
        expectedResult.setPrice(new BigDecimal("19.99"));
        expectedResult.setQuantity(15);

    // Game to be saved
        Game game = new Game();
        game.setTitle("Minecraft");
        game.setDescription("description");
        game.setStudio("studio");
        game.setEsrbRating("M");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(15);

        // Act
        game = serviceLayer.saveGame(game);

        // Assert
        assertEquals(expectedResult, game);
    }

    @Test
    public void shouldFindGameById(){

        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("description");
        expectedResult.setStudio("studio");
        expectedResult.setEsrbRating("M");
        expectedResult.setPrice(new BigDecimal("19.99"));
        expectedResult.setQuantity(15);

        assertEquals(expectedResult, serviceLayer.findGameById(1));
    }

    @Test
    public void shouldFindAllGames(){
        List<Game> expectedGameList = new ArrayList<>();// make a list of expected game titles

        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("description");
        expectedResult.setStudio("studio");
        expectedResult.setEsrbRating("M");
        expectedResult.setPrice(new BigDecimal("19.99"));
        expectedResult.setQuantity(15);

        expectedGameList.add(expectedResult); // add game to the list

        assertEquals(expectedGameList, serviceLayer.findAllGames());// compare
    }

    @Test
    public void shouldFindGamesByTitle(){
        List<Game> expectedGameList = new ArrayList<>();// make a list of expected game titles

        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("description");
        expectedResult.setStudio("studio");
        expectedResult.setEsrbRating("M");
        expectedResult.setPrice(new BigDecimal("19.99"));
        expectedResult.setQuantity(15);

        expectedGameList.add(expectedResult); // add game to the list

        assertEquals(expectedGameList, serviceLayer.findGamesByTitle("Minecraft"));// compare
    }

    @Test
    public void shouldFindGamesByEsrbRating(){
        List<Game> expectedGameList = new ArrayList<>();

        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("description");
        expectedResult.setStudio("studio");
        expectedResult.setEsrbRating("M");
        expectedResult.setPrice(new BigDecimal("19.99"));
        expectedResult.setQuantity(15);

        expectedGameList.add(expectedResult);

        assertEquals(expectedGameList, serviceLayer.findGamesByEsrbRating("M"));
    }

    @Test
    public void shouldFindGamesByStudio(){
        List<Game> expectedGameList = new ArrayList<>();

        Game expectedResult = new Game();
        expectedResult.setGameId(1);
        expectedResult.setTitle("Minecraft");
        expectedResult.setDescription("description");
        expectedResult.setStudio("studio");
        expectedResult.setEsrbRating("M");
        expectedResult.setPrice(new BigDecimal("19.99"));
        expectedResult.setQuantity(15);

        expectedGameList.add(expectedResult);

        assertEquals(expectedGameList, serviceLayer.findGamesByStudio("studio"));
    }



//
// TSHIRT SERVICE TESTING
//
    @Test
    public void shouldSaveTshirt(){
        // Arrange
        Tshirt expectedResult = new Tshirt();
        expectedResult.setId(1);
        expectedResult.setColor("purple");
        expectedResult.setDescription("description");
        expectedResult.setSize("large");
        expectedResult.setPrice(new BigDecimal("5.00"));
        expectedResult.setQuantity(2);

        // Tshirt to be saved
        Tshirt tshirt = new Tshirt();
        tshirt.setColor("purple");
        tshirt.setDescription("description");
        tshirt.setSize("large");
        tshirt.setPrice(new BigDecimal("5.00"));
        tshirt.setQuantity(2);

        // Act
        tshirt = serviceLayer.saveTshirt(tshirt);

        // Assert
        assertEquals(expectedResult, tshirt);
    }

    @Test
    public void shouldFindTshirtById(){
        Tshirt expectedResult = new Tshirt();
        expectedResult.setId(1);
        expectedResult.setColor("purple");
        expectedResult.setDescription("description");
        expectedResult.setSize("large");
        expectedResult.setPrice(new BigDecimal("5.00"));
        expectedResult.setQuantity(2);

        assertEquals(Optional.of(expectedResult),serviceLayer.findShirtById(1));
    }

    @Test
    public void shouldFindAllTShirts(){
        List<Tshirt> shirts = new ArrayList<>();

        Tshirt expectedResult = new Tshirt();
        expectedResult.setId(1);
        expectedResult.setColor("purple");
        expectedResult.setDescription("description");
        expectedResult.setSize("large");
        expectedResult.setPrice(new BigDecimal("5.00"));
        expectedResult.setQuantity(2);

        shirts.add(expectedResult);

        assertEquals(shirts, serviceLayer.findAllShirts());
    }

    @Test
    public void shouldFindTshirtsByColor(){
        List<Tshirt> shirts = new ArrayList<>();

        Tshirt expectedResult = new Tshirt();
        expectedResult.setId(1);
        expectedResult.setColor("purple");
        expectedResult.setDescription("description");
        expectedResult.setSize("large");
        expectedResult.setPrice(new BigDecimal("5.00"));
        expectedResult.setQuantity(2);

        shirts.add(expectedResult);

        assertEquals(shirts, serviceLayer.findShirtByColor("purple"));
    }

    @Test
    public void shouldFindTshirtsBySize(){
        List<Tshirt> shirts = new ArrayList<>();

        Tshirt expectedResult = new Tshirt();
        expectedResult.setId(1);
        expectedResult.setColor("purple");
        expectedResult.setDescription("description");
        expectedResult.setSize("large");
        expectedResult.setPrice(new BigDecimal("5.00"));
        expectedResult.setQuantity(2);

        shirts.add(expectedResult);

        assertEquals(shirts, serviceLayer.findShirtBySize("large"));
    }




//
// CONSOLE SERVICE TESTING
//

    @Test
    public void shouldSaveConsole(){
        // Arrange
        Console expectedResult = new Console();
        expectedResult.setId(1);
        expectedResult.setModel("Model W");
        expectedResult.setManufacturer("Microsoft");
        expectedResult.setMemoryAmount("1TB");
        expectedResult.setProcessor("AMD Zen 10 CPU");
        BigDecimal price2 = new BigDecimal("599.99");
        expectedResult.setPrice(price2);
        expectedResult.setQuantity(10);

        // This is the console we are saving
        Console console2 = new Console();
        console2.setModel("Model W");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("1TB");
        console2.setProcessor("AMD Zen 10 CPU");
        console2.setPrice(price2);
        console2.setQuantity(10);

        console2 = serviceLayer.saveConsole(console2);

        assertEquals(expectedResult, console2);
    }

    @Test
    public void shouldFindAllConsoles(){
        List<Console> consoleList = new ArrayList<>();

        Console expectedResult = new Console();
        expectedResult.setId(1);
        expectedResult.setModel("Model W");
        expectedResult.setManufacturer("Microsoft");
        expectedResult.setMemoryAmount("1TB");
        expectedResult.setProcessor("AMD Zen 10 CPU");
        BigDecimal price2 = new BigDecimal("599.99");
        expectedResult.setPrice(price2);
        expectedResult.setQuantity(10);

        consoleList.add(expectedResult); // add console to list

        assertEquals(consoleList, serviceLayer.findAllConsoles());

    }


    @Test
    public void shouldFindConsoleById(){
        Console expectedResult = new Console();
        expectedResult.setId(1);
        expectedResult.setModel("Model W");
        expectedResult.setManufacturer("Microsoft");
        expectedResult.setMemoryAmount("1TB");
        expectedResult.setProcessor("AMD Zen 10 CPU");
        BigDecimal price2 = new BigDecimal("599.99");
        expectedResult.setPrice(price2);
        expectedResult.setQuantity(10);

        assertEquals(Optional.of(expectedResult), serviceLayer.findConsoleById(1));

    }

    @Test
    public void shouldFindConsoleByManufacturer(){
        List<Console> consoleList = new ArrayList<>();

        Console expectedResult = new Console();
        expectedResult.setId(1);
        expectedResult.setModel("Model W");
        expectedResult.setManufacturer("Microsoft");
        expectedResult.setMemoryAmount("1TB");
        expectedResult.setProcessor("AMD Zen 10 CPU");
        BigDecimal price2 = new BigDecimal("599.99");
        expectedResult.setPrice(price2);
        expectedResult.setQuantity(10);

        consoleList.add(expectedResult);

        assertEquals(consoleList, serviceLayer.findConsolesByManufacturers("Microsoft"));

    }



//
//  INVOICE TESTING
//

    @Test
    public void shouldBuildInvoiceViewModel(){
        // Expected view model to be returned
        InvoiceViewModel viewModel = new InvoiceViewModel();
        viewModel.setId(1);
        viewModel.setName("Johnny Bravo");
        viewModel.setStreet("Random Street Name");
        viewModel.setCity("Los Angeles");
        viewModel.setState("CA");
        viewModel.setZipcode("12345");
        viewModel.setItemType("game");
        viewModel.setItemId(25);
        viewModel.setQuantity(1);
        viewModel.setUnitPrice(new BigDecimal("19.99"));
        viewModel.setSubtotal(new BigDecimal("19.99"));
        viewModel.setTax(new BigDecimal("0.06").multiply(viewModel.getSubtotal())); // tax rate * subtotal
        viewModel.setFee(new BigDecimal("1.49"));
        viewModel.setTotal(viewModel.getSubtotal().add(viewModel.getTax().add(viewModel.getFee())));

        // Invoice to be saved
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Johnny Bravo");
        invoice.setStreet("Random Street Name");
        invoice.setCity("Los Angeles");
        invoice.setState("CA");
        invoice.setZipcode("12345");
        invoice.setItemType("game");
        invoice.setItemId(25);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(new BigDecimal("19.99"));
        invoice.setTax(new BigDecimal("0.06").multiply(viewModel.getSubtotal())); // tax rate * subtotal
        invoice.setFee(new BigDecimal("1.49"));
        invoice.setTotal(viewModel.getSubtotal().add(viewModel.getTax().add(viewModel.getFee())));

        InvoiceViewModel ivm = serviceLayer.buildInvoiceViewModel(invoice);

        assertEquals(viewModel, ivm);

    }

    @Test
    public void shouldSaveInvoice() {
        //create invoice
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Johnny Bravo");
        invoice.setStreet("Random Street Name");
        invoice.setCity("Los Angeles");
        invoice.setState("CA");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(25);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(calculateSubtotal(invoice.getUnitPrice(), invoice.getQuantity()));
        invoice.setTax(calculateTax(invoice.getSubtotal())); // tax rate * subtotal
        invoice.setFee(calculateFee(invoice.getItemType()));

        BigDecimal grandTotal = invoice.getSubtotal().add(invoice.getTax()
                .add(invoice.getFee()).setScale(2, BigDecimal.ROUND_HALF_UP));

        invoice.setTotal(grandTotal);

        //Create expected IVM
        InvoiceViewModel expected = serviceLayer.buildInvoiceViewModel(invoice);

        // Create Actual
        InvoiceViewModel actual = serviceLayer.saveInvoice(invoice);

        //assertE
        assertEquals(expected, actual);
    }

    @Test
    public void shouldFindAllInvoices(){
        List<Invoice> invoiceList = new ArrayList<>();

        // create invoice obj
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Johnny Bravo");
        invoice.setStreet("Random Street Name");
        invoice.setCity("Los Angeles");
        invoice.setState("CA");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(25);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(calculateSubtotal(invoice.getUnitPrice(), invoice.getQuantity()));
        invoice.setTax(invoice.getSubtotal()); // tax rate * subtotal
        invoice.setFee(calculateFee(invoice.getItemType()));
        invoice.setTotal(new BigDecimal("22.68"));

        invoiceList.add(invoice); // add to list

        assertEquals(serviceLayer.findAllInvoices(), invoiceList);
    }


    @Test
    public void shouldFindInvoiceByName(){
        // Arrange - create a list for invoices
        List<Invoice> invoiceList = new ArrayList<>();

        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Johnny Bravo");
        invoice.setStreet("Random Street Name");
        invoice.setCity("Los Angeles");
        invoice.setState("CA");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(25);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(calculateSubtotal(invoice.getUnitPrice(), invoice.getQuantity()));
        invoice.setTax(invoice.getSubtotal()); // tax rate * subtotal
        invoice.setFee(calculateFee(invoice.getItemType()));
        invoice.setTotal(new BigDecimal("22.68"));

        invoiceList.add(invoice); // add to list

        // Assert
        assertEquals(invoiceList, serviceLayer.findInvoicesByCustomerName("Johnny Bravo"));

    }
// Helper methods
    // Calculate subtotal
    public BigDecimal calculateSubtotal(BigDecimal unitPrice, int quantity){
        return unitPrice.multiply(new BigDecimal(quantity)).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    // Calculate Tax for item
    public BigDecimal calculateTax(BigDecimal subtotal){
        return new BigDecimal("0.06").multiply(subtotal).setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public BigDecimal calculateFee(String itemType){
        BigDecimal fee = new BigDecimal("0");

        if(Objects.equals(itemType, "Game"))
            fee = new BigDecimal("1.49");
        else if(Objects.equals(itemType,"T-shirt"))
            fee = new BigDecimal("1.98");
        else if(Objects.equals(itemType, "Console"))
            fee =  new BigDecimal("14.99");

        return fee;
    }

    // set up GameRepo mock
    private void setUpGameRepositoryMock(){

        gameRepository = mock(GameRepository.class);
        // object being returned
        Game game = new Game();
        game.setGameId(1);
        game.setTitle("Minecraft");
        game.setDescription("description");
        game.setStudio("studio");
        game.setEsrbRating("M");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(15);

        // Object to be saved
        Game game2 = new Game();
        game2.setTitle("Minecraft");
        game2.setDescription("description");
        game2.setStudio("studio");
        game2.setEsrbRating("M");
        game2.setPrice(new BigDecimal("19.99"));
        game2.setQuantity(15);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);
        // When game2 is saved to the database, game1 will be returned, effectively mocking a post
        doReturn(game).when(gameRepository).save(game2);
        doReturn(Optional.of(game)).when(gameRepository).findById(1); // mock of GET BY ID
        doReturn(gameList).when(gameRepository).findAll(); // return gameList when GET ALL is returned
        // add any custom queries
        doReturn(gameList).when(gameRepository).findGamesByTitle("Minecraft");
        doReturn(gameList).when(gameRepository).findAllByEsrbRating("M");
        doReturn(gameList).when(gameRepository).findAllByStudio("studio");
        doNothing().when(gameRepository).deleteById(1);


    }

    // set up mock for Tshirt
    private void setUpTshirtRepositoryMock() {
        tshirtRepository = mock(TshirtRepository.class);

        // tshirt to be returned
        Tshirt tshirt = new Tshirt();
        tshirt.setId(1);
        tshirt.setColor("purple");
        tshirt.setDescription("description");
        tshirt.setSize("large");
        tshirt.setPrice(new BigDecimal("5.00"));
        tshirt.setQuantity(2);

        // tshirt being saved
        Tshirt tshirt2 = new Tshirt();
        tshirt2.setColor("purple");
        tshirt2.setDescription("description");
        tshirt2.setSize("large");
        tshirt2.setPrice(new BigDecimal("5.00"));
        tshirt2.setQuantity(2);

        List<Tshirt> shirts = new ArrayList<>();
        shirts.add(tshirt);

        doReturn(tshirt).when(tshirtRepository).save(tshirt2); // return tshirt w/ ID when it is saved
        doReturn(Optional.of(tshirt)).when(tshirtRepository).findById(1); // Return optional when find by id is called
        doReturn(shirts).when(tshirtRepository).findAll(); // return list when GET ALL is requested
        // add any custom queries
        doReturn(shirts).when(tshirtRepository).findTshirtBySize("large");
        doReturn(shirts).when(tshirtRepository).findTshirtByColor("purple");
    }

    private void setUpConsoleRepositoryMock() {
        consoleRepository = mock(ConsoleRepository.class);

        // console to be returned
        Console console = new Console();
        console.setId(1);
        console.setModel("Model W");
        console.setManufacturer("Microsoft");
        console.setMemoryAmount("1TB");
        console.setProcessor("AMD Zen 10 CPU");
        BigDecimal price2 = new BigDecimal("599.99");
        console.setPrice(price2);
        console.setQuantity(10);

        // console being saved
        Console console2 = new Console();
        console2.setModel("Model W");
        console2.setManufacturer("Microsoft");
        console2.setMemoryAmount("1TB");
        console2.setProcessor("AMD Zen 10 CPU");
        console2.setPrice(price2);
        console2.setQuantity(10);

        List<Console> consoles = new ArrayList<>();
        consoles.add(console);

        doReturn(console).when(consoleRepository).save(console2); // return console w/ ID when it is saved
        doReturn(Optional.of(console)).when(consoleRepository).findById(1); // Return optional when find by id is called
        doReturn(consoles).when(consoleRepository).findAll(); // return list when GET ALL is requested
        // add any custom queries
        doReturn(consoles).when(consoleRepository).findAllConsoleByManufacturer("Microsoft");
    }

    private void setUpInvoiceRepositoryMock() {
        invoiceRepository = mock(InvoiceRepository.class);

    // invoice view model to be returned (maybe change this to view model)
        InvoiceViewModel viewModel = new InvoiceViewModel();
        viewModel.setId(1);
        viewModel.setName("Johnny Bravo");
        viewModel.setStreet("Random Street Name");
        viewModel.setCity("Los Angeles");
        viewModel.setState("CA");
        viewModel.setZipcode("12345");
        viewModel.setItemType("Game");
        viewModel.setItemId(25);
        viewModel.setQuantity(1);
        viewModel.setUnitPrice(new BigDecimal("19.99"));
        viewModel.setSubtotal(calculateSubtotal(viewModel.getUnitPrice(),viewModel.getQuantity()));
        viewModel.setTax(calculateTax(viewModel.getSubtotal())); // tax rate * subtotal
        viewModel.setFee(calculateFee(viewModel.getItemType()));

        // calculate grand total
        BigDecimal grandTotal = viewModel.getSubtotal().add(viewModel.getTax()
                .add(viewModel.getFee()).setScale(2, BigDecimal.ROUND_HALF_UP));

        viewModel.setTotal(grandTotal);



        // Invoice being saved
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setName("Johnny Bravo");
        invoice.setStreet("Random Street Name");
        invoice.setCity("Los Angeles");
        invoice.setState("CA");
        invoice.setZipcode("12345");
        invoice.setItemType("Game");
        invoice.setItemId(25);
        invoice.setQuantity(1);
        invoice.setUnitPrice(new BigDecimal("19.99"));
        invoice.setSubtotal(calculateSubtotal(invoice.getUnitPrice(), invoice.getQuantity()));
        invoice.setTax(invoice.getSubtotal()); // tax rate * subtotal
        invoice.setFee(calculateFee(invoice.getItemType()));
        invoice.setTotal(grandTotal);

        // Example game item returned for invoice
        Game game = new Game();
        game.setGameId(25);
        game.setTitle("Minecraft");
        game.setDescription("description");
        game.setStudio("studio");
        game.setEsrbRating("M");
        game.setPrice(new BigDecimal("19.99"));
        game.setQuantity(15);
        List<Invoice> invoices = new ArrayList<>();
        invoices.add(invoice);

        doReturn(viewModel).when(invoiceRepository).save(invoice); // return invoice view model w/ ID when it is saved
        doReturn(Optional.of(viewModel)).when(invoiceRepository).findById(1); // Return optional when find by id is called
        doReturn(invoices).when(invoiceRepository).findAll(); // return list when GET ALL is requested

    // to save an invoice, a game, tshirt, or console is searched by ID, therefore:
        doReturn(Optional.of(game)).when(gameRepository).findById(25);
    // custom queries
        doReturn(invoices).when(invoiceRepository).findInvoicesByName("Johnny Bravo");
    }

    private void setUpTaxRepositoryMock(){
        taxRepository = mock(TaxRepository.class);

        // Tax expected
        Tax tax = new Tax();
        tax.setState("CA");
        tax.setRate(new BigDecimal("0.06"));

        doReturn(Optional.of(tax)).when(taxRepository).findTaxRateByState("CA");
    }
    private void setUpFeeRepositoryMock(){
        feeRepository = mock(FeeRepository.class);

        Fee fee = new Fee();
        fee.setProductType("Game");
        fee.setFee(new BigDecimal("1.49"));

        doReturn(Optional.of(fee)).when(feeRepository).findByProductType("Game");
    }





}
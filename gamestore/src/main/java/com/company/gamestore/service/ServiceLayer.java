package com.company.gamestore.service;

import com.company.gamestore.model.*;
import com.company.gamestore.repository.*;
import com.company.gamestore.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceLayer {

    private InvoiceRepository invoiceRepository;
    private GameRepository gameRepository;
    private TshirtRepository tshirtRepository;

    private ConsoleRepository consoleRepository;
    private FeeRepository feeRepository;
    private TaxRepository taxRepository;

    @Autowired
    public ServiceLayer(InvoiceRepository invRepo, GameRepository gameRepo, TshirtRepository tshirtRepo,
                        ConsoleRepository consoleRepo, FeeRepository feeRepo, TaxRepository taxRepo) {
        this.invoiceRepository = invRepo;
        this.gameRepository = gameRepo;
        this.tshirtRepository = tshirtRepo;
        this.consoleRepository = consoleRepo;
        this.feeRepository = feeRepo;
        this.taxRepository = taxRepo;
    }

    @Transactional
    public Game saveGame(Game game) {
        // Validation for Game fields
        if (game.getTitle().isEmpty())
            throw new IllegalArgumentException("Game title was not provided.");
        else if (game.getDescription().isEmpty())
            throw new IllegalArgumentException("Game description was not provided");
        else if (game.getStudio().isEmpty())
            throw new IllegalArgumentException("Game studio name was not provided.");
        else if (game.getEsrbRating().isEmpty())
            throw new IllegalArgumentException("ESRB Rating was not provided");
        else if (game.getPrice().compareTo(new BigDecimal("0.00")) < 0) // if the price is a negative value
            throw new IllegalArgumentException("Not a valid price. Must be >= 0.00");
        else if (game.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");

        // If there are no issues with validation, save the game
        game = gameRepository.save(game);

        return game;
    }


    // Find(Read) Game By ID
    // GET BY ID
    public Game findGameById(int id) {
        Optional<Game> game = gameRepository.findById(id);

        // if the game is present, return the game, otherwise return null
        return game.orElse(null);
    }


    // GET ALL Games

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    // PUT - Update a Game record
    @Transactional
    public void updateGame(Game game) {
        // Validation for Game fields
        if (game.getTitle().isEmpty())
            throw new IllegalArgumentException("Game title was not provided.");
        else if (game.getDescription().isEmpty())
            throw new IllegalArgumentException("Game description was not provided");
        else if (game.getStudio().isEmpty())
            throw new IllegalArgumentException("Game studio name was not provided.");
        else if (game.getEsrbRating().isEmpty())
            throw new IllegalArgumentException("ESRB Rating was not provided");
        else if (game.getPrice().compareTo(new BigDecimal("0.00")) < 0) // if the price is a negative value
            throw new IllegalArgumentException("Not a valid price. Must be >= 0");
        else if (game.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative");

        gameRepository.save(game); // save the game's updated info
    }

    // DELETE a game
    @Transactional
    public void deleteGame(int id) {
        Optional<Game> game = gameRepository.findById(id); // search by ID

        if (game.isEmpty()) { // if not found throw exception
            throw new IllegalArgumentException("Game cannot be found by the ID provided.");
        }

        gameRepository.deleteById(id); // delete by id if the game was found
    }

    @Transactional
    public void deleteAllGames() {
        gameRepository.deleteAll();
    }

    // FIND GAMES BY STUDIO
    public List<Game> findGamesByStudio(String studio) {
        List<Game> gameList = gameRepository.findAllByStudio(studio);

        // Do we throw an exception if empty or just return the empty list?

        return gameList;
    }

    // FIND GAMES BY RATING

    public List<Game> findGamesByEsrbRating(String rating) {
        // Do we need to validate for all possible ratings? (E, E10, T, M, etc) ?

        List<Game> gameList = gameRepository.findAllByEsrbRating(rating);

        return gameList;
    }

    public List<Game> findGamesByTitle(String title) {
        List<Game> gameList = gameRepository.findGamesByTitle(title);
        return gameList;
    }

    //
    // Console API
    //
    // SAVE A CONSOLE
    @Transactional
    public Console saveConsole(Console console) {
        if (console.getModel().isEmpty()) // check if fields are valid
            throw new IllegalArgumentException("Model not provided.");
        else if (console.getManufacturer().isEmpty())
            throw new IllegalArgumentException("Manufacturer not provided.");
        else if (console.getMemoryAmount().isEmpty())
            throw new IllegalArgumentException("Memory amount information not provided. ");
        else if (console.getProcessor().isEmpty())
            throw new IllegalArgumentException("Processor info not provided.");
        else if (console.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (console.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        console = consoleRepository.save(console); // save to database

        return console;
    }

    // READ BY ID
    public Optional<Console> findConsoleById(int id) {
        Optional<Console> console = consoleRepository.findById(id);

        return console;
    }

    // READ ALL
    public List<Console> findAllConsoles() {
        return consoleRepository.findAll();
    }

    // UPDATE CONSOLE
    @Transactional
    public void updateConsole(Console console) {
        if (console.getModel().isEmpty()) // check if fields are valid
            throw new IllegalArgumentException("Model not provided.");
        else if (console.getManufacturer().isEmpty())
            throw new IllegalArgumentException("Manufacturer not provided.");
        else if (console.getMemoryAmount().isEmpty())
            throw new IllegalArgumentException("Memory amount information not provided. ");
        else if (console.getProcessor().isEmpty())
            throw new IllegalArgumentException("Processor info not provided.");
        else if (console.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (console.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        console = consoleRepository.save(console); // save to database
    }

    // DELETE BY ID
    @Transactional
    public void deleteConsole(int id) {
        Optional<Console> console = consoleRepository.findById(id); // check if the shirt exists

        consoleRepository.deleteById(id);// delete the console
    }

    @Transactional
    public void deleteAllConsoles() {
        consoleRepository.deleteAll();
    }

    // FIND CONSOLE BY MANUFACTURER
    public List<Console> findConsolesByManufacturers(String manufacturer) {
        return consoleRepository.findAllConsoleByManufacturer(manufacturer);
    }

    //
    // Tshirt API
    //

    // SAVE A TSHIRT (POST)
    @Transactional
    public Tshirt saveTshirt(Tshirt tshirt) {
        if (tshirt.getColor().isEmpty())
            throw new IllegalArgumentException("Shirt color not provided.");
        else if (tshirt.getSize().isEmpty())
            throw new IllegalArgumentException("Shirt size not provided.");
        else if (tshirt.getDescription().isEmpty())
            throw new IllegalArgumentException("Shirt description not provided.");
        else if (tshirt.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (tshirt.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        tshirt = tshirtRepository.save(tshirt);

        return tshirt;
    }

    // READ BY ID
    public Optional<Tshirt> findShirtById(int id) {

        return tshirtRepository.findById(id);
    }

    // READ ALL
    public List<Tshirt> findAllShirts() {
        return tshirtRepository.findAll();
    }

    // UPDATE TSHIRT
    @Transactional
    public void updateShirt(Tshirt tshirt) {
        if (tshirt.getColor().isEmpty()) // Ensure all necessary fields are provided
            throw new IllegalArgumentException("Shirt color not provided.");
        else if (tshirt.getSize().isEmpty())
            throw new IllegalArgumentException("Shirt size not provided.");
        else if (tshirt.getDescription().isEmpty())
            throw new IllegalArgumentException("Shirt description not provided.");
        else if (tshirt.getPrice().compareTo(new BigDecimal("0.00")) < 0)
            throw new IllegalArgumentException("Price cannot be negative.");
        else if (tshirt.getQuantity() < 0)
            throw new IllegalArgumentException("Quantity cannot be negative.");

        tshirt = tshirtRepository.save(tshirt); // save to tshirtRepository

    }

    // DELETE A SHIRT
    @Transactional
    public void deleteShirt(int id) {
        Optional<Tshirt> shirt = tshirtRepository.findById(id); // check if the shirt exists

        tshirtRepository.deleteById(id);//  delete by id
    }

    @Transactional
    public void deleteAllShirts() {
        tshirtRepository.deleteAll();
    }

    // FIND SHIRT BY COLOR
    public List<Tshirt> findShirtByColor(String color) { // Might consider returning a Tshirt instead of List<Tshirt>
        return tshirtRepository.findTshirtByColor(color);
    }

    // FIND SHIRT BY SIZE

    public List<Tshirt> findShirtBySize(String size) {
        return tshirtRepository.findTshirtBySize(size);
    }

    //
    // Invoice API
    //
    public InvoiceViewModel buildInvoiceViewModel(Invoice invoice) {
        InvoiceViewModel inv = new InvoiceViewModel();

        // Assemble the view model
        inv.setId(invoice.getInvoiceId());
        inv.setName(invoice.getName());
        inv.setStreet(invoice.getStreet());
        inv.setCity(invoice.getCity());
        inv.setState(invoice.getState());
        inv.setZipcode(invoice.getZipcode());
        inv.setItemType(invoice.getItemType());
        inv.setItemId(invoice.getItemId());
        inv.setUnitPrice(invoice.getUnitPrice());
        inv.setQuantity(invoice.getQuantity());
        inv.setSubtotal(invoice.getSubtotal());
        inv.setTax(invoice.getTax());
        inv.setFee(invoice.getFee());
        inv.setTotal(invoice.getTotal());

        return inv; // return the invoice view model
    }


    // SAVE(POST) an invoice
    @Transactional
    public InvoiceViewModel saveInvoice(Invoice newInvoice) {
        // Validate item type (Maybe find a way to reduce later)
        switch (newInvoice.getItemType().toUpperCase()) {
            case "GAME":
                newInvoice.setItemType("Game");
                Optional<Game> game = gameRepository.findById(newInvoice.getItemId());

                if (game.isEmpty()) {// if not found
                    throw new IllegalArgumentException("Game not found.");
                }

                if (game.get().getQuantity() < newInvoice.getQuantity()) {// if not enough in stock
                    throw new IllegalArgumentException("Not enough games in stock.");
                }
                newInvoice.setUnitPrice(game.get().getPrice()); //set the price
                game.get().setQuantity(game.get().getQuantity() - newInvoice.getQuantity()); // update stock
                break;
            case "TSHIRT":
            case "T-SHIRT":
            case "T SHIRT":
                newInvoice.setItemType("T-shirt");
                Optional<Tshirt> tShirt = tshirtRepository.findById(newInvoice.getItemId());

                if (tShirt.isEmpty()) { // if not found
                    throw new IllegalArgumentException("T-shirt not found.");
                }

                if (tShirt.get().getQuantity() < newInvoice.getQuantity()) { // if not enough in stock
                    throw new IllegalArgumentException("Not enough Tshirts in stock.");
                }

                newInvoice.setUnitPrice(tShirt.get().getPrice()); // sets the price
                tShirt.get().setQuantity(tShirt.get().getQuantity() - newInvoice.getQuantity()); // update stock
                break;
            case "CONSOLE":
                newInvoice.setItemType("Console");
                Optional<Console> console = consoleRepository.findById(newInvoice.getItemId());

                if (console.isEmpty()) {// if not found
                    throw new IllegalArgumentException("Console not found.");
                }

                if (console.get().getQuantity() < newInvoice.getQuantity()) { // if not enough in stock
                    throw new IllegalArgumentException("Not enough consoles in stock.");
                }
                newInvoice.setUnitPrice(console.get().getPrice());// sets the price
                console.get().setQuantity(console.get().getQuantity() - newInvoice.getQuantity()); // update stock

                break;
            default:
                throw new IllegalArgumentException("Not a valid item type");
        }

    // Calculate Subtotal
        BigDecimal subtotalFormatted = newInvoice.getUnitPrice().multiply(new BigDecimal(newInvoice.getQuantity()))
                                         .setScale(2,BigDecimal.ROUND_HALF_UP);

        newInvoice.setSubtotal(subtotalFormatted); // set subtotal

        // Calculate tax rate
        Optional<Tax> stateTax = taxRepository.findTaxRateByState(newInvoice.getState());


        if (stateTax.isPresent()) {
            BigDecimal salesTaxRate = stateTax.get().getRate(); // gets rate from optional above
            // formats the tax calculation
            BigDecimal taxFormatted = (salesTaxRate.multiply(newInvoice.getSubtotal())).setScale(2, BigDecimal.ROUND_HALF_UP);
            newInvoice.setTax(taxFormatted);// sets the tax rate
        }else{
            throw new IllegalArgumentException("State not found. Check your input.");
        }


        // Calculate processing fee
        Optional<Fee> processingFee = feeRepository.findByProductType(newInvoice.getItemType());

        if (processingFee.isPresent()) { // if processing Fee is present
            BigDecimal invoiceFee = processingFee.get().getFee();
            if (newInvoice.getQuantity() > 10) { // add additional fee if quantity > 10
                invoiceFee = invoiceFee.add(new BigDecimal("15.49"));
            }
            newInvoice.setFee(invoiceFee); // set invoice fee
        }


        // Set the Grand totals in both the view model and the actual invoice
        newInvoice.setTotal(newInvoice.getSubtotal().add(newInvoice.getTax().add(newInvoice.getFee()))
                .setScale(2,BigDecimal.ROUND_HALF_UP));


        BigDecimal maxTotal = new BigDecimal("999.99");

        if(newInvoice.getTotal().compareTo(maxTotal) > 0){
            throw new IllegalArgumentException("Your total cannot exceed 999.99");
        }


        // save the invoice
        invoiceRepository.save(newInvoice);


        return buildInvoiceViewModel(newInvoice);
        // Current approach: Receive an invoice viewModel, update it as we go along, then return it

        // Alternative approach: Receive an Invoice, update the fields, then return buildInvoiceViewModel(invoice)
    }


    @Transactional
    public void updateInvoice(Invoice invoice){
        invoiceRepository.save(invoice);
    }

    @Transactional
    public void deleteInvoiceById(@RequestBody Integer id){
        invoiceRepository.deleteById(id);
    }

    @Transactional
    public void deleteAllInvoices(){
        invoiceRepository.deleteAll();
    }

    public List<Invoice> findAllInvoices() {return invoiceRepository.findAll();}

    public Invoice findInvoiceById(@RequestBody Integer id) {
        Optional<Invoice> inv = invoiceRepository.findById(id);

        if(inv.isPresent()) {
            return inv.get();
        }
        return null;
    }

    public List<Invoice> findInvoicesByCustomerName(String name){
        return invoiceRepository.findInvoicesByName(name);
    }
}

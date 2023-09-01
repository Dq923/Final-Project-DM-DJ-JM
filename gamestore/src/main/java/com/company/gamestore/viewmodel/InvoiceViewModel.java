package com.company.gamestore.viewmodel;


import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

public class InvoiceViewModel {

    private Integer id;
    @NotEmpty(message = "You must provide first and last name.")
    private String name;
    @NotEmpty(message = "You must provide a street address.")
    private String street;
    @NotEmpty(message = "You must provide a city.")
    private String city;
    @NotEmpty(message = "You must provide a two-character code for your state I.e. California -> CA")
    @Length(min = 2, max = 2)
    private String state;
    @NotEmpty(message = "You must provide a zipcode.")
    @Length(min = 5, max = 10)
    private String zipcode;
    @NotEmpty(message = "You must provide an item type of Game, Tshirt, or Console.")
    private String itemType;
    @NotEmpty(message = "You must provide an item ID.")
    @Digits(integer = 16, fraction = 0)
    private Integer itemId;

    private BigDecimal unitPrice;

    @Positive(message = "You must provide a quantity greater than zero.")
    @NotEmpty(message = "You must provide a quantity.")
    private Integer quantity;
    // consider other jsr 303 annotations for numbers such as min/max
    private BigDecimal subtotal;

    private BigDecimal tax;

    private BigDecimal fee;

    private BigDecimal total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceViewModel that = (InvoiceViewModel) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(street, that.street) && Objects.equals(city, that.city) && Objects.equals(state, that.state) && Objects.equals(zipcode, that.zipcode) && Objects.equals(itemType, that.itemType) && Objects.equals(itemId, that.itemId) && Objects.equals(unitPrice, that.unitPrice) && Objects.equals(quantity, that.quantity) && Objects.equals(subtotal, that.subtotal) && Objects.equals(tax, that.tax) && Objects.equals(fee, that.fee) && Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, street, city, state, zipcode, itemType, itemId, unitPrice, quantity, subtotal, tax, fee, total);
    }

    @Override
    public String toString() {
        return "InvoiceViewModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", unitPrice=" + unitPrice +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", tax=" + tax +
                ", fee=" + fee +
                ", total=" + total +
                '}';
    }
}

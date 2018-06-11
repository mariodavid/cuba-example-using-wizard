package de.diedavids.cuba.ceuw.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@NamePattern("%s, %s|street,city")
@Table(name = "CEUW_ADDRESS")
@Entity(name = "ceuw$Address")
public class Address extends StandardEntity {
    private static final long serialVersionUID = 8017319450975018318L;

    @NotNull
    @Column(name = "STREET", nullable = false)
    protected String street;

    @NotNull
    @Column(name = "POSTAL_CODE", nullable = false)
    protected String postalCode;

    @NotNull
    @Column(name = "CITY", nullable = false)
    protected String city;

    @NotNull
    @Column(name = "COUNTRY", nullable = false)
    protected String country;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }


    public Country getCountry() {
        return country == null ? null : Country.fromId(country);
    }

    public void setCountry(Country country) {
        this.country = country == null ? null : country.getId();
    }


    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }


}
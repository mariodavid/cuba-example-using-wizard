package de.diedavids.cuba.ceuw.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;

@NamePattern("%s|name")
@Table(name = "CEUW_PRODUCT")
@Entity(name = "ceuw$Product")
public class Product extends StandardEntity {
    private static final long serialVersionUID = 8094119504316695331L;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "CODE", nullable = false)
    protected String code;

    @NotNull
    @Column(name = "TAX", nullable = false)
    protected Integer tax;

    public void setTax(Integer tax) {
        this.tax = tax;
    }

    public Integer getTax() {
        return tax;
    }


    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
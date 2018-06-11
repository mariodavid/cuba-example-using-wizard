package de.diedavids.cuba.ceuw.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;

@Table(name = "CEUW_PRODUCT_PRICE")
@Entity(name = "ceuw$ProductPrice")
public class ProductPrice extends StandardEntity {
    private static final long serialVersionUID = -8331732515427766633L;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @NotNull
    @Column(name = "VALUE_", nullable = false)
    protected BigDecimal value;

    @NotNull
    @Column(name = "UNIT", nullable = false)
    protected String unit;

    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setUnit(Unit unit) {
        this.unit = unit == null ? null : unit.getId();
    }

    public Unit getUnit() {
        return unit == null ? null : Unit.fromId(unit);
    }


}
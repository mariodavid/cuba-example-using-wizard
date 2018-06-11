package de.diedavids.cuba.ceuw.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Table(name = "CEUW_ORDER_LINE")
@Entity(name = "ceuw$OrderLine")
public class OrderLine extends StandardEntity {
    private static final long serialVersionUID = 1487372204006280744L;

    @NotNull
    @Column(name = "POSITION_", nullable = false)
    protected Integer position;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID")
    protected Product product;

    @NotNull
    @Column(name = "AMOUNT", nullable = false)
    protected Integer amount;

    @NotNull
    @Column(name = "AMOUNT_UNIT", nullable = false)
    protected String amountUnit;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "ORDER_ID")
    protected Order order;

    public void setOrder(Order order) {
        this.order = order;
    }

    public Order getOrder() {
        return order;
    }


    public void setProduct(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }


    public void setAmountUnit(Unit amountUnit) {
        this.amountUnit = amountUnit == null ? null : amountUnit.getId();
    }

    public Unit getAmountUnit() {
        return amountUnit == null ? null : Unit.fromId(amountUnit);
    }


    public void setPosition(Integer position) {
        this.position = position;
    }

    public Integer getPosition() {
        return position;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }


}
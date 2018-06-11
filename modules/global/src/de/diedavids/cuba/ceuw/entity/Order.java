package de.diedavids.cuba.ceuw.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;

@NamePattern("%s %s|customer,orderDate")
@Table(name = "CEUW_ORDER")
@Entity(name = "ceuw$Order")
public class Order extends StandardEntity {
    private static final long serialVersionUID = -3667681912345764264L;

    @Temporal(TemporalType.DATE)
    @NotNull
    @Column(name = "ORDER_DATE", nullable = false)
    protected Date orderDate;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CUSTOMER_ID")
    protected Customer customer;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DELIVERY_ADDRESS_ID")
    protected Address deliveryAddress;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INVOICE_ADDRESS_ID")
    protected Address invoiceAddress;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "order")
    protected List<OrderLine> orderLines;

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }


    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setDeliveryAddress(Address deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setInvoiceAddress(Address invoiceAddress) {
        this.invoiceAddress = invoiceAddress;
    }

    public Address getInvoiceAddress() {
        return invoiceAddress;
    }


}
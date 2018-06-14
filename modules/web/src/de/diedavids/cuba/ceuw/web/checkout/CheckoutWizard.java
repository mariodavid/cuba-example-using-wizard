package de.diedavids.cuba.ceuw.web.checkout;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.data.Datasource;
import de.diedavids.cuba.ceuw.entity.Address;
import de.diedavids.cuba.ceuw.entity.Customer;
import de.diedavids.cuba.ceuw.entity.Order;

import javax.inject.Inject;
import java.util.Map;

public class CheckoutWizard extends AbstractWindow {
    @Inject
    protected Datasource<Customer> customerDs;

    @Inject
    protected Datasource<Address> deliveryAddressDs;

    @Inject
    protected Datasource<Address> invoiceAddressDs;

    @Inject
    protected Datasource<Order> orderDs;

    @Inject
    protected Metadata metadata;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        Customer customer = metadata.create(Customer.class);
        customerDs.setItem(customer);


        Address deliveryAddress = metadata.create(Address.class);
        deliveryAddress.setCustomer(customer);
        deliveryAddressDs.setItem(deliveryAddress);

        Address invoiceAddress = metadata.create(Address.class);
        invoiceAddress.setCustomer(customer);
        invoiceAddressDs.setItem(invoiceAddress);

        Order order = metadata.create(Order.class);
        order.setCustomer(customer);

        orderDs.setItem(order);

    }
}
package de.diedavids.cuba.ceuw.web.dynamiccheckout;

import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.components.AbstractWindow;
import com.haulmont.cuba.gui.data.Datasource;
import com.haulmont.cuba.gui.data.DsContext;
import de.diedavids.cuba.ceuw.entity.Address;
import de.diedavids.cuba.ceuw.entity.Customer;
import de.diedavids.cuba.ceuw.entity.Order;
import de.diedavids.cuba.wizard.gui.components.Wizard;

import javax.inject.Inject;
import java.util.Map;

public class DynamicCheckoutWizard extends AbstractWindow {
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

    @Inject
    protected Wizard checkoutWizard;

    @Inject
    protected DsContext dsContext;

    @Inject
    protected TimeSource timeSource;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);

        Customer customer = initCustomerAddress();
        Address deliveryAddress = initDeliveryAddress(customer);
        Address invoiceAddress = initInvoiceAddress();
        Order order = initOrder(customer, deliveryAddress);

        checkoutWizard.addWizardFinishClickListener(event -> {

            if (isDeliveryAddressSameAsInvoiceAddress(invoiceAddress)) {
                removeInvoiceAddress();
            } else {
                setInvoiceAddressReferences(customer, invoiceAddress, order);
            }

            closeWizard();
        });

        checkoutWizard.addWizardCancelClickListener(this::closeWizardScreen);


    }

    private void closeWizardScreen() {
        close(COMMIT_ACTION_ID);
    }

    private Order initOrder(Customer customer, Address deliveryAddress) {
        Order order = createOrderForCustomerAndDeliveryAddress(customer, deliveryAddress);
        orderDs.setItem(order);
        return order;
    }

    private Customer initCustomerAddress() {
        Customer customer = metadata.create(Customer.class);
        customerDs.setItem(customer);
        return customer;
    }

    private Address initDeliveryAddress(Customer customer) {
        Address deliveryAddress = createAddressFor(customer);
        deliveryAddressDs.setItem(deliveryAddress);
        return deliveryAddress;
    }

    private Address initInvoiceAddress() {
        Address invoiceAddress = metadata.create(Address.class);
        invoiceAddressDs.setItem(invoiceAddress);
        return invoiceAddress;
    }

    private void closeWizard() {
        dsContext.commit();
        closeWizardScreen();
        showNotification("Order placed successfully", NotificationType.TRAY);
    }

    private void setInvoiceAddressReferences(Customer customer, Address invoiceAddress, Order order) {
        invoiceAddress.setCustomer(customer);
        order.setInvoiceAddress(invoiceAddress);
    }

    private void removeInvoiceAddress() {
        invoiceAddressDs.setItem(null);
    }

    private boolean isDeliveryAddressSameAsInvoiceAddress(Address invoiceAddress) {
        return invoiceAddress.getStreet() == null && invoiceAddress.getCity() == null && invoiceAddress.getCountry() == null && invoiceAddress.getPostalCode() == null;
    }

    private Order createOrderForCustomerAndDeliveryAddress(Customer customer, Address deliveryAddress) {
        Order order = metadata.create(Order.class);
        order.setOrderDate(timeSource.currentTimestamp());
        order.setDeliveryAddress(deliveryAddress);
        order.setInvoiceAddress(deliveryAddress);
        order.setCustomer(customer);
        return order;
    }

    private Address createAddressFor(Customer customer) {
        Address deliveryAddress = metadata.create(Address.class);
        deliveryAddress.setCustomer(customer);
        return deliveryAddress;
    }

    private void closeWizardScreen(Wizard.WizardCancelClickEvent event) {
        closeWizardScreen();
    }
}
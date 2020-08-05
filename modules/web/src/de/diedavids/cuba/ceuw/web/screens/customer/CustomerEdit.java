package de.diedavids.cuba.ceuw.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Customer;

@UiController("ceuw$Customer.edit")
@UiDescriptor("customer-edit.xml")
@EditedEntityContainer("customerDc")
@LoadDataBeforeShow
public class CustomerEdit extends StandardEditor<Customer> {
}
package de.diedavids.cuba.ceuw.web.screens.customer;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Customer;

@UiController("ceuw$Customer.browse")
@UiDescriptor("customer-browse.xml")
@LookupComponent("customersTable")
@LoadDataBeforeShow
public class CustomerBrowse extends StandardLookup<Customer> {
}
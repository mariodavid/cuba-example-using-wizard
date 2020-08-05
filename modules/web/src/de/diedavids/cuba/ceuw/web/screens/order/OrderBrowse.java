package de.diedavids.cuba.ceuw.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Order;

@UiController("ceuw$Order.browse")
@UiDescriptor("order-browse.xml")
@LookupComponent("ordersTable")
@LoadDataBeforeShow
public class OrderBrowse extends StandardLookup<Order> {
}
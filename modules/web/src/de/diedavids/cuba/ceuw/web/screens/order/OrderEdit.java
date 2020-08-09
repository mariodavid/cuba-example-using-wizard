package de.diedavids.cuba.ceuw.web.screens.order;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Order;

@UiController("ceuw$Order.edit")
@UiDescriptor("order-edit.xml")
@EditedEntityContainer("orderDc")
@LoadDataBeforeShow
public class OrderEdit extends StandardEditor<Order> {
}
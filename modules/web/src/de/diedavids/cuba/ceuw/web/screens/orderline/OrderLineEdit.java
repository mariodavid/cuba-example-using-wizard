package de.diedavids.cuba.ceuw.web.screens.orderline;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.OrderLine;

@UiController("ceuw$OrderLine.edit")
@UiDescriptor("order-line-edit.xml")
@EditedEntityContainer("orderLineDc")
@LoadDataBeforeShow
public class OrderLineEdit extends StandardEditor<OrderLine> {
}
package de.diedavids.cuba.ceuw.web.screens.address;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Address;

@UiController("ceuw$Address.edit")
@UiDescriptor("address-edit.xml")
@EditedEntityContainer("addressDc")
@LoadDataBeforeShow
public class AddressEdit extends StandardEditor<Address> {
}
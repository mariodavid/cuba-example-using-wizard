package de.diedavids.cuba.ceuw.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Product;

@UiController("ceuw$Product.edit")
@UiDescriptor("product-edit.xml")
@EditedEntityContainer("productDc")
@LoadDataBeforeShow
public class ProductEdit extends StandardEditor<Product> {
}
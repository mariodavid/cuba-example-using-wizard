package de.diedavids.cuba.ceuw.web.screens.product;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.Product;

@UiController("ceuw$Product.browse")
@UiDescriptor("product-browse.xml")
@LookupComponent("productsTable")
@LoadDataBeforeShow
public class ProductBrowse extends StandardLookup<Product> {
}
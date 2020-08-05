package de.diedavids.cuba.ceuw.web.screens.productprice;

import com.haulmont.cuba.gui.screen.*;
import de.diedavids.cuba.ceuw.entity.ProductPrice;

@UiController("ceuw$ProductPrice.edit")
@UiDescriptor("product-price-edit.xml")
@EditedEntityContainer("productPriceDc")
@LoadDataBeforeShow
public class ProductPriceEdit extends StandardEditor<ProductPrice> {
}
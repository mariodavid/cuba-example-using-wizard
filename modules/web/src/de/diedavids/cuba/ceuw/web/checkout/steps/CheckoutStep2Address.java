package de.diedavids.cuba.ceuw.web.checkout.steps;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.FieldGroup;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

import javax.inject.Inject;
import java.util.Map;

@UiController("checkout-step-2-address")
@UiDescriptor("checkout-step-2-address.xml")
public class CheckoutStep2Address extends AbstractWizardStep {


    @Inject
    CheckBox sameAsDeliveryAddress;

    @Inject
    FieldGroup invoiceAddressFieldGroup;

    @Inject
    FieldGroup deliveryAddressFieldGroup;


    @Subscribe
    protected void onInit(InitEvent event) {
        sameAsDeliveryAddress.addValueChangeListener(e -> {
            Boolean value = (Boolean) e.getValue();
            invoiceAddressFieldGroup.setVisible(!value);
        });

        sameAsDeliveryAddress.setValue(true);
    }




    @Override
    public boolean preClose() {
        return validateAll();
    }

}
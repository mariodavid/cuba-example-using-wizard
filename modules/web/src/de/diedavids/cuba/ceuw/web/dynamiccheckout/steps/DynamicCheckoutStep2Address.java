package de.diedavids.cuba.ceuw.web.dynamiccheckout.steps;

import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.FieldGroup;
import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

import javax.inject.Inject;
import java.util.Map;

public class DynamicCheckoutStep2Address extends AbstractWizardStep {


    @Inject
    CheckBox sameAsDeliveryAddress;

    @Inject
    FieldGroup invoiceAddressFieldGroup;

    @Inject
    FieldGroup deliveryAddressFieldGroup;

    @Override
    public void init(Map<String, Object> params) {
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
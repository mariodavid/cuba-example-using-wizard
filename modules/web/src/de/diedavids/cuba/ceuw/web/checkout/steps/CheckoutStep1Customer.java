package de.diedavids.cuba.ceuw.web.checkout.steps;

import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;


@UiController("checkout-step-1-customer")
@UiDescriptor("checkout-step-1-customer.xml")
public class CheckoutStep1Customer extends AbstractWizardStep {

    @Override
    public boolean preClose() {
       return validateAll();

    }



}
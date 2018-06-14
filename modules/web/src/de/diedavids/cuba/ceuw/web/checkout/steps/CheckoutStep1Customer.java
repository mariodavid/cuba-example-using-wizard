package de.diedavids.cuba.ceuw.web.checkout.steps;

import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

public class CheckoutStep1Customer extends AbstractWizardStep {

    @Override
    public boolean preClose() {
       return validateAll();
    }
}
package de.diedavids.cuba.ceuw.web.checkout.steps;

import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

public class CheckoutStep4Summary extends AbstractWizardStep {
    public void buttonClick() {
        showNotification("Hello 3");
    }

    @Override
    public void onActivate() {
        showNotification("on activate step 3");
    }
}
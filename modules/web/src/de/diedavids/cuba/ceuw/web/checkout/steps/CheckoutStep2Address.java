package de.diedavids.cuba.ceuw.web.checkout.steps;

import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

public class CheckoutStep2Address extends AbstractWizardStep {
    public void buttonClick() {
        showNotification("Hello 2");
    }

    @Override
    public void onActivate() {
        showNotification("on activate step 2");
    }
}
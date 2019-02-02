package de.diedavids.cuba.ceuw.web.dynamiccheckout.steps;

import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

public class DynamicCheckoutStep1Customer extends AbstractWizardStep {

    @Override
    public boolean preClose() {
       return validateAll();
    }
}
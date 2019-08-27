package de.diedavids.cuba.ceuw.web.example1.steps;

import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

public class Example1Step2 extends AbstractWizardStep {

    public void buttonClick() {
        showNotification("Hello 2");
    }

    @Override
    public void onActivate() {
        showNotification("on activate step 2");
    }



}
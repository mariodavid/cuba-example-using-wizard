package de.diedavids.cuba.ceuw.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import com.haulmont.cuba.gui.ComponentsHelper;
import com.haulmont.cuba.gui.Screens.LaunchMode;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.Component;
import com.haulmont.cuba.gui.screen.OpenMode;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.web.app.main.MainScreen;
import com.haulmont.cuba.web.testsupport.TestUiEnvironment;
import de.diedavids.cuba.ceuw.CeuwWebTestContainer.Common;
import de.diedavids.cuba.ceuw.web.example1.WizardExample1;
import de.diedavids.cuba.wizard.gui.components.Wizard;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

// See https://doc.cuba-platform.com/manual-7.2/integration_tests_client.html

class WizardTest {

    @RegisterExtension
    TestUiEnvironment environment = new TestUiEnvironment(Common.INSTANCE).withUserLogin("admin");

    @Test
    void when_screenIsLoaded_then_wizardComponentIsAvailable() {


        environment.getScreens().create(MainScreen.class, OpenMode.ROOT);

        final WizardExample1 wizardExample = (WizardExample1) environment.getScreens().create("wizard-example-1", OpenMode.NEW_TAB);

        wizardExample.show();

        final Wizard wizard = (Wizard) wizardExample.getComponent("wizard");

        assertThat(wizard)
            .isNotNull();


        final Button finishBtn = (Button) wizard.getComponent("finish");

        assertThat(finishBtn)
            .isNotNull();
    }
}
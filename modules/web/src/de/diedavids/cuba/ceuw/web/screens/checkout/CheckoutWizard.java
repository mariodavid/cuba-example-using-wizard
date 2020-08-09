package de.diedavids.cuba.ceuw.web.screens.checkout;

import com.haulmont.cuba.core.global.TimeSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.Notifications.NotificationType;
import com.haulmont.cuba.gui.components.CheckBox;
import com.haulmont.cuba.gui.components.ComponentContainer;
import com.haulmont.cuba.gui.components.Form;
import com.haulmont.cuba.gui.components.GroupBoxLayout;
import com.haulmont.cuba.gui.components.HasValue.ValueChangeEvent;
import com.haulmont.cuba.gui.components.ValidationErrors;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.MessageBundle;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.ScreenValidation;
import com.haulmont.cuba.gui.screen.StandardOutcome;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;
import de.diedavids.cuba.ceuw.entity.Address;
import de.diedavids.cuba.ceuw.entity.Customer;
import de.diedavids.cuba.ceuw.entity.Order;
import de.diedavids.cuba.ceuw.entity.OrderLine;
import de.diedavids.cuba.wizard.gui.components.Wizard;
import de.diedavids.cuba.wizard.gui.components.Wizard.Direction;
import de.diedavids.cuba.wizard.gui.components.Wizard.WizardCancelClickEvent;
import de.diedavids.cuba.wizard.gui.components.Wizard.WizardFinishClickEvent;
import de.diedavids.cuba.wizard.gui.components.Wizard.WizardTabPreChangeEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import org.springframework.util.CollectionUtils;

@UiController("ceuw_CheckoutWizard")
@UiDescriptor("checkout-wizard.xml")
public class CheckoutWizard extends Screen {

    @Inject
    protected Notifications notifications;
    @Inject
    protected MessageBundle messageBundle;
    @Inject
    protected InstanceContainer<Address> invoiceAddressDc;
    @Inject
    protected InstanceContainer<Customer> customerDc;
    @Inject
    protected InstanceContainer<Order> orderDc;
    @Inject
    protected DataContext dataContext;
    @Inject
    protected InstanceContainer<Address> deliveryAddressDc;
    @Inject
    protected TimeSource timeSource;
    @Inject
    protected ScreenValidation screenValidation;
    @Inject
    protected CollectionPropertyContainer<OrderLine> orderLinesDc;
    @Inject
    protected Wizard wizard;
    @Inject
    protected Form customerForm;
    @Inject
    protected Form communicationForm;
    @Inject
    protected Form deliveryAddressForm;
    @Inject
    protected Form invoiceAddressForm;
    @Inject
    protected CheckBox sameAsDeliveryAddress;
    @Inject
    protected GroupBoxLayout invoiceAddressGroupBox;

    private CheckoutDataContainerCoordinator dcCoordinator;

    @Subscribe
    protected void onAfterShow(AfterShowEvent event) {

        dcCoordinator = new CheckoutDataContainerCoordinator(
            invoiceAddressDc,
            customerDc,
            orderDc,
            deliveryAddressDc, orderLinesDc, dataContext,
            timeSource
        );

        sameAsDeliveryAddress.setValue(true);

    }


    @Subscribe("wizard")
    protected void onCancelWizardClick(WizardCancelClickEvent event) {
        close(StandardOutcome.DISCARD);
    }

    @Subscribe("wizard")
    protected void onTabPreChange(WizardTabPreChangeEvent event) {
        if (nextTab(event, "step3", "step4")) {
            if (noOrderLinesPresent()) {
                event.preventTabChange();
                warn(messageBundle.formatMessage("validationAtLeastOneOrderLine"));
            }
        } else if (nextTab(event, "step1", "step2")) {
            final ValidationErrors validationErrors = validateComponents(
                customerForm, communicationForm
            );

            if (!validationErrors.isEmpty()) {
                event.preventTabChange();
                screenValidation.showValidationErrors(this, validationErrors);
            }
        } else if (nextTab(event, "step2", "step3")) {
            final ValidationErrors validationErrors = validateComponents(
                deliveryAddressForm, invoiceAddressForm
            );

            if (!validationErrors.isEmpty()) {
                event.preventTabChange();
                screenValidation.showValidationErrors(this, validationErrors);
            }
        }

    }

    @Subscribe("sameAsDeliveryAddress")
    protected void onSameAsDeliveryAddressValueChange(ValueChangeEvent<Boolean> event) {
        invoiceAddressForm.setVisible(!event.getValue());
    }



    private ValidationErrors validateComponents(ComponentContainer... containers) {

        final List<ComponentContainer> componentContainers = new ArrayList<>(Arrays.asList(containers));

        if (CollectionUtils.isEmpty(componentContainers)) {
            return ValidationErrors.none();
        }

        final ComponentContainer firstContainer = componentContainers.get(0);
        final ValidationErrors validationErrors = screenValidation
            .validateUiComponents(firstContainer);

        if (componentContainers.size() > 1) {
            componentContainers.remove(0);

            componentContainers.stream()
                .map(
                    componentContainer -> screenValidation.validateUiComponents(componentContainer))
                .forEach(validationErrors::addAll);
        }

        return validationErrors;
    }

    private boolean tabChange(WizardTabPreChangeEvent event, String from, String to, Direction direction) {
        return event.getOldTab().equals(wizard.getTab(from)) &&
            event.getNewTab().equals(wizard.getTab(to)) &&
            direction.equals(event.getDirection());
    }
    private boolean nextTab(WizardTabPreChangeEvent event, String from, String to) {
        return tabChange(event, from, to, Direction.NEXT);
    }

    private boolean previousTab(WizardTabPreChangeEvent event, String from, String to) {
        return tabChange(event, from, to, Direction.NEXT);
    }


    private boolean noOrderLinesPresent() {
        return orderLinesDc.getItems().size() == 0;
    }

    private void warn(String message) {
        notifications.create(NotificationType.WARNING)
            .withCaption(message)
            .show();
    }

    @Subscribe("wizard")
    protected void onFinishWizardClick(WizardFinishClickEvent event) {
        dcCoordinator.saveCheckout();

        close(StandardOutcome.COMMIT);
        notifications.create(NotificationType.TRAY)
            .withCaption(messageBundle.getMessage("orderPlaced"))
            .show();
    }

}
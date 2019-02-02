package de.diedavids.cuba.ceuw.web.dynamiccheckout.steps;

import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.components.actions.CreateAction;
import de.diedavids.cuba.ceuw.entity.OrderLine;
import de.diedavids.cuba.wizard.gui.components.AbstractWizardStep;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public class DynamicCheckoutStep3Items extends AbstractWizardStep {

    @Inject
    protected Table<OrderLine> orderLinesTable;

    @Named("orderLinesTable.create")
    CreateAction createAction;

    @Override
    public void init(Map<String, Object> params) {
        super.init(params);
        createAction.setInitialValuesSupplier(() -> ParamsMap.of("position", getNextPosition()));

    }

    @Override
    public boolean preClose() {
        return validateAll() && orderHasOneOrderLine();
    }

    private boolean orderHasOneOrderLine() {
        if (getOrderLines().size() == 0) {
            showNotification(formatMessage("validationAtLeastOneOrderLine"), NotificationType.TRAY);
            return false;
        }
        return true;
    }

    private int getNextPosition() {
        Collection<OrderLine> orderLines = getOrderLines();

        OrderLine found = null;
        for (OrderLine o : orderLines) {
            if (found == null) {
                found = o;
            } else if (o.getPosition() > found.getPosition()) {
                found = o;
            }

        }
        if (found == null) {
            return 1;
        } else {
            return found.getPosition() + 1;
        }

    }

    private Collection<OrderLine> getOrderLines() {
        return orderLinesTable.getDatasource().getItems();
    }

    public void positionUp() {
        OrderLine orderLineToPushUp = orderLinesTable.getSingleSelected();
        int destinationPosition = orderLineToPushUp.getPosition() - 1;

        changePosition(orderLineToPushUp, destinationPosition);
    }

    private void changePosition(OrderLine orderLineToChange, int destinationPosition) {
        if (getOrderLines().size() > 1) {
            Optional<OrderLine> orderLineWithPosition = findOrderLineWithPosition(destinationPosition);

            if (orderLineWithPosition.isPresent()) {
                orderLineWithPosition.get().setPosition(orderLineToChange.getPosition());
                orderLineToChange.setPosition(destinationPosition);
            }

        }

        orderLinesTable.sort("position", Table.SortDirection.ASCENDING);
    }

    private Optional<OrderLine> findOrderLineWithPosition(int position) {
        Collection<OrderLine> orderLines = getOrderLines();
        Optional<OrderLine> orderLineWithPosition = orderLines.stream().filter(orderLine -> orderLine.getPosition() == position).findFirst();
        return orderLineWithPosition;
    }

    public void positionDown() {
        OrderLine orderLineToPushUp = orderLinesTable.getSingleSelected();
        int destinationPosition = orderLineToPushUp.getPosition() + 1;

        changePosition(orderLineToPushUp, destinationPosition);
    }
}
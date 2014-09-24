package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledIntegerField;
import ph.txtdis.model.CreditDetail;
import ph.txtdis.model.Customer;

public class CreditDialog extends AbstractFieldDialog<CreditDetail, CustomerDTO> {

    public CreditDialog(Stage stage, CustomerDTO dto) {
        super("Credit", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledIntegerField termField = new LabeledIntegerField("Term");
        LabeledIntegerField gracePeriodField = new LabeledIntegerField("Grace Period");
        LabeledDecimalField limitField = new LabeledDecimalField("Limit");
        LabeledDatePicker startPicker = new LabeledDatePicker("Start");
        return Arrays.asList(termField, gracePeriodField, limitField, startPicker);
    }

    @Override
    protected CreditDetail createEntity(CustomerDTO dto, List<InputNode<?>> inputNodes) {
        Customer customer = dto.get();
        int term = getInputAtRow(0);
        int gracePeriod = getInputAtRow(1);
        BigDecimal limit = getInputAtRow(2);
        LocalDate start = getInputAtRow(3);

        return new CreditDetail(customer, term, gracePeriod, limit, start);
    }

    @Override
    protected void addItems(CustomerDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}

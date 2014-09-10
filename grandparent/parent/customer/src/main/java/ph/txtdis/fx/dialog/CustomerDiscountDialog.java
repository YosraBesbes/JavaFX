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
import ph.txtdis.model.Customer;
import ph.txtdis.model.CustomerDiscount;
import ph.txtdis.util.Login;

public class CustomerDiscountDialog extends AbstractFieldDialog<CustomerDiscount, CustomerDTO> {

    public CustomerDiscountDialog(Stage stage, CustomerDTO dto) {
        super("Credit", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        
        LabeledIntegerField levelField = new LabeledIntegerField("Level");
        LabeledDecimalField discountField = new LabeledDecimalField("%Discount");
        LabeledDatePicker startPicker = new LabeledDatePicker("Start");
        return Arrays.asList(levelField, discountField, startPicker);
    }

    @Override
    protected CustomerDiscount createEntity(CustomerDTO dto, List<InputNode<?>> inputNodes) {
        Customer customer = dto.get();
        int level = getInputAtRow(0);
        BigDecimal discount = getInputAtRow(1);
        LocalDate start = getInputAtRow(2);
        
        CustomerDiscount credit = new CustomerDiscount(customer, level, discount, start);
        credit.setCreatedBy(Login.user());
        return credit;
    }

    @Override
    protected void addItems(CustomerDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}

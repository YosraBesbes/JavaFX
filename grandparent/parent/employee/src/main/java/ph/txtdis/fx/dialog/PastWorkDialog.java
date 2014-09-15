package ph.txtdis.fx.dialog;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.AbstractFieldDialog;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledDecimalField;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Employee;
import ph.txtdis.model.PastWork;

public class PastWorkDialog extends AbstractFieldDialog<PastWork, EmployeeDTO> {

    public PastWorkDialog(Stage stage, EmployeeDTO dto) {
        super("Past Work", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledDatePicker startDatePicker = new LabeledDatePicker("Start");
        LabeledDatePicker endDatePicker = new LabeledDatePicker("End");
        LabeledStringField employerField = new LabeledStringField("Employer");
        LabeledStringField designationField = new LabeledStringField("Designation");
        LabeledDecimalField lastPayField = new LabeledDecimalField("Last Pay Rate");
        LabeledStringField reasonForLeavingField = new LabeledStringField("Reason for Leaving");
        LabeledStringField referenceNameField = new LabeledStringField("Reference Name");
        LabeledStringField referenceDesignationField = new LabeledStringField("Reference Designation");
        LabeledStringField referencePhoneField = new LabeledStringField("Reference Phone");
        return Arrays.asList(startDatePicker, endDatePicker, employerField, designationField, lastPayField,
                reasonForLeavingField, referenceNameField, referenceDesignationField, referencePhoneField);
    }

    @Override
    protected PastWork createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        Date startDate = getInputAtRow(0);
        Date endDate = getInputAtRow(1);
        String employer = getInputAtRow(2);
        String designation = getInputAtRow(3);
        BigDecimal lastPay = getInputAtRow(4);
        String reasonForLeaving = getInputAtRow(5);
        String referenceName = getInputAtRow(6);
        String referenceDesignation = getInputAtRow(7);
        String referencePhone = getInputAtRow(8);
        return new PastWork(employee, startDate, endDate, employer, designation, lastPay, reasonForLeaving,
                referenceName, referenceDesignation, referencePhone);
    }
}

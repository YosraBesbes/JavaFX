package ph.txtdis.fx.dialog;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.AbstractFieldDialog;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledDatePicker;
import ph.txtdis.fx.input.LabeledStringField;
import ph.txtdis.model.Discipline;
import ph.txtdis.model.Employee;

public class DisciplineDialog extends AbstractFieldDialog<Discipline, EmployeeDTO> {

    public DisciplineDialog(Stage stage, EmployeeDTO dto) {
        super("Disciplinary Action", stage, dto);
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        LabeledDatePicker incidenceDatePicker = new LabeledDatePicker("Incidence");
        LabeledStringField allegationField = new LabeledStringField("Allegation");
        return Arrays.asList(incidenceDatePicker, allegationField);
    }

    @Override
    protected Discipline createEntity(EmployeeDTO dto, List<InputNode<?>> inputNodes) {
        Employee employee = dto.get();
        Date incidenceDate = getInputAtRow(0);
        String allegation = getInputAtRow(1);
        return new Discipline(employee, incidenceDate, allegation);
    }
}

package ph.txtdis.fx.tab;

import java.math.BigDecimal;
import java.time.LocalDate;

import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import ph.txtdis.dto.EmployeeDTO;
import ph.txtdis.fx.dialog.PastWorkDialog;
import ph.txtdis.fx.table.AbstractInputTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.model.PastWork;

public class PastWorkTab extends AbstractTab<PastWork, EmployeeDTO> {
    private TableView<PastWork> table;

    public PastWorkTab(Stage stage, EmployeeDTO employee) {
        super("Past Work", stage, employee);
    }

    @Override
    protected Node[] addNodes(Stage stage, EmployeeDTO dto) {

        table = new AbstractInputTable<PastWork, EmployeeDTO>(stage, dto) {

            @Override
            @SuppressWarnings("unchecked")
            protected void addTableColumns() {
                TableColumn<PastWork, LocalDate> startCol = FX.addDateColumn("Start", "startDate");
                TableColumn<PastWork, LocalDate> endCol = FX.addDateColumn("End", "endDate");
                TableColumn<PastWork, String> employerCol = FX.addStringColumn("Employer", "employer", 120);
                TableColumn<PastWork, String> designationCol = FX.addStringColumn("Designation", "designation", 160);
                TableColumn<PastWork, BigDecimal> lastPayCol = FX.addPriceColumn("Last Pay", "lastPay");
                TableColumn<PastWork, String> reasonForLeavingCol = FX.addStringColumn("Reason for\nLeaving",
                        "reasonForLeaving", 120);
                TableColumn<PastWork, String> referenceNameCol = FX.addStringColumn("Name", "referenceName", 240);
                TableColumn<PastWork, String> referenceDesignationCol = FX.addStringColumn("Designation",
                        "referenceDesignation", 160);
                TableColumn<PastWork, String> referencePhoneCol = FX.addStringColumn("Phone", "referencePhone", 120);
                TableColumn<PastWork, String> referenceCol = new TableColumn<>("Reference");
                referenceCol.getColumns().addAll(referenceNameCol, referenceDesignationCol, referencePhoneCol);
                table.getColumns().addAll(startCol, endCol, employerCol, designationCol, lastPayCol,
                        reasonForLeavingCol, referenceCol);
            }

            @Override
            protected void createInputDialog() {
                inputDialog = new PastWorkDialog(stage, dto);
            }

        }.getTable();

        return new Node[] { table };
    }

    @Override
    public void save() {
        dto.setPastJobs(table.getItems());
    }

    @Override
    public void refresh() {
        table.setItems(dto.getPastJobs());
    }
}

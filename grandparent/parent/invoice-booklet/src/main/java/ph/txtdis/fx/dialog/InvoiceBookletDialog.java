package ph.txtdis.fx.dialog;

import java.util.Arrays;
import java.util.List;

import javafx.stage.Stage;
import ph.txtdis.dto.InvoiceBookletDTO;
import ph.txtdis.fx.input.InputNode;
import ph.txtdis.fx.input.LabeledComboBox;
import ph.txtdis.fx.input.LabeledIdField;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;

public class InvoiceBookletDialog extends AbstractFieldDialog<InvoiceBooklet, InvoiceBookletDTO> {

    private LabeledComboBox<SystemUser> userCombo;
    private LabeledIdField startIdField, endIdField;
    private InvoiceBookletDTO dto;
    private int startId, endId;

    public InvoiceBookletDialog(Stage stage, InvoiceBookletDTO dto) {
        super("Invoice Booklet\nIssuance", stage, dto);
        setListeners();
    }

    @Override
    protected List<InputNode<?>> addNodes() {
        this.dto = object;
        createInputNodes();
        return Arrays.asList(startIdField, endIdField, userCombo);
    }

    private void createInputNodes() {
        startIdField = new LabeledIdField("Starting No.");
        endIdField = new LabeledIdField("Ending No.");
        userCombo = new LabeledComboBox<>("Issued To", dto.listUsers());
    }

    private void setListeners() {
        addStartIdFieldListener();
        addEndIdFieldListener();
    }

    private void addStartIdFieldListener() {
        startIdField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                validateStartingNo();
            else
                resetOtherNodes();
        });
    }

    private void addEndIdFieldListener() {
        endIdField.getTextField().focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue)
                validateBookletSize();
            else
                userCombo.reset();
        });
    }

    private void resetOtherNodes() {
        endIdField.reset();
        userCombo.reset();
    }

    private void validateStartingNo() {
        startId = startIdField.getValue();
        InvoiceBooklet booklet = dto.getBooklet(startId);
        if (booklet != null)
            throwInvalidBookletNoException(booklet, startId);
    }

    private void validateBookletSize() {
        endId = endIdField.getValue();
        if (endId - startId + 1 != 50)
            throwInvalidBookletSizeException();
        else
            validateEndingNo();
    }

    private void throwInvalidBookletSizeException() {
        try {
            throw new Exception("Booklets have 50 pages");
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void validateEndingNo() {
        InvoiceBooklet booklet = dto.getBooklet(endId);
        if (booklet != null)
            throwInvalidBookletNoException(booklet, endId);
    }

    private void throwInvalidBookletNoException(InvoiceBooklet booklet, int id) {
        try {
            throw new Exception("S/I No. " + id + " is part of Booklet Nos. " + booklet.getStartId() + "-"
                    + booklet.getEndId() + "\nissued to " + booklet.getIssuedTo() + " on " + booklet.getTimeStamp());
        } catch (Exception e) {
            handleError(e);
        }
    }

    private void handleError(Exception e) {
        new ErrorDialog(this, e.getMessage());
        resetNodes(inputNodes);
        startIdField.getTextField().requestFocus();
    }

    @Override
    protected InvoiceBooklet createEntity(InvoiceBookletDTO dto, List<InputNode<?>> inputNodes) {
        return new InvoiceBooklet(startIdField.getValue(), endIdField.getValue(), userCombo.getValue());
    }

    @Override
    protected void addItems(InvoiceBookletDTO dto, List<InputNode<?>> inputNodes) {
        super.addItems(dto, inputNodes);
        close();
    }
}

package ph.txtdis.app;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;

import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import javax.mail.MessagingException;

import ph.txtdis.App;
import ph.txtdis.dto.StockTakeAdjustmentDTO;
import ph.txtdis.dto.StockTakeDTO;
import ph.txtdis.dto.StockTakeReconciliationDTO;
import ph.txtdis.dto.UserDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.excel.ExcelWriter;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.button.BackButton;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.button.MailButton;
import ph.txtdis.fx.button.NextButton;
import ph.txtdis.fx.button.SaveButton;
import ph.txtdis.fx.button.SearchByDateButton;
import ph.txtdis.fx.dialog.ErrorDialog;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.dialog.LatestStockTakeClosureOptionDialog;
import ph.txtdis.fx.display.StringDisplay;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;
import ph.txtdis.fx.table.StockTakeReconciliationDetailTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.mail.ApprovedByMail;
import ph.txtdis.mail.CannotCheckMailException;
import ph.txtdis.mail.Mail;
import ph.txtdis.mail.MailChecker;
import ph.txtdis.mail.MailNotSentException;
import ph.txtdis.mail.MailSender;
import ph.txtdis.model.StockTakeAdjustment;
import ph.txtdis.model.StockTakeReconciliation;
import ph.txtdis.model.StockTakeReconciliationFilteredDetail;
import ph.txtdis.model.SystemUser;
import ph.txtdis.type.UserType;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class StockTakeReconciliationAppImpl extends AbstractApp<StockTakeReconciliation, LocalDate> implements
        Referenced<StockTakeReconciliation>, ApprovedByMail, Excel {

    private CheckBox approvalCheckBox;
    private LocalDate stockTakeDate;
    private SimpleObjectProperty<UserType> userType;
    private StockTakeAdjustmentDTO adjustment;
    private StockTakeReconciliationDTO reconciliation;
    private String[] addresses;
    private StringDisplay dateDisplay;
    private TimestampDisplay cutoffOnDisplay, closedOnDisplay, reconciledOnDisplay, mailedOnDisplay, approvedOnDisplay,
            completedOnDisplay;
    private UserDisplay cutoffByDisplay, closedByDisplay, reconciledByDisplay, mailedByDisplay, approvedByDisplay,
            completedByDisplay;
    private SystemUser txtDIS;
    private TableView<StockTakeReconciliationFilteredDetail> detailTable;
    private UserDTO user;

    public StockTakeReconciliationAppImpl() {
        super("Stock Take Reconciliation", "Date");
    }

    @Override
    public void start() {
        stockTakeDate = App.getContext().getBean(StockTakeDTO.class).getLatestDate();
        reconciliation = App.getContext().getBean(StockTakeReconciliationDTO.class);
        continueIfLatestStockTakeIsClosed();
    }

    private void continueIfLatestStockTakeIsClosed() {
        if (stockTakeDate == null)
            new ErrorDialog(this, "No stock take exists");
        else if (isLatestClosed() || isLatestForClosing())
            super.start();
    }

    private boolean isLatestClosed() {
        return reconciliation.getMaxId() != null && !stockTakeDate.isEqual(reconciliation.getMaxId());
    }

    private boolean isLatestForClosing() {
        return new LatestStockTakeClosureOptionDialog(this, stockTakeDate).isAffirmative();
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("back", new BackButton<StockTakeReconciliation, LocalDate>(this, dto).getButton());
        buttons.put("open", new SearchByDateButton<StockTakeReconciliation>(this, dto).getButton());
        buttons.put("next", new NextButton<StockTakeReconciliation, LocalDate>(this, dto).getButton());
        buttons.put("save", new SaveButton<StockTakeReconciliation, LocalDate>(this, dto).getButton());
        buttons.put("excel", new ExcelButton<StockTakeReconciliation>(this).getButton());
        buttons.put("mail", new MailButton<StockTakeReconciliation, LocalDate>(this, dto).getButton());
    }

    @Override
    public void setFocus() {
        detailTable.requestFocus();
    }

    @Override
    protected void setDTO() {
        adjustment = App.getContext().getBean(StockTakeAdjustmentDTO.class);
        user = App.getContext().getBean(UserDTO.class);
        reconciliation.setById(stockTakeDate);
        dto = reconciliation;
    }

    @Override
    protected Node[] addVBoxNodes() {

        Label dateLabel = new Label("Date");
        dateDisplay = new StringDisplay(getPrimaryKey(), 90);

        userType = new SimpleObjectProperty<>(Login.user().getType());
        approvalCheckBox = new CheckBox("Approved");
        approvalCheckBox.setStyle("-fx-opacity: 1");
        approvalCheckBox.setSelected(isApproved());

        Label cutoffByLabel = new Label("Cutoff set by");
        cutoffByDisplay = new UserDisplay(reconciliation.getCutoffBy());
        Label cutoffOnLabel = new Label("on");
        cutoffOnDisplay = new TimestampDisplay(reconciliation.getCutoffOn());

        Label closedByLabel = new Label("Count closed by");
        closedByDisplay = new UserDisplay(reconciliation.getClosedBy());
        Label closedOnLabel = new Label("on");
        closedOnDisplay = new TimestampDisplay(reconciliation.getClosedOn());

        Label reconciledByLabel = new Label("Reconciled by");
        reconciledByDisplay = new UserDisplay(reconciliation.getReconciledBy());
        Label reconciledOnLabel = new Label("on");
        reconciledOnDisplay = new TimestampDisplay(reconciliation.getReconciledOn());

        Label mailedByLabel = new Label("Report mailed by");
        mailedByDisplay = new UserDisplay(reconciliation.getMailedBy());
        Label mailedOnLabel = new Label("on");
        mailedOnDisplay = new TimestampDisplay(reconciliation.getMailedOn());

        Label approvedByLabel = new Label("Dis/approved by");
        approvedByDisplay = new UserDisplay(reconciliation.getApprovedBy());
        Label approvedOnLabel = new Label("on");
        approvedOnDisplay = new TimestampDisplay(reconciliation.getApprovedOn());

        Label completedByLabel = new Label("Completed by");
        completedByDisplay = new UserDisplay(reconciliation.getCompletedBy());
        Label completedOnLabel = new Label("on");
        completedOnDisplay = new TimestampDisplay(reconciliation.getCompletedOn());

        detailTable = new StockTakeReconciliationDetailTable(this).getTable();
        detailTable.setItems(reconciliation.getStockTakeReconciliationFilteredDetail());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateDisplay, 1, 0);

        gridPane.add(approvalCheckBox, 2, 0, 2, 1);

        gridPane.add(cutoffByLabel, 0, 1);
        gridPane.add(cutoffByDisplay, 1, 1);
        gridPane.add(cutoffOnLabel, 2, 1);
        gridPane.add(cutoffOnDisplay, 3, 1);

        gridPane.add(closedByLabel, 4, 1);
        gridPane.add(closedByDisplay, 5, 1);
        gridPane.add(closedOnLabel, 6, 1);
        gridPane.add(closedOnDisplay, 7, 1);

        gridPane.add(reconciledByLabel, 0, 2);
        gridPane.add(reconciledByDisplay, 1, 2);
        gridPane.add(reconciledOnLabel, 2, 2);
        gridPane.add(reconciledOnDisplay, 3, 2);

        gridPane.add(mailedByLabel, 4, 2);
        gridPane.add(mailedByDisplay, 5, 2);
        gridPane.add(mailedOnLabel, 6, 2);
        gridPane.add(mailedOnDisplay, 7, 2);

        gridPane.add(approvedByLabel, 0, 3);
        gridPane.add(approvedByDisplay, 1, 3);
        gridPane.add(approvedOnLabel, 2, 3);
        gridPane.add(approvedOnDisplay, 3, 3);

        gridPane.add(completedByLabel, 4, 3);
        gridPane.add(completedByDisplay, 5, 3);
        gridPane.add(completedOnLabel, 6, 3);
        gridPane.add(completedOnDisplay, 7, 3);

        HBox box = new HBox(detailTable);
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox routingBox = new VBox(gridPane, box);
        return new Node[] { routingBox };
    }

    private Boolean isApproved() {
        return reconciliation.isApproved() == null ? false : reconciliation.isApproved();
    }

    @Override
    protected void setBindings() {
        setApprovalCheckBoxBindings();
        setButtonBindings();
        detailTable.editableProperty().bind(FX.isEmpty(reconciledByDisplay));
    }

    private void setApprovalCheckBoxBindings() {
        approvalCheckBox.visibleProperty().bind(
                FX.isEmpty(approvedByDisplay).not()
                        .or(FX.isEmpty(reconciledByDisplay).not().and(userType.isEqualTo(UserType.MANAGER))));
    }

    private void setButtonBindings() {
        setSaveButtonBindings();
        buttons.get("excel").disableProperty().bind(FX.isEmpty(reconciledByDisplay));
        setMailButtonBindings();
    }

    private void setSaveButtonBindings() {
        buttons.get("save")
                .disableProperty()
                .bind((FX.isEmpty(reconciledByDisplay).not().and(userType.isNotEqualTo(UserType.MANAGER))).or(FX
                        .isEmpty(approvedByDisplay).not()));
    }

    private void setMailButtonBindings() {
        buttons.get("mail")
                .disableProperty()
                .bind(FX.isEmpty(reconciledByDisplay).or(FX.isEmpty(approvedByDisplay).not())
                        .or(userType.isEqualTo(UserType.MANAGER)));
    }

    @Override
    protected String titleName() {
        return module + " " + reconciliation.getId();
    }

    @Override
    public void save() throws InvalidException {
        if (userType.get() == UserType.MANAGER && approvalCheckBox.isVisible()) {
            updateApprovedDecisionStamps();
        } else {
            ensureVariancesAreJustified();
            saveAdjustments();
            saveAsExcel();
            if (mailedByDisplay.getText().isEmpty())
                getApproval();
        }
    }

    private void ensureVariancesAreJustified() throws InvalidException {
        for (StockTakeReconciliationFilteredDetail item : detailTable.getItems())
            if (isVarianceUnjustified(item))
                throw new InvalidException("All variances must be justified");
    }

    private boolean isVarianceUnjustified(StockTakeReconciliationFilteredDetail item) {
        return !DIS.isZero(item.getCountQty().subtract(item.getSystemQty())) && DIS.isEmpty(item.getJustification());
    }

    private void saveAdjustments() throws InvalidException {
        for (StockTakeReconciliationFilteredDetail item : detailTable.getItems()) {
            adjustment.set(new StockTakeAdjustment(reconciliation.getId(), item.getItem(), item.getQualityType(), item
                    .getAdjustmentQty(), item.getJustification()));
            adjustment.save();
        }
        updateReconciliationStamp();
    }

    private void updateReconciliationStamp() throws InvalidException {
        reconciliation.setReconciledBy(Login.user());
        reconciliation.setReconciledOn(ZonedDateTime.now());
        reconciliation.save();
    }

    @Override
    public void saveAsExcel() {
        new ExcelWriter(Arrays.asList(Arrays.asList(detailTable)), module, getPrimaryKey());
    }

    private void getApproval() throws InvalidException {
        try {
            setMail();
        } catch (MailNotSentException e) {
            throw new InvalidException(e.getMessage());
        }
    }

    private void setMail() throws MailNotSentException {
        setMailAddresses();
        sendMail();
        updateMailStamp();
    }

    private void setMailAddresses() {
        txtDIS = user.getTxtDIS();
        addresses = user.getAddresses(UserType.MANAGER);
    }

    private void updateMailStamp() {
        reconciliation.setMailedBy(Login.user());
        reconciliation.setMailedOn(ZonedDateTime.now());
        reconciliation.save();
        refresh();
    }

    @Override
    public void sendMail() throws MailNotSentException {
        try {
            new MailSender(txtDIS, "For approval: ", module, getPrimaryKey(), addresses);
        } catch (Exception e) {
            throw new MailNotSentException();
        }
    }

    @Override
    public Mail checkMail() throws CannotCheckMailException {
        try {
            return new MailChecker(txtDIS, module, getPrimaryKey(), getMailedDate(), addresses).getMail();
        } catch (MessagingException e) {
            throw new CannotCheckMailException(module, getPrimaryKey());
        }
    }

    private LocalDate getMailedDate() {
        return reconciliation.getMailedOn().toLocalDate();
    }

    @Override
    public void handleCheckingResult(Mail mail) {
        if (mail.isApproved() == null)
            new InfoDialog(this, "Decision is still pending");
        else
            updateApprovedDecisionStamps(mail);
    }

    private void updateApprovedDecisionStamps() {
        updateApprovalStamps(approvalCheckBox.isSelected(), Login.user(), ZonedDateTime.now());
        saveDecisionStamps();
    }

    private void updateApprovedDecisionStamps(Mail mail) {
        updateApprovalStamps(mail.isApproved(), user.get(mail.getAddress()), mail.getTimestamp());
        saveDecisionStamps();
    }

    private void saveDecisionStamps() {
        updateCompletionStamps();
        reconciliation.save();
    }

    private void updateApprovalStamps(Boolean isApproved, SystemUser user, ZonedDateTime timestamp) {
        reconciliation.setApproved(isApproved);
        reconciliation.setApprovedBy(user);
        reconciliation.setApprovedOn(timestamp);
    }

    private void updateCompletionStamps() {
        reconciliation.setCompletedBy(Login.user());
        reconciliation.setCompleteOn(ZonedDateTime.now());
    }

    @Override
    public void refresh() {
        dateDisplay.setText(getPrimaryKey());
        approvalCheckBox.setSelected(isApproved());
        cutoffByDisplay.setUser(reconciliation.getCutoffBy());
        cutoffOnDisplay.setTimestamp(reconciliation.getCutoffOn());
        closedByDisplay.setUser(reconciliation.getClosedBy());
        closedOnDisplay.setTimestamp(reconciliation.getClosedOn());
        reconciledByDisplay.setUser(reconciliation.getReconciledBy());
        reconciledOnDisplay.setTimestamp(reconciliation.getReconciledOn());
        mailedByDisplay.setUser(reconciliation.getMailedBy());
        mailedOnDisplay.setTimestamp(reconciliation.getMailedOn());
        approvedByDisplay.setUser(reconciliation.getApprovedBy());
        approvedOnDisplay.setTimestamp(reconciliation.getApprovedOn());
        completedByDisplay.setUser(reconciliation.getCompletedBy());
        completedOnDisplay.setTimestamp(reconciliation.getCompletedOn());
        detailTable.getItems().clear();
        detailTable.getItems().addAll(reconciliation.getStockTakeReconciliationFilteredDetail());
        super.refresh();
    }

    @Override
    public void listFoundReferences(StockTakeReconciliation entity) {
        // TODO Auto-generated method stub

    }

    public LocalDate getStockTakeDate() {
        return stockTakeDate;
    }

    private String getPrimaryKey() {
        return Util.formatDate(reconciliation.getId());
    }
}

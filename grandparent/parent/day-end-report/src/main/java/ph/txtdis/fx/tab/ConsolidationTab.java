package ph.txtdis.fx.tab;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import javafx.beans.binding.BooleanBinding;
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
import javafx.stage.Stage;

import javax.mail.MessagingException;

import ph.txtdis.App;
import ph.txtdis.dto.SummaryDTO;
import ph.txtdis.dto.UserDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.fx.dialog.InfoDialog;
import ph.txtdis.fx.display.CurrencyDisplay;
import ph.txtdis.fx.display.DateDisplay;
import ph.txtdis.fx.display.DecimalDisplay;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;
import ph.txtdis.fx.table.RemittanceTable;
import ph.txtdis.fx.table.RevenueTable;
import ph.txtdis.fx.table.VolumeTable;
import ph.txtdis.fx.util.FX;
import ph.txtdis.mail.ApprovedByMail;
import ph.txtdis.mail.CannotCheckMailException;
import ph.txtdis.mail.Mail;
import ph.txtdis.mail.MailChecker;
import ph.txtdis.mail.MailNotSentException;
import ph.txtdis.mail.MailSender;
import ph.txtdis.model.Invoicing;
import ph.txtdis.model.Remittance;
import ph.txtdis.model.Users;
import ph.txtdis.model.VolumeSummary;
import ph.txtdis.type.UserType;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class ConsolidationTab extends AbstractTab<SummaryDTO> implements ApprovedByMail, MultiTabled {

    private CheckBox approvalCheckBox;
    private DateDisplay dateDisplay;
    private DecimalDisplay totalVolumeDisplay;
    private CurrencyDisplay totalRevenueDisplay, totalRemittanceDisplay;
    private String[] addresses;
    private SimpleObjectProperty<UserType> userType;
    private Users txtDIS;
    private TableView<Invoicing> revenueTable;
    private TableView<Remittance> remittanceTable;
    private TableView<VolumeSummary> volumeTable;
    private TimestampDisplay mailedOnDisplay, approvedOnDisplay, completedOnDisplay;
    private UserDisplay mailedByDisplay, approvedByDisplay, completedByDisplay;
    private UserDTO user;

    public ConsolidationTab(Stage stage, SummaryDTO dto) {
        super("Consolidated", "main", stage, dto);
        setBindings();
        setListeners(stage);
    }

    private void setBindings() {
        approvalCheckBox.visibleProperty().bind(
                FX.isEmpty(approvedByDisplay).not().or(userType.isEqualTo(UserType.MANAGER)));
    }

    private void setListeners(Stage stage) {
    }

    @Override
    protected Node[] addNodes(Stage stage, SummaryDTO dto) {

        Label dateLabel = new Label("Date");
        dateDisplay = new DateDisplay(dto.getId());

        user = App.context().getBean(UserDTO.class);
        userType = new SimpleObjectProperty<>(Login.user().getType());
        approvalCheckBox = new CheckBox("Report received");
        approvalCheckBox.setStyle("-fx-opacity: 1");
        approvalCheckBox.setSelected(isApproved());

        Label mailedByLabel = new Label("Mailed by");
        mailedByDisplay = new UserDisplay(dto.getMailedBy());
        Label mailedOnLabel = new Label("on");
        mailedOnDisplay = new TimestampDisplay(dto.getMailedOn());

        Label approvedByLabel = new Label("Confirmed by");
        approvedByDisplay = new UserDisplay(dto.getApprovedBy());
        Label approvedOnLabel = new Label("on");
        approvedOnDisplay = new TimestampDisplay(dto.getApprovedOn());

        Label completedByLabel = new Label("Closed by");
        completedByDisplay = new UserDisplay(dto.getCompletedBy());
        Label completedOnLabel = new Label("on");
        completedOnDisplay = new TimestampDisplay(dto.getCompletedOn());

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        gridPane.add(dateLabel, 0, 0);
        gridPane.add(dateDisplay, 1, 0);

        gridPane.add(approvalCheckBox, 2, 0, 2, 1);

        gridPane.add(mailedByLabel, 4, 0);
        gridPane.add(mailedByDisplay, 5, 0);
        gridPane.add(mailedOnLabel, 6, 0);
        gridPane.add(mailedOnDisplay, 7, 0);

        gridPane.add(approvedByLabel, 0, 1);
        gridPane.add(approvedByDisplay, 1, 1);
        gridPane.add(approvedOnLabel, 2, 1);
        gridPane.add(approvedOnDisplay, 3, 1);

        gridPane.add(completedByLabel, 4, 1);
        gridPane.add(completedByDisplay, 5, 1);
        gridPane.add(completedOnLabel, 6, 1);
        gridPane.add(completedOnDisplay, 7, 1);

        HBox box = new HBox(setVolumeTableVBox(), setRevenueTableVBox(), setRemittanceTableVBox());
        box.setSpacing(10);
        box.setPadding(new Insets(5));
        box.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(gridPane, box);
        return new Node[] { vBox };
    }

    private VBox setVolumeTableVBox() {
        volumeTable = new VolumeTable(stage).getTable();
        volumeTable.setItems(dto.getVolumeSummary());
        volumeTable.setUserData(new BigDecimal[] { dto.getTotalVolume() });
        totalVolumeDisplay = new DecimalDisplay(((BigDecimal[]) volumeTable.getUserData())[0]);
        HBox hBox = new HBox(new Label("Total(CS)"), totalVolumeDisplay);
        setHBoxProperties(hBox);
        return new VBox(setLabel("Volume"), volumeTable, hBox);
    }

    private VBox setRevenueTableVBox() {
        revenueTable = new RevenueTable(stage).getTable();
        revenueTable.setItems(dto.getInvoices());
        revenueTable.setUserData(new BigDecimal[] { dto.getTotalRevenue() });
        totalRevenueDisplay = new CurrencyDisplay(((BigDecimal[]) revenueTable.getUserData())[0]);
        HBox hBox = new HBox(new Label("Total"), totalRevenueDisplay);
        setHBoxProperties(hBox);
        return new VBox(setLabel("Revenue"), revenueTable, hBox);
    }

    private VBox setRemittanceTableVBox() {
        remittanceTable = new RemittanceTable(stage).getTable();
        remittanceTable.setItems(dto.getRemittances());
        remittanceTable.setUserData(new BigDecimal[] { dto.getTotalRemittance() });
        totalRemittanceDisplay = new CurrencyDisplay(((BigDecimal[]) remittanceTable.getUserData())[0]);
        HBox hBox = new HBox(new Label("Total"), totalRemittanceDisplay);
        setHBoxProperties(hBox);
        return new VBox(setLabel("Remittance"), remittanceTable, hBox);
    }

    private Label setLabel(String name) {
        Label label = new Label(name);
        label.setStyle("-fx-font-size: 16pt;");
        return label;
    }

    private void setHBoxProperties(HBox hBox) {
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(10, 0, 0, 0));
        hBox.setAlignment(Pos.CENTER_RIGHT);
    }

    private Boolean isApproved() {
        return dto.isApproved() == null ? false : dto.isApproved();
    }

    @Override
    public void save() throws InvalidException {
        if (userType.get() == UserType.MANAGER && approvalCheckBox.isVisible()) {
            updateApprovedDecisionStamps();
        } else {
            ((Excel) stage).saveAsExcel();
            if (mailedByDisplay.getText().isEmpty())
                getApproval();
        }
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
        dto.setCompletedBy(Login.user());
        dto.setCompletedOn(ZonedDateTime.now());
        dto.save();
    }

    private void updateApprovalStamps(Boolean isApproved, Users user, ZonedDateTime timestamp) {
        dto.setApproved(isApproved);
        dto.setApprovedBy(user);
        dto.setApprovedOn(timestamp);
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
        dto.setMailedBy(Login.user());
        dto.setMailedOn(ZonedDateTime.now());
        dto.save();
        refresh();
    }

    @Override
    public void sendMail() throws MailNotSentException {
        try {
            new MailSender(txtDIS, "For confirmation: ", name, getDate(), addresses);
        } catch (Exception e) {
            throw new MailNotSentException();
        }
    }

    private String getDate() {
        return Util.formatDate(dto.getId());
    }

    @Override
    public Mail checkMail() throws CannotCheckMailException {
        try {
            return new MailChecker(txtDIS, name, getDate(), getMailedDate(), addresses).getMail();
        } catch (MessagingException e) {
            throw new CannotCheckMailException(name, getDate());
        }
    }

    private LocalDate getMailedDate() {
        return dto.getMailedOn().toLocalDate();
    }

    @Override
    public void handleCheckingResult(Mail mail) {
        if (mail.isApproved() == null)
            new InfoDialog(stage, "Decision is still pending");
        else
            updateApprovedDecisionStamps(mail);
    }

    @Override
    public void refresh() {
    }

    @Override
    public List<TableView<?>> getTables() {
        return Arrays.asList(volumeTable, revenueTable, remittanceTable);
    }

    public BooleanBinding isConfirmed() {
        return FX.isEmpty(approvedByDisplay).not();
    }
}

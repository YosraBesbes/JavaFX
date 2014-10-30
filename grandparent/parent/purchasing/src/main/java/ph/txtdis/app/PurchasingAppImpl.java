package ph.txtdis.app;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ph.txtdis.App;
import ph.txtdis.dto.CustomerDTO;
import ph.txtdis.dto.ItemDTO;
import ph.txtdis.dto.PurchasingDTO;
import ph.txtdis.excel.Excel;
import ph.txtdis.fx.button.CancelButton;
import ph.txtdis.fx.button.CheckMailButton;
import ph.txtdis.fx.button.ExcelButton;
import ph.txtdis.fx.button.SendMailButton;
import ph.txtdis.fx.display.TimestampDisplay;
import ph.txtdis.fx.display.UserDisplay;
import ph.txtdis.fx.table.PurchasingTable;
import ph.txtdis.mail.ApprovedByMail;
import ph.txtdis.mail.CannotCheckMailException;
import ph.txtdis.mail.Mail;
import ph.txtdis.mail.MailNotSentException;
import ph.txtdis.model.Priced;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;

public class PurchasingAppImpl extends AbstractOrderApp<Purchasing, PurchasingDetail, PurchasingDTO> implements Excel,
        ApprovedByMail {

    private UserDisplay cancelledByDisplay, mailedByDisplay, sentByDisplay, receivedByDisplay;
    private TimestampDisplay cancelledOnDisplay, mailedOnDisplay, sentOnDisplay, receivedOnDisplay;
    private HBox mailHBox;

    public PurchasingAppImpl() {
        super("Purchasing", "P/O");
    }

    @Override
    protected void setDTO() {
        dto = orderDTO = App.getContext().getBean(PurchasingDTO.class);
        super.setDTO();
    }

    @Override
    public void setCustomerDTO() {
        customer = App.getContext().getBean(CustomerDTO.class);
    }

    @Override
    public void setItemDTO() {
        item = App.getContext().getBean(ItemDTO.class);
    }

    @Override
    protected void setButtons() {
        super.setButtons();
        buttons.put("cancel", new CancelButton<Purchasing>(this, dto).getButton());
        buttons.put("excel", new ExcelButton(this).getButton());
        buttons.put("send", new SendMailButton(this).getButton());
        buttons.put("check", new CheckMailButton(this).getButton());
    }

    @Override
    protected void createOrderedLabeledInputs() {
        super.createOrderedLabeledInputs();
        dateLabel.setText("Delivery Date");
    }

    @Override
    protected void addGridPaneNodes(GridPane gridPane) {
        super.addGridPaneNodes(gridPane);
        gridPane.add(getStatusRadio(), 6, 0, 1, 3);
    }

    private Node getStatusRadio() {
        ToggleGroup group = new ToggleGroup();
        RadioButton approvedRadio = new RadioButton("Approved");
        approvedRadio.setToggleGroup(group);
        RadioButton cancelledRadio = new RadioButton("Cancelled");
        cancelledRadio.setToggleGroup(group);
        return new VBox(approvedRadio, cancelledRadio);
    }

    @Override
    public void createDetailTable() {
        detailTable = new PurchasingTable(this, orderDTO).getTable();
    }

    @Override
    protected void setValues(Priced priced) {
        super.setValues(priced);
    }

    @Override
    public void setPrice() {
        price = item.getLatestPurchasePrice(getPickerDate()).multiply(getQtyPerUomUnit());
    }

    @Override
    public void setDetail(Priced priced) {
        detailTableItem = (PurchasingDetail) priced;
    }

    @Override
    protected void setUserBox() {
        super.setUserBox();
        userHBox.getChildren().addAll(addCancelNodes());
    }

    private Node[] addCancelNodes() {
        Label cancelledByLabel = new Label("Cancelled by");
        cancelledByDisplay = new UserDisplay(orderDTO.getCancelledBy());
        Label cancelledOnLabel = new Label("on");
        cancelledOnDisplay = new TimestampDisplay(orderDTO.getCancelledOn());
        return new Node[] { cancelledByLabel, cancelledByDisplay, cancelledOnLabel, cancelledOnDisplay };
    }

    private Node[] addMailNodes() {
        Label mailedByLabel = new Label("Mailed by");
        mailedByDisplay = new UserDisplay(orderDTO.getMailedBy());
        Label mailedOnLabel = new Label("on");
        mailedOnDisplay = new TimestampDisplay(orderDTO.getMailedOn());
        return new Node[] { mailedByLabel, mailedByDisplay, mailedOnLabel, mailedOnDisplay };
    }

    private Node[] addSendNodes() {
        Label sentByLabel = new Label("Sent by");
        sentByDisplay = new UserDisplay(orderDTO.getSentBy());
        Label sentOnLabel = new Label("on");
        sentOnDisplay = new TimestampDisplay(orderDTO.getSentOn());
        return new Node[] { sentByLabel, sentByDisplay, sentOnLabel, sentOnDisplay };
    }

    private Node[] addReceiveNodes() {
        Label receivedByLabel = new Label("Received by");
        receivedByDisplay = new UserDisplay(orderDTO.getReceivedBy());
        Label receivedOnLabel = new Label("on");
        receivedOnDisplay = new TimestampDisplay(orderDTO.getReceivedOn());
        return new Node[] { receivedByLabel, receivedByDisplay, receivedOnLabel, receivedOnDisplay };
    }

    @Override
    protected void addFooter(VBox box) {
        super.addFooter(box);
        box.getChildren().add(setMailHBox());
        addMailHBoxNodes();
    }

    private HBox setMailHBox() {
        mailHBox = new HBox();
        setHBoxProperties(mailHBox);
        return mailHBox;
    }

    private void addMailHBoxNodes() {
        mailHBox.getChildren().addAll(addMailNodes());
        mailHBox.getChildren().addAll(addSendNodes());
        mailHBox.getChildren().addAll(addReceiveNodes());
    }

    @Override
    public void sendMail() throws MailNotSentException {
        // TODO Auto-generated method stub

    }

    @Override
    public Mail checkMail() throws CannotCheckMailException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleCheckingResult(Mail mail) {
        // TODO Auto-generated method stub

    }

    @Override
    public void saveAsExcel() {
        // TODO Auto-generated method stub

    }
}

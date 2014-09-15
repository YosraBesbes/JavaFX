package ph.txtdis.dto;

import java.time.ZonedDateTime;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.springframework.stereotype.Component;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;
import ph.txtdis.service.InvoiceBookletService;

@Component
public class InvoiceBookletDTOImpl extends AbstractAuditedDTO<InvoiceBooklet, InvoiceBookletService> implements
        InvoiceBookletDTO {

    @Override
    public void reset() {
        id = 0;
        entity = new InvoiceBooklet();
    }

    @Override
    public int getStartId() {
        return entity.getStartId();
    }

    @Override
    public void setStartId(int startId) {
        entity.setStartId(startId);
    }

    @Override
    public int getEndId() {
        return entity.getEndId();
    }

    @Override
    public void setEndId(int endId) {
        entity.setEndId(endId);
    }

    @Override
    public SystemUser getIssuedTo() {
        return entity.getIssuedTo();
    }

    @Override
    public void setIssuedTo(SystemUser issuedTo) {
        entity.setIssuedTo(issuedTo);
    }

    @Override
    public SystemUser getIssuedBy() {
        return entity.getCreatedBy();
    }

    @Override
    public ZonedDateTime getTimeStamp() {
        return entity.getTimeStamp();
    }

    @Override
    public ObservableList<InvoiceBooklet> list() {
        return FXCollections.observableList(service.list());
    }

    @Override
    public ObservableList<SystemUser> listUsers() {
        return FXCollections.observableList(service.listUsers());
    }

    @Override
    public InvoiceBooklet getBooklet(int id) {
        return service.getBooklet(id);
    }
}

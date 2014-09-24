package ph.txtdis.dto;

import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;

public interface InvoiceBookletDTO extends Audited<InvoiceBooklet> {
        
    int getStartId();
    
    void setStartId(int startId);
    
    int getEndId();
    
    void setEndId(int endId);
    
    SystemUser getIssuedTo();
    
    void setIssuedTo(SystemUser issuedTo);
    
    SystemUser getIssuedBy();
    
    ZonedDateTime getTimeStamp();
    
    ObservableList<InvoiceBooklet> list();
    
    ObservableList<SystemUser> listUsers();
    
    InvoiceBooklet getBooklet(int id);
}

package ph.txtdis.dto;

import java.time.ZonedDateTime;

import javafx.collections.ObservableList;
import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Users;

public interface InvoiceBookletDTO extends Audited<InvoiceBooklet> {
        
    int getStartId();
    
    void setStartId(int startId);
    
    int getEndId();
    
    void setEndId(int endId);
    
    Users getIssuedTo();
    
    void setIssuedTo(Users issuedTo);
    
    Users getIssuedBy();
    
    ZonedDateTime getIssuedDate();
    
    ObservableList<InvoiceBooklet> list();
    
    ObservableList<Users> listUsers();
    
    InvoiceBooklet getBooklet(int id);
}

package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.SystemUser;

public interface InvoiceBookletService extends Serviced<InvoiceBooklet, Integer> {

    List<InvoiceBooklet> list();

    List<SystemUser> listUsers();

    InvoiceBooklet getBooklet(int id);
}
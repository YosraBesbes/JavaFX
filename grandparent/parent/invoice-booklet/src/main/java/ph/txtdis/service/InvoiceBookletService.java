package ph.txtdis.service;

import java.util.List;

import ph.txtdis.model.InvoiceBooklet;
import ph.txtdis.model.Users;

public interface InvoiceBookletService extends Serviced<InvoiceBooklet, Integer> {

    List<InvoiceBooklet> list();

    List<Users> listUsers();

    InvoiceBooklet getBooklet(int id);
}
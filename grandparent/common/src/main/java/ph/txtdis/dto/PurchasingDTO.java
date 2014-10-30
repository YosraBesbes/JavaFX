package ph.txtdis.dto;

import java.math.BigDecimal;

import ph.txtdis.model.Cancel;
import ph.txtdis.model.Item;
import ph.txtdis.model.Mail;
import ph.txtdis.model.Purchasing;
import ph.txtdis.model.PurchasingDetail;
import ph.txtdis.model.Receive;
import ph.txtdis.model.Send;

public interface PurchasingDTO extends OrderDTO<Purchasing, PurchasingDetail>, Cancel, Mail, Send, Receive {

    int getDaysLevel(Item item, BigDecimal qty);
}

package ph.txtdis.model;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import ph.txtdis.type.UomType;

@Entity
public class Bom extends AbstractAudited {

    private static final long serialVersionUID = -86213987350272414L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item item;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Item child;
    
    @Column(nullable = false)
    private UomType uom;
    
    @Column(nullable = false, precision = 8, scale = 4)
    private BigDecimal qty;

    protected Bom() {
    }

    public Bom(Item item, Item child, UomType uom, BigDecimal qty) {
        this.item = item;
        this.child = child;
        this.uom = uom;
        this.qty = qty;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Item getChild() {
        return child;
    }

    public void setChild(Item child) {
        this.child = child;
    }

    public UomType getUom() {
        return uom;
    }

    public void setUom(UomType uom) {
        this.uom = uom;
    }

    public BigDecimal getQty() {
        return qty;
    }

    public void setQty(BigDecimal qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return item + ": " + child;
    }
}

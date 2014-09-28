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
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((child == null) ? 0 : child.hashCode());
        result = prime * result + ((item == null) ? 0 : item.hashCode());
        result = prime * result + ((qty == null) ? 0 : qty.hashCode());
        result = prime * result + ((uom == null) ? 0 : uom.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bom other = (Bom) obj;
        if (child == null) {
            if (other.child != null)
                return false;
        } else if (!child.equals(other.child))
            return false;
        if (item == null) {
            if (other.item != null)
                return false;
        } else if (!item.equals(other.item))
            return false;
        if (qty == null) {
            if (other.qty != null)
                return false;
        } else if (!qty.equals(other.qty))
            return false;
        if (uom != other.uom)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return item + ": " + child;
    }
}

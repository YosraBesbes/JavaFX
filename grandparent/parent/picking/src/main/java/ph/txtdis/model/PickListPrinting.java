package ph.txtdis.model;

import javax.persistence.Entity;

@Entity
public class PickListPrinting extends AbstractAudited {
    
    private static final long serialVersionUID = 2882385497366968849L;
    
    private Picking picking;

    protected PickListPrinting() {
    }

    public Picking getPicking() {
        return picking;
    }

    public void setPicking(Picking picking) {
        this.picking = picking;
    }

    @Override
    public String toString() {
        return getCreatedBy() + " printed " + picking + " on " + getTimeStamp();
    }
}

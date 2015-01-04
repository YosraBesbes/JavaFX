package ph.txtdis.fx.input;

import ph.txtdis.fx.util.FX;

public class IdField extends IntegerField {

    public IdField() {
        super();
    }

    public IdField(int id) {
        super(id);
    }

    @Override
    public void setInt(int number) {
        FX.styleId(this, number);
    }

    public int getIdNo() {
        return getInt();
    }

    public void setIdNo(int id) {
        setInt(id);
    }
}
package ph.txtdis.fx.input;

import ph.txtdis.fx.util.FX;

public class IdField extends IntegerField {

    public IdField() {
        setMaxWidth(80);
    }

    public IdField(int id) {
        super(id);
        setIdNo(id);
    }

    @Override
    public void setInt(int number) {
        FX.styleId(this, number);
    }

    public int getIdNo() {
        return super.getInt();
    }

    public void setIdNo(int id) {
        this.setInt(id);
    }
}
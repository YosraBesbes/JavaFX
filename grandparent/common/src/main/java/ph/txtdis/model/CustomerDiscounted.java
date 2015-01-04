package ph.txtdis.model;

import java.math.BigDecimal;

public interface CustomerDiscounted {

    int getLevel();

    void setLevel(int level);

    BigDecimal getPerCent();

    void setPerCent(BigDecimal perCent);

    BigDecimal getValue();

    void setValue(BigDecimal value);

}
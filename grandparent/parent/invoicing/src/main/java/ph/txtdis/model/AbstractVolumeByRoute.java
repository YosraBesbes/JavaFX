package ph.txtdis.model;

import java.math.BigDecimal;

public class AbstractVolumeByRoute {

    protected BigDecimal pms1Vol, pms2Vol, pms3Vol, s41Vol, s42Vol, s43Vol, s44Vol, s45Vol, s46Vol, s47Vol, s48Vol,
            s49Vol;

    public AbstractVolumeByRoute() {
    }

    public AbstractVolumeByRoute(BigDecimal pms1Vol, BigDecimal pms2Vol, BigDecimal pms3Vol, BigDecimal s41Vol,
            BigDecimal s42Vol, BigDecimal s43Vol, BigDecimal s44Vol, BigDecimal s45Vol, BigDecimal s46Vol,
            BigDecimal s47Vol, BigDecimal s48Vol, BigDecimal s49Vol) {
        this.pms1Vol = pms1Vol;
        this.pms2Vol = pms2Vol;
        this.pms3Vol = pms3Vol;
        this.s41Vol = s41Vol;
        this.s42Vol = s42Vol;
        this.s43Vol = s43Vol;
        this.s44Vol = s44Vol;
        this.s45Vol = s45Vol;
        this.s46Vol = s46Vol;
        this.s47Vol = s47Vol;
        this.s48Vol = s48Vol;
        this.s49Vol = s49Vol;
    }

    public BigDecimal getPMS1Vol() {
        return pms1Vol == null ? BigDecimal.ZERO : pms1Vol;
    }

    public BigDecimal getPMS2Vol() {
        return pms2Vol == null ? BigDecimal.ZERO : pms2Vol;
    }

    public BigDecimal getPMS3Vol() {
        return pms3Vol == null ? BigDecimal.ZERO : pms3Vol;
    }

    public BigDecimal getS41Vol() {
        return s41Vol == null ? BigDecimal.ZERO : s41Vol;
    }

    public BigDecimal getS42Vol() {
        return s42Vol == null ? BigDecimal.ZERO : s42Vol;
    }

    public BigDecimal getS43Vol() {
        return s43Vol == null ? BigDecimal.ZERO : s43Vol;
    }

    public BigDecimal getS44Vol() {
        return s44Vol == null ? BigDecimal.ZERO : s44Vol;
    }

    public BigDecimal getS45Vol() {
        return s45Vol == null ? BigDecimal.ZERO : s45Vol;
    }

    public BigDecimal getS46Vol() {
        return s46Vol == null ? BigDecimal.ZERO : s46Vol;
    }

    public BigDecimal getS47Vol() {
        return s47Vol == null ? BigDecimal.ZERO : s47Vol;
    }

    public BigDecimal getS48Vol() {
        return s48Vol == null ? BigDecimal.ZERO : s48Vol;
    }

    public BigDecimal getS49Vol() {
        return s49Vol == null ? BigDecimal.ZERO : s49Vol;
    }
}
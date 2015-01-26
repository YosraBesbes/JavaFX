package ph.txtdis.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AbstractVolumeByRoute {

    protected BigDecimal pms1Vol = BigDecimal.ZERO;

    protected BigDecimal pms2Vol = BigDecimal.ZERO;

    protected BigDecimal pms3Vol = BigDecimal.ZERO;

    protected BigDecimal s41Vol = BigDecimal.ZERO;

    protected BigDecimal s42Vol = BigDecimal.ZERO;

    protected BigDecimal s43Vol = BigDecimal.ZERO;

    protected BigDecimal s44Vol = BigDecimal.ZERO;

    protected BigDecimal s45Vol = BigDecimal.ZERO;

    protected BigDecimal s46Vol = BigDecimal.ZERO;

    protected BigDecimal s47Vol = BigDecimal.ZERO;

    protected BigDecimal s48Vol = BigDecimal.ZERO;

    protected BigDecimal s49Vol = BigDecimal.ZERO;
}
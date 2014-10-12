package ph.txtdis.printer;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.commons.lang3.StringUtils;

import ph.txtdis.dto.BookingDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.model.BookingDetail;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class SalesOrderPrinter extends Printer<BookingDTO> {

    public final static int LINES_PER_PAGE = 14;

    public SalesOrderPrinter(BookingDTO dto) throws InvalidException {
        super(dto);
    }

    @Override
    protected void printDetails() {
        int lineNo = 0;
        for (BookingDetail detail : dto.getDetails())
            lineNo = printDetail(lineNo, detail);
        if (getRemaingLines(lineNo) > 0)
            printNothingFollowsFollowedByBlanks(getRemaingLines(lineNo));
    }

    private int printDetail(int lineNo, BookingDetail detail) {
        lineNo++;
        ps.print(StringUtils.leftPad(DIS.formatQuantity(detail.getQty()), 3));
        ps.print(detail.getUom() + " ");
        ps.print(StringUtils.rightPad(detail.getItemName(), 19));
        ps.print(StringUtils.leftPad(getPrice(detail) + "@", 8));
        ps.print(StringUtils.leftPad(getSubtotal(detail), 9));
        ps.println();
        return lineNo;
    }

    private String getSubtotal(BookingDetail detail) {
        return DIS.NO_COMMA_DECIMAL.format(detail.getQty().multiply(detail.getPrice()));
    }

    private String getPrice(BookingDetail detail) {
        return DIS.NO_COMMA_DECIMAL.format(detail.getPrice());
    }

    private int getRemaingLines(int lineNo) {
        return LINES_PER_PAGE - lineNo;
    }

    private void printNothingFollowsFollowedByBlanks(int remainingLines) {
        ps.println(StringUtils.center("-- NOTHING FOLLOWS --", COLUMN_WIDTH));
        for (int i = 0; i < remainingLines; i++)
            ps.println();
    }

    @Override
    protected void printSubheader() throws IOException {
        ps.println("DATE   : " + Util.formatDate(dto.getOrderDate()));
        ps.println("SOLD TO: " + dto.getPartnerName());
        ps.println("ADDRESS: " + dto.getPartnerAddress());
        ps.println("----------------------------------------");
        ps.println(StringUtils.center("PARTICULARS", COLUMN_WIDTH));
        ps.println(StringUtils.leftPad("", COLUMN_WIDTH, "-"));
    }

    @Override
    protected void printFooter() {
        BigDecimal total = dto.getTotalValue();
        BigDecimal vatable = total.divide(new BigDecimal(1.12), 2, RoundingMode.HALF_UP);
        BigDecimal vat = total.subtract(vatable);

        ps.println(StringUtils.leftPad("--------", COLUMN_WIDTH));
        ps.println(StringUtils.leftPad(DIS.NO_COMMA_DECIMAL.format(total), COLUMN_WIDTH));
        ps.print(StringUtils.rightPad("VATABLE", 8));
        ps.print(StringUtils.leftPad(DIS.NO_COMMA_DECIMAL.format(vatable), 9));
        ps.println(StringUtils.leftPad("--", 20));
        ps.print(StringUtils.rightPad("VAT", 8));
        ps.print(StringUtils.leftPad(DIS.NO_COMMA_DECIMAL.format(vat), 9));
        ps.println(StringUtils.leftPad("--", 20));
        ps.println(StringUtils.leftPad("--------", COLUMN_WIDTH));
        ps.print(StringUtils.leftPad("TOTAL", 33));
        ps.println(StringUtils.leftPad(DIS.NO_COMMA_DECIMAL.format(dto.getTotalValue()), 9));
        ps.println(StringUtils.leftPad("========", COLUMN_WIDTH));
        ps.println();
        ps.println("    PREPARED BY:          RECEIVED BY:");
        ps.println("___________________    ___________________");
        ps.println(StringUtils.center("MGDC", 21) + StringUtils.center(dto.getPartnerName(), 21));
        ps.println();
        ps.println("ORDER #" + dto.getId());
        printEndOfPage();
    }
}

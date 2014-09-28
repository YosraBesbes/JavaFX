package ph.txtdis.printer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import ph.txtdis.dto.PickingDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.model.PickList;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class PicklistPrinter extends Printer<PickingDTO> {

    public PicklistPrinter(PickingDTO dto) throws InvalidException {
        super(dto);
    }

    @Override
    protected void printSubheader() throws IOException {
        ps.println("DATE   : " + Util.formatDate(dto.getPickDate()));
        ps.println("LOAD TO: " + dto.getTruck());
        ps.println("----------------------------------------");
        ps.println("  QTY    DESCRIPTION     CODE  OUT    IN");
        ps.println("----------------------------------------");
        ps.println(StringUtils.leftPad("", COLUMN_WIDTH, "-"));
    }

    @Override
    protected void printDetails() {
        for (PickList pickList : dto.getPickList())
            printDetail(pickList);
    }

    private void printDetail(PickList pickList) {
        ps.print(StringUtils.leftPad(DIS.formatQuantity(pickList.getQty()), 3) + "PC ");
        ps.print(StringUtils.rightPad(pickList.getItemName(), 19));
        ps.print(StringUtils.leftPad(DIS.formatId(pickList.getItemId()), 4));
        ps.print(" _____  _____");
        ps.println();
    }

    @Override
    protected void printFooter() {
        ps.println();
        ps.println("LOAD-OUT:");
        ps.println("_____________  _____________  ____________");
        ps.println("  WAREHOUSE       CHECKER        TRUCK");
        ps.println();
        ps.println("BACKLOAD:");
        ps.println("_____________  _____________  ____________");
        ps.println("    TRUCK         CHECKER      WAREHOUSE");
        ps.println("PICKLIST #" + dto.getId());
        printEndOfPage();
    }
}

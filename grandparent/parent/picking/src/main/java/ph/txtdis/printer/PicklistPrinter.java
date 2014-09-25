package ph.txtdis.printer;

import java.io.IOException;
import java.time.ZonedDateTime;

import org.apache.commons.lang3.StringUtils;

import ph.txtdis.dto.PickingDTO;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.model.PickList;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Login;
import ph.txtdis.util.Util;

public class PicklistPrinter extends CDRKingPrinter {
    private PickingDTO dto;

    public PicklistPrinter(PickingDTO dto) throws InvalidException {
        this.dto = dto;
        setPrinter();
    }

    @Override
    public boolean print() {
        try {
            printLogo();
            printSubheader();
            printDetails();
            printFooter();
            savePrintStamps();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return printed;
    }

    private void savePrintStamps() throws IOException {
        waitForPrintingToEnd();
        dto.setPrintedBy(Login.user());
        dto.setPrintedOn(ZonedDateTime.now());
        dto.save();
    }

    private void printSubheader() throws IOException {
        ps.println("DATE   : " + Util.formatDate(dto.getPickDate()));
        ps.println("LOAD TO: " + dto.getTruck());
        ps.println("  QTY    DESCRIPTION     CODE  OUT    IN");
        ps.println(StringUtils.leftPad("", COLUMN_WIDTH, "-"));
    }

    private void printDetails() {
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

    private void printFooter() {
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

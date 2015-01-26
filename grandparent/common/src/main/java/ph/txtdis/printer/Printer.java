package ph.txtdis.printer;

import java.io.IOException;
import java.time.ZonedDateTime;

import javafx.scene.image.Image;

import org.springframework.beans.factory.annotation.Autowired;

import ph.txtdis.dto.DTO;
import ph.txtdis.dto.Printed;
import ph.txtdis.exception.InvalidException;
import ph.txtdis.util.SpringSecurityAuditorAware;

public abstract class Printer<D> extends CDRKingPrinter {

    @Autowired
    SpringSecurityAuditorAware auditor;

    protected D dto;

    public Printer(D dto) throws InvalidException {
        this.dto = dto;
        setPrinter();
    }

    @Override
    public void print() throws InvalidException {
        try {
            printLogo(getLogo());
            printSubheader();
            printDetails();
            printFooter();
            saveStamps();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new NotPrintedException();
        }
    }

    private int[][] getLogo() {
        String string;
        Image image = new Image(this.getClass().getResourceAsStream("/image/magnum.jpg"));
        int height = (int) image.getHeight();
        int yOffset = height % 8 / 2;
        int argb, red;
        int yLoop = height / 8;
        int width = (int) image.getWidth();
        int[][] value = new int[yLoop][width];
        for (int i = 0; i < yLoop; i++) {
            for (int x = 0; x < width; x++) {
                string = "";
                for (int y = 0; y < 8; y++) {
                    argb = image.getPixelReader().getArgb(x, yOffset + y + i * 8);
                    red = 0xFF & (argb >> 16);
                    string += red > 127 ? "0" : "1";
                }
                value[i][x] = Integer.parseInt(string, 2);
            }
        }
        return value;
    }

    protected abstract void printSubheader() throws IOException;

    protected abstract void printDetails();

    protected abstract void printFooter();

    protected void printEndOfPage() {
        ps.println("________________________________________");
        ps.println();
        ps.println();
        ps.println();
        ps.println();
    }

    private void saveStamps() throws IOException, InterruptedException {
        waitForPrintingToEnd();
        ((Printed) dto).setPrintedBy(auditor.getCurrentAuditor());
        ((Printed) dto).setPrintedOn(ZonedDateTime.now());
        ((DTO<?, ?>) dto).save();
    }
}

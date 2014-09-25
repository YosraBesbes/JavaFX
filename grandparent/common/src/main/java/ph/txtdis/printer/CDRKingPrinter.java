package ph.txtdis.printer;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Enumeration;

import javafx.scene.image.Image;

import org.apache.commons.lang3.StringUtils;

import ph.txtdis.exception.InvalidException;

public abstract class CDRKingPrinter {
    private int yLoop, width;
    private int[][] value;
    protected boolean printed;
    protected SerialPort port;
    protected OutputStream os;
    protected InputStream is;
    protected PrintStream ps;

    protected static final char ESC = 27;
    protected static final char AT = 64;
    protected static final char EXCLAMATION = 33;
    protected static final char WIDE_5x7 = 0b0000_0000;
    protected static final char HUGE = 0b0011_0001;
    protected static final char N = 78;
    protected static final char CHAR_PER_LINE = 4;
    protected static final char NARROW_42CPL = 1;
    protected static final char DLE = 16;
    protected static final char EOT = 4;
    protected static final char PRINTER_STATUS = 1;
    protected static final char COLUMN_WIDTH = 42;

    private static final char ASTERISK = 42;
    private static final char J = 74;
    private static final String PORT = "COM14";

    public CDRKingPrinter() throws InvalidException {
    }

    protected void setPrinter() throws InvalidException {
        printed = false;
        Enumeration<?> portIdentifiers = CommPortIdentifier.getPortIdentifiers();
        CommPortIdentifier portId = null;
        String portName = null;
        while (portIdentifiers.hasMoreElements()) {
            portId = (CommPortIdentifier) portIdentifiers.nextElement();
            if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL && portId.getName().equals(PORT)) {
                portName = portId.getName();
                break;
            }
        }
        try {
            if (portName == null) {
                throw new InvalidException(PORT + " cannot be found;\n"
                        + "ensure printer is on and plugged in to said port,\nthen reboot");
            } else {
                port = portId.open(portName, 100);
                port.setSerialPortParams(9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
                is = port.getInputStream();
                os = port.getOutputStream();
                ps = new PrintStream(os, true);
                setLogo();
                print();
            }
        } catch (PortInUseException e) {
            e.printStackTrace();
            throw new InvalidException("Port already in use;\nclose other apps.");
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
            throw new InvalidException("UnsupportedCommOperation:\n" + e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new InvalidException("No signal from printer;\nrestart it and try again.");
        } finally {
            try {
                if (is != null)
                    is.close();
                if (os != null)
                    os.close();
                if (ps != null)
                    ps.close();
                if (port != null)
                    port.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new InvalidException("Cannot reset printer; restart all");
            }
        }
    }

    private void setLogo() {
        String string;
        Image image = new Image(this.getClass().getResourceAsStream("/image/magnum.jpg"));
        int height = (int) image.getHeight();
        int yOffset = height % 8 / 2;
        int argb, red;
        yLoop = height / 8;
        width = (int) image.getWidth();
        value = new int[yLoop][width];
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
    }

    protected void printLogo() throws IOException {
        for (int i = 0; i < yLoop; i++) {
            os.write(ESC);
            os.write(ASTERISK);
            os.write((byte) 0); // m
            os.write((byte) width); // n1
            os.write((byte) 0); // n2
            for (int j = 0; j < width; j++)
                os.write((byte) value[i][j]);
            os.write(ESC);
            os.write(J);
            os.write((byte) 14); // n/144" feed
        }
    }

    protected void printNormal() throws IOException {
        os.write(ESC);
        os.write(N);
        os.write(CHAR_PER_LINE);
        os.write(NARROW_42CPL);
        os.write(ESC);
        os.write(AT);
        ps.println();
    }

    protected void printHuge() throws IOException {
        os.write(ESC);
        os.write(EXCLAMATION);
        os.write(HUGE);
    }

    protected void printDash() {
        ps.println(StringUtils.leftPad("", COLUMN_WIDTH, "-"));
    }

    protected void printEndOfPage() {
        ps.println("________________________________________");
        ps.println();
        ps.println();
        ps.println();
        ps.println();
    }

    protected void waitForPrintingToEnd() throws IOException {
        for (int i = 0; i < 10; i++) {
            int buffer = port.getOutputBufferSize();
            if (buffer == 0) {
                os.write(DLE);
                os.write(EOT);
                os.write(PRINTER_STATUS);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean print() throws InvalidException {
        return false;
    }

    public boolean isPrinted() {
        return printed;
    }
}

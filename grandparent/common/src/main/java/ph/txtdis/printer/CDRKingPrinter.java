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

import ph.txtdis.exception.InvalidException;

public abstract class CDRKingPrinter {
    protected SerialPort port;
    protected OutputStream os;
    protected InputStream is;
    protected PrintStream ps;
    protected static final char COLUMN_WIDTH = 42;

    private static final char ESC = 27;
    private static final char AT = 64;
    private static final char EXCLAMATION = 33;
    private static final char HUGE = 0b0011_0001;
    private static final char N = 78;
    private static final char CHAR_PER_LINE = 4;
    private static final char NARROW_42CPL = 1;
    private final static char DLE = 16;
    private final static char EOT = 4;
    private final static char PRINTER_STATUS = 1;

    private static final char ASTERISK = 42;
    private static final char J = 74;
    private static final String PORT = "COM14";

    public CDRKingPrinter() throws InvalidException {
    }

    protected void setPrinter() throws InvalidException {
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
                print();
            }
        } catch (PortInUseException e) {
            e.printStackTrace();
            throw new InvalidException("Serial port 14 is in use;\nclose other apps.");
        } catch (UnsupportedCommOperationException e) {
            e.printStackTrace();
            throw new InvalidException("Unsupported Comm Operation:\n" + e);
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

    protected void printLogo(int[][] value) throws IOException {
        for (int i = 0; i < value.length; i++) {
            int width = value[i].length;
            os.write(ESC);
            os.write(ASTERISK);
            os.write((byte) 0); // m
            os.write((byte) width); // n1
            os.write((byte) 0); // n2
            for (int j = 0; j < width; j++)
                os.write((byte) value[i][j]);
            os.write(ESC);
            os.write(J);
            os.write((byte) 16); // n/144" feed
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

    protected void printLarge() throws IOException {
        os.write(ESC);
        os.write(EXCLAMATION);
        os.write(HUGE);
    }

    protected void waitForPrintingToEnd() throws IOException, InterruptedException {
        for (int i = 0; i < 10; i++) {
            int buffer = port.getOutputBufferSize();
            if (buffer == 0) {
                os.write(DLE);
                os.write(EOT);
                os.write(PRINTER_STATUS);
            }
            Thread.sleep(1000);
        }
    }

    protected abstract void print() throws InvalidException;
}

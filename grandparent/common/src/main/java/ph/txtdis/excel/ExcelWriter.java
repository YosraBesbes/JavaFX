package ph.txtdis.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import ph.txtdis.util.DIS;

public class ExcelWriter<I> {

    private CellStyle titleStyle, headerStyle, rightStyle, redStyle, centerStyle, leftStyle, idStyle, integerStyle,
            decimalStyle, dateStyle;
    private Font normalFont, redFont, titleFont, boldFont;
    private DataFormat format;
    private HSSFWorkbook workbook;

    public ExcelWriter(TableView<I> table, String module, String id) {

        id = id.replace("/", "-");
        workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet(id);
        sheet.protectSheet("secretPassword");

        format = workbook.createDataFormat();
        setFonts();
        setCellStyles();

        ArrayList<String> getters = new ArrayList<>();
        addHeader(table.getColumns(), sheet, headerStyle, getters);
        sheet.createFreezePane(0, 1, 0, 1);
        populateRows(table.getItems(), sheet, getters);

        try {
            String file = System.getProperty("user.home") + "\\Desktop\\" + module + "." + id + ".xls";
            writeWorkbook(file);
            openWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setFonts() {
        setTitleFont();
        setRedFont();
        setBoldFont();
        setNormalFont();
    }

    private void setTitleFont() {
        titleFont = workbook.createFont();
        titleFont.setFontName("Calibri");
        titleFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        titleFont.setFontHeightInPoints((short) 13);
    }

    private void setBoldFont() {
        boldFont = workbook.createFont();
        boldFont.setFontName("Calibri");
        boldFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        boldFont.setFontHeightInPoints((short) 11);
    }

    private void setNormalFont() {
        normalFont = workbook.createFont();
        normalFont.setFontName("Calibri");
        normalFont.setFontHeightInPoints((short) 11);
    }

    private void setRedFont() {
        redFont = workbook.createFont();
        redFont.setFontName("Calibri");
        redFont.setFontHeightInPoints((short) 11);
        redFont.setColor(HSSFColor.RED.index);
    }

    private void setCellStyles() {
        setTitleStyle();
        setHeaderStyle();
        setIntegerStyle();
        setIdStyle();
        setDecimalStyle();
        setDateStyle();
        setRightStyle();
        setLeftStyle();
        setCenterStyle();
        setRedStyle();
    }

    private void setTitleStyle() {
        titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(CellStyle.ALIGN_CENTER);
        titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        titleStyle.setWrapText(true);
        titleStyle.setLocked(true);
    }

    private void setHeaderStyle() {
        headerStyle = workbook.createCellStyle();
        headerStyle.setBorderRight(CellStyle.BORDER_THIN);
        headerStyle.setBorderLeft(CellStyle.BORDER_THIN);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.getIndex());
        headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
        headerStyle.setFont(boldFont);
        headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
        headerStyle.setLocked(true);
        headerStyle.setWrapText(true);
    }

    private void setIntegerStyle() {
        integerStyle = workbook.createCellStyle();
        integerStyle.setFont(normalFont);
        integerStyle.setDataFormat(format.getFormat("#,##0;[Red](#,##0)"));
        integerStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        integerStyle.setLocked(true);
    }

    private void setIdStyle() {
        idStyle = workbook.createCellStyle();
        idStyle.setFont(normalFont);
        idStyle.setDataFormat(format.getFormat("###0"));
        idStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        idStyle.setLocked(true);
    }

    private void setDecimalStyle() {
        decimalStyle = workbook.createCellStyle();
        decimalStyle.setFont(normalFont);
        decimalStyle.setDataFormat(format.getFormat("#,##0.00;[Red](#,##0.00)"));
        decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        decimalStyle.setLocked(true);
    }

    private void setRedStyle() {
        redStyle = workbook.createCellStyle();
        redStyle.setFont(redFont);
        redStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        redStyle.setLocked(true);
    }

    private void setRightStyle() {
        rightStyle = workbook.createCellStyle();
        rightStyle.setFont(normalFont);
        rightStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        rightStyle.setLocked(true);
    }

    private void setCenterStyle() {
        centerStyle = workbook.createCellStyle();
        centerStyle.setFont(normalFont);
        centerStyle.setAlignment(CellStyle.ALIGN_CENTER);
        centerStyle.setLocked(true);
    }

    private void setLeftStyle() {
        leftStyle = workbook.createCellStyle();
        leftStyle.setFont(normalFont);
        leftStyle.setAlignment(CellStyle.ALIGN_LEFT);
        leftStyle.setLocked(true);
    }

    private void setDateStyle() {
        dateStyle = workbook.createCellStyle();
        dateStyle.setFont(normalFont);
        dateStyle.setDataFormat(getDateFormat());
        dateStyle.setAlignment(CellStyle.ALIGN_CENTER);
        dateStyle.setLocked(true);
    }

    private short getDateFormat() {
        CreationHelper ch = workbook.getCreationHelper();
        DataFormat df = ch.createDataFormat();
        return df.getFormat("m/d/yyyy");
    }

    private void addHeader(List<TableColumn<I, ?>> columns, Sheet sheet, CellStyle headerStyle,
            ArrayList<String> getters) {
        Row row = sheet.createRow(0);
        for (int i = 0; i < columns.size(); i++) {
            TableColumn<I, ?> column = columns.get(i);
            sheet.setColumnWidth(i, (int) (column.getWidth() / 10 + 2) * 256);
            addCell(headerStyle, row, i, column);
            getters.add("get" + StringUtils.capitalize(column.getId()));
        }
    }

    private void addCell(CellStyle headerStyle, Row row, int i, TableColumn<I, ?> column) {
        Cell cell = row.createCell(i);
        cell.setCellValue(column.getText());
        cell.setCellStyle(headerStyle);
    }

    private void populateRows(List<I> items, Sheet sheet, ArrayList<String> getters) {
        for (int i = 0; i < items.size(); i++)
            populateColumns(getters, items.get(i), sheet.createRow(i + 1));

    }

    private void populateColumns(ArrayList<String> getters, I item, Row row) {
        for (int i = 0; i < getters.size(); i++)
            setValue(getValue(getters, item, i), getters.get(i), row.createCell(i));
    }

    private Object getValue(ArrayList<String> getters, I item, int i) {
        return DIS.invokeMethod(item, getters.get(i));
    }

    private void setValue(Object object, String getter, Cell cell) {
        if (getter.contains("Id"))
            setIdValue(cell, object);
        else if (getter.contains("Qty"))
            setQtyValue(cell, object);
        else if (getter.contains("Type"))
            setCenterValue(cell, object);
        else if (getter.contains("Level"))
            setRightValue(cell, object);
        else
            setTextValue(cell, object);
    }

    private void setIdValue(Cell cell, Object id) {
        cell.setCellValue((int) id);
        cell.setCellStyle(idStyle);
    }

    private void setQtyValue(Cell cell, Object object) {
        double decimal = ((BigDecimal) object).doubleValue();
        cell.setCellValue(decimal);
        cell.setCellStyle(integerStyle);
    }

    private void setCenterValue(Cell cell, Object object) {
        cell.setCellValue(DIS.toString(object));
        cell.setCellStyle(centerStyle);
    }

    private void setRightValue(Cell cell, Object object) {
        String text = DIS.toString(object);
        cell.setCellValue(text);
        cell.setCellStyle(text.contains(">") ? redStyle : rightStyle);
    }

    private void setTextValue(Cell cell, Object object) {
        String text = DIS.toString(object);
        cell.setCellValue(text);
        cell.setCellStyle(text.contains(">") ? rightStyle : leftStyle);
    }

    private void writeWorkbook(String file) throws FileNotFoundException, IOException {
        FileOutputStream fileOut = new FileOutputStream(file);
        workbook.write(fileOut);
        fileOut.close();
    }

    private void openWorkbook(String file) throws IOException {
        String[] cmd = new String[] { "cmd.exe", "/C", file };
        Runtime.getRuntime().exec(cmd);
    }
}
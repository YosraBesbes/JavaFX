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

    private CellStyle titleStyle, headerStyle, textStyle, idStyle, integerStyle, decimalStyle, dateStyle;
    private Font normalFont, titleFont, boldFont;
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

        int width = table.getColumns().size();
        ArrayList<String> getters = new ArrayList<>();
        addHeader(table.getColumns(), sheet, headerStyle, getters);
        sheet.createFreezePane(0, 1, 0, 1);

        populateRows(table.getItems(), sheet, getters);
        resizeColumns(sheet, width);

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

    private void setCellStyles() {
        setTitleStyle();
        setHeaderStyle();
        setIntegerStyle();
        setIdStyle();
        setDecimalStyle();
        setDateStyle();
        setTextStyle();
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
        headerStyle.setLocked(true);
    }

    private void setIntegerStyle() {
        integerStyle = workbook.createCellStyle();
        integerStyle.setFont(normalFont);
        integerStyle.setDataFormat(format.getFormat("#,##0"));
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
        decimalStyle.setDataFormat(format.getFormat("#,##0.00"));
        decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        decimalStyle.setLocked(true);
    }

    private void setTextStyle() {
        textStyle = workbook.createCellStyle();
        textStyle.setFont(normalFont);
        textStyle.setAlignment(CellStyle.ALIGN_LEFT);
        textStyle.setLocked(true);
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
        populateHeader(columns, headerStyle, getters, row);
    }

    private void populateHeader(List<TableColumn<I, ?>> columns, CellStyle headerStyle, ArrayList<String> getters,
            Row row) {
        for (int i = 0; i < columns.size(); i++) {
            TableColumn<I, ?> column = columns.get(i);
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

    private void setTextValue(Cell cell, Object object) {
        cell.setCellValue(DIS.toString(object));
        cell.setCellStyle(textStyle);
    }

    private void resizeColumns(Sheet sheet, int width) {
        for (int i = 0; i < width; i++)
            sheet.autoSizeColumn(i);
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
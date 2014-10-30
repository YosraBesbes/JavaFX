package ph.txtdis.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
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
import org.apache.poi.ss.util.CellRangeAddress;

import ph.txtdis.util.DIS;
import ph.txtdis.util.Util;

public class ExcelWriter {

    private CellStyle titleStyle, headerStyle, rightStyle, redStyle, centerStyle, leftStyle, idStyle, integerStyle,
            decimalStyle, decimalSumStyle, currencyStyle, currencySumStyle, dateStyle;
    private Font normalFont, redFont, titleFont, boldFont;
    private DataFormat format;
    private HSSFWorkbook workbook;
    private int colIdx;

    public ExcelWriter(List<List<TableView<?>>> tables, String module, String... ids) {

        workbook = new HSSFWorkbook();
        format = workbook.createDataFormat();
        setFonts();
        setCellStyles();

        for (int i = 0; i < ids.length; i++) {
            String id = ids[i].replace("/", "-");
            Sheet sheet = workbook.createSheet(id);
            sheet.protectSheet("secretPassword");
            sheet.createFreezePane(0, 2, 0, 2);
            colIdx = 0;
            for (TableView<?> table : tables.get(i)) {
                ArrayList<String> getters = new ArrayList<>();
                addTitle(table.getId(), sheet, getColumns(table).size());
                addHeader(getColumns(table), sheet, headerStyle, getters);
                populateRows(table, sheet, getters);
                colIdx += getColumns(table).size();
                sheet.setColumnWidth(colIdx++, 750);
            }
        }

        try {
            String file = System.getProperty("user.home") + "\\Desktop\\" + module + "." + ids[0].replace("/", "-")
                    + ".xls";
            writeWorkbook(file);
            openWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ObservableList<?> getColumns(TableView<?> table) {
        return table.getColumns();
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
        titleFont.setFontHeightInPoints((short) 15);
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
        setDecimalSumStyle();
        setCurrencyStyle();
        setCurrencySumStyle();
        setDateStyle();
        setRightStyle();
        setLeftStyle();
        setCenterStyle();
        setRedStyle();
    }

    private void setTitleStyle() {
        titleStyle = workbook.createCellStyle();
        titleStyle.setFont(titleFont);
        titleStyle.setAlignment(CellStyle.ALIGN_LEFT);
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
        integerStyle.setDataFormat(getIntegerFormat());
        integerStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        integerStyle.setLocked(true);
    }

    private short getIntegerFormat() {
        return format.getFormat("_(#,##0_);[Red]_((#,##0);_(\"-\"??_);_(@_)");
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
        decimalStyle.setDataFormat(getDecimalFormat());
        decimalStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        decimalStyle.setLocked(true);
    }

    private void setDecimalSumStyle() {
        decimalSumStyle = workbook.createCellStyle();
        decimalSumStyle.setFont(normalFont);
        decimalSumStyle.setDataFormat(getDecimalFormat());
        decimalSumStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        decimalSumStyle.setBorderTop(CellStyle.BORDER_THIN);
        decimalSumStyle.setBorderBottom(CellStyle.BORDER_DOUBLE);
        decimalSumStyle.setLocked(true);
    }

    private short getDecimalFormat() {
        return format.getFormat("_(#,##0.00_);[Red]_((#,##0.00);_(\"-\"??_);_(@_)");
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

    private void setCurrencyStyle() {
        currencyStyle = workbook.createCellStyle();
        currencyStyle.setFont(normalFont);
        currencyStyle.setDataFormat(getCurrencyFormat());
        currencyStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        currencyStyle.setLocked(true);
    }

    private void setCurrencySumStyle() {
        currencySumStyle = workbook.createCellStyle();
        currencySumStyle.setFont(normalFont);
        currencySumStyle.setDataFormat(getCurrencyFormat());
        currencySumStyle.setAlignment(CellStyle.ALIGN_RIGHT);
        currencySumStyle.setBorderTop(CellStyle.BORDER_THIN);
        currencySumStyle.setBorderBottom(CellStyle.BORDER_DOUBLE);
        currencySumStyle.setLocked(true);
    }

    private short getCurrencyFormat() {
        return format.getFormat("_(₱* #,##0.00_);[Red]_(₱* (#,##0.00);_(₱* \"-\"??_);_(@_)");
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

    private void addTitle(String title, Sheet sheet, int colCount) {
        sheet.addMergedRegion(new CellRangeAddress(0, 0, colIdx, colIdx + colCount - 1));
        Row row = getRow(sheet, 0);
        row.setHeightInPoints(30);
        Cell cell = row.createCell(colIdx);
        cell.setCellValue(title);
        cell.setCellStyle(titleStyle);
    }

    private void addHeader(ObservableList<?> list, Sheet sheet, CellStyle headerStyle, ArrayList<String> getters) {
        for (int i = 0; i < list.size(); i++) {
            TableColumn<?, ?> column = (TableColumn<?, ?>) list.get(i);
            sheet.setColumnWidth(i + colIdx, (int) (column.getWidth() / 10 + 2) * 256);
            addCell(headerStyle, getRow(sheet, 1), i + colIdx, column);
            getters.add("get" + StringUtils.capitalize(column.getId()));
        }
    }

    private Row getRow(Sheet sheet, int rowIdx) {
        Row row = sheet.getRow(rowIdx);
        return row == null ? sheet.createRow(rowIdx) : row;
    }

    private void addCell(CellStyle headerStyle, Row row, int i, TableColumn<?, ?> column) {
        Cell cell = row.createCell(i);
        cell.setCellValue(column.getText());
        cell.setCellStyle(headerStyle);
    }

    private void populateRows(TableView<?> table, Sheet sheet, ArrayList<String> getters) {
        List<?> items = table.getItems();
        for (int i = 0; i < items.size(); i++)
            populateColumns(getters, items.get(i), getRow(sheet, i + 2));
        addSummationIfRequired(table, getters, sheet);
    }

    private void addSummationIfRequired(TableView<?> table, ArrayList<String> getters, Sheet sheet) {
        if (table.getUserData() != null)
            for (int i = toObjectArray(table).length - 1; i >= 0; i--)
                setValue(toObjectArray(table)[i], addSumToGetter(table, getters, i), createSumCell(table, sheet, i));
    }

    private Cell createSumCell(TableView<?> table, Sheet sheet, int i) {
        return getLastRow(table, sheet).createCell(i + colIdx + getOffset(table));
    }

    private String addSumToGetter(TableView<?> table, ArrayList<String> getters, int i) {
        return getters.get(i + getOffset(table)) + "Sum";
    }

    private int getOffset(TableView<?> table) {
        return table.getColumns().size() - toObjectArray(table).length;
    }

    private Object[] toObjectArray(TableView<?> table) {
        return table.getUserData() == null ? new Object[] {} : (Object[]) table.getUserData();
    }

    private Row getLastRow(TableView<?> table, Sheet sheet) {
        return getRow(sheet, getLastRowIdx(table));
    }

    private int getLastRowIdx(TableView<?> table) {
        return table.getItems().size() + 2;
    }

    private void populateColumns(ArrayList<String> getters, Object item, Row row) {
        for (int i = 0; i < getters.size(); i++)
            setValue(getValue(getters, item, i), getters.get(i), row.createCell(i + colIdx));
    }

    private Object getValue(ArrayList<String> getters, Object item, int i) {
        return DIS.invokeMethod(item, getters.get(i));
    }

    private void setValue(Object object, String getter, Cell cell) {
        if (getter.contains("Id"))
            setIdValue(cell, object);
        else if (getter.contains("Count"))
            setIntValue(cell, object);
        else if (getter.contains("Vol"))
            setDecimalValue(cell, object);
        else if (getter.contains("ValueSum"))
            setCurrencySum(cell, object);
        else if (getter.contains("QtySum"))
            setQtySum(cell, object);
        else if (getter.contains("Value"))
            setCurrencyValue(cell, object);
        else if (getter.contains("Qty"))
            setQtyValue(cell, object);
        else if (getter.contains("Date"))
            setDateValue(cell, object);
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

    private void setIntValue(Cell cell, Object id) {
        cell.setCellValue((int) id);
        cell.setCellStyle(integerStyle);
    }

    private void setDecimalValue(Cell cell, Object object) {
        double qty = ((BigDecimal) object).doubleValue();
        cell.setCellValue(qty);
        cell.setCellStyle(decimalStyle);
    }

    private void setQtyValue(Cell cell, Object object) {
        double qty = ((BigDecimal) object).doubleValue();
        cell.setCellValue(qty);
        cell.setCellStyle(integerStyle);
    }

    private void setQtySum(Cell cell, Object object) {
        double qty = ((BigDecimal) object).doubleValue();
        cell.setCellValue(qty);
        cell.setCellStyle(decimalSumStyle);
    }

    private void setCurrencyValue(Cell cell, Object object) {
        double currency = ((BigDecimal) object).doubleValue();
        cell.setCellValue(currency);
        cell.setCellStyle(currencyStyle);
    }

    private void setCurrencySum(Cell cell, Object object) {
        double currency = ((BigDecimal) object).doubleValue();
        cell.setCellValue(currency);
        cell.setCellStyle(currencySumStyle);
    }

    private void setDateValue(Cell cell, Object object) {
        cell.setCellValue(Util.localToDate((LocalDate) object));
        cell.setCellStyle(dateStyle);
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
        cell.setCellStyle(text.contains(">") ? redStyle : leftStyle);
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
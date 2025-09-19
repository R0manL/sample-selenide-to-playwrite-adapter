package com.superit.smart.qa.file;


import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;

import static com.superit.smart.qa.utils.FileUtils.generateNewFileNameWithCurrentDateSuffix;

@Slf4j
public class XlsxService {
    private final Path path;
    private final Workbook workbook;
    private Sheet sheet;


    public XlsxService(Path path) {
        this.path = path;
        FileInputStream file;
        ZipSecureFile.setMinInflateRatio(0);

        try { file = new FileInputStream(this.path.toFile()); }
        catch (FileNotFoundException e) { throw new IllegalStateException("Can't read xlsx file: " + path + "\n" + e.getMessage()); }

        try { this.workbook = new XSSFWorkbook(file); }
        catch (IOException e) { throw new IllegalStateException("Can't read workbook from xlsx file.\n" + e.getMessage());}
    }

    public XlsxService readSheet(@NotNull String name) {
        log.debug("Getting sheet: {}", name);
        this.sheet = workbook.getSheet(name);
        return this;
    }

    public CellCoordinates getCellCoordinatesWith(@NotNull String text) {
        log.info("Search cell with text: {}", text);
        for (int i = 0; i < this.sheet.getLastRowNum(); i++) {
            Row row = this.sheet.getRow(i);
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if(cell != null && text.equals(getCellValue(cell))) {
                    log.debug("Cell has been found in row: {}, col: {}", i, j);
                    return new CellCoordinates(i, j);
                }
            }
        }

        throw new IllegalStateException("Can't find cell with text: " + text);
    }

    public Cell getCell(CellCoordinates cellCoordinates) {
        return this.sheet.getRow(cellCoordinates.rowNum).getCell(cellCoordinates.colNum);
    }

    public XlsxService updateNumericCellWith(float value, CellCoordinates cellCoordinates) {
        float oldValue = Float.parseFloat(getCellValue(cellCoordinates));
        float newValue = oldValue + value;

        log.info("Update cell (row/col): {}, {} with value: {}", cellCoordinates.rowNum, cellCoordinates.colNum, newValue);
        getCell(cellCoordinates).setCellValue(newValue);

        return this;
    }

    public XlsxService setCellValue(@NotNull String value, CellCoordinates cellCoordinates) {
        log.info("Set value: '{}' into cell (row/col): {}, {}.", value, cellCoordinates.rowNum, cellCoordinates.colNum);
        getCell(cellCoordinates).setCellValue(value);

        return this;
    }

    public void writeToFile(Path path) {
        log.info("Write workbook to file: {}", path.toString());
        try(FileOutputStream outputStream = new FileOutputStream(path.toFile())) {
            this.workbook.write(outputStream);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("Can't find file: " + path + "\nError: " + e.getMessage());
        } catch (IOException e) {
            throw new IllegalStateException("Can't write to file: " + path + "\nError: " + e.getMessage());
        }
    }

    public Path writeToNewFile() {
        Path newPath = generateNewFileNameWithCurrentDateSuffix(this.path);
        writeToFile(newPath);

        return newPath;
    }

    public String getCellValue(CellCoordinates cellCoordinates) {
        Cell cell = this.sheet.getRow(cellCoordinates.rowNum).getCell(cellCoordinates.colNum);
        return getCellValue(cell);
    }

    @NotNull
    private String getCellValue(@NotNull Cell cell) {
        switch (cell.getCellType()) {
            case STRING: return cell.getRichStringCellValue().getString();
            case NUMERIC: return DateUtil.isCellDateFormatted(cell) ? cell.getDateCellValue() + "" : cell.getNumericCellValue() + "";
            case BOOLEAN: return cell.getBooleanCellValue() + "";
            case FORMULA: return cell.getCellFormula() + "";
            default: return "";
        }
    }

    public static class CellCoordinates {
        private final int rowNum;
        private final int colNum;

        public CellCoordinates(int rowNum, int colNum) {
            this.rowNum = rowNum;
            this.colNum = colNum;
        }


        public int getColNum() {
            return this.colNum;
        }

        public int getRowNum() {
            return this.rowNum;
        }

        public CellCoordinates getNextRow() {
            return new CellCoordinates(this.rowNum + 1, this.colNum);
        }
    }
}

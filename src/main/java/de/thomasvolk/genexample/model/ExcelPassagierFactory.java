package de.thomasvolk.genexample.model;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelPassagierFactory extends AbstractPassagierFactory {
    @Override
    protected List<Passagier> lesePassagiere(InputStream is, int anzahl) throws IOException {
        List<Passagier> passagiere = new ArrayList<>();
        OPCPackage pkg = null;
        try {
            pkg = OPCPackage.open(is);
        } catch (InvalidFormatException e) {
            throw new RuntimeException(e);
        }
        XSSFWorkbook xssfwb = new XSSFWorkbook(pkg);
        SXSSFWorkbook workbook = new SXSSFWorkbook(xssfwb, 100);
        Sheet sheet = workbook.getSheetAt(0);
        int i = 1;
        while (i < anzahl) {
            i++;
            Row row = sheet.getRow(i);
            if(row != null) {
                int fensterPlatz = getWertung(row.getCell(0).getStringCellValue());
                int fahrtRichtung = getWertung(row.getCell(1).getStringCellValue());
                int abteil = getWertung(row.getCell(2).getStringCellValue());
                Wertung wertung = new Wertung(fensterPlatz, abteil, fahrtRichtung);
                passagiere.add(new Passagier(i, wertung));
            }
        }
        return passagiere;
    }
}

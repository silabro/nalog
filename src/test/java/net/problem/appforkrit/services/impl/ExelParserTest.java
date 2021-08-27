package net.problem.appforkrit.services.impl;

import net.problem.appforkrit.domain.entities.*;
import net.problem.appforkrit.domain.repositories.NalogRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.persistence.MapKeyColumn;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class ExelParserTest {

    @Spy
    @InjectMocks
    private ExelParser exelParser;

    @Mock
    private NalogRepository nalogRepository;

    /**
     * Временный тест для проверки метода парсинга и работы библиотеки org.apache.poi (для работы с Microsoft документами)
     */
    @Test
    void tryParseAndCheckSuccess() {
        boolean expectedSuccess = true;
        ResponseParseEntity result;
        String fileDirectory = "C:\\work\\Nalog\\To_load\\";
        String fileName = "43_01.02.2021.xlsx";

        result = exelParser.parseExelFileAndGetResponseParseEntity(fileDirectory,fileName);

        assertAll(
                () -> assertEquals(expectedSuccess, result.isSuccess()),
                () -> assertNotNull(result.getBook())
        );
    }

    @Test
    void tryParseAllFromFolder() {
        ResponseParseSaveEntity result;
        String fileName = "16_01.02.2021.xls";
        String fileDirectoryFrom = "C:\\work\\Nalog\\To_load\\";
        String fileDirectoryWhere = "C:\\work\\Nalog\\Loaded\\";
        when(exelParser.parseExelFileAndGetResponseParseEntity(fileDirectoryFrom, fileName)).thenReturn(getResponseParseEntity());
        when(exelParser.moveFileToDirectory(fileName, fileDirectoryFrom, fileDirectoryWhere)).thenReturn(true);
        when(exelParser.getFileNamesFromDirectory(fileDirectoryFrom)).thenReturn(getListWithFileName(fileName));

        result = exelParser.parseAndSaveToRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles(fileDirectoryFrom,fileDirectoryWhere);

        assertTrue(result.getParsedAndSavedFileNames().size() > 0);
    }

    private ArrayList<String> getListWithFileName(String fileName){
        ArrayList<String> fileNames = new ArrayList<>();
        fileNames.add(fileName);
        return fileNames;
    }

    private ResponseParseEntity getResponseParseEntity(){
        ResponseParseEntity responseParseEntity = new ResponseParseEntity();
        responseParseEntity.setBook(getBookEntity());
        responseParseEntity.setSuccess(true);

        return responseParseEntity;
    }

    private RowEntity getRowEntityWithTitleData(){
        RowEntity row = new RowEntity();
        LinkedHashMap<Short, String> cells = new LinkedHashMap<>();

        cells.put((short) 0, "А");
        cells.put((short) 1, "Б");
        cells.put((short) 2, "В");
        cells.put((short) 3, "1");
        cells.put((short) 4, "2");
        cells.put((short) 5, "3");
        cells.put((short) 6, "4");
        cells.put((short) 7, "5");
        cells.put((short) 8, "6");
        cells.put((short) 9, "7");
        cells.put((short) 10, "8");
        cells.put((short) 11, "9");
        cells.put((short) 12, "10");
        cells.put((short) 13, "11");
        cells.put((short) 14, "12");
        cells.put((short) 15, "13");
        cells.put((short) 16, "14");
        cells.put((short) 17, "15");
        cells.put((short) 18, "16");
        cells.put((short) 19, "17");
        cells.put((short) 20, "18");
        cells.put((short) 21, "19");
        cells.put((short) 22, "20");
        cells.put((short) 23, "21");
        cells.put((short) 24, "22");
        cells.put((short) 25, "23");
        cells.put((short) 26, "24");
        cells.put((short) 27, "25");
        row.setCells(cells);

        return row;
    }

    private RowEntity getRowEntityWithSimpleData(){
        short maxCellsIndex = 27;
        RowEntity row = new RowEntity();
        LinkedHashMap<Short, String> cells = new LinkedHashMap<>();

        for(short keyMap = 0; keyMap <= maxCellsIndex; keyMap++){
            cells.put(keyMap, "Simple value cell number " + maxCellsIndex);
        }
        row.setCells(cells);

        return row;
    }

    private SheetEntity getSheetEntity(){
        SheetEntity sheet = new SheetEntity();
        ArrayList<RowEntity> rows = new ArrayList<>();

        rows.add(getRowEntityWithTitleData());
        rows.add(getRowEntityWithSimpleData());
        sheet.setRows(rows);

        return sheet;
    }

    private BookEntity getBookEntity(){
        BookEntity book = new BookEntity();
        ArrayList<SheetEntity> sheets = new ArrayList<>();

        sheets.add(getSheetEntity());
        book.setSheets(sheets);

        return book;
    }

}
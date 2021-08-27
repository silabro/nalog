package net.problem.appforkrit.services.impl;

import lombok.RequiredArgsConstructor;
import net.problem.appforkrit.domain.entities.*;
import net.problem.appforkrit.domain.repositories.NalogRepository;
import net.problem.appforkrit.services.IExelParser;
import net.problem.appforkrit.services.common.ExelFileTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.problem.appforkrit.services.common.ExelFileTypeEnum.*;
import static net.problem.appforkrit.mapping.NalogMapper.MAPPER;

@Service
@RequiredArgsConstructor
public class ExelParser implements IExelParser {

    private final NalogRepository nalogRepository;

    private final FileHelper fileHelper;

    @Override
    public ResponseParseEntity parseExelFileAndGetResponseParseEntity(String fileDirectory, String fileName) {
        String fileAddress = getFileAddress(fileDirectory, fileName);
        Workbook workbook = fileHelper.getWorkbook(fileAddress);

        if(workbook != null){
            return getResponseEntity(true, parseAndGetBookEntity(workbook));
        }

        return getBadResponseEntity();
    }

    @Override
    public ResponseParseSaveEntity parseAndSaveToRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles (String directoryWhereParse, String directoryWhereMoveParsedFiles) {
        ArrayList<String> fileNames = fileHelper.getFileNamesFromDirectory(directoryWhereParse);
        ArrayList<String> parsedAndSavedFileNames = new ArrayList<>();

        for (String fileName : fileNames){
            String regionNumber = getRegionNumberFromFileName(fileName);
            String date = getDateFromFileName(fileName);
            ResponseParseEntity parseResult = parseExelFileAndGetResponseParseEntity(directoryWhereParse, fileName);
            ArrayList<SheetEntity> parseResultSheets = parseResult.getBook().getSheets();

            if(parseResult.isSuccess()){
                for(SheetEntity sheet : parseResultSheets){
                    ArrayList<RowEntity> parseResultRows = sheet.getRows();
                    int indexRowStart = getIndexRowStartNeededData(parseResultRows);

                    if(indexRowStart > 0){
                        NalogEntity nalogEntityMain = getNalogEntityWithTerAndDat(regionNumber, date);

                        for(int index = indexRowStart; index < parseResultRows.size(); index++){
                            LinkedHashMap<Short, String> parseResultCells = parseResultRows.get(index).getCells();

                            if(parseResultCells.size() > 0){
                                if(checkFirstAndThirdCellNotEmpty(parseResultCells)){
                                    NalogEntity nalogEntityFragment = MAPPER.mapWithCellsAndStringsTerAndDatToNalogEntity(parseResultCells, regionNumber, date);
                                    nalogEntityMain = splitNalogEntity(nalogEntityMain, nalogEntityFragment);
                                }
                            }
                        }

                        parsedAndSavedFileNames.add(saveToRepositoryAndMoveFileAndGetFileName(  nalogEntityMain, fileName, directoryWhereParse,
                                                                                                directoryWhereMoveParsedFiles   ));
                    }
                }
            }
        }

        return getResponseParseSaveEntity(parsedAndSavedFileNames);
    }

    private String saveToRepositoryAndMoveFileAndGetFileName(NalogEntity nalogEntity, String fileName, String directoryWhereParse, String directoryWhereMoveParsedFiles){
        nalogRepository.save(nalogEntity);
        fileHelper.moveFileToDirectory(fileName, directoryWhereParse, directoryWhereMoveParsedFiles);

        return fileName;
    }

    private NalogEntity getNalogEntityWithTerAndDat(String ter, String dat){
        NalogEntity nalogEntity = new NalogEntity();

        nalogEntity.setTer(ter);
        nalogEntity.setDat(dat);

        return nalogEntity;
    }

    private ResponseParseSaveEntity getResponseParseSaveEntity(ArrayList<String> parsedAndSavedFileNames){
        ResponseParseSaveEntity responseParseSaveEntity = new ResponseParseSaveEntity();
        responseParseSaveEntity.setParsedAndSavedFileNames(parsedAndSavedFileNames);

        return responseParseSaveEntity;
    }

    private NalogEntity splitNalogEntity(NalogEntity nalogEntityMain, NalogEntity nalogEntityFragment){
        NalogEntity nalogEntity = new NalogEntity();

        nalogEntity.setFielda( ifNullThenEmpty(nalogEntityMain.getFielda()) + "|" + nalogEntityFragment.getFielda());
        nalogEntity.setFieldb( ifNullThenEmpty(nalogEntityMain.getFieldb()) + "|" + nalogEntityFragment.getFieldb());
        nalogEntity.setFieldv( ifNullThenEmpty(nalogEntityMain.getFieldv()) + "|" + nalogEntityFragment.getFieldv());
        nalogEntity.setField1( ifNullThenEmpty(nalogEntityMain.getField1()) + "|" + nalogEntityFragment.getField1());
        nalogEntity.setField2( ifNullThenEmpty(nalogEntityMain.getField2()) + "|" + nalogEntityFragment.getField2());
        nalogEntity.setField3( ifNullThenEmpty(nalogEntityMain.getField3()) + "|" + nalogEntityFragment.getField3());
        nalogEntity.setField4( ifNullThenEmpty(nalogEntityMain.getField4()) + "|" + nalogEntityFragment.getField4());
        nalogEntity.setField5( ifNullThenEmpty(nalogEntityMain.getField5()) + "|" + nalogEntityFragment.getField5());
        nalogEntity.setField6( ifNullThenEmpty(nalogEntityMain.getField6()) + "|" + nalogEntityFragment.getField6());
        nalogEntity.setField7( ifNullThenEmpty(nalogEntityMain.getField7()) + "|" + nalogEntityFragment.getField7());
        nalogEntity.setField8( ifNullThenEmpty(nalogEntityMain.getField8()) + "|" + nalogEntityFragment.getField8());
        nalogEntity.setField9( ifNullThenEmpty(nalogEntityMain.getField9()) + "|" + nalogEntityFragment.getField9());
        nalogEntity.setField10( ifNullThenEmpty(nalogEntityMain.getField10()) + "|" + nalogEntityFragment.getField10());
        nalogEntity.setField11( ifNullThenEmpty(nalogEntityMain.getField11()) + "|" + nalogEntityFragment.getField11());
        nalogEntity.setField12( ifNullThenEmpty(nalogEntityMain.getField12()) + "|" + nalogEntityFragment.getField12());
        nalogEntity.setField13( ifNullThenEmpty(nalogEntityMain.getField13()) + "|" + nalogEntityFragment.getField13());
        nalogEntity.setField14( ifNullThenEmpty(nalogEntityMain.getField14()) + "|" + nalogEntityFragment.getField14());
        nalogEntity.setField15( ifNullThenEmpty(nalogEntityMain.getField15()) + "|" + nalogEntityFragment.getField15());
        nalogEntity.setField16( ifNullThenEmpty(nalogEntityMain.getField16()) + "|" + nalogEntityFragment.getField16());
        nalogEntity.setField17( ifNullThenEmpty(nalogEntityMain.getField17()) + "|" + nalogEntityFragment.getField17());
        nalogEntity.setField18( ifNullThenEmpty(nalogEntityMain.getField18()) + "|" + nalogEntityFragment.getField18());
        nalogEntity.setField19( ifNullThenEmpty(nalogEntityMain.getField19()) + "|" + nalogEntityFragment.getField19());
        nalogEntity.setField20( ifNullThenEmpty(nalogEntityMain.getField20()) + "|" + nalogEntityFragment.getField20());
        nalogEntity.setField21( ifNullThenEmpty(nalogEntityMain.getField21()) + "|" + nalogEntityFragment.getField21());
        nalogEntity.setField22( ifNullThenEmpty(nalogEntityMain.getField22()) + "|" + nalogEntityFragment.getField22());
        nalogEntity.setField23( ifNullThenEmpty(nalogEntityMain.getField23()) + "|" + nalogEntityFragment.getField23());
        nalogEntity.setField24( ifNullThenEmpty(nalogEntityMain.getField24()) + "|" + nalogEntityFragment.getField24());
        nalogEntity.setField25( ifNullThenEmpty(nalogEntityMain.getField25()) + "|" + nalogEntityFragment.getField25());
        nalogEntity.setTer(nalogEntityMain.getTer());
        nalogEntity.setDat(nalogEntityMain.getDat());

        return nalogEntity;
    }

    private String ifNullThenEmpty(String field){
        return field == null ? "" : field;
    }

    private String getRegionNumberFromFileName(String fileName){
        String regionNumber = "";
        Pattern pattern = Pattern.compile("\\d{1,3}\\w");
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            regionNumber = fileName.substring(matcher.start(), matcher.end() - 1);
        }

        return  regionNumber;
    }

    private String getDateFromFileName(String fileName){
        String date = "";
        Pattern pattern = Pattern.compile("\\d{2}.\\d{2}.\\d{4}");
        Matcher matcher = pattern.matcher(fileName);

        if (matcher.find()) {
            date = fileName.substring(matcher.start(), matcher.end());
        }

        return  date;
    }

    private boolean checkFirstAndThirdCellNotEmpty(LinkedHashMap<Short, String> cells){
        String firstCell = cells.get((short) 0);
        String thirdCell = cells.get((short) 2);

        return !firstCell.isEmpty() && !thirdCell.isEmpty();
    }

    private int getIndexRowStartNeededData(ArrayList<RowEntity> rows){
        int indexRow = 0;
        RowEntity rowEntityForCompare = getRowEntityWithTitleData();

        for(RowEntity row : rows){
            if(compareRowEntity(row, rowEntityForCompare)){
                return indexRow += 1;
            }
            indexRow++;
        }

        return -1;
    }

    private boolean compareRowEntity(RowEntity RowEntityLeft, RowEntity RowEntityRight){
        return RowEntityLeft.hashCode() == RowEntityRight.hashCode();
    }

    private RowEntity getRowEntityWithTitleData(){
        RowEntity rowEntityWithTitleData = new RowEntity();
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
        rowEntityWithTitleData.setCells(cells);

        return rowEntityWithTitleData;
    }

    private String getFileAddress(String fileDirectory, String fileName){
        return fileDirectory.concat(fileName);
    }

    private BookEntity parseAndGetBookEntity(Workbook workbook){
        int currentIndexSheet = 0;
        ArrayList<SheetEntity> sheetEntityList = new ArrayList<>();

        for(Sheet sheet : workbook){
            SheetEntity sheetEntity = new SheetEntity();
            ArrayList<RowEntity> sheetList = new ArrayList<>();

            if(!workbook.isSheetHidden(currentIndexSheet)){
                for (Row row : sheet) {
                    RowEntity rowEntity = new RowEntity();
                    short currentNumberCell = row.getFirstCellNum();
                    LinkedHashMap<Short, String> rowMap = new LinkedHashMap<>();

                    for(Cell cell : row){
                        String cellValue;

                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf((int) cell.getNumericCellValue());
                                break;
                            case FORMULA:
                                cellValue = cell.getCellFormula();
                                break;
                            case BOOLEAN:
                                cellValue = String.valueOf(cell.getBooleanCellValue());
                            default:
                                cellValue = "";
                                break;
                        }

                        rowMap.put(currentNumberCell, cellValue);
                        currentNumberCell++;

                    }

                    rowEntity.setCells(rowMap);
                    sheetList.add(rowEntity);

                }

                sheetEntity.setRows(sheetList);
                sheetEntityList.add(sheetEntity);

            }

            currentIndexSheet++;

        }

        return getBookDTO(sheetEntityList);
    }

    private ResponseParseEntity getResponseEntity(boolean success, BookEntity bookEntity){
        ResponseParseEntity responseParseEntity = new ResponseParseEntity();
        responseParseEntity.setSuccess(success);
        responseParseEntity.setBook(bookEntity);
        return responseParseEntity;
    }

    private BookEntity getBookDTO(ArrayList<SheetEntity> sheetEntityList){
        BookEntity bookEntity = new BookEntity();
        bookEntity.setSheets(sheetEntityList);
        return bookEntity;
    }

    private ResponseParseEntity getBadResponseEntity(){
        return getResponseEntity(false, new BookEntity());
    }


}

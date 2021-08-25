package net.problem.appforkrit.services.impl;

import net.problem.appforkrit.dto.exeldto.*;
import net.problem.appforkrit.services.IExelParser;
import net.problem.appforkrit.services.common.ExelFileTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTSheet;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.DoubleBinaryOperator;
import net.problem.appforkrit.services.common.ExelFileTypeEnum.*;

import static net.problem.appforkrit.services.common.ExelFileTypeEnum.*;
import static org.apache.poi.ss.usermodel.CellType.*;

@Service
public class ExelParser implements IExelParser {

    @Override
    public ParseResponseDTO parseExelFileAndGetResponseDTO(String fileDirectory, String fileName) {
        String fileAddress = getFileAddress(fileDirectory, fileName);
        Path exelFilePath = Paths.get(fileAddress);
        Workbook workbook = getWorkbook(fileAddress, exelFilePath);

        if(workbook != null){
            return getResponseDTO(true, parseAndGetBookDTO(workbook));
        }

        return getBadResponseDTO();
    }

    @Override
    public boolean moveFileToDirectory(String fileName, String fileDirectoryFrom, String fileDirectoryWhere) {
        Path resultMove = null;
        String fileAddressFrom = getFileAddress(fileDirectoryFrom, fileName);
        String fileAddressWhere = getFileAddress(fileDirectoryWhere, fileName);

        try {
            resultMove =  Files.move(Paths.get(fileAddressFrom), Paths.get(fileAddressWhere));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultMove != null;
    }

    private String getFileAddress(String fileDirectory, String fileName){
        return fileDirectory.concat(fileName);
    }

    private BookDTO parseAndGetBookDTO(Workbook workbook){
        int currentIndexSheet = 0;
        ArrayList<SheetDTO> sheetDTOList = new ArrayList<>();

        for(Sheet sheet : workbook){
            SheetDTO sheetDTO = new SheetDTO();
            ArrayList<RowDTO> sheetList = new ArrayList<>();

            if(!workbook.isSheetHidden(currentIndexSheet)){
                for (Row row : sheet) {
                    RowDTO rowDTO = new RowDTO();
                    short currentNumberCell = row.getFirstCellNum();
                    LinkedHashMap<Short, String> rowMap = new LinkedHashMap<>();

                    for(Cell cell : row){
                        String cellValue;

                        switch (cell.getCellType()) {
                            case STRING:
                                cellValue = cell.getStringCellValue();
                                break;
                            case NUMERIC:
                                cellValue = String.valueOf(cell.getNumericCellValue());
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

                    rowDTO.setCells(rowMap);
                    sheetList.add(rowDTO);

                }

                sheetDTO.setRows(sheetList);
                sheetDTOList.add(sheetDTO);

            }

            currentIndexSheet++;

        }

        return getBookDTO(sheetDTOList);
    }

    private Workbook getWorkbook(String fileAddress, Path path){
        Workbook workbook = null;
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(fileAddress);

            if(compareEndsFileName(path, XLSX)){
                workbook = new XSSFWorkbook(inputStream);
            }
            if (compareEndsFileName(path, XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            }
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return workbook;
    }

    private boolean compareEndsFileName(Path path, ExelFileTypeEnum exelFileTypeEnum){
        return path.getFileName().toString().endsWith("."+ exelFileTypeEnum.getExelFileType());
    }

    private ParseResponseDTO getResponseDTO(boolean success, BookDTO bookDTO){
        ParseResponseDTO parseResponseDTO = new ParseResponseDTO();
        parseResponseDTO.setSuccess(success);
        parseResponseDTO.setBookDTO(bookDTO);
        return parseResponseDTO;
    }

    private BookDTO getBookDTO(ArrayList<SheetDTO> sheetDTOList){
        BookDTO bookDTO = new BookDTO();
        bookDTO.setSheets(sheetDTOList);
        return bookDTO;
    }

    private ParseResponseDTO getBadResponseDTO(){
        return getResponseDTO(false, new BookDTO());
    }


}

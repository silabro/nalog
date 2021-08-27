package net.problem.appforkrit.services.impl;

import net.problem.appforkrit.services.IFileHelper;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

@Service
public class FileHelper implements IFileHelper {

    /**
     * Перемещает файл из одной директории в другую
     *
     * @param fileName           - строка наименования файла которого необходимо переместить
     * @param fileDirectoryFrom  - строка, директория в которой находиться файл
     * @param fileDirectoryWhere - строка, директория в которую необходимо переместить файл
     * @return - boolean, успешность выполнения перемещения.
     */
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

    /**
     * Считывает файл и возвращает Workbook в зависимости от формата файла .xls (HSSFWorkbook) или .xlsx (XSSFWorkbook)
     *
     * @param fileAddress - строка, содержащая в себе полный путь к файлу
     * @return - Workbook (HSSFWorkbook или XSSFWorkbook).
     */
    @Override
    public Workbook getWorkbook(String fileAddress) {
        return null;
    }

    /**
     * Возвращает список файлов формата .xls или .xlsx из указанной директории
     *
     * @param fileDirectoryFrom - строка, содержащая директорию из которой необходимо получить список
     * @return - List из строк наименований файлов.
     */
    @Override
    public ArrayList<String> getFileNamesFromDirectory(String fileDirectoryFrom) {
        return null;
    }

    private String getFileAddress(String fileDirectory, String fileName){
        return fileDirectory.concat(fileName);
    }
}

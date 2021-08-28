package net.problem.appforkrit.services;

import org.apache.poi.ss.usermodel.Workbook;

import java.nio.file.Path;
import java.util.ArrayList;

/**
 * Интерфейс сервиса который осуществляет работу с файлами
 */
public interface IFileHelper {

    /**
     * Перемещает файл из одной директории в другую
     * @param fileName  - строка наименования файла которого необходимо переместить
     * @param fileDirectoryFrom     - строка, директория в которой находиться файл
     * @param fileDirectoryWhere    - строка, директория в которую необходимо переместить файл
     * @return  - boolean, успешность выполнения перемещения.
     */
    boolean moveFileToDirectory(String fileName, String fileDirectoryFrom, String fileDirectoryWhere);

    /**
     * Считывает файл и возвращает Workbook в зависимости от формата файла .xls (HSSFWorkbook) или .xlsx (XSSFWorkbook)
     * @param fileAddress   - строка, содержащая в себе полный путь к файлу
     * @return  - Workbook (HSSFWorkbook или XSSFWorkbook).
     */
    Workbook getWorkbook(String fileAddress);

    /**
     * Возвращает список файлов формата .xls или .xlsx из указанной директории
     * @param fileDirectoryFrom - строка, содержащая директорию из которой необходимо получить список
     * @return  - List из строк наименований файлов.
     */
    ArrayList<String> getFileNamesFromDirectory(String fileDirectoryFrom);

}

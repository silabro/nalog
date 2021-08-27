package net.problem.appforkrit.services.impl;

import net.problem.appforkrit.services.IFileHelper;
import net.problem.appforkrit.services.common.ExelFileTypeEnum;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import static net.problem.appforkrit.services.common.ExelFileTypeEnum.XLS;
import static net.problem.appforkrit.services.common.ExelFileTypeEnum.XLSX;

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
        Workbook workbook = null;
        Path filePath = Paths.get(fileAddress);
        InputStream inputStream;

        try {
            inputStream = new FileInputStream(fileAddress);

            if(compareEndsFileName(filePath, XLSX)){
                workbook = new XSSFWorkbook(inputStream);
            }
            if (compareEndsFileName(filePath, XLS)) {
                workbook = new HSSFWorkbook(inputStream);
            }

            inputStream.close();
        }
        catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }

        return workbook;
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

    private boolean compareEndsFileName(Path path, ExelFileTypeEnum exelFileTypeEnum){
        return path.getFileName().toString().endsWith("."+ exelFileTypeEnum.getExelFileType());
    }
}

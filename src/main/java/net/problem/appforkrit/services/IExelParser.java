package net.problem.appforkrit.services;

import net.problem.appforkrit.domain.entities.ResponseParseEntity;
import net.problem.appforkrit.domain.entities.ResponseParseSaveEntity;

/**
 * Интерфейс сервиса который осуществляет парсинг файлов формата .xls и .xlsx
 */
public interface IExelParser {

    /**
     * Считывает и возвращает данные из файла .xls или .xlsx
     *
     * @param fileDirectory - строка указывающая дерикторию файла
     * @param fileName      - строка указывающая наименование файла
     * @return - ResponseParseEntity:
     * success, true в случае успешного парсинга файла;
     * BookEntity, содержит в себе листы Exel файла в виде SheetEntity, листы содержат строки, строки содержат ячейки.
     */
    ResponseParseEntity parseExelFileAndGetResponseParseEntity(String fileDirectory, String fileName);

    /**
     * Считывает все файлы (формата .xls или .xlsx) из директории, сохраняет данные в репозиторий и перемещает успешно считанные в указанную директорию
     *
     * @param directoryWhereParse   - строка указывающая директорию откуда считывать файлы
     * @param directoryWhereMoveParsedFiles - строка указывающая куда перемещать файлы из которых информация была успешна записана в репозиторий
     * @return  - ResponseParseSaveEntity:
     * List содержащий в себе строки именований файлов которые были успешно записаны в репозиторий.
     */
    ResponseParseSaveEntity parseAndSaveToRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles (String directoryWhereParse, String directoryWhereMoveParsedFiles);

    /**
     * Считывает все файлы (формата .xls или .xlsx) из директории, сохраняет данные в ДВА репозитория и перемещает успешно считанные в указанную директорию
     *
     * @param directoryWhereParse   - строка указывающая директорию откуда считывать файлы
     * @param directoryWhereMoveParsedFiles - строка указывающая куда перемещать файлы из которых информация была успешна записана в репозиторий
     * @return  - ResponseParseSaveEntity:
     * List содержащий в себе строки именований файлов которые были успешно записаны в репозиторий.
     */
    ResponseParseSaveEntity parseAndSaveToTwoRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles (String directoryWhereParse, String directoryWhereMoveParsedFiles);

}

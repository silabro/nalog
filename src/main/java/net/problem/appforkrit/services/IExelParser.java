package net.problem.appforkrit.services;

import net.problem.appforkrit.domain.entities.ResponseParseEntity;
import net.problem.appforkrit.domain.entities.ResponseParseSaveEntity;

/**
 * Итерфейс сервиса который осуществляет парсинг файлов формата .xls и .xlsx
 */
public interface IExelParser {

    /**
     * Считывает и возвращает данные из файла .xls или .xlsx
     *
     * @param fileDirectory - строка указывающая дерикторию файла
     * @param fileName      - строка указывающая наименование файла
     * @return - ParseResponseDTO:
     * success, true в случае успешного парсинга файла;
     * BookDTO, содержит в себе листы Exel файла, листы содержат строки, строки содержат ячейки.
     */
    ResponseParseEntity parseExelFileAndGetResponseParseEntity(String fileDirectory, String fileName);

    boolean moveFileToDirectory(String fileName, String fileDirectoryFrom, String fileDirectoryWhere);

    ResponseParseSaveEntity parseAndSaveToRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles (String directoryWhereParse, String directoryWhereMoveParsedFiles);

}

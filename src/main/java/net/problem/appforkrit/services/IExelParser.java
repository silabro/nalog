package net.problem.appforkrit.services;

import net.problem.appforkrit.dto.exeldto.ParseResponseDTO;

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
    ParseResponseDTO parseExelFileAndGetResponseDTO(String fileDirectory, String fileName);

}

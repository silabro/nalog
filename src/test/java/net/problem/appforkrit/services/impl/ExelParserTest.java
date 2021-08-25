package net.problem.appforkrit.services.impl;

import liquibase.pro.packaged.S;
import net.problem.appforkrit.dto.exeldto.BookDTO;
import net.problem.appforkrit.dto.exeldto.ParseResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( MockitoExtension.class )
class ExelParserTest {
    @InjectMocks
    private ExelParser exelParser;

    /**
     * Временный тест для проверки метода парсинга и работы библиотеки org.apache.poi (для работы с Microsoft документами)
     */
    @Test
    void tryParseAndCheckSuccess() {
        boolean expectedSuccess = true;
        ParseResponseDTO result;
        String fileDirectory = "C:\\work\\Nalog\\To_load\\";
        String fileName = "43_01.02.2021.xlsx";

        result = exelParser.parseExelFileAndGetResponseDTO(fileDirectory,fileName);

        assertAll(
                () -> assertEquals(expectedSuccess, result.isSuccess()),
                () -> assertNotNull(result.getBookDTO())
        );
    }
}
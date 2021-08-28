package net.problem.appforkrit;

import lombok.RequiredArgsConstructor;
import net.problem.appforkrit.services.IExelParser;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ApplicationStartup implements ApplicationListener<ApplicationReadyEvent> {

    @Qualifier("ExelParser")
    private final IExelParser exelParser;

    /**
     * Костыль для того что бы выполнить методы сервиса без контроллера
     * @param applicationReadyEvent
     */
    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        //директория откуда парсить файлы
        final String FILE_DIRECTORY_FROM = "C:\\work\\Nalog\\To_load\\";
        //директория куда перемещать успешно спарсеные файлы
        final String FILE_DIRECTORY_WHERE = "C:\\work\\Nalog\\Loaded\\";

        /**
         * Метод который аналогично парсит как и второй, но сохраняет данные в две таблицы.
         * При необходимости раскомментировать
         */
        //exelParser.parseAndSaveToTwoRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles(FILE_DIRECTORY_FROM, FILE_DIRECTORY_WHERE);

        exelParser.parseAndSaveToRepositoryAllExelFilesFromDirectoryAndMoveParsedFiles(FILE_DIRECTORY_FROM, FILE_DIRECTORY_WHERE);

    }
}

package net.problem.appforkrit.dto.exeldto;

import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class RowDTO {
    private LinkedHashMap<Short, String> cells;
}

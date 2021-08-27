package net.problem.appforkrit.domain.entities;

import lombok.Builder;
import lombok.Data;

import java.util.LinkedHashMap;

@Data
public class RowEntity {
    private LinkedHashMap<Short, String> cells;
}

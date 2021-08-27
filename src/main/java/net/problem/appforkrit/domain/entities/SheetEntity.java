package net.problem.appforkrit.domain.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class SheetEntity {
    private ArrayList<RowEntity> rows;
}

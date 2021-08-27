package net.problem.appforkrit.domain.entities;

import lombok.Data;

import java.util.ArrayList;

@Data
public class BookEntity {
    private ArrayList<SheetEntity> sheets;
}

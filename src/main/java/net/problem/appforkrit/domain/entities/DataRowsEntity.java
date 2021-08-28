package net.problem.appforkrit.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "data_rows")
@Getter
@Setter
public class DataRowsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long regionAndDateId;
    private int numberRow;
    private String CELLa;
    private String CELLb;
    private String CELLv;
    private String CELL1;
    private String CELL2;
    private String CELL3;
    private String CELL4;
    private String CELL5;
    private String CELL6;
    private String CELL7;
    private String CELL8;
    private String CELL9;
    private String CELL10;
    private String CELL11;
    private String CELL12;
    private String CELL13;
    private String CELL14;
    private String CELL15;
    private String CELL16;
    private String CELL17;
    private String CELL18;
    private String CELL19;
    private String CELL20;
    private String CELL21;
    private String CELL22;
    private String CELL23;
    private String CELL24;
    private String CELL25;
}

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
    private String cella;
    private String cellb;
    private String cellv;
    private String cell1;
    private String cell2;
    private String cell3;
    private String cell4;
    private String cell5;
    private String cell6;
    private String cell7;
    private String cell8;
    private String cell9;
    private String cell10;
    private String cell11;
    private String cell12;
    private String cell13;
    private String cell14;
    private String cell15;
    private String cell16;
    private String cell17;
    private String cell18;
    private String cell19;
    private String cell20;
    private String cell21;
    private String cell22;
    private String cell23;
    private String cell24;
    private String cell25;
}

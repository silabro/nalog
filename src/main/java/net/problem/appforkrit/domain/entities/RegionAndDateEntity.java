package net.problem.appforkrit.domain.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "region_and_date")
@Getter
@Setter
public class RegionAndDateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Short numberRegion;
    private Date date;
}

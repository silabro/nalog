package net.problem.appforkrit.domain.entities;

import lombok.Data;

@Data
public class ResponseParseEntity {
    private boolean success;
    private BookEntity book;
}

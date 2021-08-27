package net.problem.appforkrit.services.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@ToString
public enum ExelFileTypeEnum {

    XLS("xls"),
    XLSX("xlsx");

    private final String exelFileType;
}

package net.problem.appforkrit.mapping;

import net.problem.appforkrit.domain.entities.NalogEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.LinkedHashMap;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NalogMapper {
    NalogMapper MAPPER = Mappers.getMapper(NalogMapper.class);

    default NalogEntity mapWithCellsAndStringsTerAndDatToNalogEntity(LinkedHashMap<Short, String> cells, String ter, String dat){
        NalogEntity nalogEntity = new NalogEntity();

        nalogEntity.setFielda(ifEmptyThenZero(cells.get((short) 0)));
        nalogEntity.setFieldb(ifEmptyThenZero(cells.get((short) 1)));
        nalogEntity.setFieldv(ifEmptyThenZero(cells.get((short) 2)));
        nalogEntity.setField1(ifEmptyThenZero(cells.get((short) 3)));
        nalogEntity.setField2(ifEmptyThenZero(cells.get((short) 4)));
        nalogEntity.setField3(ifEmptyThenZero(cells.get((short) 5)));
        nalogEntity.setField4(ifEmptyThenZero(cells.get((short) 6)));
        nalogEntity.setField5(ifEmptyThenZero(cells.get((short) 7)));
        nalogEntity.setField6(ifEmptyThenZero(cells.get((short) 8)));
        nalogEntity.setField7(ifEmptyThenZero(cells.get((short) 9)));
        nalogEntity.setField8(ifEmptyThenZero(cells.get((short) 10)));
        nalogEntity.setField9(ifEmptyThenZero(cells.get((short) 11)));
        nalogEntity.setField10(ifEmptyThenZero(cells.get((short) 12)));
        nalogEntity.setField11(ifEmptyThenZero(cells.get((short) 13)));
        nalogEntity.setField12(ifEmptyThenZero(cells.get((short) 14)));
        nalogEntity.setField13(ifEmptyThenZero(cells.get((short) 15)));
        nalogEntity.setField14(ifEmptyThenZero(cells.get((short) 16)));
        nalogEntity.setField15(ifEmptyThenZero(cells.get((short) 17)));
        nalogEntity.setField16(ifEmptyThenZero(cells.get((short) 18)));
        nalogEntity.setField17(ifEmptyThenZero(cells.get((short) 19)));
        nalogEntity.setField18(ifEmptyThenZero(cells.get((short) 20)));
        nalogEntity.setField19(ifEmptyThenZero(cells.get((short) 21)));
        nalogEntity.setField20(ifEmptyThenZero(cells.get((short) 22)));
        nalogEntity.setField21(ifEmptyThenZero(cells.get((short) 23)));
        nalogEntity.setField22(ifEmptyThenZero(cells.get((short) 24)));
        nalogEntity.setField23(ifEmptyThenZero(cells.get((short) 25)));
        nalogEntity.setField24(ifEmptyThenZero(cells.get((short) 26)));
        nalogEntity.setField25(ifEmptyThenZero(cells.get((short) 27)));
        nalogEntity.setTer(ter);
        nalogEntity.setDat(dat);

        return nalogEntity;
    }

    default String ifEmptyThenZero(String field){
        return field.isEmpty() ? "0" : field;
    }

}

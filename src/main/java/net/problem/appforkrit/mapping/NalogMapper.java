package net.problem.appforkrit.mapping;

import net.problem.appforkrit.domain.entities.DataRowsEntity;
import net.problem.appforkrit.domain.entities.NalogEntity;
import net.problem.appforkrit.domain.entities.RegionAndDateEntity;
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

    default DataRowsEntity mapWithCellsAndRegionAndDateIdAndNumberRowToDataRowsEntity(LinkedHashMap<Short, String> cells, Long regionAndDateId,int numberRow){
        DataRowsEntity dataRowsEntity = new DataRowsEntity();

        dataRowsEntity.setNumberRow(numberRow);
        dataRowsEntity.setRegionAndDateId(regionAndDateId);
        dataRowsEntity.setCella(ifEmptyThenZero(cells.get((short) 0)));
        dataRowsEntity.setCellb(ifEmptyThenZero(cells.get((short) 1)));
        dataRowsEntity.setCellv(ifEmptyThenZero(cells.get((short) 2)));
        dataRowsEntity.setCell1(ifEmptyThenZero(cells.get((short) 3)));
        dataRowsEntity.setCell2(ifEmptyThenZero(cells.get((short) 4)));
        dataRowsEntity.setCell3(ifEmptyThenZero(cells.get((short) 5)));
        dataRowsEntity.setCell4(ifEmptyThenZero(cells.get((short) 6)));
        dataRowsEntity.setCell5(ifEmptyThenZero(cells.get((short) 7)));
        dataRowsEntity.setCell6(ifEmptyThenZero(cells.get((short) 8)));
        dataRowsEntity.setCell7(ifEmptyThenZero(cells.get((short) 9)));
        dataRowsEntity.setCell8(ifEmptyThenZero(cells.get((short) 10)));
        dataRowsEntity.setCell9(ifEmptyThenZero(cells.get((short) 11)));
        dataRowsEntity.setCell10(ifEmptyThenZero(cells.get((short) 12)));
        dataRowsEntity.setCell11(ifEmptyThenZero(cells.get((short) 13)));
        dataRowsEntity.setCell12(ifEmptyThenZero(cells.get((short) 14)));
        dataRowsEntity.setCell13(ifEmptyThenZero(cells.get((short) 15)));
        dataRowsEntity.setCell14(ifEmptyThenZero(cells.get((short) 16)));
        dataRowsEntity.setCell15(ifEmptyThenZero(cells.get((short) 17)));
        dataRowsEntity.setCell16(ifEmptyThenZero(cells.get((short) 18)));
        dataRowsEntity.setCell17(ifEmptyThenZero(cells.get((short) 19)));
        dataRowsEntity.setCell18(ifEmptyThenZero(cells.get((short) 20)));
        dataRowsEntity.setCell19(ifEmptyThenZero(cells.get((short) 21)));
        dataRowsEntity.setCell20(ifEmptyThenZero(cells.get((short) 22)));
        dataRowsEntity.setCell21(ifEmptyThenZero(cells.get((short) 23)));
        dataRowsEntity.setCell22(ifEmptyThenZero(cells.get((short) 24)));
        dataRowsEntity.setCell23(ifEmptyThenZero(cells.get((short) 25)));
        dataRowsEntity.setCell24(ifEmptyThenZero(cells.get((short) 26)));
        dataRowsEntity.setCell25(ifEmptyThenZero(cells.get((short) 27)));

        return dataRowsEntity;
    }

    default String ifEmptyThenZero(String field){
        String zero = "0";

        if(field == null){
            return zero;
        }

        return field.isEmpty() ? zero : field;
    }

}

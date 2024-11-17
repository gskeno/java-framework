package com.gson.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper(uses = ConvertUtils.class)
public interface PersonMapper {

    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    @Mapping( target = "status",  source = "status", qualifiedByName = { "ConvertUtils", "convert2StatusList"})
    PersonDTO toDto(Person person);


    @Named("convert2StatusList")
    default List<Integer> convert2StatusList(Integer status){
        List<Integer> statusList = new ArrayList<>();
        if (status == 1){
            statusList.add(-1);
            statusList.add(-2);
        }else if (status == 2){
            statusList.add(-3);
            statusList.add(-4);
        }else if (status == 3){
            statusList.add(-5);
            statusList.add(-6);
        }
        return statusList;
    }

    @Named("convert2Status")
    default Integer convert2Status(List<Integer> status){
        if (status == null || status.isEmpty()){
            return null;
        }
        if (status.contains(1) || status.contains(2)){
            return 1;
        }
        if (status.contains(3) || status.contains(4)){
            return 2;
        }
        if (status.contains(5) || status.contains(6)){
            return 3;
        }
        return null;
    }

}

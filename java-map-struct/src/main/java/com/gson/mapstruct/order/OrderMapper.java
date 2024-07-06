package com.gson.mapstruct.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper
public interface OrderMapper {
    @Mapping(source = "externalOrderType", target = "orderType")
    OrderDTO toDTO(Order order);

    @ValueMappings({
            @ValueMapping(target = "SPECIAL", source = "EXTRA"),
            @ValueMapping(target = "DEFAULT", source = "STANDARD"),
            @ValueMapping(target = "DEFAULT", source = "NORMAL")
    })
    OrderType toOrderType(ExternalOrderType externalOrderType);
}

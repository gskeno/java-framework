package com.gson.mapstruct.order;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;

@Mapper
public interface OrderMapper1 {
    @Mapping(source = "externalOrderType", target = "orderTypeStr")
    OrderDTO toDTO(Order order);
}

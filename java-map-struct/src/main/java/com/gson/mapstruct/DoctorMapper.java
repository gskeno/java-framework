package com.gson.mapstruct;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.control.MappingControl;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {

    DoctorMapper INSTANCE = Mappers.getMapper(DoctorMapper.class);
    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "timeStamp", ignore = true)
    DoctorDto toDto(Doctor doctor);

    @Mapping(source = "startTime", target = "startTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(source = "endTime", target = "endTime", dateFormat = "yyyy-MM-dd HH:mm:ss")
    @Mapping(target = "timeStamp", ignore = true)
    Doctor toDoc(DoctorDto doctorDto);
}

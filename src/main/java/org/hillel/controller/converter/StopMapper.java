package org.hillel.controller.converter;

import org.hillel.controller.dto.StopDto;
import org.hillel.persistence.entity.StopEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface StopMapper {

    @Mappings(value = {@Mapping(source = "name", target = "stopName"),
    @Mapping(source = "city", target = "cityName")})
    StopDto stopDtoToStop(StopEntity stopEntity);

    @Mappings(value = {@Mapping(source = "stopName", target = "name"),
            @Mapping(source = "cityName", target = "city")})
    StopEntity stopEntityToStopDto(StopDto stopDto);
}

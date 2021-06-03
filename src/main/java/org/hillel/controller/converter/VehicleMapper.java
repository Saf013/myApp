package org.hillel.controller.converter;

import org.hillel.controller.dto.VehicleDto;
import org.hillel.persistence.entity.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper
public interface VehicleMapper {

    @Mapping(source = "nameVehicle", target = "name")
    VehicleDto vehicleToVehicleDto(VehicleEntity vehicleEntity);

    @Mapping(source = "name", target = "nameVehicle")
    VehicleEntity vehicleDtoToVehicle(VehicleDto vehicleDto);

    @Mappings(value = {@Mapping(source = "nameVehicle", target = "name"),
            @Mapping(source = "numberOfSeats", target = "numberSeats"),
    })
    VehicleDto vehicleToSortVehicleDto(VehicleEntity vehicleEntity);

    @Mappings(value = {@Mapping(source = "name", target = "nameVehicle"),
            @Mapping(source = "numberSeats", target = "numberOfSeats"),
    })
    VehicleEntity vehicleDtoToSortVehicle(VehicleDto vehicleDto);
}

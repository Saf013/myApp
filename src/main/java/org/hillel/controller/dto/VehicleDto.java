package org.hillel.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class VehicleDto {

    private Long id;
    private String name;
    private int numberSeats;
    private int page;
    private int pageSize;
    private String exp;
    private Sort.Direction direction;
}

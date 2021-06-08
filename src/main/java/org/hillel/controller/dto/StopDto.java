package org.hillel.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

@Getter
@Setter
public class StopDto {
    private long id;
    private String stopName;
    private String cityName;
    private int page;
    private int pageSize;
    private String exp;
    private Sort.Direction direction;
}

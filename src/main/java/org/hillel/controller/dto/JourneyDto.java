package org.hillel.controller.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.time.Instant;

@Getter
@Setter
public class JourneyDto {

    private long id;
    private String stationFrom;
    private String stationTo;
    private Instant departure;
    private Instant arrival;
    private String route;
    private int page;
    private int pageSize;
    private String exp;
    private Sort.Direction direction;
}

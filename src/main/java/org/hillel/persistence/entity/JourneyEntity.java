package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hillel.persistence.entity.enm.Direction;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "journey")
@Getter
@Setter
@NoArgsConstructor

@NamedQueries(value = {
        @NamedQuery(name = "findAllAsNamedQuery", query = "from JourneyEntity")
})
public class JourneyEntity extends AbstractModifyEntity<Long>{

    @Column(name = "station_from", length = 80, nullable = false)
    private String stationFrom;

    @Column(name = "station_to", length = 80, nullable = false)
    private String stationTo;

    @Column(name = "departure")
    private Instant departure;

    @Column(name = "arrival")
    private Instant arrival;

    @Column(name = "route", length = 80, nullable = false)
    private String route;

    @Column(name = "direction", length = 20)
    @Enumerated(EnumType.STRING)
    private Direction direction = Direction.TO;

    @ManyToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicle;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "journey_stop", indexes = @Index(name = "journey_stop_idx", columnList = "journey_id, stop_id"),
            joinColumns = @JoinColumn(name = "journey_id"),
            inverseJoinColumns = @JoinColumn(name = "stop_id"))
    private List<StopEntity> stops = new ArrayList<>();

    public void addVehicle(final VehicleEntity vehicle) {
        this.vehicle = vehicle;
    }

    public void addStop(final StopEntity stop){
        if (stop == null) return;
        if (stops == null) stops = new ArrayList<>();
        stops.add(stop);
    }

    @Override
    public String toString() {
        return "JourneyEntity{" +
                "id=" + getId() +
                ", stationFrom='" + stationFrom + '\'' +
                ", stationTo='" + stationTo + '\'' +
                ", departure=" + departure +
                ", arrival=" + arrival +
                ", route='" + route + '\'' +
                ", direction=" + direction +
                ", vehicle=" + vehicle +
                '}';
    }
}

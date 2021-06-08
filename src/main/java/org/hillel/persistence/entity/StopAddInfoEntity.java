package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "stop_info")
@Getter
@Setter
@NoArgsConstructor

@NamedQueries(value = {
        @NamedQuery(name = "findAllNamedQueryStopAdd", query = "from StopAddInfoEntity")
})
public class StopAddInfoEntity extends AbstractModifyEntity<Long> {

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "build_date", nullable = false)
    private Instant buildDate;

    @Column(name = "description", length = 2000)
    private String description;

    @OneToOne
    @MapsId
    @JoinColumn(name = "stop_id")
    private StopEntity stop;

    @Override
    public String toString() {
        return "StopAddInfoEntity{" +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", buildDate=" + buildDate +
                ", description='" + description + '\'' +
                '}';
    }
}

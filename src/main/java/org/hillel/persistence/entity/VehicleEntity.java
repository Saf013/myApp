package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor

@NamedQueries(value = {
        @NamedQuery(name = "findAllAsNamedQueryVehicle", query = "from VehicleEntity")
})
public class VehicleEntity extends AbstractModifyEntity<Long>{

    @Column(name = "name", length = 100)
    private String nameVehicle;

    @Column(name = "number_of_seats")
    private int numberOfSeats;

    @OneToMany(mappedBy = "vehicle")
    private List<JourneyEntity> journeys = new ArrayList<>();

    @OneToMany(mappedBy = "vehicleEntity", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private List<FreePlacesEntity> freePlaces = new ArrayList<>();

    public void addJourney(final JourneyEntity journeyEntity) {
        if (journeys == null) {
            journeys = new ArrayList<>();
        }
        journeys.add(journeyEntity);
        journeyEntity.addVehicle(this);
    }

    public void addFreePlaces(final FreePlacesEntity places) {
        if (freePlaces == null) {
            freePlaces = new ArrayList<>();
            return;
        }
        freePlaces.add(places);
        places.setVehicleEntity(this);
    }

    public void removeAllJourneys(){
        if (CollectionUtils.isEmpty(journeys)) {
            return;

        } else {
            journeys.forEach(item->item.setVehicle(null));
        }
    }

    @Override
    public String toString() {
        return "VehicleEntity{" +
                "id=" + getId() +
                ", nameVehicle='" + nameVehicle + '\'' +
                ", numberOfSeats=" + numberOfSeats +
                ", createDate=" + getCreateDate() +
                '}';
    }
}

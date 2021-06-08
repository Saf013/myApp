package org.hillel.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.*;

@Entity
@Table(name = "free_places")
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate

@NamedQueries(value = {
        @NamedQuery(name = "findFreePlaces", query = "from FreePlacesEntity")
})
public class FreePlacesEntity extends AbstractModifyEntity<Long> {

    @Column(name = "vehicel_name")
    private String vehicleName;

    @Column(name = "free_places")
    private int freePlaces;

    @ManyToOne
    @JoinColumn(name = "vehicle_id")
    private VehicleEntity vehicleEntity;

    @Override
    public String toString() {
        return "FreePlacesEntity{" +
                "freePlaces=" + freePlaces +
                '}';
    }
}

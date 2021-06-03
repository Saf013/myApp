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
@Table(name = "stop")
@Getter
@Setter
@NoArgsConstructor

@NamedQueries(value = {
        @NamedQuery(name = "findAllNamedQueryStops", query = "from StopEntity ")
})
public class StopEntity extends AbstractModifyEntity<Long>{

    @Column(name = "name", length = 80)
    private String name;

    @Column(name = "city")
    private String city;

    @OneToOne(mappedBy = "stop", cascade = {CascadeType.PERSIST}, orphanRemoval = true)
    private StopAddInfoEntity stopAddInfo;

    @ManyToMany(mappedBy = "stops")
    private List<JourneyEntity> journeys = new ArrayList<>();

    public void addAddInfo(StopAddInfoEntity stopAddInfo){
        if (stopAddInfo == null){
            this.stopAddInfo = null;
            return;
        }
        stopAddInfo.setStop(this);
        this.setStopAddInfo(stopAddInfo);
    }

    public void addJourney(JourneyEntity journey){
        if (journey == null) return;
        if (journeys == null) journeys = new ArrayList<>();
        journeys.add(journey);
    }

    public void removeJourney(){
        if (CollectionUtils.isEmpty(journeys)) {
            return;

        } else {
            journeys.forEach(item->item.setStops(null));
        }
    }

    @Override
    public String toString() {
        return "StopEntity{" +
                "id=" + getId() +
                ", name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", createDate= " + getCreateDate() +
                ", addInfo=" + stopAddInfo +
                '}';
    }
}

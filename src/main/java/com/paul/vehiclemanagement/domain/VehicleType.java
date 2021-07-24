package com.paul.vehiclemanagement.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "vehicle_type",uniqueConstraints = {
        @UniqueConstraint(columnNames = "name")
})
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VehicleType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vehicleTypeId;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(
            name = "vehicle_pars_to_type",
            joinColumns = @JoinColumn(name = "vehicleTypeId"),
            inverseJoinColumns = @JoinColumn(name = "vehiclePartId"))
    private List<VehiclePart> vehicleParts;
}
